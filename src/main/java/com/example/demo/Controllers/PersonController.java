package com.example.demo.Controllers;

import com.example.demo.Services.PersonService;
import com.example.demo.data.vo.v1.PersonVO;
import com.example.demo.data.vo.v2.PersonVOV2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/person/v1")
public class PersonController {

    @Autowired
    private PersonService service;

    @GetMapping
    public List<PersonVO> findAll(){
        return service.findAll();
    }
    @GetMapping(value = "/{id}")
    public PersonVO findById(@PathVariable(value = "id") Long id){
        return service.findById(id);
    }

    @PostMapping
    public PersonVO create(@RequestBody PersonVO person){
        return service.create(person);
    }

    @PostMapping(value = "/v2")
    public PersonVOV2 createV2(@RequestBody PersonVOV2 person){
        return service.createV2(person);
    }

    @PutMapping
    public PersonVO update(@RequestBody PersonVO person){
        return service.update(person);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
