package com.my.order.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.my.customer.vo.Customer;
import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.order.vo.OrderInfo;
import com.my.order.vo.OrderLine;
import com.my.product.vo.Product;
@Repository("oDAO")
public class OrderDAOOracle implements OrderDAOInterface {
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	
	@Autowired
	private DataSource ds;
	private Logger logger = Logger.getLogger(getClass());

	@Override
	public void add(OrderInfo info) throws AddException {
		SqlSession session = null;
		try {
		session= sqlSessionFactory.openSession();
		session.insert("com.my.order.OrderMapper.orderInfo", info.getOrderCustomer().getId());
		
		List<OrderLine> lines = info.getLines();
		for(OrderLine line : lines) {
			int quantity = line.getOrderQuantity();
			String prodNo = line.getOrderProduct().getProdNo();
//			Map<String,Object> map = new HashMap<>();
//			map.put("prodNo", prodNo);
//			map.put("quantity", quantity);
//			session.insert("com.my.order.OrderMapper.orderLine", map);
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
			logger.warn("list.size=" + list.size());
			if(list.size() > 0) {
				for(OrderInfo info: list) {
					logger.warn(info.getOrderNo());
					logger.warn(info.getOrderDt());
					logger.warn("info.getLines().size()=" + info.getLines().size());
					logger.warn("---------------");
				}
			}else if(list.size()==0) {
				throw new FindException("주문목록이 없습니다");
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
	public static void main(String[] args) {
		OrderDAOOracle dao = new OrderDAOOracle();
		OrderInfo info = new OrderInfo();
		String orderId = "id1";
		Customer c = new Customer(); c.setId(orderId);
		info.setOrderCustomer(c);

		List<OrderLine> lines = new ArrayList<>();
		OrderLine line = new OrderLine(); 
		Product p = new Product(); p.setProdNo("C0001");
		line.setOrderProduct(p);
		line.setOrderQuantity(1);
		lines.add(line);

		OrderLine line2 = new OrderLine(); 
		Product p2 = new Product(); p2.setProdNo("C0002");
		line2.setOrderProduct(p);
		line2.setOrderQuantity(1);
		lines.add(line2);

		info.setLines(lines);

		try {
			dao.add(info);
		} catch (AddException e) {
			e.printStackTrace();
		}
	}
}
