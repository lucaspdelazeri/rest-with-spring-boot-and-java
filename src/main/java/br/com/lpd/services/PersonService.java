package br.com.lpd.services;

import br.com.lpd.exception.ResourceNotFoundException;
import br.com.lpd.model.Person;
import br.com.lpd.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class PersonService {

    private Logger logger = Logger.getLogger(PersonService.class.getName());

    @Autowired
    PersonRepository repository;

    public List<Person> findAll() {
        logger.info("Finding all Peoples");
        return repository.findAll();
    }

    public Person findById(long id) {
        logger.info("Finding a Person by id: #" + id);
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not Found Person for id: " + id));
    }

    public Person update(Person person, long id) {
        logger.info("Updating a Person: " + person);

        Person entity = findById(id);
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());

        return repository.save(entity);
    }

    public Person save(Person person) {
        logger.info("Creating a person" + person);

        Person entity = null;

        if(person.getId() != null) {
            entity = findById(person.getId());
        }

        if( entity == null ) {
            return repository.save(person);
        }
        else  {
            throw new IllegalArgumentException("Person already exists!");
        }
    }

    public void delete(long id) {
        logger.info("Deleting a person by id: #" + id);

        Person entity = findById(id);
        repository.deleteById(entity.getId());
    }
}
