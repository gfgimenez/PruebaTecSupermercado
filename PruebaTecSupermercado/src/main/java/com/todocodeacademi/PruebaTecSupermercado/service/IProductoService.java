package com.todocodeacademi.PruebaTecSupermercado.service;

import com.todocodeacademi.PruebaTecSupermercado.dto.ProductoDTO;
import com.todocodeacademi.PruebaTecSupermercado.dto.VentaDTO;

import java.util.List;

public interface IProductoService {

    List<ProductoDTO> traerProductos();
    ProductoDTO crearProducto(ProductoDTO productoDto);
    ProductoDTO actualizarProducto(Long id, ProductoDTO productoDto);
    void eliminarProducto(Long id);

}
