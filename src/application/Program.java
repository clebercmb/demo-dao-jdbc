package application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {

//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//		LocalDate.parse("22/04/1985", formatter);
		SellerDao sellerDao = DaoFactory.createSellerDao();

		System.out.println("==== TEST 1: seller findById ===");
		Seller seller = sellerDao.findById(3);
		System.out.println(seller);
	}

}
