package atm.entity;

import javax.persistence.*;

@Entity
@Table(name = "ATM_Location")
public class ATM {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ATM_ID")
    private int atmID;

    @Column(name = "Location")
    private String location;

    public int getAtmID() {
        return atmID;
    }

    public void setAtmID(int atmID) {
        this.atmID = atmID;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
