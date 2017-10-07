package com.arms.app.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.arms.domain.component.PageWrapper;
import com.arms.domain.entity.User;
import com.arms.domain.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService service;
	
	@RequestMapping("/users")
	public String users(Model model, @PageableDefault(value = 3) Pageable pageable){
		Page<User> pageUserList = service.getAllUsers(pageable);
		PageWrapper<User> page = new PageWrapper<>(pageUserList, "/users");
		model.addAttribute("page", page);
		model.addAttribute("users", page.getContent());
		//model.addAttribute("url", "/users");
		return "user/list";
	}
}
