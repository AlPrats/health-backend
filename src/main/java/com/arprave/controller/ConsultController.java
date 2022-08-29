package com.arprave.controller;

import com.arprave.dto.ConsultDTO;
import com.arprave.dto.ConsultListExamDTO;
import com.arprave.dto.FilterConsultDTO;
import com.arprave.exception.ModelNotFoundException;
import com.arprave.model.Consult;
import com.arprave.model.Exam;
import com.arprave.service.IConsultService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/consults")
public class ConsultController {

    @Autowired
    private IConsultService service;

    @Autowired
    private ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<ConsultDTO>> findAll() {
        List<ConsultDTO> list = service.findAll()
                .stream()
                .map(p -> mapper.map(p, ConsultDTO.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsultDTO> findById(@PathVariable("id") Integer id) {
        ConsultDTO dtoResponse;
        Consult obj = service.findById(id);
        if (obj == null) {
            throw new ModelNotFoundException("ID NOT FOUND: " + id);
        } else {
            dtoResponse = mapper.map(obj, ConsultDTO.class);
        }
        return new ResponseEntity<>(dtoResponse, HttpStatus.OK);
    }

    /*@PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody ConsultDTO dto) {
        Consult p = service.save(mapper.map(dto, Consult.class));
        //localhost:8080/consults/3
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(p.getIdConsult()).toUri();
        return ResponseEntity.created(location).build();
    }*/

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody ConsultListExamDTO dto) {
        Consult consult = mapper.map(dto.getConsult(), Consult.class);
        List<Exam> exams = mapper.map(dto.getLstExam(), new TypeToken<List<Exam>>() {}.getType());

        Consult obj = service.saveTransactional(consult, exams);

        //localhost:8080/consults/3
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdConsult()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping
    public ResponseEntity<Consult> update(@Valid @RequestBody ConsultDTO dto) {
        Consult obj = service.findById(dto.getIdConsult());
        if (obj == null) {
            throw new ModelNotFoundException("ID NOT FOUND: " + dto.getIdConsult());
        }

        return new ResponseEntity<>(service.update(mapper.map(dto, Consult.class)), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Integer id) {
        Consult obj = service.findById(id);
        if (obj == null) {
            throw new ModelNotFoundException("ID NOT FOUND: " + id);
        } else {
            service.delete(id);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/hateoas/{id}")
    public EntityModel<ConsultDTO> findByIdHateoas(@PathVariable("id") Integer id){
        ConsultDTO dtoResponse;
        Consult obj = service.findById(id);

        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND: " + id);
        } else {
            dtoResponse = mapper.map(obj, ConsultDTO.class);
        }

        //localhost:8080/consults/{id}
        EntityModel<ConsultDTO> resource = EntityModel.of(dtoResponse);
        WebMvcLinkBuilder link1 = linkTo(methodOn(this.getClass()).findById(id));
        WebMvcLinkBuilder link2 = linkTo(methodOn(this.getClass()).findAll());
        resource.add(link1.withRel("consult-info1"));
        resource.add(link2.withRel("consult-full"));

        return resource;
    }

    @GetMapping("/search/others")
    public ResponseEntity<List<ConsultDTO>> searchByOthers(@RequestBody FilterConsultDTO filterDTO){
        List<Consult> consults = service.search(filterDTO.getDni(), filterDTO.getFullname());
        List<ConsultDTO> consultsDTO = mapper.map(consults, new TypeToken<List<ConsultDTO>>() {}.getType());

        return new ResponseEntity<>(consultsDTO, HttpStatus.OK);
    }

    @GetMapping("/search/date")
    public ResponseEntity<List<ConsultDTO>> searchByDates(@RequestParam(value = "date1") String date1, @RequestParam(value = "date2") String date2){
        List<Consult> consults = service.searchByDates(LocalDateTime.parse(date1), LocalDateTime.parse(date2));
        List<ConsultDTO> consultsDTO = mapper.map(consults, new TypeToken<List<ConsultDTO>>() {}.getType());

        return new ResponseEntity<>(consultsDTO, HttpStatus.OK);
    }

}
