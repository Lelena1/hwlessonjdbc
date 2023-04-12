package hibernate.dao;


import hibernate.entity.Employee;
import hibernate.exception.EmployeeListIsEmptyException;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class EmployeeDaoImpl implements EmployeeDao {
    public EntityManager entityManager = Persistence
            .createEntityManagerFactory("myPersistenceUnit")
            .createEntityManager();


    @Override
    public Employee addEmployee(Employee employee) {

        entityManager.getTransaction().begin();
        Employee employeeFromDB = entityManager.merge(employee);
        entityManager.getTransaction().commit();

        return employeeFromDB;
    }

    @Override
    public Employee findEmployeeById(int id) {

        return entityManager.find(Employee.class, id);

    }

    @Override
    public List<Employee> getAllEmployees() {

        String jpqlQuery = "SELECT e from Employee e";

        TypedQuery<Employee> query = entityManager.createQuery(jpqlQuery, Employee.class);
        List<Employee> employees = query.getResultList();
        if (employees.isEmpty()) {
            throw new EmployeeListIsEmptyException("Список сотрудников пуст");
        } else {

            return employees;
        }
    }

    @Override
    public void updateEmployeeById(int id, Employee employee) {
        entityManager.getTransaction().begin();
        employee.setId(id);
        entityManager.merge(employee);
        entityManager.getTransaction().commit();

    }

    @Override
    public void deleteEmployeeById(Employee employee) {

        entityManager.getTransaction().begin();
        entityManager.remove(employee);
        entityManager.getTransaction().commit();
    }
}
