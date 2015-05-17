
package de.as.geodata.app.city;

import de.as.geodata.app.Interactor;
import de.as.geodata.app.entities.City;
import de.as.geodata.persistence.CityDbResource;

public class FindCityByIdInteractor implements Interactor<Integer, City>{
    
    private CityDao dao = new CityDbResource();
    
    @Override
    public City apply(Integer cityId){
        City c = dao.findCityById(cityId);
        if(c == null){
            c = City.GHOST_TOWN;
        }
        return c;
    }

    protected void setDao(CityDao dao) {
        this.dao = dao;
    }

}
