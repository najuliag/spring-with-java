package com.example.demo.Controllers;

import com.example.demo.Model.Person;
import com.example.demo.Services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService service;


    @GetMapping
    public List<Person> findAll(){
        return service.findAll();
    }
    @GetMapping(value = "/{id}")
    public Person findById(@PathVariable(value = "id") Long id){
        return service.findById(id);
    }

    @PostMapping
    public Person create(@RequestBody Person person){
        return service.create(person);
    }

    @PutMapping
    public Person update(@RequestBody Person person){
        return service.update(person);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
