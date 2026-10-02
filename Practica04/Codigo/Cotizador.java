public class Cotizador {
    public static void main(String[] args) {
        //Estas tres primeras lineas me sirven para definir los datos del cliente, lo que pidio prestado, quien pidio prestado y el tipo de cliente que es quien pidio el prestamo
        int precioCliente1 = 12899;
        String cliente1 = "Robbie Valentino";
        char clasificacionCliente = 'E';

        //Estas 2 lineas siguientes me sirven para definir los parametros del prestamo, el interes anual con el que se maneja el prestamo y el plazo en años en el que el cliente pagara su prestamo
        double tasaAnual = 0.15;
        double plazoCliente1 = (21.0 / 12);

        //Estas ultimas 3 son mi carnita, las que estan haciendo las operaciones que me daran lo que mi cliente necesita saber...
        double interes = (precioCliente1 * tasaAnual * plazoCliente1);
        //double interes calculara solo el interes que se pagara de la laptop, cuanto pagara en puro interes
        double total = (precioCliente1 + interes);
        //double total le dira a mi cliente cuanto pagara por la laptop mas el interes del prestamo
        double mensualidades = (total / 21);
        //double mensualidades calcula cuanto pagara mes con mes nuestro cliente con base a los meses que pidio para el prestamo y el total a pagar

        /*System.out.println("Hola " + cliente1);
        System.out.println("Cliente " + clasificacionCliente);
        System.out.println("El interes a pagar suma la cantidad de $" + interes + " pesos");
        System.out.println("Le debes al Sr.Pines un total de $" + total + " pesos");
        System.out.println("Tienes que pagar $" + mensualidades + " pesos cada primero de mes");
*/
        //Las proximas lineas de codigo son la interfaz de mi programa, por eso trate de darle un buen diseño, copiandolo del laboratorista pero al parecer parece IA :(
        System.out.println("=== Cotizador Misterio ===");
        //Esta linea es mi nombre del programa, al parecer sacado de la IA
        System.out.printf("Hola %s, usted es un cliente tipo %c, ¡Bienvenido!.\n", cliente1, clasificacionCliente);
        //lo que hago en la linea 29 es recibir a mi cliente, con un printf hago una cadena de texto con especificadores de formato, que digan la informacion de mi cliente
        System.out.printf("Le debes al Sr.Pines un total de $%.2f, de lo cual $%.2f, son solo los intereses.\n", total, interes);
        //aqui se le dice al cliente, cuanto va a pagar en total y cuanto de ese total es puro interes
        System.out.printf("Tienes que pagar $%.2f mensuales.\n", mensualidades);
        //esta ultima importante le dice al cliente cuanto pagara mensualmente hasta liquidar su deuda total
        System.out.println("=== No te atrases ===");
        //esta linea era una despedida baseada a mi cliente pero al parecer parece IA, asi que la podriamos omitir 

    }
}