package com.ahievran.yabanciOgrenciBasvuru.business.requests;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;


public class CreateBasvuruRequest {
	public String getAd() {
		return ad;
	}

	public CreateBasvuruRequest() {
		super();
	}

	public void setAd(String ad) {
		this.ad = ad;
	}

	public CreateBasvuruRequest(String ad, String soyad, String anneAdi, String babaAdi, String pasaportNo,
			String dogumYeri, String dogumTarihi, String cinsiyet, String vatandaslikSekli,
			String digerVatandaslikSekli, String telefonNo, String adres, String email, String mezunOlunanLise,
			int liseEgitimSuresi, String liseAlan, String liseBaslangic, String liseBitis, String notSistemi,
			double liseNotOrtalamasi, double yosPuani, Integer ulke, Integer digerUlke, Integer yasadigiUlke,
			Integer yasadigiSehir, Integer liseninUlkesi, Integer liseninSehri, MultipartFile foto,
			MultipartFile kimlikOn, MultipartFile kimlikArka, MultipartFile pasaportOn, MultipartFile pasaportArka,
			MultipartFile diplomaOn, MultipartFile diplomaArka, MultipartFile trYosSonuc, List<Integer> secimler) {
		super();
		this.ad = ad;
		this.soyad = soyad;
		this.anneAdi = anneAdi;
		this.babaAdi = babaAdi;
		this.pasaportNo = pasaportNo;
		this.dogumYeri = dogumYeri;
		this.dogumTarihi = dogumTarihi;
		this.cinsiyet = cinsiyet;
		this.vatandaslikSekli = vatandaslikSekli;
		this.digerVatandaslikSekli = digerVatandaslikSekli;
		this.telefonNo = telefonNo;
		this.adres = adres;
		this.email = email;
		this.mezunOlunanLise = mezunOlunanLise;
		this.liseEgitimSuresi = liseEgitimSuresi;
		this.liseAlan = liseAlan;
		this.liseBaslangic = liseBaslangic;
		this.liseBitis = liseBitis;
		this.notSistemi = notSistemi;
		this.liseNotOrtalamasi = liseNotOrtalamasi;
		this.yosPuani = yosPuani;
		this.ulke = ulke;
		this.digerUlke = digerUlke;
		this.yasadigiUlke = yasadigiUlke;
		this.yasadigiSehir = yasadigiSehir;
		this.liseninUlkesi = liseninUlkesi;
		this.liseninSehri = liseninSehri;
		this.foto = foto;
		this.kimlikOn = kimlikOn;
		this.kimlikArka = kimlikArka;
		this.pasaportOn = pasaportOn;
		this.pasaportArka = pasaportArka;
		this.diplomaOn = diplomaOn;
		this.diplomaArka = diplomaArka;
		this.trYosSonuc = trYosSonuc;
		this.secimler = secimler;
	}

	public String getSoyad() {
		return soyad;
	}

	public void setSoyad(String soyad) {
		this.soyad = soyad;
	}

	public String getAnneAdi() {
		return anneAdi;
	}

	public void setAnneAdi(String anneAdi) {
		this.anneAdi = anneAdi;
	}

	public String getBabaAdi() {
		return babaAdi;
	}

	public void setBabaAdi(String babaAdi) {
		this.babaAdi = babaAdi;
	}

	public String getPasaportNo() {
		return pasaportNo;
	}

	public void setPasaportNo(String pasaportNo) {
		this.pasaportNo = pasaportNo;
	}

	public String getDogumYeri() {
		return dogumYeri;
	}

	public void setDogumYeri(String dogumYeri) {
		this.dogumYeri = dogumYeri;
	}

	public String getDogumTarihi() {
		return dogumTarihi;
	}

	public void setDogumTarihi(String dogumTarihi) {
		this.dogumTarihi = dogumTarihi;
	}

	public String getCinsiyet() {
		return cinsiyet;
	}

	public void setCinsiyet(String cinsiyet) {
		this.cinsiyet = cinsiyet;
	}

	public String getVatandaslikSekli() {
		return vatandaslikSekli;
	}

	public void setVatandaslikSekli(String vatandaslikSekli) {
		this.vatandaslikSekli = vatandaslikSekli;
	}

	public String getDigerVatandaslikSekli() {
		return digerVatandaslikSekli;
	}

	public void setDigerVatandaslikSekli(String digerVatandaslikSekli) {
		this.digerVatandaslikSekli = digerVatandaslikSekli;
	}

	public String getTelefonNo() {
		return telefonNo;
	}

	public void setTelefonNo(String telefonNo) {
		this.telefonNo = telefonNo;
	}

	public String getAdres() {
		return adres;
	}

