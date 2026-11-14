/**
 * Clase que modela un paquete para una empresa de envios postales y paqueteria.
 * Permite registrar sus dimensiones espaciales, peso fisico y valor declarado,
 * tambien calcular su peso volumetrico y determinar el peso cobrable final.
 * 
 * Incluye ademas una propuesta personal: la tarifa preferente, que aplica un
 * descuento del 15% sobre la tarifa base cuando el cliente es preferente.
 * 
 * @author Lopez Mendez Adrian
 * @version 1.0
 */
public class Paquete {

    // *** ATRIBUTOS ***
    // Todos son privados (encapsulamiento): solo se modifican mediante
    // el constructor o los setters, que validan los datos.

    /** Codigo de rastreo alfanumerico unico del paquete. */
    private String codigoRastreo;

    /** Peso fisico del paquete en kilogramos. */
    private double pesoKg;

    /** Largo del paquete en centimetros. */
    private double largo;

    /** Ancho del paquete en centimetros. */
    private double ancho;

    /** Alto del paquete en centimetros. */
    private double alto;

    /** Valor monetario declarado del contenido, en pesos. */
    private double valorDeclarado;

    /** Indica si el cliente es preferente (tiene derecho a descuento). */
    private boolean clientePreferente;

    // *** CONSTRUCTORES ***

    /**
     * Constructor por defecto. Crea un paquete unitario con valores
     * predeterminados validos: codigo "SIN-CODIGO", 1 kg de peso,
     * dimensiones de 1 cm por lado, valor declarado de $0.00 y cliente
     * no preferente.
     */
    public Paquete() {
        this.codigoRastreo = "SIN-CODIGO";
        this.pesoKg = 1.0;
        this.largo = 1.0;
        this.ancho = 1.0;
        this.alto = 1.0;
        this.valorDeclarado = 0.0;
        this.clientePreferente = false;
    }

    /**
     * Constructor con parametros. Valida los datos recibidos para que el
     * paquete siempre quede en un estado valido; si un dato no es valido
     * se sustituye por un valor de respaldo:
     * <ul>
     * <li>Codigo nulo o vacio: se asigna "SIN-CODIGO".</li>
     * <li>Peso menor o igual a 0: se asigna 1.0 kg.</li>
     * <li>Largo, ancho o alto menores o iguales a 0: se asigna 1 cm.</li>
     * <li>Valor declarado negativo: se asigna $0.00.</li>
     * </ul>
     * El paquete se crea con cliente no preferente; usa
     * {@link #setClientePreferente(boolean)} para cambiarlo.
     * 
     * @param codigoRastreo  Codigo de rastreo alfanumerico del paquete.
     * @param pesoKg         Peso fisico del paquete en kilogramos.
     * @param largo          Largo del paquete en centimetros.
     * @param ancho          Ancho del paquete en centimetros.
     * @param alto           Alto del paquete en centimetros.
     * @param valorDeclarado Valor monetario declarado del contenido, en pesos.
     */
    public Paquete(String codigoRastreo, double pesoKg,
            double largo, double ancho, double alto,
            double valorDeclarado) {
        // Un codigo nulo o vacio no identifica al paquete
        if (codigoRastreo != null && codigoRastreo.length() > 0) {
            this.codigoRastreo = codigoRastreo;
        } else {
            this.codigoRastreo = "SIN-CODIGO";
        }

        // Un paquete no puede pesar 0 kg ni menos
        if (pesoKg > 0) {
            this.pesoKg = pesoKg;
        } else {
            this.pesoKg = 1.0;
        }

        // Una dimension no positiva se sustituye por 1 cm
        if (largo > 0) {
            this.largo = largo;
        } else {
            this.largo = 1.0;
        }

        if (ancho > 0) {
            this.ancho = ancho;
        } else {
            this.ancho = 1.0;
        }

        if (alto > 0) {
            this.alto = alto;
        } else {
            this.alto = 1.0;
        }

        // El valor puede ser 0 (nada que asegurar), pero no negativo
        if (valorDeclarado >= 0) {
            this.valorDeclarado = valorDeclarado;
        } else {
            this.valorDeclarado = 0.0;
        }

        this.clientePreferente = false;
    }

    // *** METODOS DE ACCESO Y ASIGNACION ***
    // En los setters, si el dato no es valido se conserva el valor anterior.

    /**
     * Consulta el codigo de rastreo del paquete.
     * 
     * @return El codigo de rastreo.
     */
    public String getCodigoRastreo() {
        return this.codigoRastreo;
    }

    /**
     * Consulta el peso fisico del paquete.
     * 
     * @return El peso fisico en kilogramos.
     */
    public double getPesoKg() {
        return this.pesoKg;
    }

    /**
     * Consulta el largo del paquete.
     * 
     * @return El largo en centimetros.
     */
    public double getLargo() {
        return this.largo;
    }

    /**
     * Consulta el ancho del paquete.
     * 
     * @return El ancho en centimetros.
     */
    public double getAncho() {
        return this.ancho;
    }

