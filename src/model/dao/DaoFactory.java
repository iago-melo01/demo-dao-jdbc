package model.dao;

import model.dao.impl.DepartmentJDBC;
import model.dao.impl.SellerJDBC;
import db.DB;
public class DaoFactory {
    public static SellerDAO createSellerDAO(){
        return new SellerJDBC(DB.getConnection());
    }

    public static DepartmentDAO createDepartmentDAO(){
        return new DepartmentJDBC(DB.getConnection());
    }
}
