package de.as.geodata;


import de.as.geodata.web.cities.CitiesResource;
import de.as.geodata.web.city.CityResource;
import java.util.HashSet;
import java.util.Set;
import org.hibernate.cfg.AnnotationConfiguration;
import org.restlet.Application;
import org.restlet.Component;
import org.restlet.Request;
import org.restlet.Response;
import org.restlet.Restlet;
import org.restlet.data.ChallengeScheme;
import org.restlet.data.Protocol;
import org.restlet.engine.application.CorsFilter;
import org.restlet.routing.Filter;
import org.restlet.routing.Router;
import org.restlet.security.ChallengeAuthenticator;
import org.restlet.security.MapVerifier;

public class GeodataApplication extends Application{

    public static void main(String[] args) throws Exception {
        
        initDatabaseConection();
        initAndStartWebserver();
        
    }

    private static void initAndStartWebserver() throws Exception {
        Component server = new Component();
        
        server.getServers().add(Protocol.HTTP, 8182);        
        
        server.getDefaultHost().attach("/geodata", new GeodataApplication());
        
        server.start();
        
    }
    
    @Override
    public Restlet createInboundRoot() {
        setServiceMetadata();
        
        Filter databaseSessionFilter = new Filter(getContext()) {

            Database db = new Database();
            
            @Override
            protected int beforeHandle(Request request, Response response) {
                db.openSession();
                db.beginTransaction();
                
                return 0;
            }

            @Override
            protected void afterHandle(Request request, Response response) {
                db.commitTransaction();
                db.closeSession();
            }
            
            
            
        };
        Router router = createRouter();
        CorsFilter corsFilter = new CorsFilter(getContext(), router);
        corsFilter.setAllowedCredentials(true);
        Set allowedOrigins = new HashSet();
        allowedOrigins.add("http://localhost:8080");
        corsFilter.setAllowedOrigins(allowedOrigins);
        
        databaseSessionFilter.setNext(corsFilter);
        
        //databaseSessionFilter -> corsFilter -> router
        return databaseSessionFilter;
    }

    private void setServiceMetadata() {
        setName("Geodata Service");
        setOwner("iface06");
    }

    private Router createRouter() {
        Router router = new Router(getContext());
        router.attach("/cities/{query}", CitiesResource.class);
        router.attach("/city/{id}", CityResource.class);
        return router;
    }

    private ChallengeAuthenticator createGuardFilter() {
        MapVerifier verifier = new MapVerifier();
        verifier.getLocalSecrets().put("geodata_default_user", "123456".toCharArray());
        ChallengeAuthenticator guard = new ChallengeAuthenticator(getContext(), ChallengeScheme.HTTP_BASIC, "Geo Data Service");
        guard.setVerifier(verifier);
        return guard;
    }

    private static void initDatabaseConection() {
        Database.SESSION_FACTORY = new AnnotationConfiguration()
                .configure()
                .buildSessionFactory();
    }

}
