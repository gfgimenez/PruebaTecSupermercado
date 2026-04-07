package com.todocodeacademi.PruebaTecSupermercado.service;

import com.todocodeacademi.PruebaTecSupermercado.dto.DetalleVentaDTO;
import com.todocodeacademi.PruebaTecSupermercado.dto.VentaDTO;
import com.todocodeacademi.PruebaTecSupermercado.exception.NotFoundException;
import com.todocodeacademi.PruebaTecSupermercado.mapper.Mapper;
import com.todocodeacademi.PruebaTecSupermercado.model.DetalleVenta;
import com.todocodeacademi.PruebaTecSupermercado.model.Producto;
import com.todocodeacademi.PruebaTecSupermercado.model.Venta;
import com.todocodeacademi.PruebaTecSupermercado.model.Sucursal;
import com.todocodeacademi.PruebaTecSupermercado.repository.ProductoRepository;
import com.todocodeacademi.PruebaTecSupermercado.repository.SucursalRepository;
import com.todocodeacademi.PruebaTecSupermercado.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VentaService implements IVentaService{

    @Autowired
    private VentaRepository ventaRepo;

    @Autowired
    private ProductoRepository productoRepo;

    @Autowired
    private SucursalRepository sucursalRepo;

    @Override
    public List<VentaDTO> traerVentas() {

        List<Venta> ventas = ventaRepo.findAll();
        List<VentaDTO> ventasDTO = new ArrayList<>();

        VentaDTO dto;
        for(Venta v : ventas){
            dto = Mapper.toDTO(v);
            ventasDTO.add(dto);
        }

        return ventasDTO;
    }

    @Override
    public VentaDTO crearVenta(VentaDTO ventaDto) {

        // Validaciones
        if (ventaDto == null) throw new RuntimeException("VentaDTO es null");
        if (ventaDto.getIdSucursal() == null) throw new RuntimeException("Debe inicar la Sucursal");
        if (ventaDto.getDetalle() == null || ventaDto.getDetalle().isEmpty())
            throw new NotFoundException("VentaDTO es null");

        // Buscar la suscursal
        Sucursal suc =sucursalRepo.findById(ventaDto.getIdSucursal()).orElse(null);

        // Crear la Venta
        Venta vent = new Venta();
        vent.setFecha(ventaDto.getFecha());
        vent.setEstado(ventaDto.getEstado());
        vent.setSucursal(suc);
        vent.setTotal(ventaDto.getTotal());

         // La lista de Detalles
        // --> Aca están los productos
        List<DetalleVenta> detalles = new ArrayList<>();
        Double totalCalculado=0.0;

        for (DetalleVentaDTO detDTO : ventaDto.getDetalle()){
            // Buscar producto por id (si tu detDTO usa id como id de producto)
            Producto p = productoRepo.findByNombre(detDTO.getNombreProd()).orElse(null);
            if (p==null){
                throw new RuntimeException("Producto encontrado: " + detDTO.getNombreProd());
            }

        //Crear Detalle
        DetalleVenta detalleVenta =new DetalleVenta();
            detalleVenta.setProd(p);
            detalleVenta.setVenta(vent);
            detalleVenta.setPrecio(detDTO.getPrecio());
            detalleVenta.setCantProd(detDTO.getCantProd());

            detalles.add(detalleVenta);
            totalCalculado = totalCalculado+(detDTO.getPrecio()*detDTO.getCantProd());
        }

        // Seteamos la lista de detalle venta
        vent.setDetalle(detalles);

        // Guardamos en la BD
        vent = ventaRepo.save(vent);

        // Mapeo de salida
        VentaDTO ventaSalida = Mapper.toDTO(vent);

        return ventaSalida;
    }

    @Override
    public VentaDTO actualizarVenta(Long id, VentaDTO ventaDto) {

        // Buscar si la venta existe para actualizarla
        Venta v = ventaRepo.findById(id).orElse(null);
        if(v==null) throw new RuntimeException("Venta no encontrada");

        if (ventaDto.getFecha()!=null){
            v.setFecha(ventaDto.getFecha());
        }
        if (ventaDto.getEstado()!=null){
            v.setEstado(ventaDto.getEstado());
        }
        if (ventaDto.getTotal()!=null){
            v.setTotal(ventaDto.getTotal());
        }
        if (ventaDto.getIdSucursal()!=null){
            Sucursal suc= sucursalRepo.findById(ventaDto.getIdSucursal()).orElse(null);
            if (suc == null) throw new NotFoundException("Sucursal no encntrada");
            v.setSucursal(suc);
        }

        ventaRepo.save(v);

        VentaDTO ventaSalida=Mapper.toDTO(v);

        return ventaSalida;
    }

    @Override
    public void eliminarVenta(Long id) {

        Venta v = ventaRepo.findById(id).orElse(null);
        if(v==null) throw new RuntimeException("Venta no encontrada");
        ventaRepo.delete(v);
    }
}
