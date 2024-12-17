package com.example.cardatabase.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Car {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    private String brand, model, color, registerNumber;
    private int car_year;
     private int price;

    public Car() {}
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner")
    @JsonIgnoreProperties("cars") // Avoid circular serialization if Owner has a reference to Car
    private Owner owner;
   /*@ManyToMany(mappedBy = "cars")
   private Set<Owner> owners;*/

    public Car(String brand, String model, String color, String registerNumber, int car_year, int price, Owner owner){
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.registerNumber = registerNumber;
        this.car_year = car_year;
        this.price = price;
        this.owner = owner;
    }
    //getter and setter
    public String getBrand() {
        return brand;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }
    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
    public String getRegisterNumber() {
        return registerNumber;
    }
    public void setRegisterNumber(String registerNumber) {
        this.registerNumber = registerNumber;
    }
    public int getCar_year() {
        return car_year;
    }
    public void setCar_year(int car_year) {
        this.car_year = car_year;
    }
    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }

    /*public Set<Owner> getOwners() {
        return owners;
    }
    public void setOwners(Set<Owner> owners) {
        this.owners = owners;
    }*/



    //Getter and setter
    public Owner getOwner() {
        return owner;
    }
    public void setOwner(Owner owner) {
        this.owner = owner;
    }
}


