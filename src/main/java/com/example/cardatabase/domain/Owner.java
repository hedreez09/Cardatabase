package com.example.cardatabase.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Owner {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long ownerId;
    private String firstname, lastname;
    public Owner() {}

   /* @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "car_owner", joinColumns = { @JoinColumn(name =
            "ownerid") }, inverseJoinColumns = { @JoinColumn(name = "id") })
    private Set<Car> cars = new HashSet<Car>(0);*/

    @OneToMany(cascade = CascadeType.ALL, mappedBy="owner")
//    @JsonIgnore //Properties("cars") // Avoid circular serialization if Owner has a reference to Car
//    @JsonBackReference
   // @JsonManagedReference
    private List<Car> cars;
    public Owner(String firstname, String lastname) {
        super();
        this.firstname = firstname;
        this.lastname = lastname;
    }
    public long getOwnerId() {
        return ownerId;
    }
    public void setOwnerId(long ownerId) {
        this.ownerId = ownerId;
    }
    public String getFirstname() {
        return firstname;
    }
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

   /* public Set<Car> getCars() {
        return cars;
    }
    public void setCars(Set<Car> cars) {
        this.cars = cars;
    }*/
    public List<Car> getCars() {
        return cars;
    }
    public void setCars(List<Car> cars) {
        this.cars = cars;
    }
}
