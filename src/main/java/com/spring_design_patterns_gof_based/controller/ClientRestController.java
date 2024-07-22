package com.spring_design_patterns_gof_based.controller;

import com.spring_design_patterns_gof_based.model.Client;
import com.spring_design_patterns_gof_based.service.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public class ClientRestController {

    private ClientService clientService;

    @GetMapping
    public ResponseEntity<Iterable<Client>> searchAll() {
        return ResponseEntity.ok(clientService.searchAll());
    }
    @GetMapping
    public ResponseEntity<Client> searchById(@PathVariable Long id) {
        return ResponseEntity.ok(clientService.searchForId(id));
    }
    @PostMapping
    public ResponseEntity<Client> insert(@RequestBody Client client) {
        clientService.insert(client);
        return ResponseEntity.ok(client);
    }
    @PutMapping
    public ResponseEntity<Client> update(@PathVariable Long id,@RequestBody  Client client) {
        clientService.update(id, client);
        return ResponseEntity.ok(client);
    }
    @DeleteMapping
    public ResponseEntity<Client> delete(@PathVariable Long id) {
        clientService.delete(id);
        return ResponseEntity.ok().build();
    }
}
