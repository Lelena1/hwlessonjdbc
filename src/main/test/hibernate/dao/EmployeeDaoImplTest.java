package hibernate.dao;

import hibernate.entity.Employee;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EmployeeDaoImplTest {
// Для ДЗ по Hibernate.Entity
    private final EmployeeDao dao = new EmployeeDaoImpl();

    @Test
    public void shouldSaveTheEmployee() {
//        Employee employee = new Employee(57, "Leon", "Limonov", "man", 56, 5);

//        dao.addEmployee(employee);

//        assertTrue(dao.getAllEmployees().contains(employee));
    }

    @Test
    void shouldFindEmployeeById() {

//        Employee employee = new Employee(57, "Leon", "Limonov", "man", 56, 5);

//        dao.addEmployee(employee);
//        Employee employeeFromDB = dao.findEmployeeById(57);
//
//        assertEquals(employee, employeeFromDB);
    }

    @Test
    void shouldUpdateEmployeeById() {
//        Employee employee = new Employee(58, "Leon", "Limonov", "man", 56, 5);
//
//        dao.addEmployee(employee);
//        employee.setAge(28);
//        dao.updateEmployeeById(58, employee);
//        Employee updatedEmployee = dao.findEmployeeById(58);
//
//        assertEquals(updatedEmployee.getAge(), 28);
    }
}