package com.example.trainingcenter.repositories;

import com.example.trainingcenter.models.Location;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends CrudRepository<Location, Long> {
    List<Location> findByNumber(int number);
}
