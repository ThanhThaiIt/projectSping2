package com.javaweb.repository.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.javaweb.config.DBConnect;
import com.javaweb.repository.RentAreaRepository;
import com.javaweb.repository.entity.RentAreaEntity;
@Repository
public class RentAreaRepositoryImp implements RentAreaRepository{

	@Override
//	public List<RentAreaEntity> getRentAreaByBuildingID(Long id) {
//	    List<RentAreaEntity> listRentAreaEntities = new ArrayList<>();
//	    String sqlString = "SELECT * FROM rentarea r WHERE r.buildingid = ?";
//
//	    try (Connection connection = DBConnect.getConnection();
//	         PreparedStatement preparedStatement = connection.prepareStatement(sqlString)) {
//
//	        preparedStatement.setLong(1, id);
//	        ResultSet resultSet = preparedStatement.executeQuery();
//
//	        while (resultSet.next()) {
//	            RentAreaEntity rentAreaEntity = new RentAreaEntity();
//	            rentAreaEntity.setId(resultSet.getLong("id"));
//	            rentAreaEntity.setValue(resultSet.getString("value"));
//	            rentAreaEntity.setBuildingid(resultSet.getLong("buildingid"));
//	            listRentAreaEntities.add(rentAreaEntity);
//	        }
//
//	    } catch (Exception e) {
//	        System.out.println("error: " + e.getMessage());
//	    }
//
//	    return listRentAreaEntities;
//	}
	public List<RentAreaEntity> getRentAreaByBuildingID(Long id) {
		// TODO Auto-generated method stub
		List<RentAreaEntity> listRentAreaEntities = new ArrayList<RentAreaEntity>();
		String sqlString = "SELECT * FROM rentarea r WHERE r.buildingid = "+id;
		try  {
			
			Connection connection = DBConnect.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sqlString);
	        //preparedStatement.setLong(1, id);
			
	        
	        ResultSet resultSet = preparedStatement.executeQuery();
	        
	        // Nếu có kết quả, tạo đối tượng User từ dữ liệu kết quả
	        while  (resultSet.next()) {
	        	 RentAreaEntity rentAreaEntity = new RentAreaEntity();
	        	 rentAreaEntity.setId(resultSet.getLong("id"));
	        	 rentAreaEntity.setValue(resultSet.getString("value"));
	        	 rentAreaEntity.setBuildingid(resultSet.getLong("buildingid"));
	            	listRentAreaEntities.add(rentAreaEntity);	
	            
	        }
			
		} catch (Exception e) {
			System.out.println("eror"+ e.getMessage());
		}
		return listRentAreaEntities;
	}

}
