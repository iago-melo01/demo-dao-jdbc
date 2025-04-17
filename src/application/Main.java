package application;

import db.DB;
import model.dao.DaoFactory;
import model.entities.Seller;

import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        DB.getConnection();
        Seller sellerId2 = DaoFactory.createSellerDAO().findById(6);
        System.out.println(sellerId2);
    }
}
