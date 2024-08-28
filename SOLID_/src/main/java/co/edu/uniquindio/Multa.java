import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Multa {
    private Prestamo prestamo;

    public Multa(Prestamo prestamo) {
        this.prestamo = prestamo;
    }

    public double calcularMulta() {
        LocalDate hoy = LocalDate.now();
        long diasAtraso = ChronoUnit.DAYS.between(prestamo.getFechaDevolucion(), hoy);
        if (diasAtraso > 0) {
            return diasAtraso * 0.5; // $0.5 por día de atraso
        }
        return 0;
    }
}
