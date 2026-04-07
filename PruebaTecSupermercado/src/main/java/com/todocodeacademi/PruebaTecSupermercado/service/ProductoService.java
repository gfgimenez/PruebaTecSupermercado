package com.todocodeacademi.PruebaTecSupermercado.service;

import com.todocodeacademi.PruebaTecSupermercado.dto.ProductoDTO;
import com.todocodeacademi.PruebaTecSupermercado.exception.NotFoundException;
import com.todocodeacademi.PruebaTecSupermercado.mapper.Mapper;
import com.todocodeacademi.PruebaTecSupermercado.model.Producto;
import com.todocodeacademi.PruebaTecSupermercado.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService implements IProductoService{

    @Autowired
    private ProductoRepository repo;

    @Override
    public List<ProductoDTO> traerProductos() {
        return repo.findAll().stream().map(Mapper::toDTO).toList();
    }

    @Override
    public ProductoDTO crearProducto(ProductoDTO productoDto) {
        Producto prod = Producto.builder()
                .nombre(productoDto.getNombre())
                .categoria(productoDto.getCategoria())
                .precio(productoDto.getPrecio())
                .cantidad(productoDto.getCantidad())
                .build();

        return Mapper.toDTO(repo.save(prod));
    }

    @Override
    public ProductoDTO actualizarProducto(Long id, ProductoDTO productoDto) {

        // vamos a buscar si existe ese producto
        Producto prod = repo.findById(id)
        .orElseThrow(() -> new NotFoundException("Producto no encontrado"));

        prod.setNombre(productoDto.getNombre());
        prod.setCategoria(productoDto.getCategoria());
        prod.setCantidad(productoDto.getCantidad());
        prod.setPrecio(productoDto.getPrecio());

        return Mapper.toDTO(repo.save(prod));
    }

    @Override
    public void eliminarProducto(Long id) {

        if(!repo.existsById(id)){
            throw new NotFoundException("Producto no encontrado para eliminar");
        }

        repo.deleteById(id);

    }
}
