package co.edu.uniquindio;

public class Empleado {
    private String nombre;
    private String idEmpleado;

    public Empleado(String nombre, String idEmpleado) {
        this.nombre = nombre;
        this.idEmpleado = idEmpleado;
    }

    public String getNombre() {
        return nombre;
    }

    public String getIdEmpleado() {
        return idEmpleado;
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre + ", ID: " + idEmpleado;
    }
}
