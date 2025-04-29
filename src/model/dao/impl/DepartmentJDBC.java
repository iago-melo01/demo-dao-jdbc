package model.dao.impl;

import db.DB;
import db.DBException;
import model.dao.DepartmentDAO;
import model.entities.Department;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentJDBC implements DepartmentDAO {
    Connection conn = null;

    public DepartmentJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Department obj) {
        PreparedStatement pst = null;
        ResultSet rs = null;
        try{
            pst = conn.prepareStatement("INSERT INTO department (Name) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, obj.getName());

            int rowsAffected = pst.executeUpdate();
            if(rowsAffected > 0){
                rs = pst.getGeneratedKeys();
                if(rs.next())
                    System.out.println("Inserted department with id = "+rs.getInt(1));
            }else{
                throw new DBException("Unexpected error! No rows affected");
            }
        }catch(SQLException e){
            throw new DBException(e.getMessage());
        }finally{
            DB.closeStatement(pst);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public void update(Department obj, Integer id) {
        PreparedStatement pst = null;
        ResultSet rs = null;
        try{
            pst = conn.prepareStatement("UPDATE department SET Name = ? WHERE Id = ?", PreparedStatement.RETURN_GENERATED_KEYS);
            pst.setString(1, obj.getName());
            pst.setInt(2, id);

            int rowsAffected = pst.executeUpdate();
            if(rowsAffected > 0){
                System.out.println("Updated department with id = "+id);
            }else{
                throw new DBException("Unexpected error! No rows affected");
            }

        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        }
    }

    @Override
    public void deleteId(Integer id) {
        PreparedStatement pst = null;
        try{
            pst = conn.prepareStatement("DELETE FROM department WHERE Id = ?", Statement.RETURN_GENERATED_KEYS);
            pst.setInt(1, id);
            int rowsAffected = pst.executeUpdate();
            if(rowsAffected > 0){
                System.out.println("Deleted department with id = "+id);
            }else{
                throw new DBException("Unexpected error! No rows affected");
            }
        }catch(SQLException e){
            throw new DBException(e.getMessage());
        }finally{
            DB.closeStatement(pst);
        }
    }

    @Override
    public Department findById(Integer id) {
        PreparedStatement pst = null;
        try{
            pst = DB.getConnection().prepareStatement("SELECT * FROM department WHERE Id = ?");

            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if(rs.next()){

                return new Department(id, rs.getString("Name"));
            }else{
                System.out.println("Nenhum departamento encontrado com o id = "+id);
                return null;
            }
        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        }finally{
            DB.closeStatement(pst);
        }

    }

    @Override
    public List<Department> findAll() {

        PreparedStatement pst = null;
        ResultSet rs = null;
        try{
            pst = conn.prepareStatement("SELECT * FROM department ORDER BY Id");
            rs = pst.executeQuery();
            List<Department> list = new ArrayList<Department>();
            while(rs.next()){
                Department obj = new Department(rs.getInt("Id"), rs.getString("Name"));
                list.add(obj);
            }
            return list;
        }catch(SQLException e){
            throw new DBException(e.getMessage());
        }finally{
            DB.closeStatement(pst);
        }

    }
}
