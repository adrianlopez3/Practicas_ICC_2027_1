public class Programa {
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
		// Usamos un double para darle un valor exacto a nuestra variable, nos sera de
		// utilidad al momento de los calculos.
		double meses = 18.0;

		// Agregamos un titulo a nuestro pequeño programa.
		System.out.println("=== Ficha de compra ===");
		// Imprimiremos la concatenacion del contenido de nuestra variable con el texto
		// del print.
		System.out.println("- Producto : " + producto);
		// Imprimiremos el precio de la laptop con el descuento incluido.
		System.out.println("- Precio con descuento : " + (precio - descuento));
		// Mostramos los años que nos tardaremos en pagar la laptop.
		System.out.println("- Plazo de pago en anios : " + (meses / 12.0));
		// Aqui imprimimos cuanto pagaremos mensualmente por la laptop.
		System.out.println("- Pago mensual : " + ((precio - descuento) / meses));
		// Damos por finalizado el programa con un titulo.
		System.out.println("=== Fin de la ficha ===");

	}
}