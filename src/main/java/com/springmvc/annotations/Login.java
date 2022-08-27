package com.springmvc.annotations;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;



@Entity
@Table
public class Login implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 20154789654L;
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Long id;
	@NotEmpty
	@Column
	private String userName;
	@NotEmpty
	@Column
	private String password;
	@Column
	private boolean validFlag;
	@OneToMany(mappedBy="login")
	private Set<Role> roles=new HashSet<>();
	@OneToOne(mappedBy = "login")
	private Register register;
	
	
	public Register getRegister() {
		return register;
	}
	public void setRegister(Register register) {
		this.register = register;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isValidFlag() {
		return validFlag;
	}
	public void setValidFlag(boolean validFlag) {
		this.validFlag = validFlag;
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	

}
