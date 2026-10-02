public class ProgramaNuevo {
	public static void main(String[] args) {

		// Usamos un String para guardar el nombre del producto en forma de texto
		// ("Laptop para la carrera").
		String producto = "Laptop para la carrera";
		// Usamos una variable int para darle un valor numerico al precio de nuestro
		// producto.
		int precio = 15000;
		// Usamos una variable int para darle un valor numerico al descuento de nuestro
		// producto.
		int descuento = 3000;
		int precioDescuento = (precio - descuento);
		// Usamos un double para darle un valor exacto a nuestra variable, nos sera de
		// utilidad al momento de los calculos.
		double meses = 18.0;
		double plazoAnios = (meses / 12.0);
		double pagoMensual = ((precio - descuento) / meses);
		/*
		 * // Agregamos un titulo a nuestro pequeño programa.
		 * System.out.println("=== Ficha de compra ===");
		 * // Imprimiremos la concatenacion del contenido de nuestra variable con el
		 * texto del print.
		 * System.out.println("- Producto : " + producto);
		 * // Imprimiremos el precio de la laptop con el descuento incluido.
		 * System.out.println("- Precio con descuento : " + (precio - descuento));
		 * // Mostramos los años que nos tardaremos en pagar la laptop.
		 * System.out.println("- Plazo de pago en anios : " + (meses / 12.0));
		 * // Aqui imprimimos cuanto pagaremos mensualmente por la laptop.
		 * System.out.println("- Pago mensual : " + ((precio - descuento) / meses));
		 * // Damos por finalizado el programa con un titulo.
		 * System.out.println("=== Fin de la ficha ===");
		 */
		System.out.println("=== Ficha de compra ===");
		/*
		 * System.out.printf("- Producto : %s.\n", producto);
		 * System.out.printf("- Precio con descuento : %d.\n", precioDescuento);
		 * System.out.printf("- Plazo de pago en anios : %.1f.\n", plazoAnios);
		 * System.out.printf("- Pago mesual : %.1f.\n", pagoMensual);
		 */
		// La linea de abajo funciona usando un .printf donde dentro de los parentesis
		// haremos varios especificadores de formato, escritos de forma continua para al
		// final solo llamar a las variables ya establecidas
		System.out.printf(
				"- Producto : %s, - Precio con descuento : %d, - Plazo de pago en anios : %.1f, - Pago mensual : %.1f.\n",
				producto, precioDescuento, plazoAnios, pagoMensual);
		System.out.println("=== Fin de la ficha ===");

	}
}