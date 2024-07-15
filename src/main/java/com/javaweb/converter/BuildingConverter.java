package com.javaweb.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.javaweb.model.BuildingDTO;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.repository.entity.RentAreaEntity;
import com.javaweb.repository.imp.RentAreaRepositoryImp;
import com.javaweb.service.DistrictService;
@Component
public class BuildingConverter {
	@Autowired
	private DistrictService districtService;
	
	@Autowired
	
	private RentAreaRepositoryImp rentAreaRepositoryImp;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public BuildingDTO toBuidingDTO(BuildingEntity buildingEntity) {
		//BuildingDTO buildingDTO = new BuildingDTO();
		BuildingDTO buildingDTO = modelMapper.map(buildingEntity, BuildingDTO.class);
		
		buildingDTO.setAddress(buildingEntity.getStreet()+", "+buildingEntity.getWard()+", "+districtService.getNameDistrictByID(buildingEntity.getDistrictid()));
		
//		buildingDTO.setName(buildingEntity.getName());
//		buildingDTO.setNumberOfBasement(buildingEntity.getNumberofbasement());
//		buildingDTO.setManagerNameString(buildingEntity.getManagername());
//		buildingDTO.setManagerPhoneNumber(buildingEntity.getManagerphonenumber());
//		buildingDTO.setFloorarea(buildingEntity.getFloorarea());
//		buildingDTO.setRentprice(buildingEntity.getRentprice());
//		buildingDTO.setServicefee(buildingEntity.getServicefee());
//		buildingDTO.setBrokeragefee(buildingEntity.getBrokeragefee());
		
		
		List<RentAreaEntity> listRentAreaEntities = rentAreaRepositoryImp.getRentAreaByBuildingID(buildingEntity.getId());
		String rentAreaResult = listRentAreaEntities.stream().map(it -> it.getValue().toString()).collect(Collectors.joining(","));
		buildingDTO.setRentArea(rentAreaResult);
		
		
		
	return buildingDTO;
	}

}



