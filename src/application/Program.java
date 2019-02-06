package application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import db.DB;
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
		
		System.out.println();
		System.out.println("==== TEST 3: seller findAll ===");
		sellerList = sellerDao.findAll();
		sellerList.forEach(System.out::println);

		System.out.println();
		System.out.println("==== TEST 4: seller insert ===");
		Department dp = new Department(4, "Books");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate birthDate = LocalDate.parse("13/12/1977", formatter);

		seller = new Seller(null, "Cleber Barbosa", "cleber@mail.com", birthDate, 17000.00, dp );
		sellerDao.insert(seller);;
		Seller seller4 =sellerDao.findById(seller.getId());
		System.out.println("Inserted => " + seller4);

		System.out.println();
		System.out.println("==== TEST 5: seller findById ===");
		Seller seller5 = sellerDao.findById(seller4.getId());
		seller5.setBaseSalary(seller5.getBaseSalary() + 200.0);
		seller5.setName("Cleber Miranda Barbosa");
		System.out.println("To be Updated ==>" +sellerDao.findById(seller4.getId()));
		sellerDao.update(seller5);
		System.out.println("Updated ==>" +sellerDao.findById(seller4.getId()));

		System.out.println();
		System.out.println("==== TEST 6: seller delete ===");
		sellerDao.deleteById(seller4.getId());
		System.out.println("Deleted =>" +seller4);
		
		DB.closeConnection();
	}

}
