package com.example.spring_boot_java_challenge.rest;

import com.example.spring_boot_java_challenge.domain.Person;
import com.example.spring_boot_java_challenge.model.PersonDTO;
import com.example.spring_boot_java_challenge.service.PersonService;
import com.example.spring_boot_java_challenge.util.ReferencedException;
import com.example.spring_boot_java_challenge.util.ReferencedWarning;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/people", produces = MediaType.APPLICATION_JSON_VALUE)
public class PersonResource {

    private final PersonService personService;

    public PersonResource(final PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public ResponseEntity<List<PersonDTO>> getAllPeople() {
        return ResponseEntity.ok(personService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonDTO> getPerson(@PathVariable(name = "id") final String id) {
        return ResponseEntity.ok(personService.get(id));
    }

    @PostMapping
    public ResponseEntity<String> createPerson(@RequestBody @Valid final PersonDTO personDTO) {
        final Person person = personService.create(personDTO);
        return new ResponseEntity<>('"' + person.getName() + '"', HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updatePerson(@PathVariable(name = "id") final String id,
            @RequestBody @Valid final PersonDTO personDTO) {
        personService.update(id, personDTO);
        return ResponseEntity.ok('"' + id + '"');
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable(name = "id") final String id) {
        final ReferencedWarning referencedWarning = personService.getReferencedWarning(id);
        if (referencedWarning != null) {
            throw new ReferencedException(referencedWarning);
        }
        personService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
