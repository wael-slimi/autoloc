package com.example.autoloc.service;

import com.example.autoloc.domain.Vehicule;

public interface IVehiculeService {
    Vehicule affecterEquipement(Long idVehicule, Long idEquipement);
    Vehicule desaffecterEquipement(Long idVehicule, Long idEquipement);
}
