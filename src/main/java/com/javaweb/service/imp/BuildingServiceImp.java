package com.javaweb.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.model.BuildingDTO;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.service.BuildingService;


@Service
public class BuildingServiceImp implements BuildingService{
	@Autowired
	private BuildingRepository buildingRepository;
	@Override
	public List<BuildingDTO> findAll(String name,Long districId,  List<String> LilistRentType) {
		
		List<BuildingDTO> results = new ArrayList<BuildingDTO>();
		
		List<BuildingEntity> buildingEntities = buildingRepository.getlBuildingEntities(name,districId,LilistRentType);
		
		for (BuildingEntity buildingEntity : buildingEntities) {
			BuildingDTO buildingDTO = new BuildingDTO();
			buildingDTO.setName(buildingEntity.getName());
			buildingDTO.setAddress(buildingEntity.getStreet()+", "+buildingEntity.getWard());
			buildingDTO.setNumberOfBasement(buildingEntity.getNumberOfBasement());
			results.add(buildingDTO);
		}
		
		return results;
	}

	

}
