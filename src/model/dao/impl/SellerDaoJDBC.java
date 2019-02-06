package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJDBC implements SellerDao {

	private Connection conn;
	
	public SellerDaoJDBC (Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void insert(Seller seller) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Seller department) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Seller findById(Integer id) {
	
		PreparedStatement st = null;
		ResultSet rs = null;
		Seller seller = null;
		
		try {
			st = conn.prepareStatement("SELECT seller.*,department.Name as DepName\n" 
					+ "FROM seller INNER JOIN department\n" 
					+ "ON seller.DepartmentId = department.Id\n" 
					+ "WHERE seller.Id = ?");
			st.setInt(1, id);
			
			rs = st.executeQuery();
			
			if ( rs.next() ) {

				Department dp =  instantiateDepartment(rs);
				seller = instantiateSeller(rs, dp);
				
			}
			
		} 
		catch (Exception e)  
		{
			e.printStackTrace();
		}
		finally {
			DB.closeStatement(st);
			DB.closeResulSet(rs);
		}
		
		return seller;
	}

	
	@Override
	public List<Seller> findByDepartment(Department department) {
	
		PreparedStatement st = null;
		ResultSet rs = null;
		Seller seller = null;
		List<Seller> sellerList = new ArrayList<>();
		
		try {
			st = conn.prepareStatement("SELECT seller.*,department.Name as DepName\n" 
					+ "FROM seller INNER JOIN department\n" 
					+ "ON seller.DepartmentId = department.Id\n" 
					+ "WHERE seller.DepartmentId = ?\n"
					+ "ORDER  BY Name");
			st.setInt(1, department.getId());
			
			rs = st.executeQuery();
			Map<Integer, Department> map =  new HashMap<>();
			
			while ( rs.next() ) {

				Department dp =  map.get(rs.getInt("DepartmentId"));
				if (dp == null) {
					dp = instantiateDepartment(rs);
					map.put(rs.getInt("DepartmentId"), dp);
				}
				
				seller = instantiateSeller(rs, dp);
				sellerList.add(seller);
			}
			
		} 
		catch (Exception e)  
		{
			e.printStackTrace();
		}
		finally {
			DB.closeStatement(st);
			DB.closeResulSet(rs);
		}
		
		return sellerList;
	}
	
	
	
	private Seller instantiateSeller(ResultSet rs, Department dp) throws SQLException {
		Integer id  = rs.getInt("Id");
		String name = rs.getString("Name");
		String email = rs.getString("Email");
		java.sql.Date date = rs.getDate("BirthDate");
		Double baseSalary = rs.getDouble("BaseSalary");
		
		Seller seller = new Seller(id, name, email, date.toLocalDate(), baseSalary, dp);
		return seller;
	}

	private Department instantiateDepartment(ResultSet rs) throws SQLException {
		Integer departmentId = rs.getInt("DepartmentId");
		String depName = rs.getString("DepName");
		Department dp =  new Department(departmentId,depName);
		return dp;
	}

	@Override
	public List<Seller> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
