package com.todocodeacademi.PruebaTecSupermercado.controller;

import com.todocodeacademi.PruebaTecSupermercado.dto.SucursalDTO;
import com.todocodeacademi.PruebaTecSupermercado.service.ISucursalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/sucursales")
public class SucursalController {

    @Autowired
    private ISucursalService sucursalService;

    @GetMapping
    public ResponseEntity<List<SucursalDTO>> traerSucursales(){
        return ResponseEntity.ok(sucursalService.traerSucursales());
    }

    @PostMapping
    public ResponseEntity<SucursalDTO> crearSucursal(@RequestBody SucursalDTO dto){

        SucursalDTO creada = sucursalService.crearSucursal(dto);
        return ResponseEntity.created(URI.create("/api/sucursales/"
                + creada.getId())).body(creada);

    }

    @PutMapping("/{id}")
    public ResponseEntity<SucursalDTO> actualizarSucursal(@PathVariable Long id,
                                                          @RequestBody SucursalDTO dto){
        return ResponseEntity.ok(sucursalService.actualizarScursal(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarSucursal(@PathVariable Long id){
        sucursalService.eliminarSucursal(id);
        return ResponseEntity.noContent().build();
    }
}
