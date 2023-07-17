package com.java.employees.controller;

import com.java.employees.controllers.EmployeeController;
import com.java.employees.model.Employee;
import com.java.employees.services.EmployeeService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockingDetails;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

@ExtendWith(SpringExtension.class)
@WebMvcTest(EmployeeController.class)//cinmce controller connects views
public class UserController {

    @MockBean
    EmployeeService service;
    @Autowired
    MockMvc mockMvc;

    @Test
    public void testFindAll() throws Exception {
        Employee employee=new Employee("admin","manager");
        List<Employee> employees= Arrays.asList(employee);
        Mockito.when(service.findAll()).thenReturn(employees);

        mockMvc.perform(MockMvcRequestBuilders.get("/employee"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].firstName",Matchers.is("admin")));

    }


}
