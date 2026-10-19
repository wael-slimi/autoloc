package com.example.autoloc.web.controller;

import com.example.autoloc.domain.Contrat;
import com.example.autoloc.repository.ContratRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contrats")
@RequiredArgsConstructor
public class ContratController {

    private final ContratRepository repo;

    @GetMapping
    public List<Contrat> getAll() { return repo.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<Contrat> getOne(@PathVariable Long id) {
        return repo.findById(id).map(ResponseEntity::ok)
                   .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Contrat> create(@RequestBody Contrat body) {
        return ResponseEntity.status(HttpStatus.CREATED).body(repo.save(body));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Contrat> update(@PathVariable Long id, @RequestBody Contrat body) {
        if (!repo.existsById(id)) return ResponseEntity.notFound().build();
        body.setIdContrat(id);
        return ResponseEntity.ok(repo.save(body));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!repo.existsById(id)) return ResponseEntity.notFound().build();
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
