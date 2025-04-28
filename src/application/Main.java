package application;

import db.DB;
import model.dao.DaoFactory;
import model.entities.Department;
import model.entities.Seller;

import java.util.Date;
import java.util.List;
import java.util.Locale;


public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        DB.getConnection();


        System.out.println("----TEST 1 Seller findByID----");
        Seller seller = DaoFactory.createSellerDAO().findById(2);
        System.out.println(seller);

        System.out.println("\n----TEST 2 Seller findByDepartment----");
        Department dep = new Department(2, null);
        List<Seller> sellersOfDepartment2 = DaoFactory.createSellerDAO().findByDepartment(dep);
        sellersOfDepartment2.forEach(System.out::println);


        System.out.println("\n----TEST 3 Seller findAll----");
        List<Seller> allSellers = DaoFactory.createSellerDAO().findAll();
        allSellers.forEach(System.out::println);

        System.out.println("\n----TEST 4 Seller insertSeller----");
        Seller test = new Seller(1, "test", "<EMAIL>", new Date(System.currentTimeMillis()), 1000.0, new Department(1, null));
        DaoFactory.createSellerDAO().insert(test);
        List<Seller> testsellerlist = DaoFactory.createSellerDAO().findAll();
        testsellerlist.forEach(System.out::println);
        System.out.println("Inserted a new seller! Id = "+ test.getId());
        DB.closeConnection();


    }
}
