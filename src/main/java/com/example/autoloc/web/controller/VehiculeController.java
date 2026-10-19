package com.example.autoloc.web.controller;

import com.example.autoloc.domain.Vehicule;
import com.example.autoloc.repository.VehiculeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicules")
@RequiredArgsConstructor
public class VehiculeController {

    private final VehiculeRepository repo;

    @GetMapping
    public List<Vehicule> getAll() { return repo.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<Vehicule> getOne(@PathVariable Long id) {
        return repo.findById(id).map(ResponseEntity::ok)
                   .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Vehicule> create(@RequestBody Vehicule body) {
        return ResponseEntity.status(HttpStatus.CREATED).body(repo.save(body));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vehicule> update(@PathVariable Long id, @RequestBody Vehicule body) {
        if (!repo.existsById(id)) return ResponseEntity.notFound().build();
        body.setIdVehicule(id);
        return ResponseEntity.ok(repo.save(body));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!repo.existsById(id)) return ResponseEntity.notFound().build();
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
