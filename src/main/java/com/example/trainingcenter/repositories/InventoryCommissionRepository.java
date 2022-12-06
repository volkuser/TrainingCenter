package com.example.trainingcenter.repositories;

import com.example.trainingcenter.models.InventoryCommission;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryCommissionRepository extends CrudRepository<InventoryCommission, Long> {
}
