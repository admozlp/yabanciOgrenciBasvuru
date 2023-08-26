package com.ahievran.yabanciOgrenciBasvuru.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "kisiDosya")
public class KisiDosya {
	public KisiDosya(int id, String dosyaBilgi, String dosyaYol, int dosyaTuru, KisiBasvuru kisiBasvuru) {
		super();
		this.id = id;
		this.dosyaBilgi = dosyaBilgi;
		this.dosyaYol = dosyaYol;
		this.dosyaTuru = dosyaTuru;
		this.kisiBasvuru = kisiBasvuru;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDosyaBilgi() {
		return dosyaBilgi;
	}

	public void setDosyaBilgi(String dosyaBilgi) {
		this.dosyaBilgi = dosyaBilgi;
	}

	public String getDosyaYol() {
		return dosyaYol;
	}

	public void setDosyaYol(String dosyaYol) {
		this.dosyaYol = dosyaYol;
	}

	public int getDosyaTuru() {
		return dosyaTuru;
	}

	public void setDosyaTuru(int dosyaTuru) {
		this.dosyaTuru = dosyaTuru;
	}

	public KisiBasvuru getKisiBasvuru() {
		return kisiBasvuru;
	}

	public void setKisiBasvuru(KisiBasvuru kisiBasvuru) {
		this.kisiBasvuru = kisiBasvuru;
	}

	public KisiDosya() {
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "dosyaBilgi")
	private String dosyaBilgi;
	
	@Column(name = "dosyaYol")
	private String dosyaYol;
	
	@Column(name = "dosyaTuru")
	private int dosyaTuru;
		
	@ManyToOne()
	@JoinColumn(name = "kisiBasvuruId")
	private KisiBasvuru kisiBasvuru;

}
