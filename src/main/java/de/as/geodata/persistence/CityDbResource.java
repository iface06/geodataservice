
package de.as.geodata.persistence;

import de.as.geodata.Database;
import de.as.geodata.app.city.CityDao;
import de.as.geodata.app.entities.City;
import java.util.Collections;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;


public class CityDbResource implements CityDao{
    
    private Database db = new Database();

    @Override
    public City findCityById(int cityId) {
        City city = db.findById(City.class, cityId);
        
        return city;
        
    }

    @Override
    public List<City> findCities(String query) {
        Criteria crit = db.createCriteriaFor(City.class);
        crit.add(Restrictions.like("name", query + "%"));
        return crit.list();
    }

}
