package br.com.lpd.controllers.v2;

import br.com.lpd.dto.PersonDTOV2;
import br.com.lpd.mapper.PersonMapper;
import br.com.lpd.model.Person;
import br.com.lpd.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController(value = "personControllerV2")
@RequestMapping("/v2/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping(
            path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<PersonDTOV2> getPerson(@PathVariable("id") long id) {
        PersonDTOV2 dto = PersonMapper.INSTANCE.toPersonDTOV2(personService.findById(id));
        return ResponseEntity.ok(dto);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PersonDTOV2>> getAllPersons() {
        List<PersonDTOV2> dtos = PersonMapper.INSTANCE.toPersonDTOListV2(personService.findAll());
        return ResponseEntity.ok(dtos);
    }

    @PostMapping(
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<PersonDTOV2> addPerson(@RequestBody PersonDTOV2 dto) {
        Person person = PersonMapper.INSTANCE.toPersonV2(dto);
        PersonDTOV2 saved = PersonMapper.INSTANCE.toPersonDTOV2(personService.save(person));
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping(
            path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<PersonDTOV2> updatePerson(@RequestBody PersonDTOV2 dto, @PathVariable("id") long id) {
        Person person = PersonMapper.INSTANCE.toPersonV2(dto);
        PersonDTOV2 updated = PersonMapper.INSTANCE.toPersonDTOV2(personService.update(person, id));
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping(
            path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> deletePerson(@PathVariable("id") long id) {
        personService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
