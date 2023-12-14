package com.example.demo.Controllers;

import com.example.demo.Services.PersonService;
import com.example.demo.Util.MediaType;
import com.example.demo.DataVO.PersonVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/person/v1")
public class PersonController {

    @Autowired
    private PersonService service;

    @GetMapping(produces = {MediaType.APPLICAYION_JSON, MediaType.APPLICAYION_XML, MediaType.APPLICAYION_YML})
    public List<PersonVO> findAll(){
        return service.findAll();
    }

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICAYION_JSON, MediaType.APPLICAYION_YML})
    public PersonVO findById(@PathVariable(value = "id") Long id){
        return service.findById(id);
    }

    @PostMapping(produces = {MediaType.APPLICAYION_JSON, MediaType.APPLICAYION_XML, MediaType.APPLICAYION_YML},
            consumes = {MediaType.APPLICAYION_JSON, MediaType.APPLICAYION_XML, MediaType.APPLICAYION_YML})
    public PersonVO create(@RequestBody PersonVO person){
        return service.create(person);
    }

    @PutMapping(produces = {MediaType.APPLICAYION_JSON, MediaType.APPLICAYION_XML, MediaType.APPLICAYION_YML},
    consumes = {MediaType.APPLICAYION_JSON, MediaType.APPLICAYION_XML, MediaType.APPLICAYION_YML})
    public PersonVO update(@RequestBody PersonVO person){
        return service.update(person);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
