package com.ahievran.yabanciOgrenciBasvuru.business.abstracts;

import java.util.List;

import com.ahievran.yabanciOgrenciBasvuru.business.requests.UpdateApplicationRequest;
import com.ahievran.yabanciOgrenciBasvuru.entities.Sehir;

public interface UpdateKisiBasvuruService {
	UpdateApplicationRequest getBasvuruById(int id);
	List<Sehir> getSehirlerByUlkeId(int id);
	UpdateApplicationRequest updateApp(UpdateApplicationRequest updateApplicationRequest);

}
