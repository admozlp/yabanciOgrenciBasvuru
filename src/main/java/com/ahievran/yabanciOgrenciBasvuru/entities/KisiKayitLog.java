package com.ahievran.yabanciOgrenciBasvuru.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "kisiKayitLoglari")
public class KisiKayitLog {
	
	public KisiKayitLog() {
		super();
	}

	public int getId() {
		return id;
	}

	public KisiKayitLog(int id, String islemAyrinti, String gonderilenAdres, boolean gonderimBasarıiliMi) {
		super();
		this.id = id;
		this.islemAyrinti = islemAyrinti;
		this.gonderilenAdres = gonderilenAdres;
		this.gonderimBasarıiliMi = gonderimBasarıiliMi;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIslemAyrinti() {
		return islemAyrinti;
	}

	public void setIslemAyrinti(String islemAyrinti) {
		this.islemAyrinti = islemAyrinti;
	}

	public String getGonderilenAdres() {
		return gonderilenAdres;
	}

	public void setGonderilenAdres(String gonderilenAdres) {
		this.gonderilenAdres = gonderilenAdres;
	}

	public boolean isGonderimBasarıiliMi() {
		return gonderimBasarıiliMi;
	}

	public void setGonderimBasarıiliMi(boolean gonderimBasarıiliMi) {
		this.gonderimBasarıiliMi = gonderimBasarıiliMi;
	}

	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "islemAyrinti")
	private String islemAyrinti;
	
	@Column(name = "gonderilenAdres")
	private String gonderilenAdres;
	
	@Column(name = "gonderimBasarıiliMi")
	private boolean gonderimBasarıiliMi;
	
}
