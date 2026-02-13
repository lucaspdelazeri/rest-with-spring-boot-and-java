package br.com.lpd.hateoas;

import br.com.lpd.controllers.PersonController;
import br.com.lpd.dto.PersonDTO;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

/**
 *
 * @author lpd
 */
public class PersonLinkBuilder
{
    public static void addHateoasLinks(PersonDTO personDTO)
    {
        personDTO.add(
                WebMvcLinkBuilder.linkTo(
                        WebMvcLinkBuilder.methodOn(PersonController.class).getPerson(personDTO.getId()))
                        .withSelfRel()
                        .withType("GET"));
        personDTO.add(
                WebMvcLinkBuilder.linkTo(
                        WebMvcLinkBuilder.methodOn(PersonController.class).getAllPersons())
                        .withRel("findAll")
                        .withType("GET"));
        personDTO.add(
                WebMvcLinkBuilder.linkTo(
                        WebMvcLinkBuilder.methodOn(PersonController.class).addPerson(personDTO))
                        .withRel("create")
                        .withType("POST"));
        personDTO.add(
                WebMvcLinkBuilder.linkTo(
                        WebMvcLinkBuilder.methodOn(PersonController.class).updatePerson(personDTO, personDTO.getId()))
                        .withRel("update")
                        .withType("PUT"));
        personDTO.add(
                WebMvcLinkBuilder.linkTo(
                        WebMvcLinkBuilder.methodOn(PersonController.class).deletePerson(personDTO.getId()))
                        .withRel("delete")
                        .withType("DELETE"));

    }
}
