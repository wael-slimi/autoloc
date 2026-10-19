package com.example.autoloc.repository;

import com.example.autoloc.domain.Maintenance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaintenanceRepository extends JpaRepository<Maintenance, Long> {}
