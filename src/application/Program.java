package application;

import model.entities.Department;
import model.entities.Seller;

import javax.swing.text.DateFormatter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Program {
    public static void main(String[] args) {
        Department obj = new Department(1, "Departamento de marketing");
        System.out.println(obj);

        DateFormat dtf  = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date(System.currentTimeMillis());


        Seller seller01 = new Seller(1, "Thiago silva", "thiagosilvacbf@gmail.com", date, 2000d, obj  );
        System.out.println(seller01);
    }
}
