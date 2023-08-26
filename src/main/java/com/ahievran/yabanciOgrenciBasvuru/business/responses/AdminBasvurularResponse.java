package com.ahievran.yabanciOgrenciBasvuru.business.responses;

public class AdminBasvurularResponse {
	private int id;

	private String ad;

	private String soyad;
			
	public int getId() {
		return id;
	}

	public AdminBasvurularResponse(int id, String ad, String soyad, String cinsiyet, String ulke,
			double liseNotOrtalamasi, double yosPuani) {
		super();
		this.id = id;
		this.ad = ad;
		this.soyad = soyad;
		this.cinsiyet = cinsiyet;
		this.ulke = ulke;
		this.liseNotOrtalamasi = liseNotOrtalamasi;
		this.yosPuani = yosPuani;
	}

	public AdminBasvurularResponse() {
		super();
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

	public String getCinsiyet() {
		return cinsiyet;
	}

	public void setCinsiyet(String cinsiyet) {
		this.cinsiyet = cinsiyet;
	}

	public String getUlke() {
		return ulke;
	}

	public void setUlke(String ulke) {
		this.ulke = ulke;
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

	private String cinsiyet;
	
	private String ulke;   
	
	private double liseNotOrtalamasi;
		
	private double yosPuani;	
}
