package com.ahievran.yabanciOgrenciBasvuru.business.requests;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class CreateKisiKayitRequest {
	@NotNull
	@NotBlank
	@Size(min=2, max=30)
	private String email;
	
	public CreateKisiKayitRequest() {
		super();
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getPasswordTekrar() {
		return passwordTekrar;
	}


	public void setPasswordTekrar(String passwordTekrar) {
		this.passwordTekrar = passwordTekrar;
	}


	@NotNull
	@NotBlank
	@Size(min=2, max=30)
	private String password;	
	
	
	public CreateKisiKayitRequest(@NotNull @NotBlank @Size(min = 2, max = 30) String email,
			@NotNull @NotBlank @Size(min = 2, max = 30) String password,
			@NotNull @NotBlank @Size(min = 2, max = 30) String passwordTekrar) {
		super();
		this.email = email;
		this.password = password;
		this.passwordTekrar = passwordTekrar;
	}


	@NotNull
	@NotBlank
	@Size(min=2, max=30)
	private String passwordTekrar;	
}