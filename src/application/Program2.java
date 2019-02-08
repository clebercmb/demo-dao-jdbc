package application;

import java.util.List;
import java.util.Scanner;

import db.DB;
import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;


public class Program2 {

	public static void main(String[] args) {
		DepartmentDao dpDao = DaoFactory.createDepartmentDao();

		System.out.println("==== TEST 1: department findById ===");
		Department dp = dpDao.findById(1);
		System.out.println(dp);

		System.out.println();
		System.out.println("==== TEST 2: department findAll ===");
		List<Department> dpList  = dpDao.findAll();
		dpList.forEach(System.out::println);

		System.out.println();
		System.out.println("==== TEST 3: department insert ===");
		dp =  new Department(null,"Kids");
		dpDao.insert(dp);
		System.out.println("Inserted ==> " + dp);

		System.out.println();
		System.out.println("==== TEST 4: department update ===");
		dp.setName("Women");
		dpDao.update(dp);
		System.out.println("Updated ==> " + dpDao.findById(dp.getId()));
		
		System.out.println();
		System.out.println("==== TEST 5:  delete ===");
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter id  to be deleted:");
		Integer id = sc.nextInt();
		dpDao.deleteById(id);

		sc.close();
		DB.closeConnection();
	}

}
