package com.example.cardatabase.domain;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

//import static jdk.internal.jrtfs.JrtFileAttributeView.AttrID.size;

public interface CarRepository extends CrudRepository<Car, Long>, PagingAndSortingRepository<Car, Long> {
    /*int page = 10;

    @Query("select c from Car c where c.brand like %?1")
    List<Car> findByBrandEndsWith(String brand);*/

}
