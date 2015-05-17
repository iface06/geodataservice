
package de.as.geodata.app.city;

import de.as.geodata.app.entities.City;
import java.util.List;


public interface CityDao {

    public City findCityById(int cityId);

    public List<City> findCities(String query);

}
