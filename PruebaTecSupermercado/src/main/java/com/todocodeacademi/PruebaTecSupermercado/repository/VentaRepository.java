package com.todocodeacademi.PruebaTecSupermercado.repository;

import com.todocodeacademi.PruebaTecSupermercado.model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VentaRepository extends JpaRepository <Venta,Long> {
}