	public void setAdres(String adres) {
		this.adres = adres;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMezunOlunanLise() {
		return mezunOlunanLise;
	}

	public void setMezunOlunanLise(String mezunOlunanLise) {
		this.mezunOlunanLise = mezunOlunanLise;
	}

	public int getLiseEgitimSuresi() {
		return liseEgitimSuresi;
	}

	public void setLiseEgitimSuresi(int liseEgitimSuresi) {
		this.liseEgitimSuresi = liseEgitimSuresi;
	}

	public String getLiseAlan() {
		return liseAlan;
	}

	public void setLiseAlan(String liseAlan) {
		this.liseAlan = liseAlan;
	}

	public String getLiseBaslangic() {
		return liseBaslangic;
	}

	public void setLiseBaslangic(String liseBaslangic) {
		this.liseBaslangic = liseBaslangic;
	}

	public String getLiseBitis() {
		return liseBitis;
	}

	public void setLiseBitis(String liseBitis) {
		this.liseBitis = liseBitis;
	}

	public String getNotSistemi() {
		return notSistemi;
	}

	public void setNotSistemi(String notSistemi) {
		this.notSistemi = notSistemi;
	}

	public double getLiseNotOrtalamasi() {
		return liseNotOrtalamasi;
	}

	public void setLiseNotOrtalamasi(double liseNotOrtalamasi) {
		this.liseNotOrtalamasi = liseNotOrtalamasi;
	}

	public double getYosPuani() {
		return yosPuani;
	}

	public void setYosPuani(double yosPuani) {
		this.yosPuani = yosPuani;
	}

	public Integer getUlke() {
		return ulke;
	}

	public void setUlke(Integer ulke) {
		this.ulke = ulke;
	}

	public Integer getDigerUlke() {
		return digerUlke;
	}

	public void setDigerUlke(Integer digerUlke) {
		this.digerUlke = digerUlke;
	}

	public Integer getYasadigiUlke() {
		return yasadigiUlke;
	}

	public void setYasadigiUlke(Integer yasadigiUlke) {
		this.yasadigiUlke = yasadigiUlke;
	}

	public Integer getYasadigiSehir() {
		return yasadigiSehir;
	}

	public void setYasadigiSehir(Integer yasadigiSehir) {
		this.yasadigiSehir = yasadigiSehir;
	}

	public Integer getLiseninUlkesi() {
		return liseninUlkesi;
	}

	public void setLiseninUlkesi(Integer liseninUlkesi) {
		this.liseninUlkesi = liseninUlkesi;
	}

	public Integer getLiseninSehri() {
		return liseninSehri;
	}

	public void setLiseninSehri(Integer liseninSehri) {
		this.liseninSehri = liseninSehri;
	}

	public MultipartFile getFoto() {
		return foto;
	}

	public void setFoto(MultipartFile foto) {
		this.foto = foto;
	}

	public MultipartFile getKimlikOn() {
		return kimlikOn;
	}

	public void setKimlikOn(MultipartFile kimlikOn) {
		this.kimlikOn = kimlikOn;
	}

	public MultipartFile getKimlikArka() {
		return kimlikArka;
	}

	public void setKimlikArka(MultipartFile kimlikArka) {
		this.kimlikArka = kimlikArka;
	}

	public MultipartFile getPasaportOn() {
		return pasaportOn;
	}

	public void setPasaportOn(MultipartFile pasaportOn) {
		this.pasaportOn = pasaportOn;
	}

	public MultipartFile getPasaportArka() {
		return pasaportArka;
	}

	public void setPasaportArka(MultipartFile pasaportArka) {
		this.pasaportArka = pasaportArka;
	}

	public MultipartFile getDiplomaOn() {
		return diplomaOn;
	}

	public void setDiplomaOn(MultipartFile diplomaOn) {
		this.diplomaOn = diplomaOn;
	}

	public MultipartFile getDiplomaArka() {
		return diplomaArka;
	}

	public void setDiplomaArka(MultipartFile diplomaArka) {
		this.diplomaArka = diplomaArka;
	}

	public MultipartFile getTrYosSonuc() {
		return trYosSonuc;
	}

	public void setTrYosSonuc(MultipartFile trYosSonuc) {
		this.trYosSonuc = trYosSonuc;
	}

	public List<Integer> getSecimler() {
		return secimler;
	}

	public void setSecimler(List<Integer> secimler) {
		this.secimler = secimler;
	}

	private String liseSehirText;
	
	private String yasadigiSehirText;
	
	public String getLiseSehirText() {
		return liseSehirText;
	}

	public void setLiseSehirText(String liseSehirText) {
		this.liseSehirText = liseSehirText;
	}

	public String getYasadigiSehirText() {
		return yasadigiSehirText;
	}

	public void setYasadigiSehirText(String yasadigiSehirText) {
		this.yasadigiSehirText = yasadigiSehirText;
	}

	private String ad;

	private String soyad;

	private String anneAdi;

	private String babaAdi;

	private String pasaportNo;

	private String dogumYeri;

	private String dogumTarihi;

	private String cinsiyet;

	private String vatandaslikSekli;

	private String digerVatandaslikSekli;

	private String telefonNo;

	private String adres;

	private String email;

	private String mezunOlunanLise;

	private int liseEgitimSuresi;

	private String liseAlan;

	public MultipartFile getDekont() {
		return dekont;
	}

	public void setDekont(MultipartFile dekont) {
		this.dekont = dekont;
	}

	private String liseBaslangic;

	private String liseBitis;

	private String notSistemi;

	private double liseNotOrtalamasi;

	private double yosPuani;

	private Integer ulke;
	private Integer digerUlke;
	private Integer yasadigiUlke;
	private Integer yasadigiSehir;
	private Integer liseninUlkesi;
	private Integer liseninSehri;

	private MultipartFile foto;
	private MultipartFile kimlikOn;
	private MultipartFile kimlikArka;
	private MultipartFile pasaportOn;
	private MultipartFile pasaportArka;
	private MultipartFile diplomaOn;
	private MultipartFile diplomaArka;
	private MultipartFile trYosSonuc;
	private MultipartFile dekont;

	List<Integer> secimler;

}
