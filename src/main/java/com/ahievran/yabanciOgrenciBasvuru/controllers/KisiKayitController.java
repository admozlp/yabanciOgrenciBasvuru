package com.ahievran.yabanciOgrenciBasvuru.controllers;

import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.ahievran.yabanciOgrenciBasvuru.business.abstracts.KisiKayitService;
import com.ahievran.yabanciOgrenciBasvuru.business.requests.CreateKisiKayitRequest;
import com.ahievran.yabanciOgrenciBasvuru.entities.KisiKayit;

@Controller
public class KisiKayitController {    
	@Autowired
	private KisiKayitService kisiKayitService;
	
	@GetMapping("/registration")
	public String showRegistration(Model model) {        
		return "registration";
	}

	@ModelAttribute("kisiKayit")
	public CreateKisiKayitRequest createKisiKayitRequest() {
		return new CreateKisiKayitRequest();
	}
	
	@PostMapping("/kaydol")
	public String kisiKayitRegistration(@ModelAttribute("kisiKayit") CreateKisiKayitRequest createKisiKayitRequest, HttpServletRequest request) throws ParseException {	
			try {
				if(createKisiKayitRequest.getPassword().length() < 6) {
					return "redirect:registration?passwordsize";			
				}
				
				if(!createKisiKayitRequest.getPassword().equals(createKisiKayitRequest.getPasswordTekrar())) {
					return "redirect:registration?password";			
				}
				
				String gRecaptchaResponse = request.getParameter("g-recaptcha-response");			
				 if(!kisiKayitService.verifyReCaptcha(gRecaptchaResponse)) {
						return "redirect:registration?recaptcha";
				 }

				 KisiKayit kisiKayit = kisiKayitService.addKisiKayit(createKisiKayitRequest);
				 if(kisiKayit == null) {
						return "redirect:registration?mail";
				 }
				return "redirect:registration?success";			
			}catch (Exception e) {
				return "redirect:registration?error";
			}
	}
}
