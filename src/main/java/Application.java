import config.ConfigConnection;
import dao.EmployeeDao;
import dao.EmployeeDaoImpl;
import model.City;
import model.Employee;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Application {

    private static final EmployeeDao employeeDao = new EmployeeDaoImpl();
    private static ConfigConnection configConnect = new ConfigConnection();

    public static void main(String[] args) throws SQLException {
        System.out.println("Task1");
        try (
                PreparedStatement statement = configConnect.getPreparedStatement(
                        "SELECT * FROM employee WHERE id=(?)")) {
            statement.setInt(1, 2);
            final ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {

                String name = "Name: " + resultSet.getString("first_name");
                String surname = "Surname: " + resultSet.getString("last_name");
                String gender = "Gender: " + resultSet.getString("gender");
                Integer age = resultSet.getInt(5);
                int cityId = resultSet.getInt(6);

                System.out.println(name);
                System.out.println(surname);
                System.out.println(gender);
                System.out.println(age);
                System.out.println(cityId);

                System.out.println("Task2-1");
                Employee elena = new Employee(11, "Elena", "Luppol", "woman", 50, new City(1, "Donetsk"));
                employeeDao.addEmployee(elena);
                System.out.println(elena);

                System.out.println("Task2-2");
                System.out.println(employeeDao.findEmployeeById(8));

                System.out.println("Task2-3");
                for (Employee employee : employeeDao.getAllEmployees()) {
                    System.out.println(employee);
                }

                System.out.println("Task2-4");
                Employee employee = new Employee(1, "Simon", "Somov", "man", 22,
                        new City(4, "Polozk"));
                employeeDao.updateEmployeeById(10, employee);

                System.out.println("Task2-5");
                employeeDao.deleteEmployeeById(17);
                employeeDao.deleteEmployeeById(18);
            }
        }
    }
}



