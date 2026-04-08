package org.iesalandalus.programacion.tallermecanico.vista;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Revision;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.utilidades.Entrada;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Consola {
    private static final String CADENA_FORMATO_FECHA = "dd/MM/yyyy";

    private Consola() {
    }

    public static void mostrarCabecera(String mensaje) {
        System.out.printf("%n%s%n", mensaje);
        System.out.printf("-".repeat(mensaje.length()).concat("%n%n"));
    }

    public static void mostrarMenu() {
        mostrarCabecera("Menú de opciones");
        for (Opcion opcion : Opcion.values()) {
            System.out.println(opcion);
        }
    }

    public static Opcion elegirOpcion() {
        Opcion opcion = null;
        do {
            try {
                mostrarMenu();
                System.out.println("Escoja una de las siguientes acciones a realizar (Escribe el número de comando).");
                opcion = Opcion.get(Entrada.entero());
            } catch (IllegalArgumentException iae) {
                System.out.println("Opción no válida. Inténtalo de nuevo.");
            }
        } while (opcion == null);
        return opcion;
    }

    private static int leerEntero(String mensaje) {
        System.out.print(mensaje);
        return Entrada.entero();
    }

    private static float leerReal(String mensaje) {
        System.out.print(mensaje);
        return Entrada.real();
    }

    private static String leerCadena(String mensaje) {
        System.out.print(mensaje);
        return Entrada.cadena();
    }

    private static LocalDate leerFecha(String mensaje) {
        LocalDate fechaInicial = null;
        DateTimeFormatter formato = DateTimeFormatter.ofPattern(CADENA_FORMATO_FECHA);
        do {
            System.out.println(mensaje);
            String fecha = Entrada.cadena();
            try {
                fechaInicial = LocalDate.parse(fecha, formato);
            } catch (DateTimeException e) {
                System.out.print("Fecha incorrecta. Vuelve a intentarlo.");
            }
        } while (fechaInicial == null);
        return fechaInicial;
    }

    public static Cliente leerCliente() {
        System.out.println("Introduce los datos del cliente:");
        String nombre = leerCadena("Nombre: ");
        String dni = leerCadena("DNI: ");
        String telefono = leerCadena("Teléfono: ");
        return new Cliente(nombre, dni, telefono);
    }

    public static Cliente leerClienteDni() {
        String dni = leerCadena("DNI: ");
        return Cliente.get(dni);
    }

    public static String leerNuevoNombre() {
        return leerCadena("Introduce el nuevo nombre del cliente:");
    }

    public static String leerNuevoTelefono() {
        return leerCadena("Introduce el nuevo teléfono del cliente:");
    }

    public static Vehiculo leerVehiculo() {
        String marca = leerCadena("Marca: ");
        String modelo = leerCadena("Modelo: ");
        String matricula = leerCadena("Matrícula: ");
        return new Vehiculo(marca, modelo, matricula);
    }

    public static Vehiculo leerVehiculoMatricula() {
        return Vehiculo.get(leerCadena("Matrícula: "));
    }

     public static LocalDate leerFechaRevision() {
        return leerFecha("Introduce la fecha de la revisión: ");
    }

    public static Revision leerRevision() {
        Cliente cliente = leerClienteDni();
        Vehiculo vehiculo = leerVehiculoMatricula();
        LocalDate fechaRevision = leerFecha("Introduce la fecha: ");
        return new Revision(cliente, vehiculo, fechaRevision);
    }


    public static int leerHoras() {
        return leerEntero("Introduce las horas: ");
    }

    public static float leerPrecioMaterial() {
        return leerReal("Introduce el precio del material: ");
    }

    public static LocalDate leerFechaCierre() {
        return leerFecha("Introduce la fecha de cierre de la revisión: ");
    }
}