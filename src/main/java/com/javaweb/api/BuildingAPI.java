package com.javaweb.api;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.javaweb.model.BuildingDTO;
import com.javaweb.model.ErrorResponseDTO;
import com.javaweb.service.BuildingService;

import customexeption.FieldRequiedExeption;

//@Controller: khai bao cho spring boot biet day la noi dinh nghia duong dan co tra ra giao dien (html)
//@ResponseBody: Duong dan se tra ra chuoi

//@RestController: khai bao cho spring boot biet day la duong dan co tra ra json
@RestController
public class BuildingAPI {
	
	@Autowired

	private BuildingService buildingService;
	
	@GetMapping(value = "/api/building/")
	public List<BuildingDTO> getBuildingList(@RequestParam(value = "name",required = false) String name,
			@RequestParam(value = "districtid",required = false) Long districId,
			@RequestParam(value = "typeCode",required = false) List<String> listRentType) {
		List<BuildingDTO> results = buildingService.findAll(name,districId,listRentType);
		
		return results;
	}
	
	
	
	
	
	
	//cách cũ
	// @RequestMapping(value = "/api/building/",method = RequestMethod.GET): dinh nghia duong dan
	
	// cách mới
//	@GetMapping(value = "/api/building/")
//	
//	public BuildingDTO getBuilding(@RequestParam(value = "name", required = false)String name,
//			@RequestParam(value = "numberOfBasement", required = false)Integer numberOfBasementt,
//			@RequestParam(value = "ward", required = false)String wards) {
//		System.out.println(name+" "+numberOfBasementt+ " "+wards);
//		System.out.println(numberOfBasementt);
//		
//		BuildingDTO result = new BuildingDTO();
//		result.setName(name);
//		result.setNumberOfBasement(numberOfBasementt);
//		result.setWard(wards);
//		return result;
//	}
	
	
	
	
	
	
	
	
	
	//@RequestMapping(value = "/api/building/",method = RequestMethod.POST)
	
//	@PostMapping(value = "/api/building/")
//	public void getBuilding2(@RequestBody BuildingDTO buildingDTO) {
//		System.out.println("oke nha nhen em, đây là body");
//		 
//	}
	
	
	
	
	
	
	
	@DeleteMapping(value = "/api/building/{id}/{name}/" )
	public void deleteBuilding(@PathVariable Integer id,@PathVariable String name,@RequestParam(value = "street"
	, required = false) String tenDuong) {
		System.out.println("da xoa id "+ id +" roi nhe, ten la "+ name+" hello ae, "+tenDuong+" nha cac ban");
		 
	}
	
	
	
	
	
	
	
//@PostMapping(value = "/api/building/")
//	
//	public Object getBuilding(@RequestBody BuildingDTO buildingDTO) {
//		List<BuildingDTO> list = new ArrayList<BuildingDTO>();
//		
//		try {
//			validateNameOrNumberIsNull(buildingDTO);
//		} catch (Exception e) {
//			
//			// để show lỗi qua bên postman
//			ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO();
//			errorResponseDTO.setError(e.getMessage());
//			List<String> details = new ArrayList<String>();
//			details.add("Check lại name hoặc number of basement đi bởi vì đang bị null đó!!!");
//			errorResponseDTO.setDetail(details);
//			return  errorResponseDTO;
//		}
//		
//
//		
//		
//		
////		list.add(result);
//		BuildingDTO result2 = new BuildingDTO();
//		result2.setName("meomeo");
//		result2.setNumberOfBasement(9);
//		result2.setWard("2 bhhd");
//		
//		list.add(result2);
//		return result2;
//	}
	
	// bat loi kem dieu chinh status o postman
//@PostMapping(value = "/api/building/")
//	public Object getBuilding(@RequestBody BuildingDTO buildingDTO) {
//		List<BuildingDTO> list = new ArrayList<BuildingDTO>();
//		
//	
//			validateNameOrNumberIsNull(buildingDTO);
//		
//		
//
//		
//		
//		
////		list.add(result);
//		BuildingDTO result2 = new BuildingDTO();
//		result2.setName("meomeo");
//		result2.setNumberOfBasement(9);
//		result2.setWard("2 bhhd");
//		
//		list.add(result2);
//	
//		return list;
//	}
	
	
	
// bắt lỗi cụ thể. check xem name với integer có null không
// đây là cách bắt một lỗi cụ thể mà Exeption java không có
public void validateNameOrNumberIsNull(BuildingDTO buildingDTO) {
	if (buildingDTO.getName()==null || buildingDTO.getName().equals("")|| buildingDTO.getNumberOfBasement() == null) {
		throw new FieldRequiedExeption("Name or NumberoFBasement is null");
	}
}
}
