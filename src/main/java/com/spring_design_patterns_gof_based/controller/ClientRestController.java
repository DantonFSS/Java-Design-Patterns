package com.spring_design_patterns_gof_based.controller;

import com.spring_design_patterns_gof_based.model.ClientModel;
import com.spring_design_patterns_gof_based.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class ClientRestController {

    @Autowired
    private ClientService clientService;

    @GetMapping("/users")
    public ResponseEntity<Iterable<ClientModel>> searchAll() {
        return ResponseEntity.ok(clientService.searchAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientModel> searchById(@PathVariable Long id) {
        return ResponseEntity.ok(clientService.searchForId(id));
    }
    @PostMapping("/insert")
    public ResponseEntity<ClientModel> insert(@RequestBody ClientModel clientModel) {
        clientService.insert(clientModel);
        return ResponseEntity.ok(clientModel);
    }
    @PutMapping("/{id}/update")
    public ResponseEntity<ClientModel> update(@PathVariable Long id, @RequestBody ClientModel clientModel) {
        clientService.update(id, clientModel);
        return ResponseEntity.ok(clientModel);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ClientModel> delete(@PathVariable Long id) {
        clientService.delete(id);
        return ResponseEntity.ok().build();
    }
}
