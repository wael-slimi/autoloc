package com.example.autoloc.web.controller;

import com.example.autoloc.domain.Agence;
import com.example.autoloc.repository.AgenceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/agences")
@RequiredArgsConstructor
public class AgenceController {

    private final AgenceRepository repo;

    @GetMapping
    public List<Agence> getAll() { return repo.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<Agence> getOne(@PathVariable Long id) {
        return repo.findById(id).map(ResponseEntity::ok)
                   .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Agence> create(@RequestBody Agence body) {
        return ResponseEntity.status(HttpStatus.CREATED).body(repo.save(body));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Agence> update(@PathVariable Long id, @RequestBody Agence body) {
        if (!repo.existsById(id)) return ResponseEntity.notFound().build();
        body.setIdAgence(id);
        return ResponseEntity.ok(repo.save(body));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!repo.existsById(id)) return ResponseEntity.notFound().build();
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
