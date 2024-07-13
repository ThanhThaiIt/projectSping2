package com.javaweb.repository.imp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;

@Repository
public class BuildingRepositoryImp implements BuildingRepository {

	static final String urlString = "jdbc:mysql://localhost:3307/estatebasic";
	static final String username = "root";
	static final String passsword = "090902";

	// b.name like '%"+name+"%'"
	@Override
	public List<BuildingEntity> getlBuildingEntities(String name, Long districId, List<String> listRentType) {
		StringBuilder sql = new StringBuilder("SELECT * FROM building b  ");

		int check = 0;

//		if (!LilistRentType.isEmpty()) {
//			sql.append(" join ");
//		}
		if (listRentType != null && listRentType.size() > 0) {
			sql.append(" join buildingrenttype br on b.id = br.buildingid join renttype r on br.renttypeid = r.id ");
			if (!containsWhere(sql)) {
				sql.append(" where 1=1 and ");
			}
			
			for (String rentType : listRentType) {
				if (!endsWithAnd(sql)) {
					sql.append(" and ");
				}

				sql.append(" r.code = '"+rentType+ "' ");
			}
		}

		if (name != null && !name.equals("")) {
			if (!containsWhere(sql)) {
				sql.append(" where 1=1 and");
			}
			if (!endsWithAnd(sql)) {
				sql.append(" and ");
			}

			sql.append(" b.name like '%" + name + "%' ");
		}
		if (districId != null) {
			if (!containsWhere(sql)) {
				sql.append(" where 1=1 and");
			}
			if (!endsWithAnd(sql)) {
				sql.append(" and ");
			}

			sql.append(" b.districtid = " + districId + " ");
		}

		List<BuildingEntity> lBuildingDTOs = new ArrayList<BuildingEntity>();
		try (Connection connection = DriverManager.getConnection(urlString, username, passsword);
				Statement stmt = connection.createStatement();
				ResultSet resultSet = stmt.executeQuery(sql.toString());) {
			System.out.println("connected database successfully");

			while (resultSet.next()) {
				BuildingEntity buildingEntity = new BuildingEntity();
				buildingEntity.setName(resultSet.getString("name"));
				buildingEntity.setStreet(resultSet.getString("street"));
				buildingEntity.setWard(resultSet.getString("ward"));
				buildingEntity.setNumberOfBasement(resultSet.getInt("numberofbasement"));
				lBuildingDTOs.add(buildingEntity);

			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Connected databes fail");
		}
		return lBuildingDTOs;
	}

	public static boolean containsWhere(StringBuilder sb) {
		String queryString = sb.toString();
		return queryString.contains("where");
	}

	public static boolean endsWithAnd(StringBuilder sb) {
		String queryString = sb.toString().trim();
		return queryString.endsWith("and");
	}

	public static int countAnd(StringBuilder sb) {
		String queryString = sb.toString();
		String word = "and";
		int count = 0;
		int index = 0;

		while ((index = queryString.indexOf(word, index)) != -1) {
			count++;
			index += word.length();
		}

		return count;
	}

}
