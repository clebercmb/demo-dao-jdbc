package application;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {

//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//		LocalDate.parse("22/04/1985", formatter);
		SellerDao sellerDao = DaoFactory.createSellerDao();

		System.out.println("==== TEST 1: seller findById ===");
		Seller seller = sellerDao.findById(3);
		System.out.println(seller);

		System.out.println();
		System.out.println("==== TEST 2: seller findByDepartmentId ===");
		List<Seller> sellerList = sellerDao.findByDepartment(new Department(4,null));
		sellerList.forEach(System.out::println);
		
		
	}

}
