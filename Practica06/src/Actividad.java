import java.util.Scanner;

public class Actividad {
	public static void main(String[] args) {

		Scanner entrada = new Scanner(System.in);

		String binario = "";
		int numero = 0;

		boolean numeroNoValido = true;

		while (numeroNoValido) {

			System.out.print(">>>Conversor a Binario<<< \n Ingresa un numero positivo: ");
			if (entrada.hasNextInt()) {
				numero = entrada.nextInt();

				if (numero > 0) {

					numeroNoValido = false;

				} else {

					System.out.println("Ingresa un numero mayor a cero");
				}
			} else {

				entrada.next();
				System.out.println("Ingresa un numero");
			}
		}

		while (numero > 0) {
			int residuo = numero % 2;
			binario = residuo + binario;
			numero = numero / 2;
		}
		System.out.println(binario);
	}
}
