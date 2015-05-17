
package de.as.geodata.app.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ZipCode implements PersitentEntity{

    private int id;
    private int districtId;
    private District district;
    private String zipCode;

    @Id
    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDistrictId() {
        return districtId;
    }

    public void setDistrictId(int districtId) {
        this.districtId = districtId;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}
