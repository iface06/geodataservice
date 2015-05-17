
package de.as.geodata.app.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class County implements PersitentEntity {

    @Id
    private int id;
    private String name;

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final County other = (County) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    
    
  
}
