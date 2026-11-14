/**
 * Programa de prueba para verificar el correcto funcionamiento de las clases
 * {@link Paquete} y {@link CotizadorEnvio}.
 * 
 * @author Lopez Mendez Adrian
 * @version 1.0
 */
public class PruebaPaqueteria {

    /**
     * Punto de entrada del programa. Ejecuta los casos de prueba de las
     * clases Paquete y CotizadorEnvio, y las pruebas de la propuesta personal.
     * 
     * @param args Argumentos de linea de comandos (no se utilizan).
     */
    public static void main(String[] args) {
        System.out.println("======================================================");
        System.out.println("  PRUEBAS DEL SISTEMA DE ENVIOS POSTALES (PRACTICA 7) ");
        System.out.println("======================================================\n");

        // ---------------------------------------------------------------------
        // CASO 1: Paquete estandar pequeno (peso fisico domina)
        // Datos requeridos: codigo "PK-001", peso 3.5 kg, dimensiones 20x15x10 cm,
        // valor declarado $800.0 pesos.
        // ---------------------------------------------------------------------
        System.out.println("--- Caso 1: Paquete pequeno estandar ---");
        Paquete p1 = new Paquete("PK-001", 3.5, 20, 15, 10, 800.0);
        mostrarDetallePaquete(p1);
        System.out.println("Es valido?: " + CotizadorEnvio.esPaqueteValido(p1));
        System.out.printf("Costo de envio cotizado: $%.2f (Esperado: $120.00)\n", CotizadorEnvio.cotizarEnvio(p1));
        System.out.println("Requiere seguro?: " + CotizadorEnvio.requiereSeguro(p1));
        System.out.println();

        // ---------------------------------------------------------------------
        // CASO 2: Paquete liviano pero voluminoso (peso volumetrico domina)
        // Datos requeridos: codigo "PK-002", peso 2.0 kg, dimensiones 50x40x50 cm,
        // valor declarado $1500.0 pesos.
        // ---------------------------------------------------------------------
        System.out.println("--- Caso 2: Paquete voluminoso (volumen predomina) ---");
        Paquete p2 = new Paquete("PK-002", 2.0, 50, 40, 50, 1500.0);
        mostrarDetallePaquete(p2);
        if (CotizadorEnvio.esPaqueteValido(p2)) {
            System.out.printf("Peso volumetrico calculado: %.2f kg\n", p2.calculaPesoVolumetrico());
            System.out.printf("Peso cobrable final: %.2f kg\n", p2.obtenerPesoCobrable());
        }
        System.out.printf("Costo de envio cotizado: $%.2f (Esperado: $425.00)\n", CotizadorEnvio.cotizarEnvio(p2));
        System.out.println("Requiere seguro?: " + CotizadorEnvio.requiereSeguro(p2));
        System.out.println();

        // ---------------------------------------------------------------------
        // CASO 3: Paquete de alto valor comercial (requiere seguro por monto)
        // Datos requeridos: codigo "PK-003", peso 4.0 kg, dimensiones 30x25x20 cm,
        // valor declarado $6500.0 pesos.
        // ---------------------------------------------------------------------
        System.out.println("--- Caso 3: Paquete de alto valor monetario ---");
        Paquete p3 = new Paquete("PK-003", 4.0, 30, 25, 20, 6500.0);
        mostrarDetallePaquete(p3);
        System.out.printf("Costo de envio: $%.2f\n", CotizadorEnvio.cotizarEnvio(p3));
        System.out.println("Requiere seguro?: " + CotizadorEnvio.requiereSeguro(p3) + " (Esperado: true)");
        System.out.println();

        // ---------------------------------------------------------------------
        // CASO 4: Paquete sobredimensionado (arista > 60 cm o suma > 150 cm)
        // Datos requeridos: codigo "PK-004", peso 5.0 kg, dimensiones 70x30x20 cm,
        // valor declarado $1200.0 pesos.
        // ---------------------------------------------------------------------
        System.out.println("--- Caso 4: Paquete sobredimensionado ---");
        Paquete p4 = new Paquete("PK-004", 5.0, 70, 30, 20, 1200.0);
        mostrarDetallePaquete(p4);
        System.out.println(
                "Requiere seguro?: " + CotizadorEnvio.requiereSeguro(p4) + " (Esperado: true por dimensiones)");
        System.out.println();

        // ---------------------------------------------------------------------
        // CASO 5: Validacion de referencia nula (null)
        // ---------------------------------------------------------------------
        System.out.println("--- Caso 5: Validacion de referencia nula (null) ---");
        Paquete pNulo = null;
        System.out
                .println("Es valido un paquete null?: " + CotizadorEnvio.esPaqueteValido(pNulo) + " (Esperado: false)");
        System.out.printf("Cotizacion de un paquete null: $%.2f (Esperado: -1.00)\n",
                CotizadorEnvio.cotizarEnvio(pNulo));
        System.out.println(
                "Requiere seguro un paquete null?: " + CotizadorEnvio.requiereSeguro(pNulo) + " (Esperado: false)");
        System.out.println();

        // =====================================================================
        // CASO 6: PRUEBAS DE TU METODO PERSONAL (PARTE 2)
        // =====================================================================
        System.out.println("======================================================");
        System.out.println("        PRUEBAS DE TU PROPUESTA PERSONAL              ");
        System.out.println("======================================================");

        // 6A: cliente preferente con paquete pequeno (tarifa base $120.00)
        // Esperado: 120.00 * 0.85 = $102.00
        System.out.println("--- 6A: Cliente preferente, paquete pequeno ---");
        Paquete p6 = new Paquete("PK-006", 3.5, 20, 15, 10, 800.0);
        p6.setClientePreferente(true);
        mostrarDetallePaquete(p6);
        double base6 = CotizadorEnvio.cotizarEnvio(p6);
        System.out.printf("Tarifa base: $%.2f\n", base6);
        System.out.printf("Tarifa preferente: $%.2f (Esperado: $102.00)\n", p6.obtenerTarifaPreferente(base6));
        System.out.println();

        // 6B: mismo paquete pero cliente NO preferente
        // Esperado: la tarifa no cambia, $120.00
        System.out.println("--- 6B: Cliente NO preferente, mismo paquete ---");
        Paquete p7 = new Paquete("PK-007", 3.5, 20, 15, 10, 800.0);
        double base7 = CotizadorEnvio.cotizarEnvio(p7);
        System.out.println("Cliente preferente?: " + p7.esClientePreferente());
        System.out.printf("Tarifa preferente: $%.2f (Esperado: $120.00, sin descuento)\n",
                p7.obtenerTarifaPreferente(base7));
        System.out.println();

        // 6C: cliente preferente con paquete voluminoso (tarifa base $425.00)
        // Esperado: 425.00 * 0.85 = $361.25
        System.out.println("--- 6C: Cliente preferente, paquete voluminoso ---");
        Paquete p8 = new Paquete("PK-008", 2.0, 50, 40, 50, 1500.0);
        p8.setClientePreferente(true);
        double base8 = CotizadorEnvio.cotizarEnvio(p8);
        System.out.printf("Tarifa base: $%.2f\n", base8);
        System.out.printf("Tarifa preferente: $%.2f (Esperado: $361.25)\n", p8.obtenerTarifaPreferente(base8));
        System.out.println();

        // 6D: tarifa base invalida (codigo de error -1.0 de un paquete null)
        // Esperado: -1.00, no se descuenta sobre un error
        System.out.println("--- 6D: Tarifa base invalida (paquete null) ---");
        double baseNula = CotizadorEnvio.cotizarEnvio(pNulo);
        System.out.printf("Tarifa preferente: $%.2f (Esperado: -1.00)\n", p6.obtenerTarifaPreferente(baseNula));
        System.out.println();

        // =====================================================================
        // PRUEBAS EXTRA: validaciones de los constructores y setters
        // =====================================================================
        System.out.println("======================================================");
        System.out.println("      PRUEBAS EXTRA: VALIDACIONES DE LA CLASE         ");
        System.out.println("======================================================");

        // Constructor por defecto: debe producir un paquete VALIDO (no es lo mismo que
        // null)
        System.out.println("--- Extra 1: Constructor por defecto ---");
        Paquete pDefecto = new Paquete();
        mostrarDetallePaquete(pDefecto);
        System.out.println("Es valido?: " + CotizadorEnvio.esPaqueteValido(pDefecto) + " (Esperado: true)");
        System.out.printf("Costo de envio: $%.2f (Esperado: $120.00)\n", CotizadorEnvio.cotizarEnvio(pDefecto));
        System.out.println();

        // Constructor con datos invalidos: se sustituyen por valores de respaldo
        System.out.println("--- Extra 2: Constructor con datos invalidos ---");
        Paquete pInvalido = new Paquete(null, -5.0, 0, -10, 20, -100.0);
        mostrarDetallePaquete(pInvalido);
        System.out.println("(Esperado: SIN-CODIGO, 1.0 kg, 1x1x20 cm, valor $0.00)");
        System.out.println();

        // Setter con dato invalido: se conserva el valor anterior
        System.out.println("--- Extra 3: Setter con dato invalido ---");
        Paquete pSetter = new Paquete("PK-009", 3.0, 10, 10, 10, 500.0);
        pSetter.setLargo(-4);
        System.out.println("Largo despues de setLargo(-4): " + pSetter.getLargo() + " (Esperado: 10.0)");
        pSetter.setLargo(25);
        System.out.println("Largo despues de setLargo(25): " + pSetter.getLargo() + " (Esperado: 25.0)");
        System.out.println();

        // Tramo mas de 15 kg: 1 kg de peso fisico pero 60x50x40 cm = 24 kg volumetricos
        // Esperado: 300 + 25 * (24 - 15) = $525.00
        System.out.println("--- Extra 4: Tarifa para mas de 15 kg ---");
        Paquete pPesado = new Paquete("PK-010", 1.0, 60, 50, 40, 900.0);
        System.out.printf("Peso cobrable: %.2f kg\n", pPesado.obtenerPesoCobrable());
        System.out.printf("Costo de envio: $%.2f (Esperado: $525.00)\n", CotizadorEnvio.cotizarEnvio(pPesado));
        System.out.println();
    }

    /**
     * Metodo auxiliar para imprimir en consola los datos basicos de un paquete.
     * Utiliza los metodos de acceso (getters) implementados en la clase Paquete.
     * 
     * @param p Paquete a imprimir.
     */
    private static void mostrarDetallePaquete(Paquete p) {
        if (!CotizadorEnvio.esPaqueteValido(p)) {
            System.out.println("  [Paquete no valido o no instanciado]");
            return;
        }
        System.out.println("  Codigo de rastreo: " + p.getCodigoRastreo());
        System.out.println("  Peso fisico: " + p.getPesoKg() + " kg");
        System.out.println("  Dimensiones (largo x ancho x alto): "
                + p.getLargo() + " x " + p.getAncho() + " x " + p.getAlto() + " cm");
        System.out.println("  Valor declarado: $" + p.getValorDeclarado());
    }
}
