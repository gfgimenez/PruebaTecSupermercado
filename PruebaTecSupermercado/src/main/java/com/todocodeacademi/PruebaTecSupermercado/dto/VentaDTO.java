package com.todocodeacademi.PruebaTecSupermercado.dto;

import com.todocodeacademi.PruebaTecSupermercado.model.Sucursal;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VentaDTO {

    //Datos de la venta
    private Long id;
    private LocalDate fecha;
    private String estado;

    //Datos de la sucursal
    private Long idSucursal;

    //lista de detalles
    private List<DetalleVentaDTO> detalle;

    //total de ventas
    private Double total;


}
