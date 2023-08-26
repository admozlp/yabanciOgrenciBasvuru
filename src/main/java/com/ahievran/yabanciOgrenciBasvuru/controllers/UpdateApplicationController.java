package com.ahievran.yabanciOgrenciBasvuru.controllers;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ahievran.yabanciOgrenciBasvuru.business.abstracts.KisiBasvuruService;
import com.ahievran.yabanciOgrenciBasvuru.business.abstracts.UpdateKisiBasvuruService;
import com.ahievran.yabanciOgrenciBasvuru.business.requests.UpdateApplicationRequest;
import com.ahievran.yabanciOgrenciBasvuru.dataAccess.FakulteRepository;
import com.ahievran.yabanciOgrenciBasvuru.dataAccess.ProgramRepository;
import com.ahievran.yabanciOgrenciBasvuru.entities.Fakulte;
import com.ahievran.yabanciOgrenciBasvuru.entities.KisiBasvuru;
import com.ahievran.yabanciOgrenciBasvuru.entities.KisiKayit;
import com.ahievran.yabanciOgrenciBasvuru.entities.Program;
import com.ahievran.yabanciOgrenciBasvuru.entities.Sehir;
import com.ahievran.yabanciOgrenciBasvuru.entities.Ulke;

@Controller
public class UpdateApplicationController {
	@Autowired private KisiBasvuruService kisiBasvuruService;
	@Autowired private UpdateKisiBasvuruService updateBasvuruService; 
	@Autowired private FakulteRepository fakulteRepository;
	@Autowired private ProgramRepository programRepository;
	
	@Value("${app.upload.dir:${user.home}}")
	public String IMAGES_FILE_PATH;
	
	@GetMapping("/updateapp/{id}")
	public String showUpdateApp(@PathVariable int id, Model model, Authentication authentication) throws ParseException {
		boolean ogrenci = 	authentication.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("ogrenci"));
		model.addAttribute("ogrenci", ogrenci);	
		
        KisiBasvuru kisiBasvuru =   kisiBasvuruService.findKisiBasvuruById(id);
        if(kisiBasvuru == null) {
        	return "redirect:/?nouser";
        }
        KisiKayit kisiKayit = kisiBasvuru.getKisiKayit();                
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        if(!kisiKayit.getEmail().equals(userDetails.getUsername())) {        	
        	return "redirect:/?accessdenied";
        }        


        if(updateBasvuruService.getBasvuruById(id) != null) {
        	UpdateApplicationRequest updateApplicationRequest = updateBasvuruService.getBasvuruById(id);
        	List<String> tercihler = updateApplicationRequest.getKisiProgram().stream().map(kisiProgram -> kisiProgram.getProgram().getIngilizceAd().toString()).collect(Collectors.toList());
        	model.addAttribute("tercihler", tercihler);
        	model.addAttribute("basvuru", updateApplicationRequest);
        	
    		List<Ulke> ulkeler = kisiBasvuruService.getAllCountries();
    		List<Fakulte> fakulteler = fakulteRepository.findAll();
    		List<Program> programlar = programRepository.findAll();
    		
    		List<Sehir> yasadigiSehirler = updateBasvuruService.getSehirlerByUlkeId(updateApplicationRequest.getYasadigiUllke().getId());
        	List<Sehir> liseSehirler = updateBasvuruService.getSehirlerByUlkeId(updateApplicationRequest.getLiseninUlkesi().getId());
        	
        	
    		model.addAttribute("ulkeler", ulkeler);
            model.addAttribute("fakulteler", fakulteler);
            model.addAttribute("programlar", programlar);
            model.addAttribute("yasadigiSehirler", yasadigiSehirler);
            model.addAttribute("liseSehirler", liseSehirler);
        }else {
        	   model.addAttribute("basvuru", null);
        }       
		return "updateapp";
	}
	
	
	@ModelAttribute("basvuru")
	public UpdateApplicationRequest updateApplicationRequest() {
		return new UpdateApplicationRequest();
	}
	
	@PostMapping("/updateapp")
	public String updateApplication(@ModelAttribute("basvuru")  UpdateApplicationRequest upRequest,
			@RequestParam(name = "secim1", required = false) Integer secim1,@RequestParam(name = "secim2", required = false) Integer secim2,			
			@RequestParam(name = "secim3", required = false) Integer secim3,@RequestParam(value = "secim4", required = false) Integer secim4,			
			@RequestParam(value = "secim5", required = false) Integer secim5,@RequestParam(value = "secim6", required = false) Integer secim6,	
			@RequestParam(value = "secim7", required = false) Integer secim7,@RequestParam(value = "secim8", required = false) Integer secim8,
			@RequestParam(value = "secim9", required = false) Integer secim9,@RequestParam(value = "secim10", required = false) Integer secim10			
			) {		
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
        upRequest.setSecimler(tercihler);
		
		updateBasvuruService.updateApp(upRequest);
		return "redirect:/";
	}
	
}
