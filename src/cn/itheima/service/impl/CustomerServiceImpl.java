package cn.itheima.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.itheima.dao.CustomerMapper;
import cn.itheima.dao.DictMapper;
import cn.itheima.pojo.BaseDict;
import cn.itheima.pojo.Customer;
import cn.itheima.pojo.QueryVo;
import cn.itheima.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private DictMapper dictMapper;
	
	@Autowired
	private CustomerMapper customerMapper;
	
	public List<BaseDict> findDictCode(String code) {
		// TODO Auto-generated method stub
		return dictMapper.findDictByCode(code);
	}
	public List<Customer> findCustomerByVo(QueryVo vo) {
		// TODO Auto-generated method stub
		return customerMapper.findCustomerByVo(vo);
	}
	public Integer findCustomerByVoCount(QueryVo vo) {
		// TODO Auto-generated method stub
		return customerMapper.findCustomerByVoCount(vo);
	}
	public Customer findCustomerById(Long id) {
		// TODO Auto-generated method stub
		return customerMapper.findCustomerId(id);
	}
	public void updateCustomerById(Customer customer) {
		// TODO Auto-generated method stub
		customerMapper.updateCustomerById(customer);
	}
	public void deleteCustomerById(Long id) {
		// TODO Auto-generated method stub
		customerMapper.deleteCustomerById(id);
	}
	
}
