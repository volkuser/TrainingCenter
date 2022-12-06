package com.example.trainingcenter.repositories;

import com.example.trainingcenter.models.TrainingCenter;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainingCenterRepository extends CrudRepository<TrainingCenter, Long> {
}
