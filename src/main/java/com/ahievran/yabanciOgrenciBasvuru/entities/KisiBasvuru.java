package com.ahievran.yabanciOgrenciBasvuru.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "kisiBasvurular")
public class KisiBasvuru {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	public KisiBasvuru() {
		super();
	}

	@Column(name = "ad")
	private String ad;
	
	public KisiBasvuru(int id, String ad, String soyad, String anneAdi, String babaAdi, String pasaportNo,
			String dogumYeri, Date dogumTarihi, boolean cinsiyet, Ulke ulke, String vatandaslikSekli, Ulke digerUlke,
			String digerVatandaslikSekli, String telefonNo, String adres, Ulke yasadigiUllke, Sehir yasadigiSehir,
			String mezunOlunanLise, Ulke liseninUlkesi, Sehir liseninSehri, int liseEgitimSuresi, String liseAlan,
			Date liseBaslangic, Date liseBitis, String notSistemi, double liseNotOrtalamasi, double yosPuani,
			int basvuruYil, boolean basvuruDonem, Date basvuruTarihi, String basvuruHash, KisiKayit kisiKayit,
			List<KisiProgram> kisiProgram, List<KisiDosya> kisiDosya) {
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
		this.basvuruHash = basvuruHash;
		this.kisiKayit = kisiKayit;
		this.kisiProgram = kisiProgram;
		this.kisiDosya = kisiDosya;
	}

	public int getId() {
		return id;
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

	public String getBasvuruHash() {
		return basvuruHash;
	}

	public void setBasvuruHash(String basvuruHash) {
		this.basvuruHash = basvuruHash;
	}

	public KisiKayit getKisiKayit() {
		return kisiKayit;
	}

	public void setKisiKayit(KisiKayit kisiKayit) {
		this.kisiKayit = kisiKayit;
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

	@Column(name = "soyad")
	private String soyad;
	
	@Column(name = "anneAdi")
	private String anneAdi;
	
	@Column(name = "babaAdi")
	private String babaAdi;
	
	@Column(name = "pasaportNo")
	private String pasaportNo;
	
	@Column(name = "dogumYeri")
	private String dogumYeri;
	
	@Column(name = "dogumTarihi")
	private Date dogumTarihi;
	
	@Column(name = "cinsiyet")
	private boolean cinsiyet;
	
	@ManyToOne
	@JoinColumn(name="ulkeId")
	private Ulke ulke; // many to one  
	
	@Column(name = "vatandaslikSekli")
	private String vatandaslikSekli;
	
	@ManyToOne
	@JoinColumn(name="digerUlkeId")
	private Ulke digerUlke; // many to one 
	
	@Column(name = "digerVatandaslikSekli")
	private String digerVatandaslikSekli;
	
	@Column(name = "telefonNo")
	private String telefonNo;
	
	@Column(name = "adres")
	private String adres;
	
	@ManyToOne
	@JoinColumn(name="yasadigiUlkeId")
	private Ulke yasadigiUllke; // many to one 
	
	@ManyToOne
	@JoinColumn(name="yasadigiSehirId")
	private Sehir yasadigiSehir;// many to one 
	
	public String getYasadigiSehirText() {
		return yasadigiSehirText;
	}

	public void setYasadigiSehirText(String yasadigiSehirText) {
		this.yasadigiSehirText = yasadigiSehirText;
	}

	@Column(name = "yasadigiSehirText")
	private String yasadigiSehirText;
	
	@Column(name = "mezunOlunanLise")
	private String mezunOlunanLise;
	
	@ManyToOne
	@JoinColumn(name="liseUlkeId")
	private Ulke liseninUlkesi;// many to one 
	
	@ManyToOne
	@JoinColumn(name="liseSehirId")
	private Sehir liseninSehri;// many to one 
	


	
	@Column(name = "liseEgitimSuresi")
	private int liseEgitimSuresi;
	
	@Column(name = "liseAlan")
	private String liseAlan;
	
	@Column(name = "liseBaslangic")
	private Date liseBaslangic;
	
	@Column(name = "liseBitis")
	private Date liseBitis;	
	
	@Column(name = "notSistemi")
	private String notSistemi;
	
	@Column(name = "liseNotOrtalamasi")
	private double liseNotOrtalamasi;
	
	@Column(name = "yosPuani")
	private double yosPuani;	
	
	@Column(name = "basvuruYil")
	private int basvuruYil;
	
	@Column(name = "basvuruDonem")
	private boolean basvuruDonem; // false bahar, true guz
	
	@Column(name = "basvuruTarihi")
	private Date basvuruTarihi;
	
	@Column(name = "basvuruHash")
	private String basvuruHash;
	
	@ManyToOne
	@JoinColumn(name = "kisiKayitId")
	private KisiKayit kisiKayit;// many to one
	
	@OneToMany(mappedBy = "kisiBasvuru", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<KisiProgram> kisiProgram;
	
	@OneToMany(mappedBy = "kisiBasvuru", cascade = CascadeType.ALL, orphanRemoval = false)
	private List<KisiDosya> kisiDosya = new ArrayList<>();

	@Column(name = "liseSehirText")
	private String liseSehirText;// many to one 
	
	public void setLiseSehirText(String liseSehirText) {
		this.liseSehirText = liseSehirText;
		
	};
	public String getLiseSehirText() {
		return liseSehirText;
	}
	
}
