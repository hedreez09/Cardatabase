package com.example.cardatabase;

import com.example.cardatabase.domain.Car;
import com.example.cardatabase.domain.CarRepository;
import com.example.cardatabase.domain.Owner;
import com.example.cardatabase.domain.OwnerRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.SQLOutput;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

@SpringBootApplication
public class CardatabaseApplication {
   // private static final Logger logger =
            //(Logger) LoggerFactory.getLogger(CardatabaseApplication.class);
   private static final org.slf4j.Logger logger = LoggerFactory.getLogger(CardatabaseApplication.class);

   @Autowired
   private CarRepository repository;
   @Autowired
   private OwnerRepository orepository;
    public static void main(String[] args) {
        // After adding this comment the application is restarted
        SpringApplication.run(CardatabaseApplication.class, args);
    }
    @Bean
    CommandLineRunner runner(){
        return args -> {
            //owner Objects
            Owner owner1 = new Owner("John" , "Johnson");
            Owner owner2 = new Owner("Mary" , "Robinson");
            orepository.save(owner1);
            orepository.save(owner2);

            Set<Owner> owners1 = new HashSet<>();
            owners1.add(owner1);

            Set<Owner> owners2 = new HashSet<>();
            owners2.add(owner2);

            Set<Owner> owners3 = new HashSet<>();
            owners3.add(owner2);

            Set<Owner> owners4 = new HashSet<>();
            owners4.add(owner1);
            // Add car object with link to owner and save to DB
            Car car = new Car("Ford", "Mustang", "Red",
                    "ADF-1121", 2017, 59000, owners1);
            repository.save(car);
            car = new Car("Nissan", "Leaf", "White",
                    "SSJ-3002", 2014, 29000, owners2);
            repository.save(car);
            car = new Car("Toyota", "Prius", "Silver",
                    "KKO-0212", 2018, 39000, owners2);
            repository.save(car);
            car = new Car("Toyota", "Tundra", "Blue",
                    "KKO-0212", 2018, (90000), owners1);
            repository.save(car);
        };
    }

}
