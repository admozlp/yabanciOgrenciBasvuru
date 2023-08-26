package com.ahievran.yabanciOgrenciBasvuru.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ahievran.yabanciOgrenciBasvuru.business.abstracts.KisiBasvuruService;
import com.ahievran.yabanciOgrenciBasvuru.business.abstracts.KisiKayitService;
import com.ahievran.yabanciOgrenciBasvuru.business.requests.CreateBasvuruRequest;
import com.ahievran.yabanciOgrenciBasvuru.dataAccess.FakulteRepository;
import com.ahievran.yabanciOgrenciBasvuru.dataAccess.ProgramRepository;
import com.ahievran.yabanciOgrenciBasvuru.entities.Fakulte;
import com.ahievran.yabanciOgrenciBasvuru.entities.KisiKayit;
import com.ahievran.yabanciOgrenciBasvuru.entities.Program;
import com.ahievran.yabanciOgrenciBasvuru.entities.Ulke;

@Controller
public class ForgetPasswordController {
	@Autowired
	private KisiKayitService kayitService;

	@GetMapping("/forgetpassword")
	public String showForgetPasswor() {
		return "forgetpassword";
	}
	
	String degisken;
	@Autowired
	private KisiBasvuruService basvuruService;
	@Autowired
	private FakulteRepository fakulteRepository;
	@Autowired
	private ProgramRepository programRepository;
	

	
	@GetMapping("/addapplication")
	public String showeApplication(@RequestParam(value = "error", required = false)String error,  Model model, Authentication authentication, RedirectAttributes redirectAttributes) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		
		String email = userDetails.getUsername();
		if(basvuruService.checkApplication(email)) {
			redirectAttributes.addFlashAttribute("applicationavliable", true);
			return "redirect:/";
		}
		
		boolean ogrenci = 	authentication.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("ogrenci"));
		model.addAttribute("ogrenci", ogrenci);
		
		List<Ulke> ulkeler = basvuruService.getAllCountries();
		List<Fakulte> fakulteler = fakulteRepository.findAll();
		List<Program> programlar = programRepository.findAll();
		
		
		model.addAttribute("ulkeler", ulkeler);
        model.addAttribute("fakulteler", fakulteler);
        model.addAttribute("programlar", programlar);
        
        System.out.println(degisken);
		return "addapplication";
	}
	
	

    @ModelAttribute("createbasvuru")
    public CreateBasvuruRequest createBasvuruRequest() {
    	return new CreateBasvuruRequest();
    }
    
    
    @PostMapping("/completeapp")
	public String completeApplication(@ModelAttribute("createbasvuru") CreateBasvuruRequest createBasvuruRequest,
			@RequestParam(name = "secim1", required = false) Integer secim1,@RequestParam(name = "secim2", required = false) Integer secim2,			
			@RequestParam(name = "secim3", required = false) Integer secim3,@RequestParam(value = "secim4", required = false) Integer secim4,			
			@RequestParam(value = "secim5", required = false) Integer secim5,@RequestParam(value = "secim6", required = false) Integer secim6,	
			@RequestParam(value = "secim7", required = false) Integer secim7,@RequestParam(value = "secim8", required = false) Integer secim8,
			@RequestParam(value = "secim9", required = false) Integer secim9,@RequestParam(value = "secim10", required = false) Integer secim10,
			RedirectAttributes redirectAttributes
			) {
    	
    	try {        	        	
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String userName = userDetails.getUsername();
            createBasvuruRequest.setEmail(userName);
            
            List<Integer> tercihler = new ArrayList<>();
            if(secim1 != null)
            	tercihler.add(secim1);
            if(secim2 != null)
            	tercihler.add(secim2);
            if(secim3 != null)
            	tercihler.add(secim3);
            if(secim4 != null)
            	tercihler.add(secim4);
            if(secim5 != null)
            	tercihler.add(secim5);
            if(secim6 != null)
            	tercihler.add(secim6);
            if(secim7 != null)
            	tercihler.add(secim7);
            if(secim8 != null)
            	tercihler.add(secim8);
            if(secim9 != null)
            	tercihler.add(secim9);
            if(secim10 != null)
            	tercihler.add(secim10);
            createBasvuruRequest.setSecimler(tercihler);
                    	        	        
        	CreateBasvuruRequest createBasvuruRequest2=  basvuruService.add(createBasvuruRequest);
        	if(createBasvuruRequest2 == null) {
        		redirectAttributes.addFlashAttribute("error", true);
        	}else {
        		redirectAttributes.addFlashAttribute("success", true);
        	}    			        	
        	return "home";


    	}catch (Exception e) {
    		e.printStackTrace();
    		degisken = e.getLocalizedMessage();
    		redirectAttributes.addFlashAttribute("error", true);
        	return "redirect:/addapplication";
		}
		
	}
	
	@PostMapping("/sendmail")
	public String sendMail(@RequestParam("email") String email, RedirectAttributes redirectAttributes) {
		try {					
		KisiKayit kisiKayit = kayitService.findByEmail(email);
		if(kisiKayit == null)
			redirectAttributes.addFlashAttribute("error", true);
		
		KisiKayit mailAtildiMi =  kayitService.sendNewPassword(email);
		if(mailAtildiMi == null)
			redirectAttributes.addFlashAttribute("error", true);
		
		if(kisiKayit != null && mailAtildiMi != null)
			redirectAttributes.addFlashAttribute("success", true);

		}catch (Exception e) {				
			redirectAttributes.addFlashAttribute("error", true);
		}
		
		
		return "redirect:/forgetpassword";
	}
	
	
}
