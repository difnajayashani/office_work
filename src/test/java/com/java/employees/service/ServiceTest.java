package com.java.employees.service;


import com.java.employees.dao.EmployeeRepository;
import com.java.employees.model.Employee;
import com.java.employees.services.EmployeeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class ServiceTest {

    @InjectMocks
    EmployeeService service;
    @Mock
    EmployeeRepository repository;

    public void init(){
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void testFindEmployee(){
        List<Employee> list=new ArrayList<Employee>();
        Employee e1=new Employee("John","John");
        Employee e2=new Employee("Difna","Difna");
        Employee e3=new Employee("Imesha","Imesha");

        list.add(e1);
        list.add(e2);
        list.add(e3);

        Mockito.when(repository.findAll()).thenReturn(list);//here we mock
        List<Employee> emplist= service.findAll();//here we use the actual method
        assertEquals(3,emplist.size());


    }

    public void testCreateandSave(){
        Employee e1=new Employee();
        service.save(e1);
        Mockito.verify(repository,Mockito.times(1)).save(e1);
    }

}
