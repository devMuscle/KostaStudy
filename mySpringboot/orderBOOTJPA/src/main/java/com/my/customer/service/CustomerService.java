package com.my.customer.service;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.customer.dao.CustomerDAOInterface;
import com.my.customer.entity.Customer;
import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.repository.CustomerRepository;

@Service
public class CustomerService {
	@Autowired
	private CustomerRepository repository;
	
	/**
	 * 아이디와 비번인 일치하는 고객이 존재하면 고객객체를 반환하고
	 * 고객이 존재하지 않으면 FindException이 발생한다
	 * @param id
	 * @param pwd
	 * @return
	 * @throws FindException
	 */
	public Customer login(String id, String pwd) throws FindException{
		try {
			Optional<Customer> c = repository.findById(id);
			if(c.get().getPwd().equals(pwd)) {
				return c.get();
			}
			throw new FindException();
		}catch(FindException e) {
			throw new FindException("로그인 실패");
		}
	}
	
	public void addupchk(String id) throws NoSuchElementException{
		Optional<Customer> c = repository.findById(id);
		c.get();
	}
	
	public void signup(Customer c) throws AddException{
		repository.save(c);
	}
}
