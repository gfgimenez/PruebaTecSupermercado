package com.todocodeacademi.PruebaTecSupermercado.exception;

public class NotFoundException extends RuntimeException{

    public NotFoundException(String mensaje){

        super(mensaje);
    }
}
