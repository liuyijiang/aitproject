package com.ait.gb.userservice.controller;

import java.io.File;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.ait.code.util.RandomUtil;
import com.ait.code.util.SecurityUtil;
import com.ait.code.util.StringUtil;
import com.ait.code.util.ValidateUtil;
import com.ait.gb.entity.ChildEntity;
import com.ait.gb.entity.UserEntity;
import com.ait.gb.userservice.request.CreateChildReponse;
import com.ait.gb.userservice.request.CreateChildRequest;
import com.ait.gb.userservice.request.LoginRequest;
import com.ait.gb.userservice.request.LoginResponse;
import com.ait.gb.userservice.request.RegistRequest;
import com.ait.gb.userservice.service.UserService;
import com.ait.gb.userservice.vo.ChildVO;
import com.ait.gb.userservice.vo.UserVO;
import com.ait.mongodb.database.constant.AitFileTypeConstant;
import com.ait.mongodb.database.service.AitGridFSTemplateService;
import com.alibaba.fastjson.JSON;


@Controller
public class UserServiceController {
   
	private static final String SAVE_PATH = UserServiceController.class.getResource("/").getPath();
	
	public static final Logger logger = LoggerFactory.getLogger(UserServiceController.class); 

	//@Value("${gb.user.header.image.max}")
	private double max;
	
	//@Value("${gb.user.header.image.min}")
	private double min;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AitGridFSTemplateService aitGridFSTemplateService;
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index(){
		logger.trace("======1111trace");  
		 logger.debug("======222debug");  
		 logger.info("======333info");  
		 logger.warn("======444warn");  
		 logger.error("======555error");
		// userService.testquery();
		return new ModelAndView("index.jsp");
	}
	
	@RequestMapping(value = "/checkUser", method = RequestMethod.POST)
	@ResponseBody
	public LoginResponse checkUser(@RequestBody LoginRequest loginRequest){
		LoginResponse loginResponse = new LoginResponse();
		try{
			if(valiateLoginRequest(loginRequest)){
				UserVO vo = userService.findUserFromLogin(loginRequest);
				if(vo != null){
					loginResponse.setUser(vo);
				}
			}
		} catch(Exception e) {
			logger.error("error message {}. error exception {}.",e.getMessage(), e);
			logger.error("error StackTrace ", e);
		}
		return loginResponse;
	}
	
	/**
	 * 用户注册接口
	 * @return
	 */
	@RequestMapping(value = "/userRegist", method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public LoginResponse userRegist(@RequestBody RegistRequest registRequest){
		LoginResponse loginResponse = new LoginResponse();
	   	loginResponse.setCode("200");
		try{
			if(valiateRegistRequest(registRequest)){
				UserEntity entity = new UserEntity();
		    	entity.setCreateTime(StringUtil.dateToString(new Date(), null));
		    	entity.setEmail(registRequest.getEmail());
		    	entity.setName(registRequest.getUsername());
		    	entity.setRandom(RandomUtil.getRandomNumber());
		    	entity.setPassword(SecurityUtil.digestByMd5(registRequest.getPassword()));
		    	loginResponse.setUser(userService.saveUserEntity(entity));
		 
			}else{
				
			}
		} catch(Exception e) {
			logger.error("error message {}. error exception {}.",e.getMessage(), e);
			logger.error("error StackTrace ", e);
		}
		return loginResponse;
	}
	
	/**
	 * 创建孩子
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/createChild", method = RequestMethod.POST)
	public void createChild(HttpServletRequest request,HttpServletResponse response){
		CreateChildReponse createChildReponse = new CreateChildReponse();
		try{
			MultipartHttpServletRequest multipartRequest  =  (MultipartHttpServletRequest) request;  
			CreateChildRequest createChildRequest = new CreateChildRequest();
			createChildRequest.setAddress(multipartRequest.getParameter("address"));
			createChildRequest.setInfo(multipartRequest.getParameter("info"));
			createChildRequest.setParentId(multipartRequest.getParameter("parentId"));
			createChildRequest.setName(multipartRequest.getParameter("name"));
	        MultipartFile imgFile =  multipartRequest.getFile("imgFile"); 
			if(valiateCreateChildRequest(createChildRequest)){
				ChildEntity entity = new ChildEntity();
				entity.setAddress(createChildRequest.getAddress());
				entity.setCreateTime(StringUtil.dateToString(new Date(), null));
				entity.setInfo(createChildRequest.getInfo());
				entity.setName(createChildRequest.getName());
				entity.setParentId(createChildRequest.getParentId());
				entity.setRandom(RandomUtil.getRandomNumber());
				ChildVO vo = userService.saveUserChildEntity(entity);
				if(vo != null && vo.getId() != null){
					if(imgFile != null){
						String path = SAVE_PATH + vo.getId() +AitFileTypeConstant.FILE_TYPE_IMAGE_PNG.getString();
						File file = new File(path);
						imgFile.transferTo(file); 
						String imageurl = aitGridFSTemplateService.uploadImage(file, vo.getId(),AitFileTypeConstant.FILE_TYPE_IMAGE_PNG, AitFileTypeConstant.FILE_IMAGE_SIZE_LX, 11);
						String minimageurl = aitGridFSTemplateService.uploadImage(file, vo.getId(),AitFileTypeConstant.FILE_TYPE_IMAGE_PNG, AitFileTypeConstant.FILE_IMAGE_SIZE_SM,11);
						vo.setImageMax(imageurl);
						vo.setImageSmall(minimageurl);
						if(!userService.updateUserImageOfChildEntity(vo)){
							vo.setImageMax(null);
							vo.setImageSmall(null);
					    }
					}
					createChildReponse.setVo(vo);
				}
			}
			response.setContentType("text/html");  
		    response.getWriter().println(JSON.toJSONString(createChildReponse)); 
		} catch(Exception e) {
			logger.error("error message {}. error exception {}.",e.getMessage(), e);
			logger.error("error StackTrace ", e);
		}
	}
	
	
	
	private boolean valiateCreateChildRequest(CreateChildRequest createChildRequest){
		if(createChildRequest == null){
			return false;
		}
		if(StringUtil.stringIsEmpty(createChildRequest.getParentId())){
			return false;
		}
		if(StringUtil.stringIsEmpty(createChildRequest.getName())){
			return false;
		}
		return true;
	}
	
	private boolean valiateLoginRequest(LoginRequest loginRequest){
		if (loginRequest == null){
			return false;
		}
		if (StringUtil.stringIsEmpty(loginRequest.getPassword())) {
			return false;
		}
		if (StringUtil.stringIsEmpty(loginRequest.getEmail())) {
			return false;
		}
		return true;
	}
	
	private boolean valiateRegistRequest(RegistRequest registRequest){
		if (registRequest == null ){
			return false;
		}
		if (StringUtil.stringIsEmpty(registRequest.getUsername())) {
			return false;
		}
		if (StringUtil.stringIsEmpty(registRequest.getPassword())) {
			return false;
		}
		if ( !ValidateUtil.isEmail(registRequest.getEmail())) {
			return false;
		}
		return true;
	}
	
}

	