package com.todocodeacademi.PruebaTecSupermercado.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DetalleVentaDTO {

    private Long id;
    private String nombreProd;
    private int cantProd;
    private Double precio;
    private Double subtotal;

}
