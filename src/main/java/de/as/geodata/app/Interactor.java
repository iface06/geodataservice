
package de.as.geodata.app;


public interface Interactor<REQUEST, RESPONSE>{
    
    public RESPONSE apply(REQUEST r);

}
