package com.example.demo.Services;

import com.example.demo.Controllers.PersonController;
import com.example.demo.Exceptions.RequiredObjectIsNullException;
import com.example.demo.Exceptions.ResourceNotFound;
import com.example.demo.Mapper.DozerMapper;
import com.example.demo.Mapper.PersonMapper;
import com.example.demo.Model.Person;
import com.example.demo.Repositories.PersonRepository;
import com.example.demo.DataVO.PersonVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class PersonService {

    private Logger logger = Logger.getLogger(PersonService.class.getName());

    @Autowired
    PersonRepository repository;
    @Autowired
    PersonMapper mapper;

    public PersonVO findById(Long id){
        logger.info("Finding one person!");
        var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFound("No records found for this id."));
        PersonVO vo = DozerMapper.parseObject(entity, PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
        return vo;
    }
    public List<PersonVO> findAll(){
        logger.info("Finding all people!");
        var persons = DozerMapper.parseListObject(repository.findAll(), PersonVO.class);
        persons
                .stream()
                .forEach(p -> p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel()));
        return persons;
    }

    public PersonVO create(PersonVO person){

        if (person == null) throw new RequiredObjectIsNullException();

        logger.info("Creating one person!");
        var entity = DozerMapper.parseObject(person, Person.class);
        var vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
        return vo;

    }

    public PersonVO update(PersonVO person){

        if (person == null) throw new RequiredObjectIsNullException();

        logger.info("Updating one person!");

        Person entity = repository.findById(person.getKey())
                .orElseThrow(() -> new ResourceNotFound("No records found for this id."));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        var vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
        return vo;
    }

    public void delete(Long key){
        logger.info("Deleting one person!");

        Person entity = repository.findById(key)
                .orElseThrow(() -> new ResourceNotFound("No records found for this key."));
        repository.delete(entity);
    }

}
