package com.example.autoloc.service;

import com.example.autoloc.domain.Employe;

public interface IEmployeService {
    Employe desaffecterEmploye(Long idEmploye);
    Employe affecterEmploye(Long idEmploye, Long idAgence);
}
