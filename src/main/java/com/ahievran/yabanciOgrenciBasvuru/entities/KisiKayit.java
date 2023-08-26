package com.ahievran.yabanciOgrenciBasvuru.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "kisiKayitlar")
public class KisiKayit {
	public KisiKayit(int id, String email, String password, String dogrulamaKodu, boolean dogrulandiMi,
			Date kayitTarihi, Collection<Rol> roller, List<KisiBasvuru> basvurular) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.dogrulamaKodu = dogrulamaKodu;
		this.dogrulandiMi = dogrulandiMi;
		this.kayitTarihi = kayitTarihi;
		this.roller = roller;
		this.basvurular = basvurular;
	}

	public KisiKayit() {
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "email", unique = true)
	private String email;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDogrulamaKodu() {
		return dogrulamaKodu;
	}

	public void setDogrulamaKodu(String dogrulamaKodu) {
		this.dogrulamaKodu = dogrulamaKodu;
	}

	public boolean isDogrulandiMi() {
		return dogrulandiMi;
	}

	public void setDogrulandiMi(boolean dogrulandiMi) {
		this.dogrulandiMi = dogrulandiMi;
	}

	public Date getKayitTarihi() {
		return kayitTarihi;
	}

	public void setKayitTarihi(Date kayitTarihi) {
		this.kayitTarihi = kayitTarihi;
	}

	public Collection<Rol> getRoller() {
		return roller;
	}

	public void setRoller(Collection<Rol> roller) {
		this.roller = roller;
	}

	public List<KisiBasvuru> getBasvurular() {
		return basvurular;
	}

	public void setBasvurular(List<KisiBasvuru> basvurular) {
		this.basvurular = basvurular;
	}

	@Column(name = "password")
	private String password;
	
	@Column(name = "dogrulamaKodu")
	private String dogrulamaKodu;
	
	@Column(name = "dogrulandiMi")
	private  boolean dogrulandiMi;
	
	@Column(name = "kayitTarihi")
	private Date kayitTarihi;
	
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "kisi_roller",
			joinColumns = @JoinColumn(name = "kisi_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "rol_id", referencedColumnName = "id"))
	private Collection<Rol> roller = new ArrayList<Rol>();
	
	@OneToMany(mappedBy = "kisiKayit", cascade = CascadeType.ALL,orphanRemoval = true)
	private List<KisiBasvuru> basvurular;
}
