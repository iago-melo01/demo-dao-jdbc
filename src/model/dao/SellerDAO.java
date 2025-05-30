package model.dao;

import model.entities.Department;
import model.entities.Seller;

import java.util.List;

public interface SellerDAO {
    void insert(Seller obj);
    void update(Seller obj, Integer id);
    void deleteId(Integer id);
    Seller findById(Integer id);
    List<Seller> findAll();
    List<Seller> findByDepartment(Department department);
}
