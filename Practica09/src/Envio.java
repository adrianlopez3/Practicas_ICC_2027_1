/**
 * Modela una Guia de Envio postal relacionando a una Persona remitente, 
 * una Persona destinatario y un objeto Paquete.
 * 
 * Cada guia recibe automaticamente un folio unico al momento de crearse
 * (compuesto por caracteres aleatorios y un numero consecutivo global).
 * 
 * @author Lopez Mendez Adrian / 324188758
 * @version 2.0
 */
public class Envio {

    // 1. ATRIBUTOS

    /** Cargo adicional fijo en pesos para envios foraneos (entre distintas ubicaciones). */
    public static final double CARGO_FORANEO = 80.0;

    /** Caracteres con los que construiremos el folio */
    private static final String CARACTERES_FOLIO = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    /**
    * Contador de guias. Es static porque pertenece a la clase y no a cada guia,
    * asi todas las guias comparten el mismo conteo
    */
   private static int totalGuiasHechas = 0;

   /** Folio unico de una guia con formato "GUIA-XXX-N" */

   private String folioGuia;

   /** Persona que envia el paquete */
   private Persona remitente;

   /** Persona que recibe el paquete */
   private Persona destinatario;

   /** Paquete fisico validado por esta guia */
   private Paquete paquete;

    // 2. CONSTRUCTORES 

    /**
     * Constructor principal para emitir una nueva guia de envio.
     * Genera automaticamente el folioGuia (2 o 3 letras/digitos aleatorios + consecutivo).
     * Valida que ninguna referencia recibida sea invalida; si se envia a la misma
     * persona se permite (envio personal o mudanza).
     * 
     * @param remitente Persona que envia el paquete.
     * @param destinatario Persona que recibe el paquete.
     * @param paquete Paquete fisico amparado por esta guia.
     */
    public Envio(Persona remitente, Persona destinatario, Paquete paquete) {
        //Si alguna referencia llega null usamos un objeto por omision 
        this.remitente = (remitente != null) ? remitente:new Persona();
        this.destinatario = (destinatario != null) ? destinatario:new Persona();
        //El paquete ademas debe pasar la revision del cotizador
        this.paquete = CotizadorEnvio.esPaqueteValido(paquete) ? paquete:new Paquete();
        this.folioGuia = generarFolio();
    }

    /**
     * Constructor para copiar una guia existente cuando el mismo remitente envia
     * un nuevo paquete al mismo destinatario.
     * Conserva las referencias a remitente y destinatario de guiaBase, asocia el
     * nuevoPaquete y genera un NUEVO folioGuia consecutivo para esta guia.
     * 
     * @param guiaBase Guia original de la cual se toman remitente y destinatario.
     * @param nuevoPaquete El nuevo paquete que viajara bajo esta nueva guia.
     */
    public Envio(Envio guiaBase, Paquete nuevoPaquete) {
        if(guiaBase != null){
            //Se comparten las mismas personas (misma referencia), no se copian
            this.remitente = guiaBase.remitente;
            this.destinatario = guiaBase.destinatario;
        }else{
            this.remitente = new Persona();
            this.destinatario = new Persona();
        }
        this.paquete = CotizadorEnvio.esPaqueteValido(nuevoPaquete) ? nuevoPaquete:new Paquete();
        this.folioGuia = generarFolio();
    }


    // 3. METODOS DE ACCESO (GETTERS) Y CONSULTA DE GUIAS EMITIDAS

    /** @return El folio unico generado para esta guia. */
    public String getFolioGuia() {
        return folioGuia;
    }

    /** @return La persona remitente de la guia */
    public Persona getRemitente(){
        return remitente;
    }
    
    /** @return La persona destinataria de la guia */
    public Persona getDestinatario(){
        return destinatario;
    }

    /** @return El paquete validado por la guia */
    public Paquete getPaquete(){
        return paquete;
    }

    /** @return El numero de guias hechas */
    public static int getTotalGuiasHechas(){
        return totalGuiasHechas;
    }
    /**
     * Este metodo auxiliar me ayudara a aumentar el contador global y a armar 
     * el folio con formato "GUIA-XXX-N": donde XXX son 2 o 3 letras/digitos al 
     * azar y N el consecutivo global de la guia.
     *
     * @return El nuevo folio generado.
     */
    private static String generarFolio() {
        totalGuiasHechas++;

        // Decidimos al azar si la parte aleatoria lleva 2 o 3 caracteres
        int longitud = (Math.random() < 0.5) ? 2 : 3;
        String aleatorio = "";
        for (int i = 0; i < longitud; i++) {
            int posicion = (int) (Math.random() * CARACTERES_FOLIO.length());
            aleatorio = aleatorio + CARACTERES_FOLIO.charAt(posicion);
        }
        return "GUIA-" + aleatorio + "-" + totalGuiasHechas;
    }
    

