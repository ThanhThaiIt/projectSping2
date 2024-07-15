package com.javaweb.repository.imp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.utils.ListUtil;
import com.javaweb.utils.NumberUtils;
import com.javaweb.utils.StringUtil;

@Repository
public class BuildingRepositoryImp implements BuildingRepository {

	static final String urlString = "jdbc:mysql://localhost:3307/estatebasic";
	static final String username = "root";
	static final String passsword = "090902";

	public void joinTable(Map<String, Object> params, List<String> listRentType, StringBuilder sql) {
		String staffId = (String) params.get("staffID");
		if (StringUtil.checkStringNull(staffId)) {
			sql.append(" join assignmentbuilding ab on b.id = ab.buildingid ");

		}
		String rentFrom = (String) params.get("rentareaValueFrom");
		String rentTo = (String) params.get("rentareaValueTo");
		if (StringUtil.checkStringNull(rentFrom) || StringUtil.checkStringNull(rentTo)) {
			sql.append(" join rentarea ra on ra.buildingid = b.id");
		}

		if (listRentType != null && listRentType.size() > 0 || ListUtil.listIsNull(listRentType)) {
			sql.append(" join buildingrenttype br on b.id = br.buildingid join renttype rt on br.renttypeid = rt.id");
		}
	}

	public void basicQuery(Map<String, Object> params, List<String> listRentType, StringBuilder where) {
		for (Map.Entry<String, Object> item : params.entrySet()) {
			if (!item.getKey().equals("staffID") && !item.getKey().equals("rentTypeCode")
					&& !item.getKey().startsWith("rentarea") && !item.getKey().startsWith("rentprice")) {
				String value = item.getValue().toString();// always check empty value, if the value is empty, get it out
															// of list params
				if (StringUtil.checkStringNull(value)) {
					if (NumberUtils.isNumber(value) == true) {
						where.append(" and b." + item.getKey() + " = " + value);
					} else {
						where.append(" and b." + item.getKey() + " like '%" + value + "%' ");
					}
				}
			}
		}

	}

	public void specialQuery(Map<String, Object> params, List<String> listRentType, StringBuilder where) {
		String staffId = (String) params.get("staffID");
		if (StringUtil.checkStringNull(staffId)) {
			where.append(" and ab.staffid =" + staffId);

		}

		String rentFrom = (String) params.get("rentareaValueFrom");
		String rentTo = (String) params.get("rentareaValueTo");
		if (StringUtil.checkStringNull(rentFrom)) {
			where.append(" and ra.value >= " + rentFrom);
		}
		if (StringUtil.checkStringNull(rentTo)) {
			where.append(" and ra.value <= " + rentTo);
		}

		String rentPriceFrom = (String) params.get("rentpriceFrom");
		String rentPriceTo = (String) params.get("rentpriceTo");
		if (StringUtil.checkStringNull(rentPriceFrom)) {
			where.append(" and b.rentprice >= " + rentPriceFrom);
		}
		if (StringUtil.checkStringNull(rentPriceTo)) {
			where.append(" and b.rentprice <= " + rentPriceTo);
		}

		if (listRentType != null && listRentType.size() > 0) {
			List<String> code = new ArrayList<String>();// exchang listrent -> listcode including ' '

			for (String item : listRentType) {
				code.add("'" + item + "'");
			}
			where.append(" and rt.code in(" + String.join(",", code) + ") ");

		}
	}

