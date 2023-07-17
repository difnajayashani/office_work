package com.java.employees.dao;

import com.java.employees.model.Employee;
import org.assertj.core.api.Assert;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)//to clear the dat once test is done
public class DevTest {

    @Autowired
    EmployeeRepository employeeRepository;

    @Test
    public void  testCreate(){
        Employee employee=new Employee("admin","manager");
        employeeRepository.save(employee);
        Iterable<Employee> emp= employeeRepository.findAll();
        Assertions.assertThat(emp).extracting(Employee::getFirstName).containsOnly("admin");

        employeeRepository.deleteAll();
        Assertions.assertThat(employeeRepository.findAll()).isEmpty();

    }


}
