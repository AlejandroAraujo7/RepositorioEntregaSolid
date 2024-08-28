import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class BibliotecaApp {
    private static List<Libro> libros = new ArrayList<>();
    private static List<Miembro> miembros = new ArrayList<>();
    private static List<Prestamo> prestamos = new ArrayList<>();

    public static void main(String[] args) {
        JFrame frame = new JFrame("Sistema de Gestión de Biblioteca");
        frame.setSize(400, 300);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton agregarLibroBtn = new JButton("Agregar Libro");
        JButton agregarMiembroBtn = new JButton("Agregar Miembro");
        JButton gestionarPrestamosBtn = new JButton("Gestionar Préstamos");
        JButton verLibrosBtn = new JButton("Ver Libros");
        JButton verMiembrosBtn = new JButton("Ver Miembros");

        agregarLibroBtn.setBounds(20, 20, 150, 30);
        agregarMiembroBtn.setBounds(20, 60, 150, 30);
        gestionarPrestamosBtn.setBounds(20, 100, 150, 30);
        verLibrosBtn.setBounds(200, 20, 150, 30);
        verMiembrosBtn.setBounds(200, 60, 150, 30);

        frame.add(agregarLibroBtn);
        frame.add(agregarMiembroBtn);
        frame.add(gestionarPrestamosBtn);
        frame.add(verLibrosBtn);
        frame.add(verMiembrosBtn);

        agregarLibroBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String titulo = JOptionPane.showInputDialog("Título del libro:");
                String autor = JOptionPane.showInputDialog("Autor del libro:");
                String isbn = JOptionPane.showInputDialog("ISBN del libro:");
                libros.add(new Libro(titulo, autor, isbn));
                JOptionPane.showMessageDialog(null, "Libro agregado exitosamente.");
            }
        });

        agregarMiembroBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = JOptionPane.showInputDialog("Nombre del miembro:");
                String idMiembro = JOptionPane.showInputDialog("ID del miembro:");
                miembros.add(new Miembro(nombre, idMiembro));
                JOptionPane.showMessageDialog(null, "Miembro agregado exitosamente.");
            }
        });

        gestionarPrestamosBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String libroTitulo = JOptionPane.showInputDialog("Título del libro para prestar:");
                String miembroId = JOptionPane.showInputDialog("ID del miembro:");

                Libro libro = libros.stream().filter(l -> l.getTitulo().equals(libroTitulo) && l.isDisponible()).findFirst().orElse(null);
                Miembro miembro = miembros.stream().filter(m -> m.getIdMiembro().equals(miembroId)).findFirst().orElse(null);

                if (libro != null && miembro != null) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    try {
                        LocalDate fechaPrestamo = LocalDate.now();
                        LocalDate fechaDevolucion = LocalDate.parse(JOptionPane.showInputDialog("Fecha de devolución (dd/MM/yyyy):"), formatter);

                        Prestamo prestamo = new Prestamo(libro, miembro, fechaPrestamo, fechaDevolucion);
                        prestamos.add(prestamo);
                        libro.prestar();
                        miembro.agregarPrestamo(prestamo);
                        JOptionPane.showMessageDialog(null, "Préstamo registrado exitosamente.");
                    } catch (DateTimeParseException ex) {
                        JOptionPane.showMessageDialog(null, "Error en el formato de fecha.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Libro o miembro no encontrado.");
                }
            }
        });

        verLibrosBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder librosInfo = new StringBuilder();
                for (Libro libro : libros) {
                    librosInfo.append(libro.toString()).append("\n");
                }
                JOptionPane.showMessageDialog(null, "Libros:\n" + librosInfo.toString());
            }
        });

        verMiembrosBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder miembrosInfo = new StringBuilder();
                for (Miembro miembro : miembros) {
                    miembrosInfo.append(miembro.toString()).append("\n");
                }
                JOptionPane.showMessageDialog(null, "Miembros:\n" + miembrosInfo.toString());
            }
        });

        frame.setVisible(true);
    }
}
