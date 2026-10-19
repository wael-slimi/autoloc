package com.example.autoloc.web.controller;

import com.example.autoloc.domain.Maintenance;
import com.example.autoloc.repository.MaintenanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/maintenances")
@RequiredArgsConstructor
public class MaintenanceController {

    private final MaintenanceRepository repo;

    @GetMapping
    public List<Maintenance> getAll() { return repo.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<Maintenance> getOne(@PathVariable Long id) {
        return repo.findById(id).map(ResponseEntity::ok)
                   .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Maintenance> create(@RequestBody Maintenance body) {
        return ResponseEntity.status(HttpStatus.CREATED).body(repo.save(body));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Maintenance> update(@PathVariable Long id, @RequestBody Maintenance body) {
        if (!repo.existsById(id)) return ResponseEntity.notFound().build();
        body.setIdMaintenance(id);
        return ResponseEntity.ok(repo.save(body));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!repo.existsById(id)) return ResponseEntity.notFound().build();
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
