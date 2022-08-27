package com.springmvc.annotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.commons.net.util.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class UserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private EmailService emailService;
	@Autowired
	private AlertService alertService;

	@Transactional(propagation = Propagation.REQUIRED)
	public boolean saveUser(Register register) {
		if (userRepository.saveUser(register)) {
			//emailService.sendEmail(register);
			alertService.sendUserAlert(register);
			return true;
		}
		return false;
	}

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean loadUserInfo(Login login) {
		Login validLogin = userRepository.loadUserInfo(login.getUserName());
		if (Objects.nonNull(validLogin))
			return Boolean.TRUE;
		else
			return Boolean.FALSE;

	}

	public List<Register> loadRegisterInfo() {
		 List<Register> registerInfo = userRepository.loadRegisterInfo();
		 List<Register> registerList=new ArrayList<>();
		 for(Register register: registerInfo) {
			 register.setBase64(Base64.encodeBase64String(register.getImage()));
			 registerList.add(register);
		 }
		 /*Register register=alertService.getRegisterAlert();
		 System.out.println(register.getId());
		 System.out.println(register.getFirstName());
		 System.out.println(register.getLastName());*/
		 return registerList;
	}
}
