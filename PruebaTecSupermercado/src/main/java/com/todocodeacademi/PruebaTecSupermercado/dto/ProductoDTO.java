package com.todocodeacademi.PruebaTecSupermercado.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductoDTO {

    private Long id;
    private String nombre;
    private String categoria;
    private Double precio;
    private int cantidad;

}
