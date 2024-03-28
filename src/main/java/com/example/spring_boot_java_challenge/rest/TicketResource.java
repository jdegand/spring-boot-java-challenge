package com.example.spring_boot_java_challenge.rest;

import com.example.spring_boot_java_challenge.model.TicketDTO;
import com.example.spring_boot_java_challenge.service.TicketService;
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
@RequestMapping(value = "/api/tickets", produces = MediaType.APPLICATION_JSON_VALUE)
public class TicketResource {

    private final TicketService ticketService;

    public TicketResource(final TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping
    public ResponseEntity<List<TicketDTO>> getAllTickets() {
        return ResponseEntity.ok(ticketService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TicketDTO> getTicket(@PathVariable(name = "id") final String id) {
        return ResponseEntity.ok(ticketService.get(id));
    }

    @PostMapping
    public ResponseEntity<String> createTicket(@RequestBody @Valid final TicketDTO ticketDTO) {
        final String createdId = ticketService.create(ticketDTO);
        return new ResponseEntity<>('"' + createdId + '"', HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateTicket(@PathVariable(name = "id") final String id,
            @RequestBody @Valid final TicketDTO ticketDTO) {
        ticketService.update(id, ticketDTO);
        return ResponseEntity.ok('"' + id + '"');
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTicket(@PathVariable(name = "id") final String id) {
        ticketService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
