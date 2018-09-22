package cn.itheima.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.itcast.utils.Page;
import cn.itheima.pojo.BaseDict;
import cn.itheima.pojo.Customer;
import cn.itheima.pojo.QueryVo;
import cn.itheima.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	@Autowired
	private CustomerService customerService;
	
	@Value("${customer.dict.source}")
	private String source;
	@Value("${customer.dict.industry}")
	private String industry;
	@Value("${customer.dict.level}")
	private String level;
	
	@RequestMapping("/list")
	public String list(QueryVo vo, Model model) throws Exception {
		// 客户来源
		List<BaseDict> sourceList = customerService.findDictCode(source);
		
		// 所属行业
		List<BaseDict> industryList = customerService.findDictCode(industry);
		
		// 客户级别
		List<BaseDict> levelList = customerService.findDictCode(level);
		
		
		if (vo != null && vo.getCustName() != null) {
			vo.setCustName(new String(vo.getCustName().getBytes("iso8859-1"), "utf-8"));
		}
		
		if (vo != null && vo.getPage() != null) {
			vo.setPage(1);
		}
		
		vo.setStart((vo.getPage() - 1) * vo.getSize());
		// 查询数据列表和总数
		List<Customer> resultList = customerService.findCustomerByVo(vo);
		Integer count = customerService.findCustomerByVoCount(vo);
		
		// 分页数据 
		Page<Customer> page = new Page<Customer>();
		page.setTotal(count);
		page.setSize(vo.getSize());
		page.setPage(vo.getPage());
		page.setRows(resultList);
		
		model.addAttribute("page", page);
		
		// 高级查询下拉列表数据
		model.addAttribute("fromType", sourceList);
		model.addAttribute("industryType", industryList);
		model.addAttribute("levelType", levelList);
		
		// 高级查询回显数据
		model.addAttribute("custName", vo.getCustName());
		model.addAttribute("custSource", vo.getCustSource());
		model.addAttribute("custIndustry", vo.getCustIndustry());
		model.addAttribute("custLevel", vo.getCustLevel());
		
		return "customer";
	}
	

	@RequestMapping("/detail")
	@ResponseBody
	public Customer detail(Long id) throws Exception {
		return customerService.findCustomerById(id);
	}	
	
	@RequestMapping("/update")
	public String update(Customer customer) throws Exception {
		customerService.updateCustomerById(customer);
		return "customer";
	}
	
	@RequestMapping("delete")
	public String delete(Long id) throws Exception {
		customerService.deleteCustomerById(id);
		return "customer";
	}
	
}
