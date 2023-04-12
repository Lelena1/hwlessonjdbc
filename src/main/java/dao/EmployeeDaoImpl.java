package dao;

import config.ConfigConnection;
import model.City;
import model.Employee;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDaoImpl implements EmployeeDao {

    private final ConfigConnection configConnect = new ConfigConnection();

    @Override
    public void addEmployee(Employee employee) {
        try (
                PreparedStatement statement = configConnect.getPreparedStatement(
                        "INSERT INTO employee (first_name,last_name,gender,age,city_id) " +
                                "VALUES((?),(?),(?),(?),(?))");) {

            statement.setString(1, employee.getFirst_name());
            statement.setString(2, employee.getLast_name());
            statement.setString(3, employee.getGender());
            statement.setInt(4, employee.getAge());
            statement.setInt(5, employee.getCity().getCity_id());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Employee findEmployeeById(int id) {
        Employee employee = new Employee(15,"Sergey", "Rubtsov", "man", 36,new City(7,"Omsk"));
        try (
                PreparedStatement statement = configConnect.getPreparedStatement(
                        "SELECT * FROM employee " +
                                "INNER JOIN city ON employee.city_id=city.city_id WHERE id=(?)")) {

            statement.setInt(1, id);
            statement.executeQuery();
            ResultSet resultSet = statement.getResultSet();

            while (resultSet.next()) {

                employee.setId(resultSet.getInt(1));
                employee.setFirst_name(resultSet.getString("first_name"));
                employee.setLast_name(resultSet.getString("last_name"));
                employee.setGender(resultSet.getString("gender"));
                employee.setAge(resultSet.getInt(5));
                employee.setCity(new City((resultSet.getInt("city_id")), resultSet.getString("city_name")));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }

    @Override
    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        try (
                PreparedStatement statement = configConnect.getPreparedStatement(
                        "SELECT * FROM employee INNER JOIN city " +
                                "ON employee.city_id=city.city_id")) {
            statement.executeQuery();
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                employees.add(new Employee(resultSet.getInt("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("gender"),
                        resultSet.getInt("age"),
                        new City(resultSet.getInt("city_id"),
                                resultSet.getString("city_name"))));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return employees;
    }

    @Override
    public void updateEmployeeById(int id, Employee employee) {
        try (
                PreparedStatement statement = configConnect.getPreparedStatement(
                        "UPDATE employee SET first_name =(?),last_name=(?)," +
                                "gender=(?),age=(?),city_id=(?) " +
                                "WHERE id=(?)")) {

            statement.setString(1, employee.getFirst_name());
            statement.setString(2, employee.getLast_name());
            statement.setString(3, employee.getGender());
            statement.setInt(4, employee.getAge());
            statement.setInt(5, employee.getCity().getCity_id());
            statement.setInt(6, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deleteEmployeeById(int id) {
        try (
                PreparedStatement statement = configConnect.getPreparedStatement(
                        "DELETE FROM employee WHERE id=(?)")) {

            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
