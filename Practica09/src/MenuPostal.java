import java.util.Scanner;

/**
 * Programa interactivo por consola para gestionar guias de envio (Parte 4).
 * 
 * @author Lopez Mendez Adrian / 324188758
 * @version 2.0
 */
public class MenuPostal {


    /**
     * Ciclo principal del menu interactivo.
     * 
     * @param args Argumentos de linea de comandos (no utilizados).
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Envio guiaActiva = null;
        boolean continuar = true;

        System.out.println("================================================================");
        System.out.println("  SISTEMA INTERACTIVO DE GUIAS POSTALES | ICC 2027-1");
        System.out.println("================================================================");

        while (continuar) {
            System.out.println("\n--- MENU PRINCIPAL ---");
            System.out.println("1. Emitir nueva guia de envio");
            System.out.println("2. Clonar guia activa con un nuevo paquete (mismo remitente y destinatario)");
            System.out.println("3. Clonar paquete de la guia activa para enviarlo a otro destinatario");
            System.out.println("4. Generar guia de retorno (devolucion al remitente)");
            System.out.println("5. Mostrar detalle y costo de la guia activa");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opcion: ");

            String opcion = sc.nextLine().trim();

            switch (opcion) {
                case "1":
                    guiaActiva = emitirNuevaGuia(sc);
                    break;

                case "2":
                    guiaActiva = clonarGuiaActiva(sc, guiaActiva);
                    break;

                case "3":
                    guiaActiva = enviarPaqueteClonado(sc, guiaActiva);
                    break;

                case "4":
                    guiaActiva = generarRetorno(guiaActiva);
                    break;

                case "5":
                    mostrarGuiaActiva(guiaActiva);
                    break;

                case "0":
                    continuar = false;
                    System.out.println("Saliendo del sistema postal...");
                    break;

                default:
                    System.out.println("Opcion no valida. Intente de nuevo.");
                    break;
            }
        }
        sc.close();
    }


    /**
     * Metodo auxiliar que solicita al usuario por consola
     * los datos de un paquete y devuelve la instancia creada.
     * 
     * @param sc Lector de entrada estandar.
     * @return Nuevo objeto Paquete con los datos capturados.
     */
    private static Paquete capturarPaquete(Scanner sc) {
        System.out.println("\n--- Captura de datos del Paquete ---");
        System.out.print("  Codigo de rastreo (ej. PKG-500): ");
        String codigo = sc.nextLine().trim();

        System.out.print("  Peso fisico en kg (ej. 3.5): ");
        double peso = leerDouble(sc, 1.0);

        System.out.print("  Alto en cm (ej. 20): ");
        double alto = leerDouble(sc, 10.0);

        System.out.print("  Ancho en cm (ej. 20): ");
        double ancho = leerDouble(sc, 10.0);

        System.out.print("  Largo en cm (ej. 30): ");
        double largo = leerDouble(sc, 10.0);

        System.out.print("  Valor declarado en MXN (ej. 800): ");
        double valor = leerDouble(sc, 100.0);

        System.out.print("  ¿Es envio express? (s/n): ");
        boolean express = sc.nextLine().trim().equalsIgnoreCase("s");

        return new Paquete(codigo, peso, alto, ancho, largo, valor, express);
    }

    /**
     * Lee un numero decimal validando con hasNextDouble() y limpiando el salto de linea
     * del buffer de entrada sin utilizar manejo de excepciones.
     * 
     * @param sc Lector de entrada estandar.
     * @param valorPorOmision Valor a regresar si el usuario no ingresa un numero valido.
     * @return El numero leido, o valorPorOmision si la entrada no fue numerica.
     */
    private static double leerDouble(Scanner sc, double valorPorOmision) {
        if (sc.hasNextDouble()) {
            double valor = sc.nextDouble();
            sc.nextLine(); // Limpia el salto de linea pendiente en el buffer
            return valor;
        }
        sc.nextLine(); // Descarta la entrada no numerica
        return valorPorOmision;
    }

    /**
     * Solicita por consola los datos de una Persona de acuerdo con los atributos
     * que disenaste en Persona.java y devuelve el objeto instanciado.
     * 
     * @param sc Lector de entrada estandar.
     * @return Nueva instancia de Persona con los datos capturados.
     */
    private static Persona capturarPersona(Scanner sc) {
        System.out.print("  Identificador corto (ej. PER-01): ");
        String identificador = sc.nextLine().trim();

        System.out.print("  Nombre completo: ");
        String nombre = sc.nextLine().trim();

        System.out.print("  Ciudad o codigo postal: ");
        String ubicacion = sc.nextLine().trim();

        // El constructor de Persona se encarga de poner valores por omision
        // si alguna cadena llega vacia
        return new Persona(identificador, nombre, ubicacion);
    }

