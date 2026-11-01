import java.util.Scanner;

public class CabanaDelMisterio {
        public static void main(String[] args) {

                Scanner entrada = new Scanner(System.in);

                // Datos del cliente y del prestamo
                String nombreCliente = "";
                double capital = 0;
                int plazoMeses = 0;
                char tipoCliente = 'E';
                double tasaBase = 0.0;
                int errores = 0;
                int operacionesEfectivas = 0;

                // Validar los datos antes de cualquier cuenta
                boolean datosValidos = true;

                System.out.println("Bienvenido de nuevo a la Cabaña del misterio \n Ingresa el nombre del cliente: ");
                nombreCliente = entrada.nextLine();

                while (datosValidos) {
                        System.out.print("Ingresa tu tipo de cliente: ");
                        if (entrada.hasNext()) {
                                tipoCliente = entrada.next().charAt(0);
                                if (tipoCliente != 'P' && tipoCliente != 'E' && tipoCliente != 'R') {
                                        System.out.println("Error: el tipo de cliente debe ser 'P', 'E' o 'R'.");
                                } else {
                                        datosValidos = false;
                                }
                        }
                }

                datosValidos = true; // Reiniciar la validación para el capital
                while (datosValidos) {
                        System.out.print("Ingresa el capital prestado: ");
                        if (entrada.hasNextDouble()) {
                                capital = entrada.nextDouble();
                                if (capital <= 0) {
                                        System.out.println("Error: el capital debe ser mayor que 0.");
                                } else {
                                        datosValidos = false;
                                }
                        } else {
                                System.out.println("Error: el capital debe ser un número.");
                                entrada.next();
                        }
                }

                datosValidos = true; // Reiniciar la validación para la tasa base
                while (datosValidos) {
                        System.out.print("Ingresa la tasa anual:");
                        if (entrada.hasNextDouble()) {
                                tasaBase = entrada.nextDouble();
                                if (tasaBase < 0) {
                                        System.out.println("Error: la tasa anual no puede ser negativa.");
                                } else {
                                        datosValidos = false;
                                }
                        } else {
                                System.out.println("Error: la tasa anual debe ser un número.");
                                entrada.next();
                        }
                }

                datosValidos = true; // Reiniciar la validación para el plazo en meses
                while (datosValidos) {
                        System.out.print("Ingresa el plazo en meses: ");
                        if (entrada.hasNextInt()) {
                                plazoMeses = entrada.nextInt();
                                if (plazoMeses <= 0) {
                                        System.out.println("Error: el plazo debe ser de al menos 1 mes.");

                                } else {
                                        datosValidos = false;
                                }
                        } else {
                                System.out.println("Error: el plazo debe ser un número entero.");
                                entrada.next();
                        }
                }

                int opcion = -1;

                do {
                        System.out.println("###Sistema de prestamos de la Cabaña del Misterio###");
                        System.out.println("Las opciones disponibles son :");
                        System.out.println("1) Cotizar con interes simple.");
                        System.out.println("2) Cotizar con interes compuesto.");
                        System.out.println("3) Comparar interes simple y compuesto.");
                        System.out.println("4) Cambiar el capital del prestamo.");
                        System.out.println("0) Salir.");
                        System.out.print("> Por favor, elige una opción : ");

                        // Validamos que la opción ingresada sea un número
                        if (entrada.hasNextInt()) {
                                opcion = entrada.nextInt();

                                double tasaAplicada;
                                String tipoClienteStr = (tipoCliente == 'P') ? "Preferente"
                                                : (tipoCliente == 'E') ? "Estándar" : "Riesgoso";

                                if (tipoCliente == 'P') {
                                        tasaAplicada = tasaBase - 2;
                                } else if (tipoCliente == 'E') {
                                        tasaAplicada = tasaBase;
                                } else {
                                        tasaAplicada = tasaBase + 3;
                                }

                                // Cálculos iniciales
                                double plazoAnios = plazoMeses / 12.0;
                                double interesSimple = capital * (tasaAplicada / 100) * plazoAnios;
                                double totalSimple = capital + interesSimple;
                                double tasaMensual = tasaAplicada / 100 / 12;
                                double saldo = capital;

                                switch (opcion) {
                                        case 1:
                                                System.out.println(
                                                                "\n### Opción 1 : Cotización de interés simple ###\n");
                                                System.out.printf("> Cliente: %s %n> Tipo de cliente: %s%n",
                                                                nombreCliente, tipoClienteStr);
                                                System.out.printf("> Tasa aplicada = %.0f%%%n", tasaAplicada);
                                                System.out.printf("> Prestamo = $ %.2f%n", capital);
                                                System.out.printf("> Meses de pago = %d%n", plazoMeses);
                                                System.out.println();
                                                System.out.printf("> Interés simple = $%.2f%n", interesSimple);
                                                System.out.printf("> Total con interés simple = $%.2f%n",
                                                                totalSimple);
                                                operacionesEfectivas++;
                                                break;

                                        case 2:
                                                System.out.println(
                                                                "\n### Opción 2 : Cotización de interés compuesto ###\n");
                                                System.out.printf("> Cliente: %s %n> Tipo de cliente: %s%n",
                                                                nombreCliente, tipoClienteStr);
                                                System.out.printf("> Tasa aplicada = %.0f%%%n", tasaAplicada);
                                                System.out.printf("> Prestamo = $ %.2f%n", capital);
                                                System.out.printf("> Meses de pago = %d%n", plazoMeses);
                                                System.out.println();
                                                for (int mes = 1; mes <= plazoMeses; mes++) {
                                                        saldo = saldo * (1 + tasaMensual);
                                                        System.out.printf(
                                                                        "> En el mes %d se tiene un saldo de %.2f%n",
                                                                        mes, saldo);
                                                }
                                                double totalCompuesto = saldo;
                                                double interesCompuesto = totalCompuesto - capital;
                                                System.out.printf("> Interés compuesto = $%.2f%n",
                                                                interesCompuesto);
                                                System.out.printf("> Total a pagar = $%.2f%n", totalCompuesto);
                                                System.out.println();
                                                operacionesEfectivas++;
                                                break;

                                        case 3:
                                                double totalFormula = capital
                                                                * Math.pow(1 + tasaMensual, plazoMeses);
                                                double interesCompuestoTres = totalFormula - capital;

                                                System.out.println(
                                                                "\n### Opción 3 : Comparación de intereses ###\n");
                                                System.out.printf("> Interés simple = $%.2f%n", interesSimple);
                                                System.out.printf("> Interés compuesto = $%.2f%n",
                                                                interesCompuestoTres);
                                                double diferencia = Math
                                                                .abs(interesCompuestoTres - interesSimple);
                                                System.out.printf("> La diferencia entre ellos es de $%.2f%n",
                                                                diferencia);

                                                if (interesCompuestoTres > interesSimple) {
                                                        System.out.println("> El interés compuesto es mayor");
                                                } else if (interesCompuestoTres < interesSimple) {
                                                        System.out.println("> El interés simple es mayor");
                                                } else {
                                                        System.out.println("> Ambos intereses son iguales.");
                                                }
                                                operacionesEfectivas++;
                                                break;

                                        case 4:
                                                datosValidos = true;
                                                while (datosValidos && errores < 3) {
                                                        System.out.print("¿Cuál será el nuevo monto?: ");
                                                        if (entrada.hasNextDouble()) {
                                                                capital = entrada.nextDouble();
                                                                if (capital < 0) {
                                                                        System.out.println(
                                                                                        "Error: el capital debe ser mayor que 0.");
                                                                        errores++;
                                                                } else {
                                                                        System.out.printf(
                                                                                        "> El nuevo capital es $%.2f%n",
                                                                                        capital);
                                                                        datosValidos = false;
                                                                }
                                                        } else {
                                                                System.out.println(
                                                                                "Error: el capital debe ser un número.");
                                                                errores++;
                                                                entrada.next();
                                                        }
                                                }

                                                while (datosValidos == false && errores < 3) {
                                                        double plazoAniosCuatro = plazoMeses / 12.0;
                                                        double interesSimpleCuatro = capital
                                                                        * (tasaAplicada / 100)
                                                                        * plazoAniosCuatro;
                                                        double totalSimpleCuatro = capital
                                                                        + interesSimpleCuatro;
                                                        double tasaMensualCuatro = tasaAplicada / 100 / 12;
                                                        double totalFormulaCuatro = capital * Math
                                                                        .pow(1 + tasaMensualCuatro, plazoMeses);
                                                        double interesCompuestoCuatro = totalFormulaCuatro
                                                                        - capital;
                                                        double diferenciaNueva = Math.abs(interesCompuestoCuatro
                                                                        - interesSimpleCuatro);
                                                        System.out.println(
                                                                        "\n### Opción 4 : Nuevo monto ###\n");
                                                        System.out.printf("> Interés simple = $%.2f%n",
                                                                        interesSimpleCuatro);
                                                        System.out.printf(
                                                                        "> Total con interés simple = $%.2f%n",
                                                                        totalSimpleCuatro);
                                                        System.out.printf("> Interés compuesto = $%.2f%n",
                                                                        interesCompuestoCuatro);
                                                        System.out.printf(
                                                                        "> Total con interés compuesto = $%.2f%n",
                                                                        totalFormulaCuatro);
                                                        System.out.printf("> Diferencia = $%.2f%n",
                                                                        diferenciaNueva);

                                                        datosValidos = true;
                                                }
                                                if (errores < 3) {
                                                        operacionesEfectivas++;
                                                }
                                                break;

                                        case 0:
                                                System.out.println(
                                                                "!Hasta luego! " + nombreCliente
                                                                                + " Gracias por usar el sistema de prestamos de la Cabaña del Misterio.");
                                                break;

                                        default:
                                                System.out.println(
                                                                "Opción inválida. Por favor, elige una opcion de 0 a 4.");
                                                errores++;
                                                break;
                                }

                        } else {
                                // Si entró texto en el menú
                                System.out.println("Texto invalido. Por favor, ingresa un número de opción.");
                                entrada.next();
                                errores++;
                                opcion = -1; // Mantiene el ciclo vivo para volver a preguntar (si no hay 3
                                             // errores)
                        }

                        System.out.println();

                        // Condición que termina si escoge 0, si hay 3 errores, o si hay 5 operaciones
                } while (opcion != 0 && errores < 3 && operacionesEfectivas < 5);

                // Bloque de salida definitivo
                if (errores >= 3) {
                        System.out.println("Gracias " + nombreCliente
                                        + " por usar el sistema de prestamos de la Cabaña del Misterio.");
                        System.out.printf("El capital con el que terminó el préstamo es de: $%.2f", capital);
                        System.out.println("\nEl programa termino por que se llego al limite de errores");
                        System.out.println("Se han cometido " + errores + " errores\n y " + operacionesEfectivas
                                        + " operaciones efectivas. Hasta pronto");
                } else if (operacionesEfectivas >= 5) {
                        System.out.println("Gracias " + nombreCliente
                                        + " por usar el sistema de prestamos de la Cabaña del Misterio.");
                        System.out.printf("El capital con el que terminó el préstamo es de: $%.2f", capital);
                        System.out.println("\nEl programa termino por que se llego al limite de operaciones efectivas");
                        System.out.println("Se han realizado " + operacionesEfectivas + " operaciones\n y " + errores
                                        + " errores. Hasta pronto");
                } else {
                        System.out.printf("El capital con el que terminó el préstamo es de: $%.2f", capital);
                        System.out.println("\nEl programa termino por que se ingreso el 0");
                        System.out.println("Se relizaron " + operacionesEfectivas + " operacion(es) efectiva(s) y");
                        System.out.println("se cometieron " + errores + " error(es).");

                }

                entrada.close();
        }
}