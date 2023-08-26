package com.ahievran.yabanciOgrenciBasvuru.entities;

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
@Table(name = "sehirler")
public class Sehir {
	public Sehir() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Sehir(int id, String turkceAd, String ingilizceAd, Ulke ulke, List<KisiBasvuru> kisiBasvuruYasadigiSehir,
			List<KisiBasvuru> kisiBasvuruLiseSehri) {
		super();
		this.id = id;
		this.turkceAd = turkceAd;
		this.ingilizceAd = ingilizceAd;
		this.ulke = ulke;
		this.kisiBasvuruYasadigiSehir = kisiBasvuruYasadigiSehir;
		this.kisiBasvuruLiseSehri = kisiBasvuruLiseSehri;
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

	public Ulke getUlke() {
		return ulke;
	}

	public void setUlke(Ulke ulke) {
		this.ulke = ulke;
	}

	public List<KisiBasvuru> getKisiBasvuruYasadigiSehir() {
		return kisiBasvuruYasadigiSehir;
	}

	public void setKisiBasvuruYasadigiSehir(List<KisiBasvuru> kisiBasvuruYasadigiSehir) {
		this.kisiBasvuruYasadigiSehir = kisiBasvuruYasadigiSehir;
	}

	public List<KisiBasvuru> getKisiBasvuruLiseSehri() {
		return kisiBasvuruLiseSehri;
	}

	public void setKisiBasvuruLiseSehri(List<KisiBasvuru> kisiBasvuruLiseSehri) {
		this.kisiBasvuruLiseSehri = kisiBasvuruLiseSehri;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "turkceAd")
	private String  turkceAd;
	
	@Column(name = "ingilizceAd")
	private String ingilizceAd;
	
	@ManyToOne
    @JoinColumn(name = "ulkeId")
	private Ulke ulke;	
		
    @OneToMany(mappedBy = "yasadigiSehir", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<KisiBasvuru> kisiBasvuruYasadigiSehir;
          
    @OneToMany(mappedBy = "liseninSehri", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<KisiBasvuru> kisiBasvuruLiseSehri;
}
