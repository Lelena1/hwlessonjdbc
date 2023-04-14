package hibernate.app;

import hibernate.dao.CityDao;
import hibernate.dao.CityDaoImpl;
import hibernate.dao.EmployeeDao;
import hibernate.dao.EmployeeDaoImpl;
import hibernate.entity.City;
import hibernate.entity.Employee;
import hibernate.exception.CityListIsEmptyException;
import hibernate.exception.EmployeeListIsEmptyException;
import hibernate.exception.NotExistInDBException;

import java.util.List;


public class Application {
    static EmployeeDao employeeDao = new EmployeeDaoImpl();
    static CityDao cityDao = new CityDaoImpl();


    public static void main(String[] args) {
        System.out.println("Homework Hibernate.Entity");

//        createNewEmployee(
//                new Employee("Sergey", "Rubtsov", "man", 36, 1));
//
//        System.out.println(readEmployeeFromDBByID(10));

//        readAllEmployeesFromBD();
//
//        Employee olga = new Employee("Olga", "Reutova", "woman", 22, 3);
//        updateEmployee(19, olga);
//
//        deleteEmployeeById(new Employee(32));

        System.out.println("Homework Hibernate.Настройка связей между таблицами");
        City poltava = City.builder()
                .cityName("Poltava")
                .build();
        createNewCity(poltava);

        City pskov = City.builder()
                .cityName("Pskov")
                .build();
        updateCity(57, pskov);

        deleteCityById(pskov);

        readCityFromDBByID(2);
        readAllCitiesFromBD();

        City madrid = City.builder()
                .cityName("Madrid")
                .employees(List.of())
                .build();
        createNewCity(madrid);

        Employee tim = Employee.builder()
                .firstName("Tim")
                .lastName("Timov")
                .gender("man")
                .age(34)
                .city(madrid)
                .build();
        Employee tom = Employee.builder()
                .firstName("Tom")
                .lastName("Tomov")
                .gender("man")
                .age(45)
                .city(madrid)
                .build();
        Employee taya = Employee.builder()
                .firstName("Taya")
                .lastName("Kozakova")
                .gender("woman")
                .age(29)
                .city(madrid)
                .build();

        madrid.setEmployees(List.of(tim, tom, taya));
        updateCity(53, madrid);
        Employee valya = Employee.builder()
                .firstName("Valya")
                .lastName("Fursova")
                .gender("woman")
                .age(25)
                .city(madrid)
                .build();

        employeeDao.updateEmployeeById(67, valya);

        deleteCityById(cityDao.findCityById(1));
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

    static void createNewCity(City city) {
        cityDao.addCity(city);
    }

    static Object readCityFromDBByID(int cityId) {
        try {
            return cityDao.findCityById(cityId);
        } catch (NotExistInDBException e) {
            return e.getMessage();
        }
    }

    static void readAllCitiesFromBD() {
        try {
            for (City city : cityDao.getAllCities()) {
                System.out.println(city);
            }
        } catch (CityListIsEmptyException e) {
            System.out.println(e.getMessage());
        }
    }

    static void updateCity(int cityId, City city) {
        cityDao.updateCityById(cityId, city);
    }

    static void deleteCityById(City city) {

        cityDao.deleteCityById(cityDao.findCityById(city.getCityId()));

    }
}
