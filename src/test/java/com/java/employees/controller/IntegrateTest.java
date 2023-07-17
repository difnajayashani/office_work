package com.java.employees.controller;


import com.java.employees.controllers.EmployeeController;
import com.java.employees.model.Employee;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.xml.bind.ValidationException;

@ExtendWith(SpringExtension.class)
@SpringBootTest //used for integration testing ,not for unit testing
public class IntegrateTest {
    @Autowired
    EmployeeController controller;


    @Test
    public  void testCreateRead(){
        Employee e1=new Employee("admin","manager");
        Employee result=controller.create(e1);

        Iterable<Employee> employees=controller.read();
        Assertions.assertThat(employees).first().hasFieldOrPropertyWithValue("firstName","admin");

        controller.delete(result.getId());
        Assertions.assertThat(controller.read()).isEmpty();

    }

    @Test
    public void errorHandling(){
        Assertions.assertThatExceptionOfType(ValidationException.class)
                .isThrownBy(() -> controller.somethingIsWrong());
    }
}
