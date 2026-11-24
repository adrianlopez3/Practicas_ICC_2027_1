/**
 * Programa de pruebas y simulacion para las Guias de Envios
 * Ejecuta pruebas automatizadas.
 * 
 * @author Lopez Mendez Adrian / 324188758, Romero Arzate Omar / 121006480
 * @version 2.0
 */
public class PruebaEnvio {

    /*
    * - Incluye las secciones guiadas para que agregues las pruebas de tu diseno
    *   de Persona (Parte 2), Envio y generacion de folios consecutivos (Parte 3),
    *   asi como la simulacion integral de envios, clones y devolucion (Parte 4).
    */

    private static int pruebasExitosas = 0;
    private static int totalPruebas = 0;

    /**
     * Metodo principal de verificacion y simulacion no interactiva.
     * 
     * @param args Argumentos de linea de comandos (no utilizados).
     */
    public static void main(String[] args) {
        System.out.println("================================================================================");
        System.out.println("  ICC 2027-1 | PRACTICA 9: AGREGACION, CLONACION Y GUIAS DE ENVIO");
        System.out.println("================================================================================\n");

        // ---------------------------------------------------------------------
        // PARTE 1: PAQUETES ESTANDARIZADOS (CONSTRUCTOR POR COPIA Y TOSTRING)
        // ---------------------------------------------------------------------
        System.out.println(">>> PARTE 1: REPRESENTACION TEXTUAL Y CLONACION DE PAQUETE");
        Paquete kitBase = new Paquete("KIT-101", 4.0, 20.0, 20.0, 20.0, 500.0, false);
        Paquete otroPaquete = new Paquete("PKG-202", 12.0, 40.0, 30.0, 30.0, 1500.0, false);

        String txtKit = kitBase.toString();
        verificar("Paquete.toString() incluye el codigo de rastreo",
                  txtKit != null && txtKit.contains("KIT-101") && !txtKit.contains("Paquete@"));

        Paquete kitClon = new Paquete(kitBase);
        double pesoOriginal = kitBase.getPeso();
        kitBase.setPeso(99.0);
        verificar("Constructor por copia de Paquete crea instancia independiente en memoria",
                  kitClon != kitBase && Math.abs(kitClon.getPeso() - pesoOriginal) < 1e-6);
        kitBase.setPeso(pesoOriginal);

        kitClon.setCodigoRastreo("KIT-102"); // Nuevo codigo para envio en serie
        verificar("Modificar el codigo del paquete clonado no altera el paquete plantilla original",
                  "KIT-101".equals(kitBase.getCodigoRastreo()) && "KIT-102".equals(kitClon.getCodigoRastreo()));

        Paquete clonNulo = new Paquete(null);
        verificar("Constructor por copia con null asigna valores predeterminados validos",
                  clonNulo.getCodigoRastreo() != null && clonNulo.getPeso() > 0.0);
        System.out.println();

        // ---------------------------------------------------------------------
        // PARTE 2: PRUEBAS DE TU DISENO DE LA CLASE PERSONA
        // ---------------------------------------------------------------------
        System.out.println(">>> PARTE 2: VERIFICACION DE LA CLASE PERSONA");
        // Dos personas en la misma ciudad (escrita con distintas mayusculas a proposito)
        Persona personaX = new Persona("PER-01", "Tyrion Lannister", "Desembarco del Rey");
        Persona personaY = new Persona("PER-02", "Sansa Stark", "desembarco del rey");
        // Una persona en otra ciudad
        Persona personaZ = new Persona("PER-03", "Jon Snow", "Invernalia");
        // Otro objeto con el mismo ID que personaX (mismo registro, distinto objeto)
        Persona copiaX = new Persona("PER-01", "Tyrion Lannister", "Desembarco del Rey");
        // Persona con datos vacios / nulos para probar las validaciones
        Persona personaVacia = new Persona("", null, "   ");

        verificar("mismaUbicacionQue(): X y Y estan en la misma ciudad (sin importar mayusculas)",
                  personaX.mismaUbicacionQue(personaY));
        verificar("mismaUbicacionQue(): X y Z estan en distinta ciudad",
                  !personaX.mismaUbicacionQue(personaZ));
        verificar("mismaUbicacionQue(null) devuelve false",
                  !personaX.mismaUbicacionQue(null));
        verificar("esLaMismaPersona(): mismo identificador aunque sean objetos distintos",
                  personaX.esLaMismaPersona(copiaX) && personaX != copiaX);
        verificar("esLaMismaPersona(): identificador distinto devuelve false",
                  !personaX.esLaMismaPersona(personaY));
        verificar("esLaMismaPersona(null) devuelve false",
                  !personaX.esLaMismaPersona(null));

        String txtX = personaX.toString();
        verificar("Persona.toString() incluye identificador, nombre y ubicacion",
                  txtX.contains("PER-01") && txtX.contains("Tyrion Lannister")
                  && txtX.contains("Desembarco del Rey") && !txtX.contains("Persona@"));
        verificar("Constructor con cadenas nulas o vacias asigna valores por omision",
                  "PER-00".equals(personaVacia.getIdentificador())
                  && "Sin nombre".equals(personaVacia.getNombreCompleto())
                  && "Sin ubicacion".equals(personaVacia.getUbicacion()));

        personaVacia.setNombreCompleto("Arya Stark");
        verificar("setNombreCompleto() con dato valido actualiza el nombre",
                  "Arya Stark".equals(personaVacia.getNombreCompleto()));

        System.out.println("  Persona X: " + personaX);
        System.out.println("  Persona Y: " + personaY);
        System.out.println("  Persona Z: " + personaZ);
        System.out.println();

        // ---------------------------------------------------------------------
        // PARTE 3: PRUEBAS DE AGREGACION, FOLIOS CONSECUTIVOS Y OPERACIONES EN ENVIO
        // ---------------------------------------------------------------------
        System.out.println(">>> PARTE 3: VERIFICACION DE ENVIO Y FOLIOS AUTOMATICOS");
        verificar("Al inicio se han emitido 0 guias",
                  Envio.getTotalGuiasHechas() == 0);

        Envio guiaLocal = new Envio(personaX, personaY, kitBase);
        Envio guiaForanea = new Envio(personaX, personaZ, kitClon);

        String folioLocal = guiaLocal.getFolioGuia();
        String folioForaneo = guiaForanea.getFolioGuia();
        System.out.println("  Folio guia local  : " + folioLocal);
        System.out.println("  Folio guia foranea: " + folioForaneo);

        verificar("Ambos folios no son nulos y son distintos",
                  folioLocal != null && folioForaneo != null && !folioLocal.equals(folioForaneo));
        verificar("Los folios tienen el formato GUIA-XXX-N",
                  tieneFormatoFolio(folioLocal) && tieneFormatoFolio(folioForaneo));
        verificar("Numeracion consecutiva: la primera guia es 1 y la segunda es 2",
                  numeroDeFolio(folioLocal) == 1 && numeroDeFolio(folioForaneo) == 2);
        verificar("El total de guias emitidas ahora es 2",
                  Envio.getTotalGuiasHechas() == 2);
        // tieneFormatoFolio(...) y numeroDeFolio(...) son metodos auxiliares
        // declarados al final de este archivo.

        verificar("esEnvioForaneo(): guia entre X y Y es LOCAL",
                  !guiaLocal.esEnvioForaneo());
        verificar("esEnvioForaneo(): guia entre X y Z es FORANEA",
                  guiaForanea.esEnvioForaneo());
        verificar("esAutoEnvio(): X enviando a Y no es envio personal",
                  !guiaLocal.esAutoEnvio());

        double tarifaBase = CotizadorEnvio.cotizarEnvio(kitBase);
        verificar("calcularCostoTotal() local es igual a la tarifa base ($" + tarifaBase + ")",
                  Math.abs(guiaLocal.calcularCostoTotal() - tarifaBase) < 1e-6);
        verificar("calcularCostoTotal() foraneo suma el cargo de $" + Envio.CARGO_FORANEO,
                  Math.abs(guiaForanea.calcularCostoTotal()
                           - (CotizadorEnvio.cotizarEnvio(kitClon) + Envio.CARGO_FORANEO)) < 1e-6);

        verificar("cambiarDestinatario(null) se rechaza y no cambia al destinatario",
                  !guiaLocal.cambiarDestinatario(null) && guiaLocal.getDestinatario() == personaY);
        verificar("cambiarDestinatario(Z) se acepta y la guia pasa a ser foranea",
                  guiaLocal.cambiarDestinatario(personaZ) && guiaLocal.getDestinatario() == personaZ
                  && guiaLocal.esEnvioForaneo());
        verificar("cambiarDestinatario() hacia una persona con el mismo ID que X se vuelve auto envio",
                  guiaLocal.cambiarDestinatario(copiaX) && guiaLocal.esAutoEnvio());
        guiaLocal.cambiarDestinatario(personaY); // Regresamos la guia a su destinatario original
        verificar("Cambiar el destinatario no genera folios nuevos (siguen siendo 2 guias)",
                  Envio.getTotalGuiasHechas() == 2);
        System.out.println();

        // ---------------------------------------------------------------------
        // PARTE 4: SIMULACION INTEGRAL (CLONACION DE GUIA Y DEVOLUCION / RETORNO)
        // ---------------------------------------------------------------------
        System.out.println(">>> PARTE 4: SIMULACION DE ENVIOS, CLONACION DE GUIA Y RETORNO");

        // a) Persona X envia el kit promocional a Persona Y
        System.out.println("\n a) " + personaX.getNombreCompleto() + " envia el kit promocional a "
                           + personaY.getNombreCompleto());
        Envio guia1 = new Envio(personaX, personaY, kitBase);
        System.out.println(guia1);

        // b) X envia un segundo paquete a Y clonando la guia anterior
        System.out.println("\n b) " + personaX.getNombreCompleto() + " envia un segundo paquete a "
                           + personaY.getNombreCompleto() + " clonando la guia " + guia1.getFolioGuia());
        Envio guia2 = new Envio(guia1, otroPaquete);
        System.out.println(guia2);
        verificar("Guia clonada conserva al mismo remitente (misma referencia)",
                  guia2.getRemitente() == guia1.getRemitente());
        verificar("Guia clonada conserva al mismo destinatario (misma referencia)",
                  guia2.getDestinatario() == guia1.getDestinatario());
        verificar("Guia clonada lleva el nuevo paquete PKG-202",
                  guia2.getPaquete() == otroPaquete && guia2.getPaquete() != guia1.getPaquete());
        verificar("Guia clonada tiene un folio nuevo y consecutivo",
                  !guia2.getFolioGuia().equals(guia1.getFolioGuia())
                  && numeroDeFolio(guia2.getFolioGuia()) == numeroDeFolio(guia1.getFolioGuia()) + 1);

        // b.2) X clona el kit promocional para mandarlo a Z en otra ciudad
        System.out.println("\n b.2) " + personaX.getNombreCompleto() + " clona el kit promocional para enviarlo a "
                           + personaZ.getNombreCompleto() + " en otra ciudad");
        Paquete kitParaZ = new Paquete(kitBase);
        kitParaZ.setCodigoRastreo("KIT-103");
        Envio guia3 = new Envio(personaX, personaZ, kitParaZ);
        System.out.println(guia3);
        verificar("El kit clonado para Z es otro objeto con los mismos datos fisicos",
                  kitParaZ != kitBase && kitParaZ.getPeso() == kitBase.getPeso()
                  && kitParaZ.getValorDeclarado() == kitBase.getValorDeclarado());
        verificar("El kit original conserva su codigo KIT-101",
                  "KIT-101".equals(kitBase.getCodigoRastreo()));
        verificar("La guia hacia Z es foranea y cobra el cargo adicional",
                  guia3.esEnvioForaneo()
                  && Math.abs(guia3.calcularCostoTotal() - guia1.calcularCostoTotal() - Envio.CARGO_FORANEO) < 1e-6);

        // c) Y solicita devolver el primer paquete a X
        System.out.println("\n c) " + personaY.getNombreCompleto() + " solicita devolver el primer paquete a "
                           + personaX.getNombreCompleto());
        Envio guiaRetorno = guia1.generarGuiaRetorno();
        System.out.println(guiaRetorno);
        verificar("En la guia de retorno el remitente es Y",
                  guiaRetorno.getRemitente() == personaY);
        verificar("En la guia de retorno el destinatario es X",
                  guiaRetorno.getDestinatario() == personaX);
        verificar("El paquete de retorno es una copia independiente del original",
                  guiaRetorno.getPaquete() != guia1.getPaquete()
                  && guiaRetorno.getPaquete().getPeso() == guia1.getPaquete().getPeso());
        verificar("El paquete de retorno tiene el prefijo RET- y el original no cambio",
                  "RET-KIT-101".equals(guiaRetorno.getPaquete().getCodigoRastreo())
                  && "KIT-101".equals(guia1.getPaquete().getCodigoRastreo()));
        verificar("La guia de retorno tiene un folio nuevo y consecutivo",
                  numeroDeFolio(guiaRetorno.getFolioGuia()) == numeroDeFolio(guia3.getFolioGuia()) + 1);

        System.out.println("\n  Total acumulado de guias emitidas en el sistema: "
                           + Envio.getTotalGuiasHechas());

        System.out.println("\n================================================================================");
        System.out.println("  RESUMEN DE PRUEBAS: " + pruebasExitosas + " / " + totalPruebas + " pruebas superadas con exito");
        System.out.println("================================================================================");
    }

