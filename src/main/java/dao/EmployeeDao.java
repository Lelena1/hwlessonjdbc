package dao;

import model.Employee;

import java.util.List;

public interface EmployeeDao {

    void addEmployee(Employee employee);

    Employee findEmployeeById(int id);

    List<Employee> getAllEmployees();

    void updateEmployeeById(int id, Employee employee);

    void deleteEmployeeById(int id);
}
