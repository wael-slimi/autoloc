package com.example.autoloc.service;

import com.example.autoloc.domain.Agence;
import com.example.autoloc.domain.Vehicule;
import com.example.autoloc.repository.AgenceRepository;
import com.example.autoloc.repository.VehiculeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AgenceServiceImpl implements IAgenceService {

    private final AgenceRepository agenceRepository;
    private final VehiculeRepository vehiculeRepository;

    @Override
    @Transactional
    public Agence ajouterAgenceEtAffecterVehicules(Agence agence) {
        List<Vehicule> vehicules = new ArrayList<>(agence.getVehicules());
        agence.setVehicules(new ArrayList<>());

        Agence agenceSauvee = agenceRepository.save(agence);          // 1. enregistrer l'agence

        for (Vehicule vehicule : vehicules) {
            Vehicule vehiculeSauve = vehiculeRepository.save(vehicule); // 2. enregistrer le vehicule
            vehiculeSauve.setAgence(agenceSauvee);                      // 3. l'affecter a l'agence
            agenceSauvee.getVehicules().add(vehiculeSauve);
            vehiculeRepository.save(vehiculeSauve);
        }
        return agenceSauvee;
    }
}
