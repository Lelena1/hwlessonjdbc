package hibernate.dao;

import hibernate.entity.City;

import java.util.List;

public interface CityDao {
    City addCity(City city);

    City findCityById(int cityId);

    List<City> getAllCities();

    void updateCityById(int cityId, City city);

    void deleteCityById(City city);
}
