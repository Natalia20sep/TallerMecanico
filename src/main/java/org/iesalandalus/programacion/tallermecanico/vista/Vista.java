package org.iesalandalus.programacion.tallermecanico.vista;

import org.iesalandalus.programacion.tallermecanico.controlador.Controlador;
import org.iesalandalus.programacion.tallermecanico.modelo.TallerMecanicoExcepcion;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Revision;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;

import java.util.List;

public class Vista {
    private Controlador controlador;


    public void setControlador(Controlador controlador) {
        if (controlador == null) {
            throw new NullPointerException("El controlador no puede ser nulo.");
        }
        this.controlador = controlador;
    }
    public void comenzar() {
        Opcion opcion;
        do {
            Consola.mostrarMenu();
            opcion = Consola.elegirOpcion();
            ejecutar(opcion);
        } while (opcion != Opcion.SALIR);
        controlador.terminar();
    }
    public void terminar(){
        System.out.println("¡¡¡Hasta luego Maricarmennnn!!!");
    }
    private void ejecutar(Opcion opcion) {
        try {
            switch (opcion) {
                case INSERTAR_CLIENTE -> insertarCliente();
                case INSERTAR_VEHICULO -> insertarVehiculo();
                case INSERTAR_REVISION -> insertarRevision();
                case BUSCAR_CLIENTE -> buscarCliente();
                case BUSCAR_VEHICULO -> buscarVehiculo();
                case BUSCAR_REVISION -> buscarRevision();
                case MODIFICAR_CLIENTE -> modificarCliente();
                case ANADIR_HORAS_REVISION -> anadirHoras();
                case ANADIR_PRECIO_REVISION -> anadirPrecioMaterial();
                case CERRAR_REVISION -> cerrarRevision();
                case BORRAR_CLIENTE -> borrarCliente();
                case BORRAR_VEHICULO -> borrarVehiculo();
                case BORRAR_REVISION -> borrarRevision();
                case LISTAR_CLIENTES -> listarClientes();
                case LISTAR_VEHICULOS -> listarVehiculos();
                case LISTAR_REVISIONES -> listarRevisiones();
                case LISTAR_REVISIONES_CLIENTE -> listarRevisionesCliente();
                case LISTAR_REVISIONES_VEHICULO -> listarRevisionesVehiculo();
                case SALIR -> salir();
            }
        } catch (Exception e) {
            System.out.printf("Error: %s%n", e.getMessage());
        }
    }
    private void insertarCliente() throws TallerMecanicoExcepcion {
        Consola.mostrarCabecera("Insertar cliente");
        controlador.insertar(Consola.leerCliente());
        System.out.println("Cliente insertado correctamente.");
    }
    private void insertarVehiculo() throws TallerMecanicoExcepcion {
        Consola.mostrarCabecera("Insertar vehículo");
        controlador.insertar(Consola.leerVehiculo());
        System.out.println("Vehículo insertado correctamente.");
    }
    private void insertarRevision() throws TallerMecanicoExcepcion {
        Consola.mostrarCabecera("Insertar revisión");
        controlador.insertar(Consola.leerRevision());
        System.out.println("Revisión insertada correctamente.");
    }
    private void buscarCliente() {
        Consola.mostrarCabecera("Buscar cliente");
        Cliente cliente = controlador.buscar(Consola.leerClienteDni());
        System.out.println(cliente != null ? cliente : "No existe ningún cliente con ese DNI.");
    }
    private void buscarVehiculo() {
        Consola.mostrarCabecera("Buscar vehículo");
        Vehiculo vehiculo = controlador.buscar(Consola.leerVehiculoMatricula());
        System.out.println(vehiculo != null ? vehiculo : "No existe ningún vehículo con esa matrícula.");
    }
    private void buscarRevision() {
        Consola.mostrarCabecera("Buscar revisión");
        Revision revision = controlador.buscar(Consola.leerRevision());
        System.out.println(revision != null ? revision : "No existe ninguna revisión con esos datos.");
    }
    private void modificarCliente() throws TallerMecanicoExcepcion {
        Consola.mostrarCabecera("Modificar cliente");
        controlador.modificar(Consola.leerClienteDni(), Consola.leerNuevoNombre(), Consola.leerNuevoTelefono());
        System.out.println("Cliente modificado correctamente." );
    }
    private void anadirHoras() throws TallerMecanicoExcepcion {
        Consola.mostrarCabecera("Añadir horas a una revisión");
        controlador.anadirHoras(Consola.leerRevision(), Consola.leerHoras());
        System.out.println("Horas añadidas correctamente.");
    }
    private void anadirPrecioMaterial() throws TallerMecanicoExcepcion {
        Consola.mostrarCabecera("Añadir precio de material a una revisión");
        controlador.anadirPrecioMaterial(Consola.leerRevision(), Consola.leerPrecioMaterial());
        System.out.println("Precio de material añadido correctamente.");
    }
    private void cerrarRevision() throws TallerMecanicoExcepcion {
        Consola.mostrarCabecera("Cerrar revisión");
        controlador.cerrar(Consola.leerRevision(), Consola.leerFechaCierre());
        System.out.println("Revisión cerrada correctamente.");
    }
    private void borrarCliente() throws TallerMecanicoExcepcion {
        Consola.mostrarCabecera("Borrar cliente");
        controlador.borrar(Consola.leerClienteDni());
        System.out.println("Cliente borrado correctamente.");
    }
    private void borrarVehiculo() throws TallerMecanicoExcepcion {
        Consola.mostrarCabecera("Borrar vehículo");
        controlador.borrar(Consola.leerVehiculoMatricula());
        System.out.println("Vehículo borrado correctamente.");
    }
    private void borrarRevision() throws TallerMecanicoExcepcion {
        Consola.mostrarCabecera("Borrar revisión");
        controlador.borrar(Consola.leerRevision());
        System.out.println("Revisión borrada correctamente.");
    }
    private void listarClientes() {
        Consola.mostrarCabecera("Listar clientes");
        List<Cliente> clientes = controlador.getClientes();
        if (!clientes.isEmpty()) {
            for (Cliente cliente : clientes) {
                System.out.println(cliente);
            }
        }else {
            System.out.println("No hay clientes para mostrar.");
        }
    }
    private void listarVehiculos() {
        Consola.mostrarCabecera("Listar vehículos");
        List<Vehiculo> vehiculos = controlador.getVehiculos();
        if (!vehiculos.isEmpty()) {
            for (Vehiculo vehiculo : vehiculos) {
                System.out.println(vehiculo);
            }
        } else {
            System.out.println("No hay vehículos para mostrar.");
        }
    }
    private void listarRevisiones() {
        Consola.mostrarCabecera("Listar revisiones");
        List<Revision> revisiones = controlador.getRevisiones();
        if (!revisiones.isEmpty()) {
            for (Revision revision : revisiones) {
                System.out.println(revision);
            }
        } else {
            System.out.println("No hay revisiones.");
        }
    }
    private void listarRevisionesCliente() {
        Consola.mostrarCabecera("Listar revisiones de un cliente");
        List<Revision> revisiones = controlador.getRevisiones(Consola.leerClienteDni());
        if (!revisiones.isEmpty()) {
            for (Revision revision : revisiones) {
                System.out.println(revision);
            }
        } else {
            System.out.println("No hay revisiones para mostrar.");
        }
    }
    private void listarRevisionesVehiculo() {
        Consola.mostrarCabecera("Listar revisiones de un vehículo");
        List<Revision> revisiones = controlador.getRevisiones(Consola.leerVehiculoMatricula());
        if (!revisiones.isEmpty()) {
            for (Revision revision : revisiones) {
                System.out.println(revision);
            }
        } else {
            System.out.println("No hay revisiones para mostrar.");
        }
    }
    private void salir() {
    }
}