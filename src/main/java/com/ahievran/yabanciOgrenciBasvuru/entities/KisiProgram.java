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
@Table(name = "kisiProgram")
public class KisiProgram {
	public int getId() {
		return id;
	}

	public KisiProgram() {
		super();
	}

	public void setId(int id) {
		this.id = id;
	}

	public KisiProgram(int id, int tercihSirasi, Program program, KisiBasvuru kisiBasvuru) {
		super();
		this.id = id;
		this.tercihSirasi = tercihSirasi;
		this.program = program;
		this.kisiBasvuru = kisiBasvuru;
	}

	public int getTercihSirasi() {
		return tercihSirasi;
	}

	public void setTercihSirasi(int tercihSirasi) {
		this.tercihSirasi = tercihSirasi;
	}

	public Program getProgram() {
		return program;
	}

	public void setProgram(Program program) {
		this.program = program;
	}

	public KisiBasvuru getKisiBasvuru() {
		return kisiBasvuru;
	}

	public void setKisiBasvuru(KisiBasvuru kisiBasvuru) {
		this.kisiBasvuru = kisiBasvuru;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "tercihSirasi")
	private int tercihSirasi;
		
	@ManyToOne
	@JoinColumn(name = "programId")
	private Program program;
	
	@ManyToOne
	@JoinColumn(name = "kisiBasvuruId")
	private KisiBasvuru kisiBasvuru;
	

}
