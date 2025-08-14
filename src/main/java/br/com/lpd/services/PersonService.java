package br.com.lpd.services;

import br.com.lpd.data.dto.PersonDTO;
import br.com.lpd.exception.ResourceNotFoundException;
import br.com.lpd.mapper.PersonMapper;
import br.com.lpd.model.Person;
import br.com.lpd.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    private Logger logger = LoggerFactory.getLogger(PersonService.class);

    @Autowired
    PersonRepository repository;

    public List<PersonDTO> findAll() {
        logger.info("Finding all Peoples");
        return PersonMapper.INSTANCE.toPersonDTOList(repository.findAll());
    }

    public PersonDTO findById(long id) {
        logger.info("Finding a Person by id: #{}", id);
        Person entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not Found Person for id: #" + id));

        return PersonMapper.INSTANCE.toPersonDTO(entity);
    }

    public PersonDTO update(PersonDTO person, long id) {
        logger.info("Updating a Person: {}", person);

        Person entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not Found Person for id: #" + id));

        if (!entity.getId().equals(person.getId()))
            throw new IllegalArgumentException("Don't can modify id of Person");

        return PersonMapper.INSTANCE.toPersonDTO(repository.save(entity));
    }

    public PersonDTO save(PersonDTO person) {
        logger.info("Creating a person{}", person);

        if( person.getId() != null && repository.findById(person.getId()).isPresent() )
            throw new IllegalArgumentException("Person already exists for id: #" + person.getId());

        return PersonMapper.INSTANCE.toPersonDTO(
                repository.save(
                        PersonMapper.INSTANCE.toPerson(person)
                )
        );
    }

    public void delete(long id) {
        logger.info("Deleting a person by id: #{}", id);

        PersonDTO entity = findById(id);
        repository.deleteById(entity.getId());
    }
}
