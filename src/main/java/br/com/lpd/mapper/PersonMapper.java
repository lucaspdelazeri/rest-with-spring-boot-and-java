package br.com.lpd.mapper;

import br.com.lpd.dto.PersonDTO;
import br.com.lpd.dto.PersonDTOV2;
import br.com.lpd.model.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PersonMapper {

    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

    //v1

    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "lastName", source = "lastName")
    @Mapping(target = "address", source = "address")
    @Mapping(target = "gender", source = "gender")
    Person toPerson(PersonDTO personDTO);

    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "lastName", source = "lastName")
    @Mapping(target = "address", source = "address")
    @Mapping(target = "gender", source = "gender")
    List<Person> toPersonList(List<PersonDTO> personDTOList);

    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "lastName", source = "lastName")
    @Mapping(target = "address", source = "address")
    @Mapping(target = "gender", source = "gender")
    PersonDTO toPersonDTO(Person person);

    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "lastName", source = "lastName")
    @Mapping(target = "address", source = "address")
    @Mapping(target = "gender", source = "gender")
    List<PersonDTO> toPersonDTOList(List<Person> person);

    // v2

    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "lastName", source = "lastName")
    @Mapping(target = "birthDay", source = "birthDay")
    @Mapping(target = "address", source = "address")
    @Mapping(target = "gender", source = "gender")
    Person toPersonV2(PersonDTOV2 personDTOV2);

    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "lastName", source = "lastName")
    @Mapping(target = "birthDay", source = "birthDay")
    @Mapping(target = "address", source = "address")
    @Mapping(target = "gender", source = "gender")
    List<Person> toPersonListV2(List<PersonDTOV2> personDTOV2List);

    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "lastName", source = "lastName")
    @Mapping(target = "birthDay", source = "birthDay")
    @Mapping(target = "address", source = "address")
    @Mapping(target = "gender", source = "gender")
    PersonDTOV2 toPersonDTOV2(Person person);

    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "lastName", source = "lastName")
    @Mapping(target = "birthDay", source = "birthDay")
    @Mapping(target = "address", source = "address")
    @Mapping(target = "gender", source = "gender")
    List<PersonDTOV2> toPersonDTOListV2(List<Person> person);
}
