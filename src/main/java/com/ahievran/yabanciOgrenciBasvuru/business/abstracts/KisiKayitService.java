package com.ahievran.yabanciOgrenciBasvuru.business.abstracts;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.ahievran.yabanciOgrenciBasvuru.business.requests.CreateKisiKayitRequest;
import com.ahievran.yabanciOgrenciBasvuru.entities.KisiKayit;

public interface KisiKayitService  extends UserDetailsService{
	KisiKayit addKisiKayit(CreateKisiKayitRequest createKisiKayitRequest) throws Exception;
	boolean sendVerifiacationCode(KisiKayit kisiKayit);	
	KisiKayit updateVerification(String code);
	boolean verifyReCaptcha(String gRecaptchaResponse);
	KisiKayit findByEmail(String email);
	KisiKayit sendNewPassword(String email) throws Exception;
}
