package com.javaweb.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.repository.DistrictRepository;
import com.javaweb.service.DistrictService;

@Service
public class DistrictServiceImp implements DistrictService{

	@Autowired
	
	DistrictRepository districtRepository;
	
	@Override
	public String getNameDistrictByID(Long id) {
		// TODO Auto-generated method stub
		return districtRepository.getDistrictById(id);
	}

}
