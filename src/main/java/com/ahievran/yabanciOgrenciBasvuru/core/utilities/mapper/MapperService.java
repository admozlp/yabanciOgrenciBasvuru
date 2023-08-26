package com.ahievran.yabanciOgrenciBasvuru.core.utilities.mapper;

import org.modelmapper.ModelMapper;

public interface MapperService {
	ModelMapper forRequest();
	ModelMapper forResponse();
}
