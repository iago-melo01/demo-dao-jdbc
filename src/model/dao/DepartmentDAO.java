package model.dao;
import model.entities.Department;
import model.entities.Seller;

import java.util.List;

public interface DepartmentDAO {
    void insert(Department obj);
    void update(Department obj, Integer id);
    void deleteId(Integer id);
    Department findById(Integer id);
    List<Department> findAll();
}
