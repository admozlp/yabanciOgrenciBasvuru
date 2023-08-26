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


@Table(name = "fakulteler")
@Entity
public class Fakulte {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	public Fakulte(int id, String ingilizceAd, String turkceAd, List<Program> programlar) {
		super();
		this.id = id;
		this.ingilizceAd = ingilizceAd;
		this.turkceAd = turkceAd;
		this.programlar = programlar;
	}

	public Fakulte() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIngilizceAd() {
		return ingilizceAd;
	}

	public void setIngilizceAd(String ingilizceAd) {
		this.ingilizceAd = ingilizceAd;
	}

	public String getTurkceAd() {
		return turkceAd;
	}

	public void setTurkceAd(String turkceAd) {
		this.turkceAd = turkceAd;
	}

	public List<Program> getProgramlar() {
		return programlar;
	}

	public void setProgramlar(List<Program> programlar) {
		this.programlar = programlar;
	}

	@Column(name = "ingilizceAd")
	private String ingilizceAd;
	
	@Column(name = "turkceAd")
	private String turkceAd;
	
	@OneToMany(mappedBy = "fakulte", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Program> programlar;
	
}
