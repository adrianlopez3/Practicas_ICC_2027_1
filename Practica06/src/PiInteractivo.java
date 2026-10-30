import java.util.Scanner;

public class PiInteractivo {
    public static void main(String[] args) {
        // La herramienta que metera el dato del usuario
        Scanner entrada = new Scanner(System.in);

        // Total de dardos
        int dardosTotales = 0;
        boolean dardosNoValidos = true;
        // Mientras el numero de dardos sea no valido, seguira pidiendo un numero positivo
        while (dardosNoValidos) {
            System.out.print(">>>Pi Interactivo<<<\n ¿Cuántos dardos quieres lanzar? :");

            if (entrada.hasNextInt()) {
                dardosTotales = entrada.nextInt();

                if (dardosTotales > 0) {

                    dardosNoValidos = false;
                } else {
                    System.out.println("Ingresa un numero positivo");
                }
            } else {

                entrada.next();
                System.out.println("Ingresa un valor numerico entero");
            }
        }

        // Lanzamientos
        int dardosLanzados = 0;
        // Lanzamientos dentro del circulo
        int dardosDentro = 0;

        // Mientras el total de dardos sea mayor a los lanzamientos entrara al while
        while (dardosTotales > dardosLanzados) {
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

        }
        // aproximamos pi con MonteCarlo
        double aproxPi = 4.0 * dardosDentro / dardosTotales;

        System.out.printf("Estimación de pi: %.6f%n", aproxPi);
        entrada.close();
    }
}