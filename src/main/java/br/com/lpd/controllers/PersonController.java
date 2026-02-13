package br.com.lpd.controllers;

import br.com.lpd.dto.PersonDTO;
import br.com.lpd.hateoas.PersonLinkBuilder;
import br.com.lpd.mapper.PersonMapper;
import br.com.lpd.model.Person;
import br.com.lpd.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping(
            path = "/{id}",
            produces = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_YAML_VALUE
            }    )
    public ResponseEntity<PersonDTO> getPerson(@PathVariable("id") long id) {
        PersonDTO dto = PersonMapper.INSTANCE.toPersonDTO(personService.findById(id));
        PersonLinkBuilder.addHateoasLinks(dto);
        return ResponseEntity.ok(dto);
    }

    @GetMapping(
            produces = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_YAML_VALUE
            }    )
    public ResponseEntity<List<PersonDTO>> getAllPersons() {
        List<PersonDTO> dtos = PersonMapper.INSTANCE.toPersonDTOList(personService.findAll());
        dtos.forEach(PersonLinkBuilder::addHateoasLinks);
        return ResponseEntity.ok(dtos);
    }

    @PostMapping(
            produces = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_YAML_VALUE
            },
            consumes = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_YAML_VALUE
            }
    )
    public ResponseEntity<PersonDTO> addPerson(@RequestBody PersonDTO dto) {
        Person person = PersonMapper.INSTANCE.toPerson(dto);
        PersonDTO saved = PersonMapper.INSTANCE.toPersonDTO(personService.save(person));
        PersonLinkBuilder.addHateoasLinks(saved);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping(
            path = "/{id}",
            produces = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_YAML_VALUE
            },
            consumes = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_YAML_VALUE
            }
    )
    public ResponseEntity<PersonDTO> updatePerson(@RequestBody PersonDTO dto, @PathVariable("id") long id) {
        Person person = PersonMapper.INSTANCE.toPerson(dto);
        PersonDTO updated = PersonMapper.INSTANCE.toPersonDTO(personService.update(person, id));
        PersonLinkBuilder.addHateoasLinks(updated);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping(
            path = "/{id}",
            produces = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_YAML_VALUE
            }
    )
    public ResponseEntity<?> deletePerson(@PathVariable("id") long id) {
        personService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
