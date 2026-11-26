/**
 * Modela una estacion meteorologica universitaria que almacena y procesa
 * las temperaturas diarias (en grados Celsius) de un periodo de monitoreo.
 *
 * @author Lopez Mendez Adrian / 324188758
 * @author Romero Arzate Omar / 121006480
 * @version 1.0
 */
public class EstacionMeteorologica {

    // CONSTANTES DE CLASE

    /** Marca de un dia sin lectura registrada (no es una temperatura real). */
    private static final double SIN_LECTURA = -300.0;

    /** Temperatura minima fisicamente valida en grados Celsius. */
    private static final double TEMP_MINIMA = -90.0;

    /** Temperatura maxima fisicamente valida en grados Celsius. */
    private static final double TEMP_MAXIMA = 60.0;

    /** Punto de congelacion en grados Celsius, se usa para detectar heladas. */
    private static final double PUNTO_CONGELACION = 0.0;

    /** Nombre que se usa cuando no se recibe uno valido. */
    private static final String NOMBRE_POR_OMISION = "Estacion UNAM";

    /** Dias que se usan cuando no se recibe una cantidad valida (una semana). */
    private static final int DIAS_POR_OMISION = 7;

    // ATRIBUTOS

    /** Nombre de la sede a la que pertenece la estacion. */
    private String nombreEstacion;

    /** Temperatura de cada dia del periodo; la casilla i guarda el dia i. */
    private double[] temperaturas;


    // CONSTRUCTORES

    /**
     * Inicializa la estacion con el nombre predeterminado "Estacion UNAM"
     * y reserva espacio para una semana (7 dias), marcando cada posicion
     * inicialmente sin lectura registrada (-300.0).
     */
    public EstacionMeteorologica() {
        this.nombreEstacion = NOMBRE_POR_OMISION;
        this.temperaturas = new double[DIAS_POR_OMISION];

        // Recorremos cada casilla del arreglo y la marcamos como dia sin lectura
        for (int i = 0; i < this.temperaturas.length; i++) {
            this.temperaturas[i] = SIN_LECTURA;
        }
    }

    /**
     * Inicializa una estacion con el nombre y la cantidad de dias indicados,
     * marcando cada dia inicialmente sin lectura registrada (-300.0).
     * Si el nombre es null o esta vacio se usa "Estacion UNAM", y si el
     * numero de dias no es positivo se usa una semana (7 dias).
     *
     * @param nombreEstacion Nombre de la sede de la estacion.
     * @param numDias Cantidad de dias que dura el periodo de monitoreo.
     */
    public EstacionMeteorologica(String nombreEstacion, int numDias) {
        // Validamos el nombre; si no es valido usamos el nombre por omision
        if (nombreEstacion == null || nombreEstacion.trim().isEmpty()) {
            this.nombreEstacion = NOMBRE_POR_OMISION;
        } else {
            this.nombreEstacion = nombreEstacion;
        }

        // Validamos los dias; si no es un numero positivo usamos una semana
        if (numDias > 0) {
            this.temperaturas = new double[numDias];
        } else {
            this.temperaturas = new double[DIAS_POR_OMISION];
        }

        // Recorremos cada casilla del arreglo y la marcamos como dia sin lectura
        for (int i = 0; i < this.temperaturas.length; i++) {
            this.temperaturas[i] = SIN_LECTURA;
        }
    }

    // METODOS DE CONSULTA Y REGISTRO (PARTE 1)

    /**
     * Devuelve el nombre de la estacion.
     *
     * @return Nombre de la sede a la que corresponde el registro.
     */
    public String getNombreEstacion() {
        return this.nombreEstacion;
    }

    /**
     * Devuelve cuantos dias abarca el periodo de monitoreo.
     *
     * @return Longitud del arreglo de temperaturas.
     */
    public int getNumeroDias() {
        return this.temperaturas.length;
    }

    /**
     * Registra la temperatura de un dia especifico en grados Celsius.
     * Solo modifica el arreglo si el indice es valido y la temperatura esta
     * dentro del rango fisico ambiental [-90.0, 60.0].
     *
     * @param indiceDia Posicion del dia a registrar (desde 0 hasta N - 1).
     * @param temp Temperatura ambiental registrada en grados Celsius.
     * @return true si el dato fue valido y se almaceno; false en caso contrario.
     */
    public boolean registrarTemperatura(int indiceDia, double temp) {
        boolean indiceValido = indiceDia >= 0 && indiceDia < this.temperaturas.length;
        boolean tempValida = temp >= TEMP_MINIMA && temp <= TEMP_MAXIMA;

        // Solo guardamos la lectura si el dia existe y la temperatura es posible
        if (indiceValido && tempValida) {
            this.temperaturas[indiceDia] = temp;
            return true;
        }
        return false;
    }

    /**
     * Devuelve la temperatura guardada en un dia especifico.
     *
     * @param indiceDia Posicion del dia a consultar (desde 0 hasta N - 1).
     * @return La lectura de ese dia (-300.0 si aun no tiene lectura),
     *         o -300.0 si el indice esta fuera de los limites del arreglo.
     */
    public double obtenerTemperatura(int indiceDia) {
        // Si el indice se sale del arreglo no hay lectura que devolver
        if (indiceDia < 0 || indiceDia >= this.temperaturas.length) {
            return SIN_LECTURA;
        }
        return this.temperaturas[indiceDia];
    }

