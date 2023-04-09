package hibernate.dao;


import hibernate.entity.Employee;

import java.util.List;

public interface EmployeeDao {


    Employee addEmployee(Employee employee);

    Employee findEmployeeById(int id);

    List<Employee> getAllEmployees();

    void updateEmployeeById(int id, Employee employee);

    void deleteEmployeeById(Employee employee);

}
