package icomp.ufam.micaelyanlab1android.model.dao;

import android.support.annotation.NonNull;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;
import java.util.List;

import icomp.ufam.micaelyanlab1android.model.bean.Country;


@Table(name = "Countries")
public class DAOCountry extends Model {

    @Column(name = "Name", unique = true)
    private String name;

    @Column(name = "Latitude")
    private Double latitude;

    @Column(name = "Longitude")
    private Double longitude;


    public String getName() {
        return name;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setName(String name) {
    	this.name = name;
    }

    public void setLatitude(Double latitude) {
    	this.latitude = latitude;
	}

	public void setLongitude(Double longitude) {
    	this.longitude = longitude;
	}

    public DAOCountry() {
        super();
    }

    public DAOCountry(Country country) {
        super();
        this.name = country.getName();
        this.latitude = country.getLatlng().get(0);
        this.longitude = country.getLatlng().get(1);
    }


    public static List<DAOCountry> getCountries() {
        return new Select().from(DAOCountry.class).execute();
    }

    @Override
    public String toString() {
        return name;
    }
}

