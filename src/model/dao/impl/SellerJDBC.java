package model.dao.impl;

import db.DB;
import db.DBException;
import model.dao.DaoFactory;
import model.dao.SellerDAO;
import model.entities.Department;
import model.entities.Seller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class SellerJDBC implements SellerDAO {
    @Override
    public void insert(Seller obj) {

    }

    @Override
    public void update(Seller obj) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public Seller findById(Integer id) {
        try{
            PreparedStatement pst = DB.getConnection().prepareStatement("SELECT * FROM seller WHERE Id = ?");
            pst.setInt(1, id.intValue());
            ResultSet rs = pst.executeQuery();
            DateFormat dtf = new SimpleDateFormat("yyyy-MM-DD HH:mm:ss");
            Double baseSalaryWrapper= null;
            String name = null;
            String email = null;
            Integer departmentId = null;
            if (rs.next()){
                baseSalaryWrapper = rs.getDouble("baseSalary");
                name = rs.getString("Name");
                email = rs.getString("Email");
                departmentId = rs.getInt("DepartmentId");
            }


                return new Seller(id.intValue(), name, email, new Date("04/20/2000"), baseSalaryWrapper, DaoFactory.createDepartmentDAO().findById(departmentId));

        }catch(SQLException e ){
            throw new DBException(e.getMessage());
        }
        //| ParseException e
        //dtf.parse(rs.getString("BirthDate"))
    }

    @Override
    public List<Seller> findAll() {
        return List.of();
    }
}
