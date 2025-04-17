package model.dao;

import model.dao.impl.DepartmentJDBC;
import model.dao.impl.SellerJDBC;

public class DaoFactory {
    public static SellerDAO createSellerDAO(){
        return new SellerJDBC();
    }

    public static DepartmentDAO createDepartmentDAO(){
        return new DepartmentJDBC();
    }
}
