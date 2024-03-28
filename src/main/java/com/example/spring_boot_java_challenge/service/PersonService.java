package com.example.spring_boot_java_challenge.service;

import com.example.spring_boot_java_challenge.domain.Person;
import com.example.spring_boot_java_challenge.domain.Ticket;
import com.example.spring_boot_java_challenge.model.PersonDTO;
import com.example.spring_boot_java_challenge.repos.PersonRepository;
import com.example.spring_boot_java_challenge.repos.TicketRepository;
import com.example.spring_boot_java_challenge.util.NotFoundException;
import com.example.spring_boot_java_challenge.util.ReferencedWarning;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    private final PersonRepository personRepository;
    private final TicketRepository ticketRepository;

    public PersonService(final PersonRepository personRepository,
            final TicketRepository ticketRepository) {
        this.personRepository = personRepository;
        this.ticketRepository = ticketRepository;
    }

    public List<PersonDTO> findAll() {
        final List<Person> persons = personRepository.findAll(Sort.by("id"));
        return persons.stream()
                .map(person -> mapToDTO(person, new PersonDTO()))
                .toList();
    }

    public PersonDTO get(final String id) {
        return personRepository.findById(id)
                .map(person -> mapToDTO(person, new PersonDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Person create(final PersonDTO personDTO) {
        final Person person = new Person();
        mapToEntity(personDTO, person);
        // person.setId(personDTO.getId());
        // return personRepository.save(person).getId();
        return personRepository.save(person);
    }

    public void update(final String id, final PersonDTO personDTO) {
        final Person person = personRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(personDTO, person);
        personRepository.save(person);
    }

    public void delete(final String id) {
        personRepository.deleteById(id);
    }

    private PersonDTO mapToDTO(final Person person, final PersonDTO personDTO) {
        //personDTO.setId(person.getId());
        personDTO.setName(person.getName());
        personDTO.setEmail(person.getEmail());
        return personDTO;
    }

    private Person mapToEntity(final PersonDTO personDTO, final Person person) {
        person.setName(personDTO.getName());
        person.setEmail(personDTO.getEmail());
        return person;
    }

    public boolean idExists(final String id) {
        return personRepository.existsByIdIgnoreCase(id);
    }

    public ReferencedWarning getReferencedWarning(final String id) {
        final ReferencedWarning referencedWarning = new ReferencedWarning();
        final Person person = personRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        final Ticket personIdTicket = ticketRepository.findFirstByPersonId(person);
        if (personIdTicket != null) {
            referencedWarning.setKey("person.ticket.personId.referenced");
            referencedWarning.addParam(personIdTicket.getId());
            return referencedWarning;
        }
        return null;
    }

}
