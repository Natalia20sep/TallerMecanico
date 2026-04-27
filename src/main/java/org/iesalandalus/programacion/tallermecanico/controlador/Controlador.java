package org.iesalandalus.programacion.tallermecanico.controlador;

import org.iesalandalus.programacion.tallermecanico.modelo.Modelo;
import org.iesalandalus.programacion.tallermecanico.vista.Vista;
import org.iesalandalus.programacion.tallermecanico.vista.eventos.Evento;



import java.util.Objects;

public class Controlador implements IControlador {
    private final Modelo modelo;
    private final Vista vista;

    public Controlador(Modelo modelo, Vista vista) {
        Objects.requireNonNull(modelo, "El modelo no puede ser nulo.");
        Objects.requireNonNull(vista, "La vista no puede ser nula.");
        this.modelo = modelo;
        this.vista = vista;
    }
    @Override
    public void comenzar() {
        modelo.comenzar();
        vista.comenzar();
    }
    @Override
    public void terminar() {
        vista.terminar();
        modelo.terminar();
    }

    @Override
    public void actualizar(Evento evento) {
        vista.getGestorEventos().notificar(evento);
    }
}