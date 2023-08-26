package com.ahievran.yabanciOgrenciBasvuru.controllers;

import java.io.ByteArrayOutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.ahievran.yabanciOgrenciBasvuru.business.abstracts.KisiBasvuruService;
import com.ahievran.yabanciOgrenciBasvuru.dataAccess.KisiDosyaRepository;
import com.ahievran.yabanciOgrenciBasvuru.entities.KisiBasvuru;
import com.ahievran.yabanciOgrenciBasvuru.entities.KisiDosya;
import com.ahievran.yabanciOgrenciBasvuru.entities.KisiKayit;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;




@Controller
public class MainController {
	@Autowired
	private KisiBasvuruService kisiBasvuruService;
	@Autowired KisiDosyaRepository dosyaRepository;

	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/")
	public String home(@RequestParam(value = "error", required = false)String error,  Model model, Authentication authentication) {
		boolean ogrenci = 	authentication.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("ogrenci"));
		model.addAttribute("ogrenci", ogrenci);	
		
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        List<KisiBasvuru> kisiBasvurular =   kisiBasvuruService.getAllKisiBasvuruByEmail(userDetails.getUsername());
        model.addAttribute("kisiBasvurular", kisiBasvurular);    
        return "home";		
        
	}
	
	@GetMapping("/generate-pdf/{id}")
	public void downloadPdf(@PathVariable int id, HttpServletResponse response) throws Exception{
        KisiBasvuru kisiBasvuru =   kisiBasvuruService.findKisiBasvuruById(id);
        if(kisiBasvuru == null) {
        	System.out.println("bos");
        	return;
        }
        KisiKayit kisiKayit = kisiBasvuru.getKisiKayit();
        
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        if(!kisiKayit.getEmail().equals(userDetails.getUsername())) {
        	System.out.println("kisiKayit : " + kisiKayit.getEmail());
        	System.out.println("kisiBasvuru : " + userDetails.getUsername());
        	return;
        }       
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Document document = new Document();
        PdfWriter.getInstance(document, baos);
       
        document.open();
        List<KisiDosya> kisiDosyalari = dosyaRepository.findByKisiBasvuruId(id);
        document = kisiBasvuruService.getHtmlContent(kisiBasvuru, document, kisiDosyalari);

        document.close();

        
        response.setContentType("application/pdf");
        String fileName = kisiBasvuru.getBasvuruHash() + ".pdf";
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.getOutputStream().write(baos.toByteArray());
	}
	
	
	
	
	
	
	
	
	
	
	
	
}