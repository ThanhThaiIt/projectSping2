package com.javaweb.service.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.model.BuildingDTO;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.service.BuildingService;
import com.javaweb.service.DistrictService;


@Service
public class BuildingServiceImp implements BuildingService{
	@Autowired
	private BuildingRepository buildingRepository;
	
	@Autowired
	private DistrictService districtService;
	@Override
	public List<BuildingDTO> findAll(Map<String, Object> params,List<String> listRentType) {
		
		List<BuildingDTO> results = new ArrayList<BuildingDTO>();
		
		List<BuildingEntity> buildingEntities = buildingRepository.getlBuildingEntities(params, listRentType);
		
		for (BuildingEntity buildingEntity : buildingEntities) {
		BuildingDTO buildingDTO = new BuildingDTO();
		buildingDTO.setName(buildingEntity.getName());
		buildingDTO.setAddress(buildingEntity.getStreet()+", "+buildingEntity.getWard()+", "+districtService.getNameDistrictByID(buildingEntity.getDistrictid()));
		buildingDTO.setNumberOfBasement(buildingEntity.getNumberofbasement());
		buildingDTO.setManagerNameString(buildingEntity.getManagername());
		buildingDTO.setManagerPhoneNumber(buildingEntity.getManagerphonenumber());
		buildingDTO.setFloorarea(buildingEntity.getFloorarea());
		buildingDTO.setRentprice(buildingEntity.getRentprice());
		buildingDTO.setServicefee(buildingEntity.getServicefee());
		buildingDTO.setBrokeragefee(buildingEntity.getBrokeragefee());
		
		results.add(buildingDTO);
	}
		
		return results;
	}

	

}



//List<BuildingDTO> results = new ArrayList<BuildingDTO>();
//
//List<BuildingEntity> buildingEntities = buildingRepository.getlBuildingEntities(name,districId,LilistRentType);
//
//for (BuildingEntity buildingEntity : buildingEntities) {
//	BuildingDTO buildingDTO = new BuildingDTO();
//	buildingDTO.setName(buildingEntity.getName());
//	buildingDTO.setAddress(buildingEntity.getStreet()+", "+buildingEntity.getWard());
//	buildingDTO.setNumberOfBasement(buildingEntity.getNumberOfBasement());
//	results.add(buildingDTO);
//}
//
//return results;
