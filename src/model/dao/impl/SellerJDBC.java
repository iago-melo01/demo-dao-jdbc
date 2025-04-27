package model.dao.impl;

import db.DB;
import db.DBException;
import model.dao.DaoFactory;
import model.dao.SellerDAO;
import model.entities.Department;
import model.entities.Seller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class SellerJDBC implements SellerDAO {
    private Connection conn = null;

    public SellerJDBC(Connection conn) {
        this.conn = conn;
    }

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

            return null; // inacabado
        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(pst);
        }
        //| ParseException e
        //dtf.parse(rs.getString("BirthDate"))
    }
    private Department instantiateDepartment(ResultSet rs) throws SQLException{
        Department dep = new Department();
        dep.setId(rs.getInt("DepartmentId"));
        dep.setName(rs.getString("DepName"));
        return dep;
    }

    private Seller instantiateSeller(ResultSet rs, Department dep) throws SQLException{
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
        return List.of();
    }
}
