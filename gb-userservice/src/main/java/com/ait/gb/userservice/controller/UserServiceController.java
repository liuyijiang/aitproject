package com.ait.gb.userservice.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
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
import com.ait.gb.userservice.vo.UserVO;


@Controller
public class UserServiceController {
   
	public static final Logger logger = LoggerFactory.getLogger(UserServiceController.class); 

	@Autowired
	private UserService userService;
	
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
	@RequestMapping(value = "/userRegist", method = RequestMethod.POST)
	@ResponseBody
	public LoginResponse userRegist(@RequestBody RegistRequest registRequest){
		LoginResponse loginResponse = new LoginResponse();
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
	
	@RequestMapping(value = "/createChild", method = RequestMethod.POST)
	@ResponseBody
	public CreateChildReponse createChild(CreateChildRequest createChildRequest){
		CreateChildReponse createChildReponse = new CreateChildReponse();
		try{
			if(valiateCreateChildRequest(createChildRequest)){
				ChildEntity entity = new ChildEntity();
				entity.setAddress(createChildRequest.getAddress());
//				entity.setAge(age);
//				entity.setCreateTime(createTime);
				//entity.set
			}
		} catch(Exception e) {
			logger.error("error message {}. error exception {}.",e.getMessage(), e);
			logger.error("error StackTrace ", e);
		}
		return createChildReponse;
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

	