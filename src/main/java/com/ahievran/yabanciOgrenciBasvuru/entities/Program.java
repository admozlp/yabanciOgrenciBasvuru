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
@Table(name = "programlar")
public class Program {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	public Program() {
		super();
	}

	public int getId() {
		return id;
	}

	public Program(int id, String turkceAd, String ingilizceAd, int egitimSuresi, Fakulte fakulte,
			List<KisiProgram> kisiProgramlar) {
		super();
		this.id = id;
		this.turkceAd = turkceAd;
		this.ingilizceAd = ingilizceAd;
		this.egitimSuresi = egitimSuresi;
		this.fakulte = fakulte;
		this.kisiProgramlar = kisiProgramlar;
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

	public int getEgitimSuresi() {
		return egitimSuresi;
	}

	public void setEgitimSuresi(int egitimSuresi) {
		this.egitimSuresi = egitimSuresi;
	}

	public Fakulte getFakulte() {
		return fakulte;
	}

	public void setFakulte(Fakulte fakulte) {
		this.fakulte = fakulte;
	}

	public List<KisiProgram> getKisiProgramlar() {
		return kisiProgramlar;
	}

	public void setKisiProgramlar(List<KisiProgram> kisiProgramlar) {
		this.kisiProgramlar = kisiProgramlar;
	}

	@Column(name = "turkceAd")
	private String turkceAd;
	
	@Column(name = "ingilizceAd")
	private String ingilizceAd;
	
	@Column(name = "egitimSuresi")
	private int egitimSuresi;
	
	@ManyToOne
	@JoinColumn(name = "fakulteId")
	private Fakulte fakulte;
		
	@OneToMany(mappedBy = "program", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<KisiProgram> kisiProgramlar;

}
