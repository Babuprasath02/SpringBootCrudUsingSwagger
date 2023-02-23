package com.kgisl.springdataquerymethods.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.kgisl.springdataquerymethods.model.Employee;
import com.kgisl.springdataquerymethods.repository.EmployeeRepository;



@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    public EmployeeRepository emprepository;

    @GetMapping("/getAll")
    public @ResponseBody ResponseEntity<List<Employee>> getAll(){
        return new ResponseEntity<>(emprepository.findAll(),HttpStatus.OK);
        
    }
    @PostMapping(value="/create",headers = "Accept=application/json")
    public ResponseEntity<Employee> create(@RequestBody Employee emp){
        Employee e = emprepository.save(emp);

        HttpHeaders head = new HttpHeaders();
        return new ResponseEntity<>(e,head,HttpStatus.CREATED);

    }
    @GetMapping(value="/getById/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Employee> getById(@PathVariable("id") int id){
        Employee emp = emprepository.findById(id) .orElseThrow(() -> new IllegalArgumentException("Not found"));
        if(emp==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
        return new ResponseEntity<>(emp,HttpStatus.OK);
    }
    @PutMapping(value="/update/{id}",headers = "Accept=application/json")
    public ResponseEntity<Employee> update(@PathVariable("id") int id, @RequestBody Employee emp){
        Employee e = emprepository.save(emp);
        return new ResponseEntity<>(e,HttpStatus.OK);

    }
    @DeleteMapping(value="/delete/{id}", headers = "Accept=application/json")
    public void deleteById(@PathVariable("id") int id){
        emprepository.deleteById(id);
    }

    
}
