package hibernate.dao;

import hibernate.entity.City;
import lombok.Builder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Builder
class CityDaoImplTest {

    private final EmployeeDao employeeDao = new EmployeeDaoImpl();
    private final CityDao cityDao = new CityDaoImpl();

    @Test
    void shouldAddCity() {
        City city = City.builder()
                .cityId(108)
                .cityName("Vitebsk")
                .build();

        cityDao.addCity(city);

        assertTrue(cityDao.getAllCities().contains(city));
    }

    @Test
    void shouldFindCityById() {
        City city = City.builder()
                .cityId(108)
                .cityName("Vitebsk")
                .build();

        cityDao.findCityById(108);

        assertEquals(city.getCityId(), 108);
    }

    @Test
    void shouldUpdateCityById() {
        City city = City.builder()
                .cityId(108)
                .cityName("Vitebsk")
                .build();

        City cityFromDB = cityDao.addCity(city);
        cityFromDB.setCityName("Orsha");
        cityDao.updateCityById(108, cityFromDB);

        assertEquals(cityDao.findCityById(108).getCityName(), "Orsha");
    }

    @Test
    void deleteCityById() {
        City city = City.builder()
                .cityName("Pskov")
                .build();

        cityDao.deleteCityById(city);

        assertFalse(cityDao.getAllCities().contains("Pskov"));
    }
}