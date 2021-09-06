package com.spring.mvc;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.mvc.model.Customer;
import com.spring.mvc.service.CustomerService;

@Controller
public class CustomerController {
	@Autowired
	private CustomerService customerService;

	@RequestMapping("/")
	public ModelAndView home() {
		List<Customer> allCustomers = customerService.getAllCustomers();
		ModelAndView mav = new ModelAndView("home");
		mav.addObject("listOfCustomers", allCustomers);
	
		return mav;
	}

	@RequestMapping("/newCustomer")
	public String newCustomer(Map<String, Object> model) {
		model.put("customer", new Customer());
		return "newCustomer";
	}

	@RequestMapping(method=RequestMethod.POST,value={"/saveCustomer","/edit/saveCustomer"})
	public String saveCustomer(@Valid @ModelAttribute("customer") Customer customer, BindingResult result) {
		if(result.hasErrors())
			return "newCustomer";
		customerService.saveCustomer(customer);
		return "redirect:/";
	}
	@RequestMapping("/edit")
	public ModelAndView editCustomer(@RequestParam Long id) {
		Customer customer = customerService.getCustomer(id);
		ModelAndView mav = new ModelAndView("editCustomer");
		mav.addObject("customer", customer);
		return mav;
	}
	@RequestMapping("/delete")
	public String deleteCustomer(@RequestParam Long id) {
		Customer customer = customerService.getCustomer(id);
		customerService.deleteCustomer(customer);
		
	 return "redirect:/";
	}

}
