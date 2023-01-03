package com.example.trainingcenter.repositories;

import com.example.trainingcenter.models.Location;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LocationRepository extends CrudRepository<Location, Long> {
    List<Location> findByNumber(int number);
}
