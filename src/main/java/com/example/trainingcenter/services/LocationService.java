package com.example.trainingcenter.services;

import com.example.trainingcenter.models.Location;
import com.example.trainingcenter.repositories.LocationRepository;
import com.example.trainingcenter.repositories.TrainingCenterRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LocationService {
    private final LocationRepository locationRepository;
    private final TrainingCenterRepository trainingCenterRepository;

    public LocationService(LocationRepository locationRepository, TrainingCenterRepository trainingCenterRepository) {
        this.locationRepository = locationRepository;
        this.trainingCenterRepository = trainingCenterRepository;
    }

    public Iterable<Location> getAll() { return locationRepository.findAll(); }

    public List<Location> getResultExactSearch(String query) {
        return locationRepository.findByNumber(Integer.parseInt(query));
    }

    public List<Location> getResultImpreciseSearch(String query) {
        List<Location> desired = new ArrayList<>();

        for (Location location : locationRepository.findAll())
            if (Integer.toString(location.getNumber()).contains(query)) desired.add(location);

        return desired;
    }

    public void save(Location location){
        locationRepository.save(location);
    }

    public Location getById(Long id){
        return locationRepository.findById(id).orElseThrow();
    }
    public void deleteById(Long id) { locationRepository.deleteById(id); }
}
