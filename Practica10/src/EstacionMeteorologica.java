/**
 * Modela una estacion meteorologica universitaria que almacena y procesa
 * las temperaturas diarias (en grados Celsius) de un periodo de monitoreo.
 *
 * @author [Tu Nombre Completo]
 * @version 1.0
 */
public class EstacionMeteorologica {

    // CONSTANTES DE CLASE

    /** Marca que indica que un dia aun no tiene lectura registrada. */
    public static final double SIN_LECTURA = -300.0;

    /** Temperatura ambiental minima fisicamente valida (grados Celsius). */
    public static final double TEMP_MINIMA = -90.0;

    /** Temperatura ambiental maxima fisicamente valida (grados Celsius). */
    public static final double TEMP_MAXIMA = 60.0;

    /** Nombre asignado cuando no se proporciona uno valido. */
    public static final String NOMBRE_PREDETERMINADO = "Estacion UNAM";

    /** Numero de dias asignado cuando no se proporciona uno valido. */
    public static final int DIAS_PREDETERMINADOS = 7;

    // ATRIBUTOS

    /** Nombre de la sede de la estacion. */
    private String nombreEstacion;

    /** Temperaturas diarias registradas en grados Celsius. */
    private double[] temperaturas;

    // CONSTRUCTORES

    /**
     * Inicializa la estacion con el nombre predeterminado "Estacion UNAM"
     * y reserva espacio para una semana (7 dias), marcando cada posicion
     * inicialmente sin lectura registrada (-300.0).
     */
    public EstacionMeteorologica() {
        this.nombreEstacion = NOMBRE_PREDETERMINADO;
        this.temperaturas = new double[DIAS_PREDETERMINADOS];
        // Recorre todas las casillas y las marca como "sin lectura"
        for (int i = 0; i < this.temperaturas.length; i++) {
            this.temperaturas[i] = SIN_LECTURA;
        }
    }

    /**
     * Inicializa la estacion con el nombre y la duracion indicados.
     * Si el nombre es {@code null} o vacio se usa "Estacion UNAM"; si el
     * numero de dias no es positivo se usan 7 dias. Todas las casillas
     * inician sin lectura registrada (-300.0).
     *
     * @param nombreEstacion Nombre de la sede de la estacion.
     * @param numDias Cantidad de dias que abarca el periodo de monitoreo.
     */
    public EstacionMeteorologica(String nombreEstacion, int numDias) {
        if (nombreEstacion == null || nombreEstacion.trim().isEmpty()) {
            this.nombreEstacion = NOMBRE_PREDETERMINADO;
        } else {
            this.nombreEstacion = nombreEstacion;
        }

        if (numDias <= 0) {
            numDias = DIAS_PREDETERMINADOS;
        }
        this.temperaturas = new double[numDias];

        // Recorre todas las casillas y las marca como "sin lectura"
        for (int i = 0; i < this.temperaturas.length; i++) {
            this.temperaturas[i] = SIN_LECTURA;
        }
    }

    // METODOS DE CONSULTA Y REGISTRO (PARTE 1)

    /**
     * Devuelve el nombre de la sede de la estacion.
     *
     * @return Nombre de la estacion.
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
        if (!indiceValido || !tempValida) {
            return false;
        }
        this.temperaturas[indiceDia] = temp;
        return true;
    }

    /**
     * Devuelve la temperatura almacenada en un dia especifico.
     *
     * @param indiceDia Posicion del dia a consultar (desde 0 hasta N - 1).
     * @return La lectura almacenada en grados Celsius, o -300.0 si el indice
     *         esta fuera de los limites del arreglo o el dia no tiene lectura.
     */
    public double obtenerTemperatura(int indiceDia) {
        if (indiceDia < 0 || indiceDia >= this.temperaturas.length) {
            return SIN_LECTURA;
        }
        return this.temperaturas[indiceDia];
    }

    // METODOS ESTADISTICOS (PARTE 2)

    /**
     * Calcula el promedio aritmetico de las temperaturas del periodo,
     * asumiendo que todos los dias cuentan con lectura registrada.
     *
     * @return Promedio de las temperaturas en grados Celsius.
     */
    public double calcularPromedio() {
        double suma = 0.0;
        // Acumula cada lectura del arreglo
        for (int i = 0; i < this.temperaturas.length; i++) {
            suma = suma + this.temperaturas[i];
        }
        return suma / this.temperaturas.length;
    }

