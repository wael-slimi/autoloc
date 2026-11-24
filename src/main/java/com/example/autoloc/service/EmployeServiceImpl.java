package com.example.autoloc.service;

import com.example.autoloc.domain.Agence;
import com.example.autoloc.domain.Employe;
import com.example.autoloc.repository.AgenceRepository;
import com.example.autoloc.repository.EmployeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EmployeServiceImpl implements IEmployeService {

    private final EmployeRepository employeRepository;
    private final AgenceRepository agenceRepository;

    @Override
    @Transactional
    public Employe desaffecterEmploye(Long idEmploye) {
        Employe employe = employeRepository.findById(idEmploye)
                .orElseThrow(() -> new RuntimeException("Employe introuvable : " + idEmploye));
        Agence ancienne = employe.getAgence();
        if (ancienne != null) {
            ancienne.getEmployes().remove(employe);
        }
        employe.setAgence(null);
        return employeRepository.save(employe);
    }

    @Override
    @Transactional
    public Employe affecterEmploye(Long idEmploye, Long idAgence) {
        Employe employe = employeRepository.findById(idEmploye)
                .orElseThrow(() -> new RuntimeException("Employe introuvable : " + idEmploye));
        Agence agence = agenceRepository.findById(idAgence)
                .orElseThrow(() -> new RuntimeException("Agence introuvable : " + idAgence));
        employe.setAgence(agence);
        agence.getEmployes().add(employe);
        return employeRepository.save(employe);
    }
}
