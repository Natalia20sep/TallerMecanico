package org.iesalandalus.programacion.tallermecanico.modelo;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Revision;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.Clientes;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.Revisiones;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.Vehiculos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Modelo {
    private Clientes clientes;
    private Revisiones revisiones;
    private Vehiculos vehiculos;

    public Modelo() {
        comenzar();
    }

    public void comenzar() {
        clientes = new Clientes();
        revisiones = new Revisiones();
        vehiculos = new Vehiculos();
    }

    public void terminar() {
        System.out.println("Ya he terminado, eres perfecta xoxo.");
    }

    public void insertar(Cliente cliente) throws TallerMecanicoExcepcion {
        Objects.requireNonNull(cliente, "No se puede insertar un cliente nulo.");
        clientes.insertar(new Cliente(cliente));
    }

    public void insertar(Vehiculo vehiculo) throws TallerMecanicoExcepcion {
        Objects.requireNonNull(vehiculo, "No se puede insertar un vehículo nulo.");
        vehiculos.insertar(vehiculo);
    }

    public void insertar(Revision revision) throws TallerMecanicoExcepcion {
        Objects.requireNonNull(revision, "No se puede insertar una revisión nula.");
        Cliente cliente = clientes.buscar(revision.getCliente());
        Vehiculo vehiculo = vehiculos.buscar(revision.getVehiculo());
        revisiones.insertar(new Revision(cliente, vehiculo, revision.getFechaInicio()));
    }

    public Cliente buscar(Cliente cliente) {
        cliente = Objects.requireNonNull(clientes.buscar(cliente), "No se puede buscar un cliente nulo.");
        return new Cliente(cliente);     }

    public Vehiculo buscar(Vehiculo vehiculo) {
        vehiculo = Objects.requireNonNull(vehiculos.buscar(vehiculo), "No se puede buscar un vehículo nulo.");
        return vehiculo;
    }

    public Revision buscar(Revision revision) {
        revision = Objects.requireNonNull(revisiones.buscar(revision), "No se puede buscar una revisión nula.");
        return new Revision(revision);
    }

    public Cliente modificar(Cliente cliente, String nombre, String telefono) throws TallerMecanicoExcepcion {
        return new Cliente(clientes.modificar(cliente, nombre, telefono));
    }

    public Revision anadirHoras(Revision revision, int horas) throws TallerMecanicoExcepcion {
        Objects.requireNonNull(revision, "No se puede añadir horas a una revisión nula.");
        return new Revision(revisiones.anadirHoras(revision, horas));
    }

    public Revision anadirPrecioMaterial(Revision revision, float precioMaterial) throws TallerMecanicoExcepcion {
        Objects.requireNonNull(revision, "No se puede añadir material a una revisión nula.");
        return new Revision(revisiones.anadirPrecioMaterial(revision, precioMaterial));
    }

    public Revision cerrar(Revision revision, LocalDate fechaFin) throws TallerMecanicoExcepcion {
        return new Revision(revisiones.cerrar(revision, fechaFin));
    }

    public void borrar(Cliente cliente) throws TallerMecanicoExcepcion {
        List<Revision> revisionesCliente = revisiones.get(cliente);
        for (Revision revision : revisionesCliente) {
            revisiones.borrar(revision);
        }
        clientes.borrar(cliente);
    }

    public void borrar(Vehiculo vehiculo) throws TallerMecanicoExcepcion {
        List<Revision> revisionesVehiculo = revisiones.get(vehiculo);
        for (Revision revision : revisionesVehiculo) {
            revisiones.borrar(revision);
        }
        vehiculos.borrar(vehiculo);
    }

    public void borrar(Revision revision) throws TallerMecanicoExcepcion {
        revisiones.borrar(revision);
    }

    public List<Cliente> getClientes() {
        List<Cliente> copiaClientes = new ArrayList<>();
        for (Cliente cliente : clientes.get()) {
            copiaClientes.add(new Cliente(cliente));
        }
        return copiaClientes;
    }

    public List<Vehiculo> getVehiculos() {
        return vehiculos.get();
    }

    public List<Revision> getRevisiones() {
        List<Revision> copiaRevisiones = new ArrayList<>();
        for (Revision revision : revisiones.get()) {
            copiaRevisiones.add(new Revision(revision));
        }
        return copiaRevisiones;
    }

    public List<Revision> getRevisiones(Cliente cliente) {
        List<Revision> revisionesCliente = new ArrayList<>();
        for (Revision revision : revisiones.get(cliente)) {
            revisionesCliente.add((new Revision(revision)));
        }
        return revisionesCliente;
    }

    public List<Revision> getRevisiones(Vehiculo vehiculo) {
        List<Revision> revisionesVehiculo = new ArrayList<>();
        for (Revision revision : revisiones.get(vehiculo)) {
            revisionesVehiculo.add((new Revision(revision)));
        }
        return revisionesVehiculo;
    }


}
