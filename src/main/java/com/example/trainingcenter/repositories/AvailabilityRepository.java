package com.example.trainingcenter.repositories;

import com.example.trainingcenter.models.Availability;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvailabilityRepository extends CrudRepository<Availability, Long> {
}