    /**
     * Obtiene la temperatura mas alta registrada en el periodo. Funciona
     * aun cuando todas las lecturas son negativas.
     *
     * @return Temperatura maxima en grados Celsius.
     */
    public double obtenerMaxima() {
        // Se parte de la primera lectura real (no de 0.0) para soportar negativos
        double maxima = this.temperaturas[0];
        // Compara el resto de las casillas contra el maximo actual
        for (int i = 1; i < this.temperaturas.length; i++) {
            if (this.temperaturas[i] > maxima) {
                maxima = this.temperaturas[i];
            }
        }
        return maxima;
    }

    /**
     * Cuenta cuantos dias tuvieron una temperatura estrictamente mayor
     * a un umbral dado.
     *
     * @param umbral Temperatura de alerta en grados Celsius.
     * @return Numero de dias cuya temperatura supera estrictamente el umbral.
     */
    public int contarDiasCalurosos(double umbral) {
        int contador = 0;
        // Revisa cada dia e incrementa el contador si rebasa el umbral
        for (int i = 0; i < this.temperaturas.length; i++) {
            if (this.temperaturas[i] > umbral) {
                contador++;
            }
        }
        return contador;
    }

    /**
     * Devuelve un resumen estadistico de la estacion con el formato
     * "Estacion: &lt;nombre&gt; | Dias: &lt;N&gt; | Promedio: &lt;prom&gt; C | Maxima: &lt;max&gt; C".
     *
     * @return Cadena con el resumen de la estacion.
     */
    @Override
    public String toString() {
        return "Estacion: " + this.getNombreEstacion()
                + " | Dias: " + this.getNumeroDias()
                + " | Promedio: " + this.calcularPromedio() + " C"
                + " | Maxima: " + this.obtenerMaxima() + " C";
    }

    // CONVERSION DE ESCALAS (PARTE 3)

    /**
     * Convierte una temperatura de grados Celsius a grados Fahrenheit
     * mediante la relacion F = C * 1.8 + 32.0. Es un metodo de clase
     * porque no depende del estado de ninguna estacion.
     *
     * @param celsius Temperatura en grados Celsius.
     * @return Temperatura equivalente en grados Fahrenheit.
     */
    public static double convertirAFahrenheit(double celsius) {
        return celsius * 1.8 + 32.0;
    }

    /**
     * Calcula el promedio de las temperaturas en la escala solicitada.
     *
     * @param escala Escala en la que se desea el promedio; si es
     *               {@code Escala.FAHRENHEIT} se convierte, en cualquier
     *               otro caso se devuelve en Celsius.
     * @return Promedio de las temperaturas en la escala indicada.
     */
    public double calcularPromedio(Escala escala) {
        double promedioCelsius = this.calcularPromedio();
        if (escala == Escala.FAHRENHEIT) {
            return convertirAFahrenheit(promedioCelsius);
        }
        return promedioCelsius;
    }

    // RETOS (PARTE 4)

    /**
     * Busca el primer dia del periodo con temperatura igual o inferior
     * al punto de congelacion (0.0 C).
     *
     * @return Indice del primer dia con helada, o -1 si no hubo heladas.
     */
    public int buscarPrimerDiaHelada() {
        // Recorre desde el dia 0 y se detiene en cuanto encuentra una helada
        for (int i = 0; i < this.temperaturas.length; i++) {
            if (this.temperaturas[i] <= 0.0) {
                return i;
            }
        }
        // El ciclo termino sin encontrar ninguna helada
        return -1;
    }

    /**
     * Calcula la mayor diferencia absoluta |t[i+1] - t[i]| entre dos dias
     * consecutivos del periodo.
     *
     * @return La mayor variacion entre dias seguidos, o 0.0 si hay menos
     *         de 2 dias.
     */
    public double mayorCambioConsecutivo() {
        double mayorCambio = 0.0;
        // Avanza solo hasta length - 2 para que i + 1 no se salga del arreglo
        for (int i = 0; i < this.temperaturas.length - 1; i++) {
            double cambio = Math.abs(this.temperaturas[i + 1] - this.temperaturas[i]);
            if (cambio > mayorCambio) {
                mayorCambio = cambio;
            }
        }
        return mayorCambio;
    }
}
