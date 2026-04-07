package com.todocodeacademi.PruebaTecSupermercado.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.jmx.export.annotation.ManagedAttribute;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class DetalleVenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Venta
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "ventaId")
    private Venta venta;

    //Producto
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "productoId")
    private Producto prod;
    private int cantProd;
    private Double precio;

}
