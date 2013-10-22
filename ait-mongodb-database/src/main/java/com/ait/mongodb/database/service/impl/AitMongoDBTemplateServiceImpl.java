package com.ait.mongodb.database.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Order;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Sort;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.ait.mongodb.database.query.PageBean;
import com.ait.mongodb.database.query.QueryBean;
import com.ait.mongodb.database.query.UpdateBean;
import com.ait.mongodb.database.service.AitMongoDBTemplateService;

@Service
public class AitMongoDBTemplateServiceImpl implements AitMongoDBTemplateService {

	public static final Logger logger = LoggerFactory.getLogger(AitMongoDBTemplateServiceImpl.class);

	@Autowired(required = true)
	private MongoOperations mog;

	/**
	 * 保存对象
	 */
	@Override
	public Object persist(Object obj) {
		try {
			mog.save(obj);
		} catch (Exception e) {
			logger.error("error message {}. error exception {}.",e.getMessage(), e);
			logger.error("error StackTrace ", e);
		}
		return obj;
	}

	@Override
	public boolean delete(Object obj) {
		try {
			mog.remove(obj);
		} catch (Exception e) {
			logger.error("error message {}. error exception {}.",e.getMessage(), e);
			logger.error("error StackTrace ", e);
			return false;
		}
		return true;
	}

	/**
	 * 更具对象的id跟新对象
	 */
	@Override
	public boolean update(UpdateBean bean, Class<?> clzss) {
		try {
			Criteria c = Criteria.where("id").is(bean.getId());
			for (String key : bean.getQuery().keySet()) {
			    c.and(key).is(bean.getQuery().get(key));
			}
			Query q = new Query(c);
			Update u = new Update();
			for (String key : bean.getData().keySet()) {
				u.set(key, bean.getData().get(key));
			}
			mog.updateMulti(q, u, clzss);
		} catch (Exception e) {
			logger.error("error message {}. error exception {}.",e.getMessage(), e);
			logger.error("error StackTrace ", e);
			return false;
		}
		return true;
	}
	
	/**
	 *  属性加减
	 */
	@Override
	public boolean incUpdate(UpdateBean bean, Class<?> clzss) {
		try {
			Criteria c = Criteria.where("id").is(bean.getId());
			for (String key : bean.getQuery().keySet()) {
				c.and(key).is(bean.getQuery().get(key));
			}
			Query q = new Query(c);
			Update u = new Update();
			for (String key : bean.getData().keySet()) {
				u.inc(key, Integer.parseInt( bean.getData().get(key).toString()));
			}
			mog.updateMulti(q, u, clzss);
		} catch (Exception e) {
			logger.error("error message {}. error exception {}.",e.getMessage(), e);
			logger.error("error StackTrace ", e);
			return false;
		}	
		return true;
	}

	/**
	 * 查询一个
	 */
	@Override
	public <T> Object findOne(QueryBean bean, Class<T> clzss) {
		Object obj = null;
		try {
			Criteria c = Criteria.where("id").is(bean.getId());
			for (String key : bean.getQuery().keySet()) {
				c.and(key).is(bean.getQuery().get(key));
			}
			Query q = new Query(c);
			obj = mog.findOne(q, clzss);
		} catch (Exception e) {
			logger.error("error message {}. error exception {}.",e.getMessage(), e);
			logger.error("error StackTrace ", e);
			return null;
		}	
		return obj;
	}

	/**
	 * 查询多条记录 false 从小到大序   | true 从大到小
	 */
	@Override
	public <T> List<T> find(QueryBean bean, PageBean page, Class<T> clzss) {
		Criteria c = null;
		List<T> list = null;
		try{
			if(bean.getId() != null){
			   c = Criteria.where("id").is(bean.getId());
			   for (String key : bean.getQuery().keySet()) {
				 c.and(key).is(bean.getQuery().get(key));
			   }
			}else{
				boolean isFrist = true;
				for (String key : bean.getQuery().keySet()) {
					if(isFrist){
						c = Criteria.where(key).is(bean.getQuery().get(key));
						isFrist = false;
					}else{
						c.and(key).is(bean.getQuery().get(key));
					}
				}
			}
			Query q = new Query(c);
			Sort sort = q.sort();
			for(String key:bean.getOrder().keySet()){
				sort.on(key, bean.getOrder().get(key) == true ? Order.DESCENDING : Order.ASCENDING);
			}
			if( page != null ){
				q.limit(page.getPageSize());
				q.skip(page.getPageSize() *(page.getPage() - 1));
			}
			list = mog.find(q,clzss);
		} catch(Exception e){
			logger.error("error message {}. error exception {}.",e.getMessage(), e);
			logger.error("error StackTrace ", e);
			return null;
		}
		return list;
	}

	/**
	 * 模糊查询
	 */
	@Override
	public <T> List<T> findRegex(QueryBean bean, PageBean page, Class<T> clzss) {
		Criteria c = null;
		boolean isFrist = true;
		List<T> list = null;
		try{
			for (String key : bean.getQuery().keySet()) {
				if(isFrist){
					c = Criteria.where(key).regex(".*?" + bean.getQuery().get(key) +".*");
					isFrist = false;
				}else{
					c.and(key).regex(".*?" + bean.getQuery().get(key) +".*");
				}
			}
			Query q = new Query(c);
			Sort sort = q.sort();
			for(String key:bean.getOrder().keySet()){
				sort.on(key, bean.getOrder().get(key) == true ? Order.DESCENDING : Order.ASCENDING);
			}
			if( page != null ){
				q.limit(page.getPageSize());
				q.skip(page.getPageSize() *(page.getPage() - 1));
			}
			list = mog.find(q,clzss);
		} catch(Exception e){
			logger.error("error message {}. error exception {}.",e.getMessage(), e);
			logger.error("error StackTrace ", e);
			return null;
		}
		return list;
	}
   	
}