    /**
     * Opcion 1 (REFERENCIA IMPLEMENTADA): Captura remitente, destinatario
     * y paquete para emitir y mostrar una nueva guia de envio.
     * 
     * @param sc Lector de entrada estandar.
     * @return La nueva guia de envio creada.
     */
    private static Envio emitirNuevaGuia(Scanner sc) {
        System.out.println("\n--- Captura de datos del REMITENTE ---");
        Persona remitente = capturarPersona(sc);

        System.out.println("\n--- Captura de datos del DESTINATARIO ---");
        Persona destinatario = capturarPersona(sc);

        Paquete paquete = capturarPaquete(sc);
        Envio nuevaGuia = new Envio(remitente, destinatario, paquete);
        System.out.println("\nGuia emitida con exito:\n" + nuevaGuia);
        return nuevaGuia;
    }

    private static void mostrarGuiaActiva(Envio guiaActiva) {
        if (guiaActiva == null) {
            System.out.println("Aviso: No hay ninguna guia activa.");
        } else {
            System.out.println("\n" + guiaActiva.toString());
        }
    }

    /**
     * Opcion 2: Clona la guia actual conservando remitente y destinatario,
     * pidiendo los datos de un nuevo paquete. La guia nueva pasa a ser la actual.
     * 
     * @param sc Lector de entrada estandar.
     * @param guiaActual La guia que se va a clonar (puede ser null).
     * @return La nueva guia clonada, o la misma guiaActual si no habia guia.
     */
    private static Envio clonarGuiaActiva(Scanner sc, Envio guiaActual) {
        if (guiaActual == null) {
            System.out.println("Aviso: Primero emite una guia (opcion 1) para poder clonarla.");
            return guiaActual;
        }
        System.out.println("\nSe clonara la guia " + guiaActual.getFolioGuia()
                           + " (mismo remitente y destinatario).");
        Paquete nuevoPaquete = capturarPaquete(sc);
        Envio guiaClonada = new Envio(guiaActual, nuevoPaquete);
        System.out.println("\nGuia clonada con exito:\n" + guiaClonada);
        return guiaClonada;
    }

    /**
     * Opcion 3: Copia el paquete de la guia actual (mismas medidas, peso y valor),
     * le asigna un nuevo codigo de rastreo y lo envia desde el mismo remitente
     * a un destinatario nuevo. La guia nueva pasa a ser la actual.
     * 
     * @param sc Lector de entrada estandar.
     * @param guiaActual La guia de la cual se toma el paquete (puede ser null).
     * @return La nueva guia con el paquete clonado, o la misma guiaActual si no habia guia.
     */
    private static Envio enviarPaqueteClonado(Scanner sc, Envio guiaActual) {
        if (guiaActual == null) {
            System.out.println("Aviso: Primero emite una guia (opcion 1) para poder clonar su paquete.");
            return guiaActual;
        }
        Paquete paqueteClonado = new Paquete(guiaActual.getPaquete());

        System.out.print("\nNuevo codigo de rastreo para la copia de "
                         + paqueteClonado.getCodigoRastreo() + ": ");
        String nuevoCodigo = sc.nextLine().trim();
        if (nuevoCodigo.isEmpty()) {
            // Si no escribe nada, le agregamos un sufijo para distinguirlo del original
            nuevoCodigo = paqueteClonado.getCodigoRastreo() + "-C";
        }
        paqueteClonado.setCodigoRastreo(nuevoCodigo);

        System.out.println("\n--- Captura de datos del NUEVO DESTINATARIO ---");
        Persona nuevoDestinatario = capturarPersona(sc);

        Envio nuevaGuia = new Envio(guiaActual.getRemitente(), nuevoDestinatario, paqueteClonado);
        System.out.println("\nGuia con paquete clonado emitida con exito:\n" + nuevaGuia);
        return nuevaGuia;
    }

    /**
     * Opcion 4: Genera la guia de retorno de la guia actual (roles invertidos y
     * paquete con prefijo "RET-"). La guia de retorno pasa a ser la actual.
     * 
     * @param guiaActual La guia que se quiere devolver (puede ser null).
     * @return La guia de retorno, o la misma guiaActual si no habia guia.
     */
    private static Envio generarRetorno(Envio guiaActual) {
        if (guiaActual == null) {
            System.out.println("Aviso: No hay ninguna guia actual para devolver.");
            return guiaActual;
        }
        Envio guiaRetorno = guiaActual.generarGuiaRetorno();
        System.out.println("\nGuia de retorno generada con exito:\n" + guiaRetorno);
        return guiaRetorno;
    }
}
