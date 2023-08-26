package com.ahievran.yabanciOgrenciBasvuru.business.requests;

import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ahievran.yabanciOgrenciBasvuru.entities.KisiDosya;
import com.ahievran.yabanciOgrenciBasvuru.entities.KisiProgram;
import com.ahievran.yabanciOgrenciBasvuru.entities.Sehir;
import com.ahievran.yabanciOgrenciBasvuru.entities.Ulke;


public class UpdateApplicationRequest {
	private int id;

	public UpdateApplicationRequest() {
		super();
	}
	public int getId() {
		return id;
	}
	public UpdateApplicationRequest(int id, String ad, String soyad, String anneAdi, String babaAdi, String pasaportNo,
			String dogumYeri, Date dogumTarihi, boolean cinsiyet, Ulke ulke, String vatandaslikSekli, Ulke digerUlke,
			String digerVatandaslikSekli, String telefonNo, String adres, Ulke yasadigiUllke, Sehir yasadigiSehir,
			String mezunOlunanLise, Ulke liseninUlkesi, Sehir liseninSehri, int liseEgitimSuresi, String liseAlan,
			Date liseBaslangic, Date liseBitis, String notSistemi, double liseNotOrtalamasi, double yosPuani,
			int basvuruYil, boolean basvuruDonem, Date basvuruTarihi, List<KisiProgram> kisiProgram,
			List<KisiDosya> kisiDosya, String tarihDogum, String baslangicLise, String bitisLise, String gender,
			Integer ulkeId, Integer digerUlkeId, Integer yasadigiUlkeId, Integer yasadigiSehirId,
			Integer liseninUlkesiId, Integer liseninSehriId, List<Integer> secimler, MultipartFile foto,
			MultipartFile kimlikOn, MultipartFile kimlikArka, MultipartFile pasaportOn, MultipartFile pasaportArka,
			MultipartFile diplomaOn, MultipartFile diplomaArka, MultipartFile trYosSonuc) {
		super();
		this.id = id;
		this.ad = ad;
		this.soyad = soyad;
		this.anneAdi = anneAdi;
		this.babaAdi = babaAdi;
		this.pasaportNo = pasaportNo;
		this.dogumYeri = dogumYeri;
		this.dogumTarihi = dogumTarihi;
		this.cinsiyet = cinsiyet;
		this.ulke = ulke;
		this.vatandaslikSekli = vatandaslikSekli;
		this.digerUlke = digerUlke;
		this.digerVatandaslikSekli = digerVatandaslikSekli;
		this.telefonNo = telefonNo;
		this.adres = adres;
		this.yasadigiUllke = yasadigiUllke;
		this.yasadigiSehir = yasadigiSehir;
		this.mezunOlunanLise = mezunOlunanLise;
		this.liseninUlkesi = liseninUlkesi;
		this.liseninSehri = liseninSehri;
		this.liseEgitimSuresi = liseEgitimSuresi;
		this.liseAlan = liseAlan;
		this.liseBaslangic = liseBaslangic;
		this.liseBitis = liseBitis;
		this.notSistemi = notSistemi;
		this.liseNotOrtalamasi = liseNotOrtalamasi;
		this.yosPuani = yosPuani;
		this.basvuruYil = basvuruYil;
		this.basvuruDonem = basvuruDonem;
		this.basvuruTarihi = basvuruTarihi;
		this.kisiProgram = kisiProgram;
		this.kisiDosya = kisiDosya;
		this.tarihDogum = tarihDogum;
		this.baslangicLise = baslangicLise;
		this.bitisLise = bitisLise;
		this.gender = gender;
		this.ulkeId = ulkeId;
		this.digerUlkeId = digerUlkeId;
		this.yasadigiUlkeId = yasadigiUlkeId;
		this.yasadigiSehirId = yasadigiSehirId;
		this.liseninUlkesiId = liseninUlkesiId;
		this.liseninSehriId = liseninSehriId;
		this.secimler = secimler;
		this.foto = foto;
		this.kimlikOn = kimlikOn;
		this.kimlikArka = kimlikArka;
		this.pasaportOn = pasaportOn;
		this.pasaportArka = pasaportArka;
		this.diplomaOn = diplomaOn;
		this.diplomaArka = diplomaArka;
		this.trYosSonuc = trYosSonuc;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAd() {
		return ad;
	}
	public void setAd(String ad) {
		this.ad = ad;
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
	public Date getDogumTarihi() {
		return dogumTarihi;
	}
	public void setDogumTarihi(Date dogumTarihi) {
		this.dogumTarihi = dogumTarihi;
	}
	public boolean isCinsiyet() {
		return cinsiyet;
	}
	public void setCinsiyet(boolean cinsiyet) {
		this.cinsiyet = cinsiyet;
	}
	public Ulke getUlke() {
		return ulke;
	}
	public void setUlke(Ulke ulke) {
		this.ulke = ulke;
	}
	public String getVatandaslikSekli() {
		return vatandaslikSekli;
	}
	public void setVatandaslikSekli(String vatandaslikSekli) {
		this.vatandaslikSekli = vatandaslikSekli;
	}
	public Ulke getDigerUlke() {
		return digerUlke;
	}
	public void setDigerUlke(Ulke digerUlke) {
		this.digerUlke = digerUlke;
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
	public Ulke getYasadigiUllke() {
		return yasadigiUllke;
	}
	public void setYasadigiUllke(Ulke yasadigiUllke) {
		this.yasadigiUllke = yasadigiUllke;
	}
	public Sehir getYasadigiSehir() {
		return yasadigiSehir;
	}
	public void setYasadigiSehir(Sehir yasadigiSehir) {
		this.yasadigiSehir = yasadigiSehir;
	}
	public String getMezunOlunanLise() {
		return mezunOlunanLise;
	}
	public void setMezunOlunanLise(String mezunOlunanLise) {
		this.mezunOlunanLise = mezunOlunanLise;
	}
	public Ulke getLiseninUlkesi() {
		return liseninUlkesi;
	}
	public void setLiseninUlkesi(Ulke liseninUlkesi) {
		this.liseninUlkesi = liseninUlkesi;
	}
	public Sehir getLiseninSehri() {
		return liseninSehri;
	}
	public void setLiseninSehri(Sehir liseninSehri) {
		this.liseninSehri = liseninSehri;
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
	public Date getLiseBaslangic() {
		return liseBaslangic;
	}
	public void setLiseBaslangic(Date liseBaslangic) {
		this.liseBaslangic = liseBaslangic;
	}
	public Date getLiseBitis() {
		return liseBitis;
	}
	public void setLiseBitis(Date liseBitis) {
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
	public int getBasvuruYil() {
		return basvuruYil;
	}
	public void setBasvuruYil(int basvuruYil) {
		this.basvuruYil = basvuruYil;
	}
	public boolean isBasvuruDonem() {
		return basvuruDonem;
	}
	public void setBasvuruDonem(boolean basvuruDonem) {
		this.basvuruDonem = basvuruDonem;
	}
	public Date getBasvuruTarihi() {
		return basvuruTarihi;
	}
	public void setBasvuruTarihi(Date basvuruTarihi) {
		this.basvuruTarihi = basvuruTarihi;
	}
	public List<KisiProgram> getKisiProgram() {
		return kisiProgram;
	}
	public void setKisiProgram(List<KisiProgram> kisiProgram) {
		this.kisiProgram = kisiProgram;
	}
	public List<KisiDosya> getKisiDosya() {
		return kisiDosya;
	}
	public void setKisiDosya(List<KisiDosya> kisiDosya) {
		this.kisiDosya = kisiDosya;
	}
	public String getTarihDogum() {
		return tarihDogum;
	}
	public void setTarihDogum(String tarihDogum) {
		this.tarihDogum = tarihDogum;
	}
	public String getBaslangicLise() {
		return baslangicLise;
	}
	public void setBaslangicLise(String baslangicLise) {
		this.baslangicLise = baslangicLise;
	}
	public String getBitisLise() {
		return bitisLise;
	}
	public void setBitisLise(String bitisLise) {
		this.bitisLise = bitisLise;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Integer getUlkeId() {
		return ulkeId;
	}
	public void setUlkeId(Integer ulkeId) {
		this.ulkeId = ulkeId;
	}
	public Integer getDigerUlkeId() {
		return digerUlkeId;
	}
	public void setDigerUlkeId(Integer digerUlkeId) {
		this.digerUlkeId = digerUlkeId;
	}
	public Integer getYasadigiUlkeId() {
		return yasadigiUlkeId;
	}
	public void setYasadigiUlkeId(Integer yasadigiUlkeId) {
		this.yasadigiUlkeId = yasadigiUlkeId;
	}
	public Integer getYasadigiSehirId() {
		return yasadigiSehirId;
	}
	public void setYasadigiSehirId(Integer yasadigiSehirId) {
		this.yasadigiSehirId = yasadigiSehirId;
	}
	public Integer getLiseninUlkesiId() {
		return liseninUlkesiId;
	}
	public void setLiseninUlkesiId(Integer liseninUlkesiId) {
		this.liseninUlkesiId = liseninUlkesiId;
	}
	public Integer getLiseninSehriId() {
		return liseninSehriId;
	}
	public void setLiseninSehriId(Integer liseninSehriId) {
		this.liseninSehriId = liseninSehriId;
	}
	public List<Integer> getSecimler() {
		return secimler;
	}
	public void setSecimler(List<Integer> secimler) {
		this.secimler = secimler;
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

	private Date dogumTarihi;

	private boolean cinsiyet; // true : male, false : female

	private Ulke ulke; 

	private String vatandaslikSekli;

	private Ulke digerUlke; 

	private String digerVatandaslikSekli;

	private String telefonNo;

	private String adres;

	private Ulke yasadigiUllke; 

	private Sehir yasadigiSehir;

	private String mezunOlunanLise;

	private Ulke liseninUlkesi;

	private Sehir liseninSehri;

	private int liseEgitimSuresi;

	private String liseAlan;

	private Date liseBaslangic;

	private Date liseBitis;

	private String notSistemi;

	private double liseNotOrtalamasi;

	private double yosPuani;

	private int basvuruYil;

	private boolean basvuruDonem; // true guz, false bahar

	private Date basvuruTarihi;

	private List<KisiProgram> kisiProgram;

	private List<KisiDosya> kisiDosya;
	///////////////////////////////////////////
	private String tarihDogum;
	private String baslangicLise;
	private String bitisLise;
	private String gender;
	
	private Integer ulkeId;
	private Integer digerUlkeId;
	private Integer yasadigiUlkeId;
	private Integer yasadigiSehirId;
	private Integer liseninUlkesiId;
	public MultipartFile getDekont() {
		return dekont;
	}
	public void setDekont(MultipartFile dekont) {
		this.dekont = dekont;
	}
	private Integer liseninSehriId;

	List<Integer> secimler;

	
	private MultipartFile foto;
	private MultipartFile kimlikOn;
	private MultipartFile kimlikArka;
	private MultipartFile pasaportOn;
	private MultipartFile pasaportArka;
	private MultipartFile diplomaOn;
	private MultipartFile diplomaArka;
	private MultipartFile trYosSonuc;
	private MultipartFile dekont;

}