    /**
     * Consulta el alto del paquete.
     * 
     * @return El alto en centimetros.
     */
    public double getAlto() {
        return this.alto;
    }

    /**
     * Consulta el valor declarado del contenido.
     * 
     * @return El valor declarado en pesos.
     */
    public double getValorDeclarado() {
        return this.valorDeclarado;
    }

    /**
     * Consulta si el cliente del paquete es preferente.
     * 
     * @return true si el cliente es preferente; false en caso contrario.
     */
    public boolean esClientePreferente() {
        return this.clientePreferente;
    }

    /**
     * Modifica el codigo de rastreo. Solo se acepta un codigo no nulo y no
     * vacio; en otro caso se conserva el codigo anterior.
     * 
     * @param codigoRastreo El nuevo codigo de rastreo.
     */
    public void setCodigoRastreo(String codigoRastreo) {
        if (codigoRastreo != null && codigoRastreo.length() > 0) {
            this.codigoRastreo = codigoRastreo;
        }
    }

    /**
     * Modifica el peso fisico. Solo se acepta un peso mayor a 0; en otro
     * caso se conserva el peso anterior.
     * 
     * @param pesoKg El nuevo peso fisico en kilogramos.
     */
    public void setPesoKg(double pesoKg) {
        if (pesoKg > 0) {
            this.pesoKg = pesoKg;
        }
    }

    /**
     * Modifica el largo. Solo se acepta un valor mayor a 0; en otro caso
     * se conserva el largo anterior.
     * 
     * @param largo El nuevo largo en centimetros.
     */
    public void setLargo(double largo) {
        if (largo > 0) {
            this.largo = largo;
        }
    }

    /**
     * Modifica el ancho. Solo se acepta un valor mayor a 0; en otro caso
     * se conserva el ancho anterior.
     * 
     * @param ancho El nuevo ancho en centimetros.
     */
    public void setAncho(double ancho) {
        if (ancho > 0) {
            this.ancho = ancho;
        }
    }

    /**
     * Modifica el alto. Solo se acepta un valor mayor a 0; en otro caso
     * se conserva el alto anterior.
     * 
     * @param alto El nuevo alto en centimetros.
     */
    public void setAlto(double alto) {
        if (alto > 0) {
            this.alto = alto;
        }
    }

    /**
     * Modifica el valor declarado. Solo se acepta un valor mayor o igual
     * a 0; en otro caso se conserva el valor anterior.
     * 
     * @param valorDeclarado El nuevo valor declarado en pesos.
     */
    public void setValorDeclarado(double valorDeclarado) {
        if (valorDeclarado >= 0) {
            this.valorDeclarado = valorDeclarado;
        }
    }

    /**
     * Indica si el cliente del paquete es preferente.
     * 
     * @param clientePreferente true si el cliente es preferente; false si no.
     */
    public void setClientePreferente(boolean clientePreferente) {
        this.clientePreferente = clientePreferente;
    }

    // *** METODOS CALCULADORES BASE ***

    /**
     * Calcula el peso volumetrico del paquete segun el estandar comercial (IATA):
     * (largo * ancho * alto) / 5000.
     * 
     * @return El peso volumetrico equivalente en kilogramos.
     */
    public double calculaPesoVolumetrico() {
        // 5000 cm3/kg es el factor de conversion de la norma IATA
        return (this.largo * this.ancho * this.alto) / 5000.0;
    }

    /**
     * Determina el peso cobrable final para efectos de tarificacion postal,
     * el cual corresponde al valor maximo entre el peso fisico y el volumetrico.
     * 
     * @return El mayor entre el peso real y el peso volumetrico.
     */
    public double obtenerPesoCobrable() {
        double pesoVolumetrico = this.calculaPesoVolumetrico();
        if (pesoVolumetrico > this.pesoKg) {
            return pesoVolumetrico;
        } else {
            return this.pesoKg;
        }
    }

    // *** PROPUESTA PERSONAL ***

    /**
     * Calcula la tarifa que paga el cliente aplicando el beneficio de cliente
     * preferente. Si el cliente es preferente se descuenta el 15% de la
     * tarifa base; si no lo es, la tarifa se devuelve sin cambios.
     * 
     * La tarifa base se recibe como parametro porque sus reglas viven en
     * {@link CotizadorEnvio#cotizarEnvio(Paquete)}, y no pertenecen al
     * paquete. Si la tarifa recibida es negativa (por ejemplo el codigo de
     * error -1.0) se regresa -1.0, ya que no tiene sentido aplicar un
     * descuento a un error.
     * 
     * @param tarifaBase Tarifa base del envio en pesos, tal como la regresa
     *                   {@link CotizadorEnvio#cotizarEnvio(Paquete)}.
     * @return La tarifa final en pesos, o -1.0 si la tarifa base es negativa.
     */
    public double obtenerTarifaPreferente(double tarifaBase) {
        if (tarifaBase < 0) {
            return -1.0;
        }
        if (this.clientePreferente) {
            // Descuento del 15%: se paga el 85% de la tarifa base
            return tarifaBase * 0.85;
        } else {
            return tarifaBase;
        }
    }
}
