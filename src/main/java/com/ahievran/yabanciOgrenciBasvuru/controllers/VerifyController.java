package com.ahievran.yabanciOgrenciBasvuru.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ahievran.yabanciOgrenciBasvuru.business.abstracts.KisiKayitService;

@Controller
public class VerifyController {
	@Autowired
	private KisiKayitService kisiKayitService;

	//http://localhost:8080/verifyaccount?code=
	@GetMapping("/verifyaccount")
	public String verifyAccount(@RequestParam(value  = "code", required = false) String code, RedirectAttributes redirectAttributes) {		
		redirectAttributes.addAttribute("verify", false);
		if(code != null) {
			if(kisiKayitService.updateVerification(code) != null) {
				redirectAttributes.addAttribute("verify", true);
			}
		}
		return "redirect:/showerify";
	}
	
	@GetMapping("/showerify")
	public String showVerify() {
		return "verifyaccount";
	}
	
}
