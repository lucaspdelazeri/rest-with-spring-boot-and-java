package br.com.lpd.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class PersonDTOV2 implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String firstName;
    private String lastName;
    private String address;
    private LocalDate birthDay;
    private String gender;

    public PersonDTOV2() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(LocalDate birthDay) {
        this.birthDay = birthDay;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof PersonDTOV2 personDTOV2)) return false;
        return Objects.equals(id, personDTOV2.id) && Objects.equals(firstName, personDTOV2.firstName) && Objects.equals(lastName, personDTOV2.lastName) && Objects.equals(address, personDTOV2.address) && Objects.equals(birthDay, personDTOV2.birthDay) && Objects.equals(gender, personDTOV2.gender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, address, birthDay, gender);
    }
}
