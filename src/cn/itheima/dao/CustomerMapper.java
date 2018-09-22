package cn.itheima.dao;

import java.util.List;

import cn.itheima.pojo.Customer;
import cn.itheima.pojo.QueryVo;

public interface CustomerMapper {
	// 使用查询条件查询数据（分页）
	public List<Customer> findCustomerByVo(QueryVo vo);
	
	// 使用查询条件查询数据总数（分页）
	public Integer findCustomerByVoCount(QueryVo vo);
	
	// 查看详情
	public Customer findCustomerId(Long id);
	
	// 更新
	public void updateCustomerById(Customer customer);

	public void deleteCustomerById(Long id);
}
