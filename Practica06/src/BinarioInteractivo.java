import java.util.Scanner;

public class BinarioInteractivo {
	public static void main(String[] args) {
		// La herramienta que metera el dato del usuario
		Scanner entrada = new Scanner(System.in);

		// Variable para almacenar el numero en binario
		String binario = "";
		int numero = 0;

		// Variable para controlar el ciclo de validacion del numero
		boolean numeroNoValido = true;

		// Mientras el numero sea no valido, seguira pidiendo un numero positivo
		while (numeroNoValido) {

			System.out.print(">>>Conversor a Binario<<< \n Ingresa un numero positivo: ");
			// Validamos que el numero sea un entero y positivo
			if (entrada.hasNextInt()) {
				numero = entrada.nextInt();

				if (numero > 0) {

					numeroNoValido = false;

					// Si el numero es cero, se imprimira 0 y se saldra del ciclo
				} else if (numero == 0) {

					binario = "0";

					numeroNoValido = false;

				} else {

					System.out.println("Ingresa un numero positivo");
				}

			} else {
				entrada.next();
				System.out.println("Ingresa un numero entero menor a " + Integer.MAX_VALUE);
			}
		}
		// Se guardara el numero original para mostrarlo en pantalla
		int numeroPantalla = numero;
		while (numero > 0) {
			int residuo = numero % 2;
			binario = residuo + binario;
			numero = numero / 2;
		}
		System.out.println("El numero " + numeroPantalla + " en binario es:" + binario);

		// Se le preguntara si quiere volver a usarlo o no
		System.out.print("¿Quieres volver a usar el programa? (s/n): ");
		char respuesta = entrada.next().charAt(0);
		if (respuesta == 's' || respuesta == 'S') {
			main(args); // Llamada recursiva para reiniciar el programa
		} else {
			System.out.println("Programa finalizado.");
		}

		entrada.close();
	}
}
