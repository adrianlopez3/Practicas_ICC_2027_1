public class Errores {

    public static void main(String[] args) {

        System.out.println("=== Ficha de compra ===");
        System.out.println("Producto: laptop para la carrera");
        System.out.println("Precio con descuento:" + (15000 - 3000));
        System.out.println("Plazo en años:" + 18.0 / 12);
        System.out.println("Pago mensual:" + ((15000 - 3000) / 18.0));
        System.out.println("Folio en binario:" + Integer.toBinaryString(26));
        System.out.println("Clave descifrada:" + Integer.parseInt("47", 8));
        System.out.println("Fin de la ficha");
        System.out.println("los primeros 3 errores fueron por codigo mal escrito");
        System.out.println("los proximos 2 errores fueron codigo bien escrito pero sin sentido");
        System.out.println("los ultimos 3 errores fueron codigo que compila, se ejecuta y da un resultado equivocado");

    }
}


