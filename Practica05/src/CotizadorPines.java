public class CotizadorPines {
    public static void main(String[] args) {

        // ### Datos del cliente ###
        String nombreCliente = "Robbie Valentino";

        // Clasificaciones: 'P' de Preferente; 'E' de Estandar; 'R' de Riesgoso.
        // Por ahora no cambia el calculo, pero el senor Pines la quiere registrada...

        char clasificacionCliente = 'E';

        // Restringir el uso de letras que no sean P, E y R
        if (clasificacionCliente != 'E' && clasificacionCliente != 'P' && clasificacionCliente != 'R') {
            System.out.println("Tipo de cliente invalido, por favor ingrese como |P| o |E| o |R|");
            return;
        }

        // ### Datos del prestamo ###

        // Monto prestado.
        double precio = 12899.0;

        double precioCompuesto = precio;

        // Porcentaje que se cobra por año.
        double tasaAnual = 15.0;

        // Plazo en meses en los que se hará el pago.
        int plazoMeses = 21;

        // ### Tipos de cliente ###

        // Tasa aplicada segun el tipo de cliente

        if (clasificacionCliente == 'P') {
            tasaAnual -= 2;

        } else if (clasificacionCliente == 'R') {
            tasaAnual += 3;

        } else {
            tasaAnual = 15;
        }

        // ### Cálculos Interes Simple###

        // La fórmula usa el tiempo en anios.
        // Se divide entre los 12 meses del año.
        double plazoAnios = plazoMeses / 12.0;

        // La condicion que nos librara de malos datos
        boolean condicion = (plazoMeses > 0) && (precio > 0) && (plazoAnios > 0);

        // Interes simple = capital * (tasa / 100) * tiempo en años.
        double interes = precio * (tasaAnual / 100) * plazoAnios;

        // El pago en total.
        double totalAPagar = precio + interes;

        // ### Reporte del cliente ###

        // Se imprimen datos del cliente
        System.out.println("\n+++++ Cotizador de La Cabana del Misterio +++++\n");
        System.out.println("--- Datos del cliente ---");
        System.out.printf("Cliente: %s%n", nombreCliente);
        System.out.printf("Clasificacion: %c%n", clasificacionCliente);
        System.out.println();

        // Usar la condicion para que no de valores no deseados
        if (condicion) {
            // Nueva tasa mensual para el interes compuesto
            double tasaMensual = (tasaAnual / 100) / 12.0;

            // ### Calculos Interes Compuesto ###
            // Inicializamos una variable paara nuestro # de mes, una condicion, y el
            // incremento
            for (int m = 1; m <= plazoMeses; m++) {
                // Formula para que cada mes crezca el interes
                precioCompuesto = precioCompuesto * (1 + tasaMensual);
                // Imprimir tabla de interes mes con mes
                System.out.printf("En el mes %d se tiene un saldo de %.2f %n", m, precioCompuesto);
            }

            // Calculo directo del IC, sin ciclo, con esta formula
            double saldoFinal = precio * (Math.pow((1 + tasaMensual), 21.0));
            // El puro valor del interes compuesto
            double interesCompuesto = precioCompuesto - precio;

            // Imprimir la ficha que se nos pide en la actividad 2.2, punto 3
            System.out.printf("Tasa aplicada = %.0f%% %nPréstamo = $%.2f %nMeses de pago = %d%n", tasaAnual, precio,
                    plazoMeses);
            System.out.printf(
                    "Interés simple = $%.2f %nTotal con interés simple = $%.2f %n Interés Compuesto = $%.2f%n", interes,
                    totalAPagar, interesCompuesto);
            System.out.printf("Total con interés compuesto = $%.2f%n", saldoFinal);
            System.out.println();
        } else {
            System.out.println("Inserta un valor valido");
        }

    }
}