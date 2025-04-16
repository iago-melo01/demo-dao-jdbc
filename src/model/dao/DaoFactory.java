package model.dao;

import model.dao.impl.DepartmentJDBC;
import model.dao.impl.SellerJDBC;

public class DaoFactory {
    public SellerDAO createSellerDAO(){
        return new SellerJDBC();
    }

    public DepartmentDAO createDepartmentDAO(){
        return new DepartmentJDBC();
    }
}
