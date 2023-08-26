package com.ahievran.yabanciOgrenciBasvuru.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "ulkeler")
public class Ulke {
	public int getId() {
		return id;
	}

	public Ulke() {
		super();
	}

	public Ulke(int id, String turkceAd, String ingilizceAd, List<Sehir> sehirler, List<KisiBasvuru> kisiBasvuru,
			List<KisiBasvuru> kisiBasvuruDigerUlke, List<KisiBasvuru> kisiBasvuruYasadigiUlke,
			List<KisiBasvuru> kisiBasvuruLiseUlke) {
		super();
		this.id = id;
		this.turkceAd = turkceAd;
		this.ingilizceAd = ingilizceAd;
		this.sehirler = sehirler;
		this.kisiBasvuru = kisiBasvuru;
		this.kisiBasvuruDigerUlke = kisiBasvuruDigerUlke;
		this.kisiBasvuruYasadigiUlke = kisiBasvuruYasadigiUlke;
		this.kisiBasvuruLiseUlke = kisiBasvuruLiseUlke;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTurkceAd() {
		return turkceAd;
	}

	public void setTurkceAd(String turkceAd) {
		this.turkceAd = turkceAd;
	}

	public String getIngilizceAd() {
		return ingilizceAd;
	}

	public void setIngilizceAd(String ingilizceAd) {
		this.ingilizceAd = ingilizceAd;
	}

	public List<Sehir> getSehirler() {
		return sehirler;
	}

	public void setSehirler(List<Sehir> sehirler) {
		this.sehirler = sehirler;
	}

	public List<KisiBasvuru> getKisiBasvuru() {
		return kisiBasvuru;
	}

	public void setKisiBasvuru(List<KisiBasvuru> kisiBasvuru) {
		this.kisiBasvuru = kisiBasvuru;
	}

	public List<KisiBasvuru> getKisiBasvuruDigerUlke() {
		return kisiBasvuruDigerUlke;
	}

	public void setKisiBasvuruDigerUlke(List<KisiBasvuru> kisiBasvuruDigerUlke) {
		this.kisiBasvuruDigerUlke = kisiBasvuruDigerUlke;
	}

	public List<KisiBasvuru> getKisiBasvuruYasadigiUlke() {
		return kisiBasvuruYasadigiUlke;
	}

	public void setKisiBasvuruYasadigiUlke(List<KisiBasvuru> kisiBasvuruYasadigiUlke) {
		this.kisiBasvuruYasadigiUlke = kisiBasvuruYasadigiUlke;
	}

	public List<KisiBasvuru> getKisiBasvuruLiseUlke() {
		return kisiBasvuruLiseUlke;
	}

	public void setKisiBasvuruLiseUlke(List<KisiBasvuru> kisiBasvuruLiseUlke) {
		this.kisiBasvuruLiseUlke = kisiBasvuruLiseUlke;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "turkceAd")
	private String turkceAd;
	
	@Column(name = "ingilizceAd")
	private String ingilizceAd;	
	
    @OneToMany(mappedBy = "ulke", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Sehir> sehirler;
    
    @OneToMany(mappedBy = "ulke", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<KisiBasvuru> kisiBasvuru;
    
    @OneToMany(mappedBy = "digerUlke", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<KisiBasvuru> kisiBasvuruDigerUlke;
    
    @OneToMany(mappedBy = "yasadigiUllke", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<KisiBasvuru> kisiBasvuruYasadigiUlke; 
    
    @OneToMany(mappedBy = "liseninUlkesi", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<KisiBasvuru> kisiBasvuruLiseUlke;
}
