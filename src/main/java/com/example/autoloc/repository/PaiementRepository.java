package com.example.autoloc.repository;

import com.example.autoloc.domain.Paiement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaiementRepository extends JpaRepository<Paiement, Long> {}
