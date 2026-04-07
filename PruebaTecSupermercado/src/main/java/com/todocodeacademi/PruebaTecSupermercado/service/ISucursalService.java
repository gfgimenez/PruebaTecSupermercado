package com.todocodeacademi.PruebaTecSupermercado.service;

import com.todocodeacademi.PruebaTecSupermercado.dto.SucursalDTO;

import java.util.List;

public interface ISucursalService {

    List<SucursalDTO> traerSucursales();
    SucursalDTO crearSucursal(SucursalDTO sucursalDto);
    SucursalDTO actualizarScursal(Long id, SucursalDTO sucursalDto);
    void eliminarSucursal(Long id);

}
