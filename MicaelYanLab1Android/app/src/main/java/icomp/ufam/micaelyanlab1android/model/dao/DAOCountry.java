package icomp.ufam.micaelyanlab1android.model.dao;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;
import java.util.List;


@Table(name="countries")
public class DAOCountry extends Model {

    @Column(name="name")
    private String name;

    public String getName() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }

    public DAOCountry() {
        super();
    }

    public static List<DAOCountry> getCountries() {
        return new Select().from(DAOCountry.class).execute();
    }

    // Will be used by the ArrayAdapter in the ListView
    @Override
    public String toString() {
        return name;
    }
}

