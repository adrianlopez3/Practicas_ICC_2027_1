public class PrecisionPi {
    public static void main(String[] args) {
        double tolerancia = 0.001;
        double aproxPi = 0;
        double error = Math.abs((aproxPi - Math.PI));

        // Lanzamientos
        int dardosLanzados = 0;
        // Lanzamientos dentro del circulo
        int dardosDentro = 0;

        while (error >= tolerancia) {
            // agragamos las variables aleatorias para nuestros pares de coordenadas
            double coordEnX = Math.random();
            double coordEnY = Math.random();

            // usamos la funcion para calcular distancias
            double distancia_centro = Math.sqrt(Math.pow(coordEnX, 2) + Math.pow(coordEnY, 2));

            // verificamos si nuestros dardos cayeron dentro o fuera del circulo
            if (distancia_centro <= 1.0) {
                // si cumplen la condicion se agregan los valores al contador
                dardosDentro++;
            }
            // si la o no la cumple aumentara el contador de lanzamientos

            dardosLanzados++;

            aproxPi = 4.0 * dardosDentro / dardosLanzados;

            error = Math.abs((aproxPi - Math.PI));

        }

        System.out.printf("%.6f%n", aproxPi);
        System.out.println(dardosLanzados);
    }
}