/**
 * Representa a una persona registrada en el sistema postal, la cual puede actuar
 * como remitente o como destinatario dentro de una guia de envio.
 * Cada persona tiene un identificador, su numbre completo y la ciudad donde 
 * se encuentra, que sirve para saber si un envio es local o foraneo.
 *  * 
 * @author Lopez Mendez Adrian / 324188758, Romero Arzate Omar / 121006480
 * @version 2.0
 */
public class Persona {


    // 1. ATRIBUTOS

    /** Identificador corto y unico de la persona dentro del sistema */
    private String identificador;
    /** Nombre completo de la persona */
    private String nombreCompleto;
    /** Ciudad (o alcaldia / codigo postal) donde se encuentra la persona */
    private String ubicacion;

    // 2. CONSTRUCTORES 

    /** 
     * Constructor por omision: crea una persona generica con datos validos para 
     * que nunca queden atributos null
     */

    public Persona(){
        this.identificador = "PER-00";
        this.nombreCompleto = "Sin nombre";
        this.ubicacion = "Sin ubicacion";
    }

    /** 
     * Constructor con parametros. Si alguna cadena llega vacia
     * se usa el valor por omision que corresponda
     * 
     * @param identificador Clave corta unica de la persona 
     * @param nombreCompleto Nombre completo de la persona 
     * @param ubicacion Ciudad, alcaldia o codigo postal de la persona
     */

    public Persona(String identificador, String nombreCompleto, String ubicacion){
        this.identificador = esCadenaValida(identificador) ? identificador.trim() : "PER-00";
        this.nombreCompleto = esCadenaValida(nombreCompleto) ? nombreCompleto.trim() : "Sin nombre";
        this.ubicacion = esCadenaValida(ubicacion) ? ubicacion.trim() : "Sin ubicacion";
    }

    /** 
     * Constructor por copia: crea un objeto Persona nuevo en memoria con los 
     * mismos datos que otra. Si otra es null se usan los valores por omision 
     * 
     * @param otra La persona a copiar
     */

    public Persona(Persona otra){
        if(otra == null){
            this.identificador = "PER-OO";
            this.nombreCompleto = "Sin nombre";
            this.ubicacion = "Sin ubicacion";
        }else{
            this.identificador = otra.identificador;
            this.nombreCompleto = otra.nombreCompleto;
            this.ubicacion = otra.ubicacion;
        }
    }

    // 3. GETTERS, SETTERS Y COLABORACION 

    /** @return El identificador corto de la persona */
    public String getIdentificador(){
        return identificador;
    }

    /**
     * Cambia el identificador solo si la cadena es valida (no nula ni vacia)
     * 
     * @param identificador El nuevo identificador
     */
    public void setIdentificador(String identificador){
        if(esCadenaValida(identificador)){
            this.identificador = identificador.trim();
        }else{
            System.out.println("El identificador no puede estar vacio");
        }
    }

    /** @return El nombre completo de la persona */
    public String getNombreCompleto(){
        return nombreCompleto;
    }

    /**
     * Cambia el nombre solo si la cadena es valida (no nula ni vacia)
     * 
     * @param nombreCompleto El nuevo nombre completo
     */
    public void setNombreCompleto(String nombreCompleto){
        if(esCadenaValida(nombreCompleto)){
            this.nombreCompleto = nombreCompleto.trim();
        }else{
            System.out.println("El nombre no puede estar vacio");
        }
    }

    /** @return La ubicacion de la persona */
    public String getUbicacion(){
        return ubicacion;
    }

    /**
     * Cambia la ubicacion solo si la cadena es valida (no nula ni vacia)
     * 
     * @param ubicacion La nueva ubicacion
     */
    public void setUbicacion(String ubicacion){
        if(esCadenaValida(ubicacion)){
            this.ubicacion = ubicacion.trim();
        }else{
            System.out.println("La ubicacion no puede estar vacio");
        }
    }


    /**
     * Determina si esta persona se encuentra en la misma ubicacion (ciudad o codigo postal)
     * que otra persona recibida como parametro, para saber si un envio entre ambas
     * es local o foraneo.
     * 
     * @param otra La otra persona con quien comparar la ubicacion.
     * @return true si otra no es null y ambas comparten la misma ubicacion; false en caso contrario.
     */
    public boolean mismaUbicacionQue(Persona otra) {
        if(otra == null){
            return false;
        }
        return this.ubicacion.equalsIgnoreCase(otra.ubicacion);
    }


    /**
     * Determina si este objeto Persona y el recibido como parametro representan
     * a la misma persona comparando su identificador unico.
     * 
     * @param otra La otra persona a comparar.
     * @return true si otra no es null y ambas tienen el mismo identificador; false en caso contrario.
     */
    public boolean esLaMismaPersona(Persona otra) {
        if(otra == null){
            return false;
        }
        return this.identificador.equals(otra.identificador);
    }

    /**
     * Metodo auxiliar que revisa que una cadena no sea null ni este vacia
     * (o formada solo por espacios).
     *
     * @param cadena La cadena a revisar.
     * @return true si la cadena tiene contenido; false en caso contrario.
     */
    private static boolean esCadenaValida(String cadena) {
        return cadena != null && !cadena.trim().isEmpty();
    }

    // 4. REPRESENTACION TEXTUAL

    /**
     * Devuelve una representacion legible de la persona con sus datos principales.
     * 
     * @return Cadena con el identificador, nombre y ubicacion de la persona.
     */
    @Override
    public String toString() {
        return "[" + identificador + "] " + nombreCompleto + " (" + ubicacion + ")";
    }
}
