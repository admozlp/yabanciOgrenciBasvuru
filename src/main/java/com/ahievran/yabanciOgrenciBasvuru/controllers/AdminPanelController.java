package com.ahievran.yabanciOgrenciBasvuru.controllers;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ahievran.yabanciOgrenciBasvuru.business.abstracts.KisiBasvuruService;
import com.ahievran.yabanciOgrenciBasvuru.business.responses.AdminBasvurularResponse;
import com.ahievran.yabanciOgrenciBasvuru.dataAccess.KisiDosyaRepository;
import com.ahievran.yabanciOgrenciBasvuru.entities.KisiBasvuru;
import com.ahievran.yabanciOgrenciBasvuru.entities.KisiDosya;
import com.ahievran.yabanciOgrenciBasvuru.entities.Program;
import com.ahievran.yabanciOgrenciBasvuru.entities.Ulke;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;

@Controller
public class AdminPanelController {
	@Autowired private KisiBasvuruService basvuruService;
	@Autowired private KisiDosyaRepository dosyaRepository;
	

	
	@GetMapping("/adminpanel")
	public String showAdminPanel(Model model,Authentication authentication) {
		boolean ogrenci = authentication.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("ogrenci"));
		if(ogrenci) {
			return "redirect:/";
		}
				
		List<Program> tumProgramlar = basvuruService.getAllProgram();
		List<Ulke> tumUlkeler = basvuruService.getAllCountries();		
						
		model.addAttribute("tumProgramlar", tumProgramlar);
		model.addAttribute("tumUlkeler", tumUlkeler);
		return "adminpanel";
	}
		
	
    @GetMapping("/basvurular")
    @ResponseBody
    public List<AdminBasvurularResponse> getPersons(@RequestParam int draw,
                                   @RequestParam Integer start,
                                   @RequestParam Integer length,
                                   @RequestParam(value = "search[value]", required = false) String searchValue,
                                   @RequestParam(value = "secilenUlke", required = false) Integer ulke,
                                   @RequestParam(value = "secilenProgram", required = false) Integer program) {
    	
        Pageable pageable = PageRequest.of(start / length, length);        
        List<AdminBasvurularResponse> personsPage = new ArrayList<AdminBasvurularResponse>();
        
        if (searchValue != null && !searchValue.isEmpty()) {
    		personsPage = basvuruService.findByAdContainingIgnoreCaseOrSoyadContainingIgnoreCase(searchValue.toLowerCase(), searchValue.toLowerCase(), pageable);        	
        }  
        else if(ulke != null && program == null){
    		personsPage = basvuruService.getAllBasvuruByUlke(ulke, pageable);
        }
        else if(ulke == null && program != null){
            personsPage = basvuruService.getByProgramId(program, pageable);
        }
        else if(ulke == null && program == null){
    		personsPage = basvuruService.getAllBasvuru(pageable);
        }
        else if(ulke != null && program != null){
    		personsPage = basvuruService.getByProgramIdAndUlkeId(program, ulke, pageable);
        }
                        
        return personsPage;
    }
	
	
	
	@GetMapping("/getbyulke")
	public List<KisiBasvuru> getBasvuruByUlkeId(@RequestParam(value = "secilenUlke", required = false) Integer secilenUlke){
		return  basvuruService.getBasvuruByUlke(secilenUlke);			
	}		

	@GetMapping("/generate-pdf-admin/{id}")
	public void pdfIndir(@PathVariable int id,HttpServletResponse response,Authentication authentication) throws Exception {
		boolean ogrenci = 	authentication.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("ogrenci"));
		if(ogrenci) {
			return;
		}
        KisiBasvuru kisiBasvuru =   basvuruService.findKisiBasvuruById(id);

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Document document = new Document();
        PdfWriter.getInstance(document, baos);
       
        document.open();
        List<KisiDosya> kisiDosyalari = dosyaRepository.findByKisiBasvuruId(id);
        document = basvuruService.getHtmlContent(kisiBasvuru, document, kisiDosyalari);

        document.close();

        
        response.setContentType("application/pdf");
        String fileName = kisiBasvuru.getBasvuruHash() + ".pdf";
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.getOutputStream().write(baos.toByteArray());
	}
	
}
