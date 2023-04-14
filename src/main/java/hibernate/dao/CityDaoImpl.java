package hibernate.dao;

import hibernate.entity.City;
import hibernate.exception.CityListIsEmptyException;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class CityDaoImpl implements CityDao {
    public EntityManager entityManager = Persistence
            .createEntityManagerFactory("myPersistenceUnit")
            .createEntityManager();

    @Override
    public City addCity(City city) {
        entityManager.getTransaction().begin();
        City cityFromDB = entityManager.merge(city);
        entityManager.getTransaction().commit();

        return cityFromDB;
    }

    @Override
    public City findCityById(int cityId) {
        return entityManager.find(City.class, cityId);
    }

    @Override
    public List<City> getAllCities() {
        String jpqlQuery = "SELECT e from City e";

        TypedQuery<City> query = entityManager.createQuery(jpqlQuery, City.class);
        List<City> cities = query.getResultList();
        if (cities.isEmpty()) {
            throw new CityListIsEmptyException("Список городов пуст");
        } else {

            return cities;
        }
    }

    @Override
    public void updateCityById(int cityId, City city) {

        entityManager.getTransaction().begin();
        city.setCityId(cityId);
        entityManager.merge(city);
        entityManager.getTransaction().commit();
    }

    @Override
    public void deleteCityById(City city) {

        entityManager.getTransaction().begin();
        entityManager.remove(city);
        entityManager.getTransaction().commit();
    }
}
