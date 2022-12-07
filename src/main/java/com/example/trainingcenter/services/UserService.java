package com.example.trainingcenter.services;

import com.example.trainingcenter.models.Employee;
import com.example.trainingcenter.models.Role;
import com.example.trainingcenter.models.User;
import com.example.trainingcenter.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.stream.StreamSupport;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    // registration
    public boolean createUser(User user, Employee employee){
        if (userRepository.findByEmail(user.getEmail()) != null) return false;

        user.setActive(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.addInRoles(Role.EMPLOYEE);

        employeeService.save(employee);
        user.setEmployee(StreamSupport.stream(employeeService.getAll().spliterator(),
                false).toList().get(StreamSupport.stream(employeeService.getAll().spliterator(),
                false).toList().size() - 1));

        userRepository.save(user);

        return true;
    }
}
