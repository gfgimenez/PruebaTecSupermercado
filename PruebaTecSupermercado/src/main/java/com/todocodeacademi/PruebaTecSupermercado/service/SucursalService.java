package com.todocodeacademi.PruebaTecSupermercado.service;

import com.todocodeacademi.PruebaTecSupermercado.dto.SucursalDTO;
import com.todocodeacademi.PruebaTecSupermercado.exception.NotFoundException;
import com.todocodeacademi.PruebaTecSupermercado.mapper.Mapper;
import com.todocodeacademi.PruebaTecSupermercado.model.Sucursal;
import com.todocodeacademi.PruebaTecSupermercado.repository.SucursalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SucursalService implements ISucursalService{

    @Autowired
    private SucursalRepository repo;

    @Override
    public List<SucursalDTO> traerSucursales() {
        return repo.findAll()
                .stream()
                .map(Mapper::toDTO)
                .toList();
    }

    @Override
    public SucursalDTO crearSucursal(SucursalDTO sucursalDto) {

        Sucursal suc = Sucursal.builder()
                .nombre(sucursalDto.getNombre())
                .direccion(sucursalDto.getDireccion())
                .build();

        return Mapper.toDTO(repo.save(suc));
    }

    @Override
    public SucursalDTO actualizarScursal(Long id, SucursalDTO sucursalDto) {
        Sucursal suc = repo.findById(id)
        .orElseThrow(() -> new NotFoundException("Sucursal no encontrada"));

        suc.setNombre(sucursalDto.getNombre());
        suc.setDireccion(sucursalDto.getDireccion());

        return Mapper.toDTO(repo.save(suc));
    }

    @Override
    public void eliminarSucursal(Long id) {

        if(!repo.existsById(id)){
            throw new NotFoundException("Sucursal no encontrada para eliminar");
        }

        repo.deleteById(id);

    }
}