    // 4. METODOS DE OPERACION SOBRE LOS OBJETOS AGREGADOS  

    /**
     * Determina si el envio es foraneo verificando si el remitente y el destinatario
     * se encuentran en distinta ubicacion.
     * 
     * @return true si viaja entre distintas ubicaciones; false si es un envio local.
     */
    public boolean esEnvioForaneo() {
        return !remitente.mismaUbicacionQue(destinatario);
    }

    /**
     * Determina si el remitente y el destinatario representan a la misma persona
     * (por ejemplo, un envio personal o mudanza).
     * 
     * @return true si remitente y destinatario son la misma persona; false en caso contrario.
     */
    public boolean esAutoEnvio() {
        return remitente.esLaMismaPersona(destinatario);
    }

    /**
     * Calcula el costo total del envio:
     * Obtiene la tarifa base del paquete y, si es un envio foraneo,
     * suma el cargo adicional correspondiente.
     * 
     * @return El costo total de la guia en pesos (MXN), o -1.0 si el paquete es invalido.
     */
    public double calcularCostoTotal() {
        double tarifaBase = CotizadorEnvio.cotizarEnvio(paquete);
        if(tarifaBase < 0){
            return -1.0;
        }
        if(esEnvioForaneo()){
            return tarifaBase + CARGO_FORANEO;
        }
        return tarifaBase;
    }

    /**
     * Redirige el envio a un nuevo destinatario antes de su despacho.
     * Verifica unicamente que nuevoDestinatario no sea null.
     * 
     * @param nuevoDestinatario Nueva persona destinataria.
     * @return true si el cambio fue valido y aplicado; false si nuevoDestinatario es null.
     */
    public boolean cambiarDestinatario(Persona nuevoDestinatario) {
        if(nuevoDestinatario == null){
            return false;
        }
        this.destinatario = nuevoDestinatario;
        return true;
    }

    /**
     * Genera y devuelve una NUEVA guia de envio para devolver la mercancia al origen:
     * 1. Invierte los roles entre remitente y destinatario.
     * 2. Crea una copia independiente del paquete en memoria y actualiza su codigo
     *    de rastreo anteponiendo el prefijo "RET-".
     * 3. La nueva guia recibe automaticamente su propio folioGuia consecutivo.
     * 
     * @return Nuevo objeto Envio configurado para el retorno al remitente original.
     */
    public Envio generarGuiaRetorno() {
        //Copia de seguridad para no alterar el original
        Paquete paqueteDevuelto = new Paquete(this.paquete);
        paqueteDevuelto.setCodigoRastreo("RET-"+ paqueteDevuelto.getCodigoRastreo()); 

        //Se invierten los roles
        return new Envio(this.destinatario, this.remitente, paqueteDevuelto);
    }
    

    /**
     * Emite el comprobante textual de la guia de envio delegando en toString()
     * de remitente, destinatario y paquete, e indicando el folio, tipo de ruta
     * (LOCAL o FORANEO) y el costo total.
     * 
     * @return Cadena con el formato completo de la guia de envio.
     */
    @Override
    public String toString() {
        String ruta = esEnvioForaneo() ? "FORANEO" : "LOCAL";
        String personal = esAutoEnvio() ? "SI (envio personal / mudanza)" : "NO";

        return "/////////////////////////////\n"
        + " GUIA DE ENVIO: " + folioGuia + "\n"
        + "/////////////////////////////\n"
        + " Reminente: " + remitente.toString() + "\n"
        + " Destinatario: " + destinatario.toString() + "\n"
        + " Tipo de ruta: " + ruta + "\n"
        + " Auto envio: " + personal + "\n"
        + " /// paquete ///\n" + paquete.toString() + "\n"
        + " /// Costos ///\n"
        + String.format(" Tarifa base: $%.2f%n", CotizadorEnvio.cotizarEnvio(paquete))
        + String.format(" Cargo foraneo: $%.2f%n", esEnvioForaneo() ? CARGO_FORANEO : 0.0)
        + String.format(" Costo total: $%.2f%n", calcularCostoTotal())
        + "/////////////////////////////\n";
    }
}
