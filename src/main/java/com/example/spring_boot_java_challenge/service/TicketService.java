package com.example.spring_boot_java_challenge.service;

import com.example.spring_boot_java_challenge.domain.Person;
import com.example.spring_boot_java_challenge.domain.Ticket;
import com.example.spring_boot_java_challenge.model.TicketDTO;
import com.example.spring_boot_java_challenge.repos.PersonRepository;
import com.example.spring_boot_java_challenge.repos.TicketRepository;
import com.example.spring_boot_java_challenge.util.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;
    private final PersonRepository personRepository;

    public TicketService(final TicketRepository ticketRepository,
            final PersonRepository personRepository) {
        this.ticketRepository = ticketRepository;
        this.personRepository = personRepository;
    }

    public List<TicketDTO> findAll() {
        final List<Ticket> tickets = ticketRepository.findAll(Sort.by("id"));
        return tickets.stream()
                .map(ticket -> mapToDTO(ticket, new TicketDTO()))
                .toList();
    }

    public TicketDTO get(final String id) {
        return ticketRepository.findById(id)
                .map(ticket -> mapToDTO(ticket, new TicketDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public String create(final TicketDTO ticketDTO) {
        final Ticket ticket = new Ticket();
        mapToEntity(ticketDTO, ticket);
        ticket.setId(ticketDTO.getId());
        return ticketRepository.save(ticket).getId();
    }

    public void update(final String id, final TicketDTO ticketDTO) {
        final Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(ticketDTO, ticket);
        ticketRepository.save(ticket);
    }

    public void delete(final String id) {
        ticketRepository.deleteById(id);
    }

    private TicketDTO mapToDTO(final Ticket ticket, final TicketDTO ticketDTO) {
        ticketDTO.setId(ticket.getId());
        ticketDTO.setOrigin(ticket.getOrigin());
        ticketDTO.setDestination(ticket.getDestination());
        ticketDTO.setDepartureDate(ticket.getDepartureDate());
        ticketDTO.setTime(ticket.getTime());
        //ticketDTO.setDuration(ticket.getDuration());
        ticketDTO.setPersonId(ticket.getPersonId() == null ? null : ticket.getPersonId().getId());
        return ticketDTO;
    }

    private Ticket mapToEntity(final TicketDTO ticketDTO, final Ticket ticket) {
        ticket.setOrigin(ticketDTO.getOrigin());
        ticket.setDestination(ticketDTO.getDestination());
        ticket.setDepartureDate(ticketDTO.getDepartureDate());
        ticket.setTime(ticketDTO.getTime());
        //ticket.setDuration(ticketDTO.getDuration());
        final Person personId = ticketDTO.getPersonId() == null ? null
                : personRepository.findById(ticketDTO.getPersonId())
                        .orElseThrow(() -> new NotFoundException("personId not found"));
        ticket.setPersonId(personId);
        return ticket;
    }

    public boolean idExists(final String id) {
        return ticketRepository.existsByIdIgnoreCase(id);
    }

}
