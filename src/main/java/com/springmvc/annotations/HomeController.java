package com.springmvc.annotations;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.net.util.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
	@Autowired
	private UserService userService;

	@RequestMapping("/")
	public ModelAndView homePage() {
		List<Register> registerList=userService.loadRegisterInfo();
		System.out.println("homePage method is calling...");
		ModelAndView view=new ModelAndView("home");
		if(!registerList.isEmpty()) {
			view.addObject("registerList", List.of(registerList));
		}else
			view.addObject("registerList", List.of(new Register()));
		
		return view;
	}

	@RequestMapping("/login")
	public ModelAndView loadLoginPage() {
		return new ModelAndView("login").addObject("login", new Login());
	}

	@RequestMapping("/register")
	public String loadRegisterPage(Model model ) {
		model.addAttribute("register", new Register());
		return "register";
	}

	@RequestMapping("/loginProcess")
	public ModelAndView loginProcess(@Valid Login login, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return new ModelAndView("login")
					.addObject("error", "Login failed try one more time");
		}
		boolean isValidUser=userService.loadUserInfo(login);
		if (isValidUser)
			return new ModelAndView("home")
					.addObject("registerMessage", "Successfully-Login");
		else
			return new ModelAndView("login")
					.addObject("error", "Login failed try one more time");
		
	}

	@RequestMapping("/registerProcess")
	public ModelAndView registerProcess(@Valid Register register, 
			BindingResult bindingResult, @RequestParam("upload") MultipartFile file) {
		if(!file.isEmpty()) {
			try {
				register.setImage(file.getBytes());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(bindingResult.hasErrors()) {
			return new ModelAndView("register")
					.addObject("error", "Registration failed try one more time");
		}
		
		boolean saveUser = userService.saveUser(register);
		if (saveUser) {
			register.setBase64(Base64.encodeBase64String(register.getImage()));
			return new ModelAndView("registerProcess")
					.addObject("registers", register);
		}
		else
			return new ModelAndView("register")
					.addObject("error", "Registration failed try one more time");
	}
	@RequestMapping("/accessdenied")
	public ModelAndView accessDenied() {
		return new ModelAndView("accessdenied");
	}
	
	@RequestMapping("/logout")
	public ModelAndView logout() {
		return new ModelAndView("logout");
	}

}
