package com.javaweb.repository.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.stereotype.Repository;

import com.javaweb.config.DBConnect;
import com.javaweb.repository.DistrictRepository;



@Repository
public class DistrictRepositoryImp implements DistrictRepository{

	@Override
	public String getDistrictById(Long idDistrict) {
		String nameDistrict=null;
		String sqlString = "SELECT d.name FROM district d WHERE id = ?";
		try  {
			
			Connection connection = DBConnect.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sqlString);
	        preparedStatement.setLong(1, idDistrict);
			
	        
	        ResultSet resultSet = preparedStatement.executeQuery();
	        
	        // Nếu có kết quả, tạo đối tượng User từ dữ liệu kết quả
	        while  (resultSet.next()) {
	        	 nameDistrict = resultSet.getString("name");
	            
	            		
	            
	        }
			
		} catch (Exception e) {
			System.out.println("eror"+ e.getMessage());
		}
		
		return nameDistrict;
	}

}
