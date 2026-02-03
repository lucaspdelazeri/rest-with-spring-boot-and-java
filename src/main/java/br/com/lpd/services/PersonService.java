package br.com.lpd.services;

import br.com.lpd.exception.ResourceNotFoundException;
import br.com.lpd.model.Person;
import br.com.lpd.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    private final Logger logger = LoggerFactory.getLogger(PersonService.class);

    @Autowired
    private PersonRepository repository;

    public List<Person> findAll() {
        logger.info("Finding all Peoples");
        return repository.findAll();
    }

    public Person findById(long id) {
        logger.info("Finding a Person by id: #{}", id);

        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not Found Person for id: #" + id));
    }

    public Person update(Person person, long id) {
        logger.info("Updating a Person: {}", person);

        Person entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not Found Person for id: #" + id));

        person.setId(entity.getId());

        return repository.save(person);
    }

    public Person save(Person person) {
        logger.info("Creating a person: {}", person);
        person.setId(null);
        return repository.save(person);
    }

    public void delete(long id) {
        logger.info("Deleting a person by id: #{}", id);
        findById(id);
        repository.deleteById(id);
    }
}
