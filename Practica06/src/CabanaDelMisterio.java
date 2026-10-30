import java.util.Scanner;

public class CabanaDelMisterio {
        public static void main(String[] args) {

                // ====== Datos del cliente y del prestamo ======
                Scanner entrada = new Scanner(System.in);
                String nombreCliente = entrada.nextLine();
                String tipoCliente = entrada.nextLine();
                int capital = entrada.nextInt();
                int plazoMeses = entrada.nextInt();
                double tasaBase = entrada.nextDouble();

                // ====== Validar los datos antes de cualquier cuenta ======

                // Esta variable sirve como pregunta "¿Los datos son válidos?"
                // Originalmente, la respuesta es true.
                boolean datosValidos = true;

                /*
                 Comenzamos a verificar los posibles errores con los datos.
                 Si alguno es incorrecto, la variable "datosValidos" se
                 vuelve false y se imprime un mensaje explicando la falla con
                 el dato que estamos revisando.
                 */

                // El capital prestado no puede ser negativo o igual a cero.
                if (capital <= 0) {
                        System.out.println("Error: el capital debe ser mayor que 0.");
                        datosValidos = false;
                }

                // La tasa base no puede ser negativa.
                if (tasaBase < 0) {
                        System.out.println("Error: la tasa anual no puede ser negativa.");
                        datosValidos = false;
                }

                // El plazo en meses no puede ser negativo o igual a cero.
                if (plazoMeses <= 0) {
                        System.out.println("Error: el plazo debe ser de al menos 1 mes.");
                        datosValidos = false;
                }

                // El tipo de cliente no puede ser diferente de 'R', 'P' o 'E'.
                if (tipoCliente != "P" && tipoCliente != "E" && tipoCliente != "R") {
                        System.out.println("Error: el tipo de cliente debe ser 'P', 'E' o 'R'.");
                        datosValidos = false;
                }

                // Si todos los datos pasaron las pruebas, procedemos con los cálculos.
                // En otro caso, no hacemos ningún cálculo. Basta con los mensajes que acabamos
                // de mostrar para que el usuario sepa que salio mal.
                if (datosValidos) {

                        // ===== Ajuste de tasa aplicada =====

                        // Variable para la tasa que será aplicada.
                        double tasaAplicada;

                        // Dependiendo el tipo de cliente, ajustamos la tasa.
                        if (tipoCliente == "P") {
                                tasaAplicada = tasaBase - 2; // Preferente -> Se le restan 2 puntos.
                        } else if (tipoCliente == "E") {
                                tasaAplicada = tasaBase; // Estandar -> Sin cambios para la tasa.
                        } else {
                                tasaAplicada = tasaBase + 3; // Riesgoso -> Se le suman 3 puntos.
                        }

                        // ===== Cálculo de interés simple =====

                        // Cálculos de interés simple, como en la práctica 4.
                        double plazoAnios = plazoMeses / 12.0;
                        double interesSimple = capital * (tasaAplicada / 100) * plazoAnios;
                        double totalSimple = capital + interesSimple;

                        // Se imprimen todos los datos del cliente, junto con sus cálculos
                        // para el interés simple.
                        System.out.println("\n###### Cotizador de La Cabana del Misterio ######\n");
                        System.out.printf("> Cliente: %s (tipo %c)%n", nombreCliente, tipoCliente);
                        System.out.printf("> Tasa aplicada = %.0f%%%n", tasaAplicada);
                        System.out.printf("> Prestamo = $ %.2f%n", capital);
                        System.out.printf("> Meses de pago = %d%n", plazoMeses);
                        System.out.println(); // Esto para un salto de línea.
                        System.out.printf("> Interes simple = $%.2f%n", interesSimple);
                        System.out.printf("> Total con interes simple = $%.2f%n", totalSimple);

                        // ===== Interes compuesto con un ciclo =====

                        // Tasa anual en porcentaje. Primero se pasa a fracción y luego se
                        // divide entre los 12 meses.
                        double tasaMensual = tasaAplicada / 100 / 12;

                        // Antes del primer mes, la deuda es igual al capital
                        double saldo = capital;

                        System.out.println();
                        System.out.println("--- Saldo mes por mes ---");

                        // Este ciclo se ejecuta desde el mes 1 hasta el plazo de meses.
                        for (int mes = 1; mes <= plazoMeses; mes++) {

                                // Cada mes, la deuda crece multiplicandose por (1 + tasa mensual)
                                saldo = saldo * (1 + tasaMensual);
                                // Se imprime el saldo del mes en la iteración que va el ciclo.
                                System.out.printf("> En el mes %d se tiene un saldo de %.2f%n", mes, saldo);

                        }

                        // El saldo final es el total.
                        double totalCompuesto = saldo;
                        // El interes es lo que crecio
                        double interesCompuesto = totalCompuesto - capital;

                        // ===== El mismo calculo con la formula directa =====
                        double totalFormula = capital * Math.pow(1 + tasaMensual, plazoMeses);

                        // Finalmente, se imprimen los datos del interes compuesto.
                        System.out.println();
                        System.out.printf("> Interes compuesto = $%.2f%n", interesCompuesto);
                        System.out.printf("> Total con interes compuesto = $%.2f%n", totalCompuesto);
                        System.out.printf("> Total con la formula (Math.pow) = $%.2f%n", totalFormula);
                        System.out.println();

                }
        }
}