package hibernate.app;

import hibernate.dao.EmployeeDao;
import hibernate.dao.EmployeeDaoImpl;
import hibernate.entity.Employee;
import hibernate.exception.EmployeeListIsEmptyException;
import hibernate.exception.NotExistInDBException;


public class Application {
    static EmployeeDao employeeDao = new EmployeeDaoImpl();


    public static void main(String[] args) {

        createNewEmployee(
                new Employee("Sergey", "Rubtsov", "man", 36, 1));

        System.out.println(readEmployeeFromDBByID(10));

        readAllEmployeesFromBD();

        Employee olga = new Employee("Olga", "Reutova", "woman", 22, 3);
        updateEmployee(19, olga);

        deleteEmployeeById(new Employee(32));
    }

    static void createNewEmployee(Employee employee) {
        employeeDao.addEmployee(employee);
    }

    static Object readEmployeeFromDBByID(int id) {
        try {
            return employeeDao.findEmployeeById(id);
        } catch (NotExistInDBException e) {
            return e.getMessage();
        }
    }

    static void readAllEmployeesFromBD() {
        try {
            for (Employee employee : employeeDao.getAllEmployees()) {
                System.out.println(employee);
            }
        } catch (EmployeeListIsEmptyException e) {
            System.out.println(e.getMessage());
        }
    }

    static void updateEmployee(int id, Employee employee) {
        employeeDao.updateEmployeeById(id, employee);
    }

    static void deleteEmployeeById(Employee employee) {
        employeeDao.deleteEmployeeById(employeeDao.findEmployeeById(employee.getId()));
    }
}
