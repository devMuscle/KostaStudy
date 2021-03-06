package com.my.order.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.my.customer.entity.Customer;
import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.order.entity.OrderInfo;
import com.my.order.entity.OrderLine;
import com.my.product.entity.Product;

public class OrderDAOOracle implements OrderDAOInterface {

	private SqlSessionFactory sqlSessionFactory;
	private Logger log = LoggerFactory.getLogger(getClass());

	@Override
	@Transactional(rollbackFor = AddException.class)
	public void add(OrderInfo info) throws AddException {
		SqlSession session = null;
		try {
		session= sqlSessionFactory.openSession();
//		session.insert("com.my.order.OrderMapper.orderInfo", info.getOrder_c().getId());
		
		List<OrderLine> lines = info.getLines();
		for(OrderLine line : lines) {
//			int quantity = line.getOrder_quantity();
//			String prodNo = line.getOrder_p().getProdNo();
			session.insert("com.my.order.OrderMapper.orderLine", line);
		}
		
		} catch(Exception e) {
			e.printStackTrace();
			throw new AddException(e.getMessage());
		} finally {
			if(session != null) {
				session.close();
			}
		}
	}

	@Override
	public List<OrderInfo> findById(String orderId) throws FindException {
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			List<OrderInfo> list = session.selectList("com.my.order.OrderMapper.findById", orderId);
			SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			log.warn("list.size=" + list.size());
			if(list.size() > 0) {
				for(OrderInfo info: list) {
//					log.warn(Integer.toString(info.getOrder_no()));
//					log.warn(fm.format(info.getOrder_dt()));
					log.warn("info.getLines().size()=" + info.getLines().size());
					log.warn("---------------");
				}
			}else if(list.size()==0) {
				throw new FindException("??????????????? ????????????");
			}
			return list;
		} catch(Exception e) {
			e.printStackTrace();
			throw new FindException(e.getMessage());
		} finally {
			if(session != null) {
				session.close();
			}
		}
	}

	@Override
	public List<OrderInfo> findByDate(Date dt) throws FindException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrderInfo> findByDate(Date startDt, Date endDate) throws FindException {
		// TODO Auto-generated method stub
		return null;
	}
}
