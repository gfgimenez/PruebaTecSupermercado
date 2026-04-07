package com.todocodeacademi.PruebaTecSupermercado.mapper;

import com.todocodeacademi.PruebaTecSupermercado.dto.DetalleVentaDTO;
import com.todocodeacademi.PruebaTecSupermercado.dto.ProductoDTO;
import com.todocodeacademi.PruebaTecSupermercado.dto.SucursalDTO;
import com.todocodeacademi.PruebaTecSupermercado.dto.VentaDTO;
import com.todocodeacademi.PruebaTecSupermercado.model.DetalleVenta;
import com.todocodeacademi.PruebaTecSupermercado.model.Producto;
import com.todocodeacademi.PruebaTecSupermercado.model.Sucursal;
import com.todocodeacademi.PruebaTecSupermercado.model.Venta;

import java.util.stream.Collectors;
import java.util.stream.Stream;



public class Mapper {

    // Mapeo de Producto a ProductoDTO
    public static ProductoDTO toDTO(Producto p){
        if(p==null) return null;

        return ProductoDTO.builder()
                .id(p.getId())
                .nombre(p.getNombre())
                .categoria(p.getCategoria())
                .precio(p.getPrecio())
                .cantidad(p.getCantidad())
                .build();

    }

    // Mapeo de Venta a VentaDTO
    public static VentaDTO toDTO(Venta v){
        if (v==null) return null;

        var detalle = v.getDetalle().stream().map(det->
                DetalleVentaDTO.builder()
                        .id(det.getProd().getId())
                        .nombreProd(det.getProd().getNombre())
                        .cantProd(det.getCantProd())
                        .precio(det.getPrecio())
                        .subtotal(det.getPrecio() * det.getCantProd())
                        .build()
        ).collect(Collectors.toList());

        var total = detalle.stream() //Stream<DetalleVentaDTO>
                .map(DetalleVentaDTO::getSubtotal) //Stream<Double>DetalleVentaDTO
                .reduce(0.0, Double::sum);

        return VentaDTO.builder()
                .id(v.getId())
                .fecha(v.getFecha())
                .idSucursal(v.getSucursal().getId())
                .estado(v.getEstado())
                .detalle(detalle)
                .total(total)
                .build();

    }


    // Mapeo de Sucursal a SucursalDTO
    public static SucursalDTO toDTO(Sucursal s){
        if(s==null) return null;

        return SucursalDTO.builder()
                .id(s.getId())
                .nombre(s.getNombre())
                .direccion(s.getDireccion())
                .build();
    }
}
