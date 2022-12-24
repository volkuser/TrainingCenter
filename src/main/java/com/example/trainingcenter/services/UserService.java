package com.example.trainingcenter.services;

import com.example.trainingcenter.models.Employee;
import com.example.trainingcenter.models.Role;
import com.example.trainingcenter.models.User;
import com.example.trainingcenter.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.stream.StreamSupport;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final EmployeeService employeeService;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, EmployeeService employeeService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.employeeService = employeeService;
        this.passwordEncoder = passwordEncoder;
    }

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
