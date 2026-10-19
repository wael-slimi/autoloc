package com.example.autoloc.web.controller;

import com.example.autoloc.domain.Employe;
import com.example.autoloc.repository.EmployeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employes")
@RequiredArgsConstructor
public class EmployeController {

    private final EmployeRepository repo;

    @GetMapping
    public List<Employe> getAll() { return repo.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<Employe> getOne(@PathVariable Long id) {
        return repo.findById(id).map(ResponseEntity::ok)
                   .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Employe> create(@RequestBody Employe body) {
        return ResponseEntity.status(HttpStatus.CREATED).body(repo.save(body));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employe> update(@PathVariable Long id, @RequestBody Employe body) {
        if (!repo.existsById(id)) return ResponseEntity.notFound().build();
        body.setIdEmploye(id);
        return ResponseEntity.ok(repo.save(body));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!repo.existsById(id)) return ResponseEntity.notFound().build();
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
