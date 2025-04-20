package model.dao.impl;

import db.DB;
import db.DBException;
import model.dao.DepartmentDAO;
import model.entities.Department;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DepartmentJDBC implements DepartmentDAO {
    Connection conn = null;

    public DepartmentJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Department obj) {

    }

    @Override
    public void update(Department obj) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public Department findById(Integer id) {
        try{
            PreparedStatement pst = DB.getConnection().prepareStatement("SELECT * FROM department WHERE Id = ?");

            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                return new Department(id, rs.getString("Name"));
            }
        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Department> findAll() {
        return List.of();
    }
}
