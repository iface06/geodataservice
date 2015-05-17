package de.as.geodata.web.cities;

import de.as.geodata.app.city.CityDao;
import de.as.geodata.app.entities.City;
import de.as.geodata.persistence.CityDbResource;
import java.util.List;
import org.restlet.ext.jackson.JacksonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

public class CitiesResource extends ServerResource {

    CityDao dao = new CityDbResource();

    @Get
    public Representation getCities() {
        String query = (String) getRequestAttributes().get("query");
        
        query = query.trim();

        List<City> cities = dao.findCities(query);

        return new JacksonRepresentation(cities);
    }

}
