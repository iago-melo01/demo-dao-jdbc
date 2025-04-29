package model.dao.impl;

import db.DB;
import db.DBException;
import model.dao.DaoFactory;
import model.dao.SellerDAO;
import model.entities.Department;
import model.entities.Seller;

import java.sql.*;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class SellerJDBC implements SellerDAO {
    private Connection conn = null;

    public SellerJDBC(Connection conn) {
        this.conn = conn;
    }

    public List<Seller> findByDepartment(Department department) {
        ResultSet rs = null;
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(
                    "SELECT seller.*, department.Name AS DepName " +
                            "FROM seller INNER JOIN department " +
                            "ON seller.DepartmentId = department.Id " +
                            "WHERE department.Id = ?");
            pst.setInt(1, department.getId());
            rs = pst.executeQuery();

            List<Seller> list = new ArrayList<>();
            Map<Integer, Department> map = new HashMap<>(); //
            while (rs.next()) {
                Department departmentobj = map.get(rs.getInt("DepartmentId"));
                if (departmentobj == null) {
                    departmentobj = instantiateDepartment(rs);
                    map.put(departmentobj.getId(), departmentobj); // armazena dentro de um hash a referencia pra esse objeto
                    //pra nao ficar criando um novo objeto toda vez que executar a função
                }

                Seller obj = instantiateSeller(rs, departmentobj);
                list.add(obj);

            }

            return list;
        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(pst);
        }
    }

    @Override
    public void insert(Seller obj) {
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            pst = conn.prepareStatement("INSERT INTO seller " +
                    "(Name, Email, BirthDate, BaseSalary, DepartmentId) " +
                    "VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            Date birthDate = new Date(obj.getBirthDate().getTime());
            pst.setString(1, obj.getName());
            pst.setString(2, obj.getEmail());
            pst.setDate(3, birthDate);
            pst.setDouble(4, obj.getBaseSalary());
            pst.setInt(5, obj.getDepartment().getId());
            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                rs = pst.getGeneratedKeys();  // getGeneratedKeys() retorna as Keys que foram criadas no banco
                if (rs.next()) { //Nesse caso, só está retornando um Id inserido, que é trazido em forma de tabela
                    obj.setId(rs.getInt(1)); // E essa tabela só tem uma coluna que é a das primary keys criadas

                }
            } else {
                throw new DBException("Unexpected error! No rows affected");
            }


        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(pst);
        }
    }

    @Override
    public void update(Seller obj, Integer id) {
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement("UPDATE seller SET Name = ?, Email = ?, BirthDate = ?, BaseSalary = ?, DepartmentId = ?      WHERE Id = ?", Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, obj.getName());
            pst.setString(2, obj.getEmail());
            Date birthDate = new Date(obj.getBirthDate().getTime());
            pst.setDate(3, birthDate);
            pst.setDouble(4, obj.getBaseSalary());
            pst.setInt(5, obj.getDepartment().getId());
            pst.setInt(6, id);

            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Updated seller with id = " + id);
            } else {
                throw new DBException("Unexpected error! No rows affected");
            }
        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        } finally {
            DB.closeStatement(pst);
        }
    }

    @Override
    public void deleteId(Integer id) {
        PreparedStatement pst = null;

        try {
            pst = conn.prepareStatement("DELETE FROM seller WHERE id = ?", Statement.RETURN_GENERATED_KEYS);
            pst.setInt(1, id);

            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Deleted seller with id = " + id);
            } else {
                throw new DBException("Unexpected error! No rows affected");
            }
        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        } finally {

            DB.closeStatement(pst);
        }
    }

    @Override
    public Seller findById(Integer id) {
        ResultSet rs = null;
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement("SELECT seller.*, department.Name AS DepName " +
                    "FROM seller INNER JOIN department " +
                    "ON seller.DepartmentId = department.Id " +
                    "WHERE seller.Id = ?");
            pst.setInt(1, id);
            rs = pst.executeQuery();

            if (rs.next()) {
                Department departmentobj = instantiateDepartment(rs);

                Seller obj = instantiateSeller(rs, departmentobj);

                return obj;
            }

            return null;
        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(pst);
        }
    }

    private Department instantiateDepartment(ResultSet rs) throws SQLException {
        Department dep = new Department();
        dep.setId(rs.getInt("DepartmentId"));
        dep.setName(rs.getString("DepName"));
        return dep;
    }

    private Seller instantiateSeller(ResultSet rs, Department dep) throws SQLException {
        Seller obj = new Seller();
        obj.setId(rs.getInt("Id"));
        obj.setName(rs.getString("Name"));
        obj.setEmail(rs.getString("Email"));
        obj.setBirthDate(rs.getDate("BirthDate"));
        obj.setBaseSalary(rs.getDouble("BaseSalary"));
        obj.setDepartment(dep);
        return obj;
    }

    @Override
    public List<Seller> findAll() {
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            pst = conn.prepareStatement("SELECT seller.*, department.Name AS DepName FROM seller INNER JOIN department ON seller.DepartmentId = department.Id ORDER BY Name");
            rs = pst.executeQuery();
            List<Seller> list = new ArrayList<>();
            Map<Integer, Department> map = new HashMap<>();

            while (rs.next()) {
                Department departmentobj = map.get(rs.getInt("DepartmentId"));
                if (departmentobj == null) {
                    departmentobj = instantiateDepartment(rs);
                }
                Seller sellerobj = instantiateSeller(rs, departmentobj);
                list.add(sellerobj);
            }
            return list;
        } catch (SQLException e) {
            throw new DBException(e.getMessage());

        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(pst);
        }


    }
}
