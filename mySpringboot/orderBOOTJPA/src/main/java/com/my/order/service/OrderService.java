package com.my.order.service;

import java.text.SimpleDateFormat;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.customer.entity.Customer;
import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.order.dao.OrderDAOInterface;
import com.my.order.dao.OrderDAOOracle;
import com.my.order.entity.OrderInfo;
import com.my.repository.OrderInfoRepository;


public class OrderService {
	private Logger log = LoggerFactory.getLogger(getClass());


	private OrderInfoRepository repository;

	public void add(OrderInfo info) throws AddException {
		repository.save(info);
	}

	public List<OrderInfo> findById(String orderId) throws Exception {
		Customer c = new Customer();
		c.setId(orderId);

		try {
			List<OrderInfo> list = null;
			SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			log.warn("list.size=" + list.size());

			if (list.size() > 0) {
				for (OrderInfo info : list) {
//					log.warn(Integer.toString(info.getOrder_no()));
//					log.warn(fm.format(info.getOrder_dt()));
					log.warn("info.getLines().size()=" + info.getLines().size());
					log.warn("---------------");
				}
			} else if (list.size() == 0) {
				throw new FindException("주문목록이 없습니다");
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}

		

	}
}
