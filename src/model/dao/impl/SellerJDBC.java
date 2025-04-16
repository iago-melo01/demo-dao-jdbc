package model.dao.impl;

import db.DB;
import db.DBException;
import model.dao.SellerDAO;
import model.entities.Seller;

import java.sql.PreparedStatement;
import java.sql.SQLException;
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
            PreparedStatement pst = DB.getConnection().prepareStatement("")
        }catch(SQLException e){
            throw new DBException(e.getMessage());
            e.printStackTrace();
        }

    }

    @Override
    public List<Seller> findAll() {
        return List.of();
    }
}
