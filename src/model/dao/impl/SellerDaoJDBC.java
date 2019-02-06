package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

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
				String name = rs.getString("Name");
				String email = rs.getString("Email");
				java.sql.Date date = rs.getDate("BirthDate");
				Double baseSalary = rs.getDouble("BaseSalary");
				Integer departmentId = rs.getInt("DepartmentId");
				String depName = rs.getString("DepName");
				
				Department dp =  new Department(departmentId,depName);
				
				seller = new Seller(id, name, email, date.toLocalDate(), baseSalary, dp);
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
	public List<Seller> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