    /**
     * Evalua una condicion booleana e imprime [OK] o [FALLO].
     * 
     * @param nombrePrueba Descripcion breve del caso evaluado.
     * @param condicion Resultado booleano esperado (true si paso).
     */
    private static void verificar(String nombrePrueba, boolean condicion) {
        totalPruebas++;
        if (condicion) {
            pruebasExitosas++;
            System.out.println("  [OK]    " + nombrePrueba);
        } else {
            System.out.println("  [FALLO] " + nombrePrueba);
        }
    }

    /**
     * Auxiliar de prueba: comprueba que un folio cumpla el formato que pide la
     * practica, "GUIA-XXX-N", donde XXX son 2 o 3 letras mayusculas o digitos
     * y N es el numero consecutivo.
     *
     * @param folio El folio de la guia que se esta probando.
     * @return true si el folio pasa la prueba de formato; false si no la pasa o es null.
     */
    private static boolean tieneFormatoFolio(String folio) {
        return folio != null && folio.matches("GUIA-[A-Z0-9]{2,3}-[0-9]+");
    }
        
    /**
     * Auxiliar de prueba: extrae el numero consecutivo de un folio "GUIA-XXX-N"
     * para comprobar que las guias se numeran en orden (1, 2, 3, ...).
     * Como la parte de en medio es aleatoria, las pruebas no pueden comparar
     * el folio completo y solo comparan este numero.
     *
     * @param folio El folio de la guia que se esta probando.
     * @return El numero consecutivo, o -1 si el folio no paso la prueba de formato.
     */
    private static int numeroDeFolio(String folio) {
        if (!tieneFormatoFolio(folio)) {
            return -1;
        }
        return Integer.parseInt(folio.substring(folio.lastIndexOf('-') + 1));
    }

}