    // METODOS ESTADISTICOS (PARTE 2)

    /**
     * Calcula el promedio de las temperaturas del periodo en grados Celsius.
     * Supone que todos los dias ya tienen una lectura registrada.
     *
     * @return Promedio aritmetico de las temperaturas del arreglo.
     */
    public double calcularPromedio() {
        double suma = 0.0;

        // Recorremos todo el arreglo y vamos acumulando cada temperatura
        for (int i = 0; i < this.temperaturas.length; i++) {
            suma = suma + this.temperaturas[i];
        }
        return suma / this.temperaturas.length;
    }

    /**
     * Busca la temperatura mas alta del periodo. Funciona aunque todas
     * las lecturas sean negativas.
     *
     * @return La temperatura maxima registrada en grados Celsius.
     */
    public double obtenerMaxima() {
        // Empezamos con la primera lectura (y no con 0.0) para no inventar un maximo
        double maxima = this.temperaturas[0];

        // Comparamos las demas casillas y nos quedamos con la mayor
        for (int i = 1; i < this.temperaturas.length; i++) {
            if (this.temperaturas[i] > maxima) {
                maxima = this.temperaturas[i];
            }
        }
        return maxima;
    }

    /**
     * Cuenta cuantos dias tuvieron una temperatura estrictamente mayor
     * a un umbral de alerta termica.
     *
     * @param umbral Temperatura limite en grados Celsius.
     * @return Numero de dias cuya temperatura es mayor que umbral.
     */
    public int contarDiasCalurosos(double umbral) {
        int diasCalurosos = 0;

        // Recorremos el arreglo y sumamos uno por cada dia que rebasa el umbral
        for (int i = 0; i < this.temperaturas.length; i++) {
            if (this.temperaturas[i] > umbral) {
                diasCalurosos++;
            }
        }
        return diasCalurosos;
    }

    /**
     * Genera un resumen con los indicadores principales de la estacion,
     * por ejemplo: "Estacion: CU-UNAM | Dias: 5 | Promedio: 19.8 C | Maxima: 23.0 C".
     *
     * @return Cadena con el nombre, los dias, el promedio y la maxima de la estacion.
     */
    @Override
    public String toString() {
        return "Estacion: " + this.nombreEstacion + " | Dias: " + getNumeroDias()
                + " | Promedio: " + calcularPromedio() + " C | Maxima: " + obtenerMaxima() + " C";
    }

    // CONVERSION DE ESCALAS (PARTE 3)

    /**
     * Convierte una temperatura de grados Celsius a grados Fahrenheit con la
     * formula F = C * 1.8 + 32.0. Es un metodo de clase (static) porque solo
     * depende del valor que recibe y no de los datos de una estacion.
     *
     * @param celsius Temperatura en grados Celsius.
     * @return La misma temperatura en grados Fahrenheit.
     */
    public static double convertirAFahrenheit(double celsius) {
        return celsius * 1.8 + 32.0;
    }

    /**
     * Calcula el promedio de las temperaturas del periodo en la escala pedida.
     *
     * @param escala Escala en la que se quiere el resultado (CELSIUS o FAHRENHEIT).
     * @return El promedio en Fahrenheit si se recibe Escala.FAHRENHEIT;
     *         en cualquier otro caso, el promedio en Celsius.
     */
    public double calcularPromedio(Escala escala) {
        double promedio = calcularPromedio();

        if (escala == Escala.FAHRENHEIT) {
            return convertirAFahrenheit(promedio);
        }
        return promedio;
    }

    // RETOS (PARTE 4)

    /**
     * Busca el primer dia del periodo con helada, es decir, con temperatura
     * igual o menor a 0.0 C. Los dias sin lectura (-300.0) no cuentan.
     *
     * @return Indice del primer dia con helada, o -1 si no hubo heladas.
     */
    public int buscarPrimerDiaHelada() {
        // Recorremos desde el dia 0 y nos detenemos en cuanto aparece la primera helada
        for (int i = 0; i < this.temperaturas.length; i++) {
            if (this.temperaturas[i] != SIN_LECTURA && this.temperaturas[i] <= PUNTO_CONGELACION) {
                return i;
            }
        }
        // Si el ciclo termino sin encontrar ninguna, no hubo heladas
        return -1;
    }

    /**
     * Calcula la mayor variacion de temperatura entre dos dias seguidos,
     * es decir, el mayor valor de |t[i+1] - t[i]|. Los pares que incluyen
     * un dia sin lectura (-300.0) no se toman en cuenta.
     *
     * @return El mayor cambio entre dias consecutivos en grados Celsius,
     *         o 0.0 si hay menos de 2 dias.
     */
    public double mayorCambioConsecutivo() {
        double mayorCambio = 0.0;

        // El ciclo llega solo al penultimo dia porque cada vuelta compara i con i + 1
        for (int i = 0; i < this.temperaturas.length - 1; i++) {
            // Solo comparamos si los dos dias tienen una lectura real
            if (this.temperaturas[i] != SIN_LECTURA && this.temperaturas[i + 1] != SIN_LECTURA) {
                double cambio = Math.abs(this.temperaturas[i + 1] - this.temperaturas[i]);
                if (cambio > mayorCambio) {
                    mayorCambio = cambio;
                }
            }
        }
        return mayorCambio;
    }
}