	// b.name like '%"+name+"%'"
	@Override
	public List<BuildingEntity> getlBuildingEntities(Map<String, Object> params, List<String> listRentType) {
		StringBuilder sql = new StringBuilder("SELECT b.id ,\n" + "	b.name ,\n" + "	b.street ,\n" + "	b.ward ,\n"
				+ "	b.districtid ,\n" + "	b.numberofbasement ,\n" + "	b.floorarea ,\n" + "	b.rentprice ,\n"
				+ "	b.managername ,\n" + "	b.managerphonenumber ,\n" + "	b.servicefee ,\n"
				+ "	b.brokeragefee  FROM building b  ");
		joinTable(params, listRentType, sql);
		StringBuilder where = new StringBuilder(" where 1 = 1 ");
		basicQuery(params, listRentType, where);
		specialQuery(params, listRentType, where);

		where.append("group by b.id");
		sql.append(where);

		List<BuildingEntity> lBuildingDTOs = new ArrayList<BuildingEntity>();
		try (Connection connection = DriverManager.getConnection(urlString, username, passsword);
				Statement stmt = connection.createStatement();
				ResultSet resultSet = stmt.executeQuery(sql.toString());) {
			System.out.println("connected database successfully");

			while (resultSet.next()) {
				BuildingEntity buildingEntity = new BuildingEntity();
				buildingEntity.setId(resultSet.getLong("id"));
				buildingEntity.setName(resultSet.getString("name"));
				buildingEntity.setStreet(resultSet.getString("street"));
				buildingEntity.setWard(resultSet.getString("ward"));
				buildingEntity.setDistrictid(resultSet.getLong("districtid"));
				buildingEntity.setNumberofbasement(resultSet.getInt("numberofbasement"));
				buildingEntity.setFloorarea(resultSet.getInt("floorarea"));
				buildingEntity.setRentprice(resultSet.getInt("rentprice"));
				buildingEntity.setManagername(resultSet.getString("managername"));
				buildingEntity.setManagerphonenumber(resultSet.getString("managerphonenumber"));
				buildingEntity.setServicefee(resultSet.getString("servicefee"));
				buildingEntity.setBrokeragefee(resultSet.getDouble("brokeragefee"));
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

//@Override
//public List<BuildingEntity> getlBuildingEntities(String name, Long districId, List<String> listRentType) {
//	StringBuilder sql = new StringBuilder("SELECT * FROM building b  ");
//
//	int check = 0;
//
////	if (!LilistRentType.isEmpty()) {
////		sql.append(" join ");
////	}
//	if (listRentType != null && listRentType.size() > 0) {
//		sql.append(" join buildingrenttype br on b.id = br.buildingid join renttype r on br.renttypeid = r.id ");
//		if (!containsWhere(sql)) {
//			sql.append(" where 1=1 and ");
//		}
//		
//		for (String rentType : listRentType) {
//			if (!endsWithAnd(sql)) {
//				sql.append(" and ");
//			}
//
//			sql.append(" r.code = '"+rentType+ "' ");
//		}
//	}
//
//	if (name != null && !name.equals("")) {
//		if (!containsWhere(sql)) {
//			sql.append(" where 1=1 and");
//		}
//		if (!endsWithAnd(sql)) {
//			sql.append(" and ");
//		}
//
//		sql.append(" b.name like '%" + name + "%' ");
//	}
//	if (districId != null) {
//		if (!containsWhere(sql)) {
//			sql.append(" where 1=1 and");
//		}
//		if (!endsWithAnd(sql)) {
//			sql.append(" and ");
//		}
//
//		sql.append(" b.districtid = " + districId + " ");
//	}
//
//	List<BuildingEntity> lBuildingDTOs = new ArrayList<BuildingEntity>();
//	try (Connection connection = DriverManager.getConnection(urlString, username, passsword);
//			Statement stmt = connection.createStatement();
//			ResultSet resultSet = stmt.executeQuery(sql.toString());) {
//		System.out.println("connected database successfully");
//
//		while (resultSet.next()) {
//			BuildingEntity buildingEntity = new BuildingEntity();
//			buildingEntity.setName(resultSet.getString("name"));
//			buildingEntity.setStreet(resultSet.getString("street"));
//			buildingEntity.setWard(resultSet.getString("ward"));
//			buildingEntity.setNumberOfBasement(resultSet.getInt("numberofbasement"));
//			lBuildingDTOs.add(buildingEntity);
//
//		}
//
//	} catch (SQLException e) {
//		e.printStackTrace();
//		System.out.println("Connected databes fail");
//	}
//	return lBuildingDTOs;
//}
