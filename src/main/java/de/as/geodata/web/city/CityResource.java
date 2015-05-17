package de.as.geodata.web.city;

import de.as.geodata.app.Interactor;
import de.as.geodata.app.city.FindCityByIdInteractor;
import de.as.geodata.app.entities.City;
import java.io.IOException;
import org.restlet.data.Status;
import org.restlet.ext.jackson.JacksonRepresentation;
import org.restlet.representation.EmptyRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

public class CityResource extends ServerResource {

    private Interactor<Integer, City> findCityById = new FindCityByIdInteractor();
    
    @Get
    public Representation getCity() throws IOException {
        
        City city;
        Representation representation;
        String id = (String) getRequestAttributes().get("id");
        Integer cityId = parseToInteger(id);

        if (cityId > 0) {
            city = findCity(cityId);
            representation = new JacksonRepresentation(city);
        } else {
            representation = new EmptyRepresentation();
            setStatus(Status.CLIENT_ERROR_NOT_FOUND);
        }

        return representation;
    }

    private City findCity(Integer cityId) {
        return findCityById.apply(cityId);
    }

    private Integer parseToInteger(String id) throws NumberFormatException {
        if (id != null) {
            id = id.trim();
            return Integer.valueOf(id);
        }
        
        return 0;
    }

    protected void setInteractor(Interactor<Integer, City> interactor) {
        this.findCityById = interactor;
    }
    
    

}
