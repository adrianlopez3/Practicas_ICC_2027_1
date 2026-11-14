# Práctica 7: Introducción a la Programación Orientada a Objetos
**Introducción a las Ciencias de la Computación (Semestre 2027-1)**  
**Facultad de Ciencias, UNAM**

---

## Descripción del proyecto

Sistema para una empresa de entrega de paquetes que necesita dejar de dar
seguimiento manual a sus envíos. Del enunciado se identificó un objeto central,
el **paquete**, y una serie de reglas de negocio que se aplican sobre él. Por
eso la solución se organizó en dos clases:

- **`Paquete`**: modela un paquete con su código de rastreo, peso físico (kg),
  largo, ancho y alto (cm), valor declarado (pesos) y si el cliente es
  preferente. Todos sus atributos son privados (encapsulamiento) y solo se
  modifican mediante el constructor o los setters, que validan los datos para
  que el objeto nunca quede en un estado inválido. Calcula su peso volumétrico
  (largo × ancho × alto / 5000) y su peso cobrable (el mayor entre el peso real
  y el volumétrico).
- **`CotizadorEnvio`**: clase de utilería con métodos estáticos que contienen las
  reglas de negocio: si un paquete es válido (`esPaqueteValido`), cuánto cuesta
  su envío con la tarifa escalonada por peso cobrable (`cotizarEnvio`) y si
  necesita seguro obligatorio por su valor o por ser sobredimensionado
  (`requiereSeguro`). No guarda datos propios, por eso no se instancia.
- **`PruebaPaqueteria`**: programa de prueba con los cinco casos de la práctica,
  las pruebas de la propuesta personal y algunas pruebas extra de validación.

### Propuesta personal: tarifa preferente

Se agregó el atributo `clientePreferente` y el método
`obtenerTarifaPreferente(double tarifaBase)`, que aplica un **15 % de descuento**
a la tarifa base cuando el cliente es preferente. La tarifa base se recibe como
parámetro porque sus reglas viven en `CotizadorEnvio` y no en el paquete. Si la
tarifa recibida es negativa (el código de error `-1.0` de `cotizarEnvio`), el
método regresa `-1.0` en lugar de aplicar un descuento a un error.

---

## Estructura

```
Practica07/
|-- README.md   (este documento)
|-- src/        código fuente (.java)
|-- bin/        archivos compilados (.class)
|-- doc/        documentación HTML generada con javadoc
```

---

## Comandos para compilar, ejecutar y documentar

Todos los comandos se ejecutan desde la raíz de la carpeta `Practica07`.

### 1. Compilación
```bash
javac -encoding UTF-8 -d bin src/*.java
```

### 2. Ejecución
```bash
java -cp bin PruebaPaqueteria
```

### 3. Generación de documentación Javadoc
```bash
javadoc -d doc -encoding UTF-8 -docencoding UTF-8 -charset UTF-8 -author -version src/*.java
```

Después de generarla, se abre `doc/index.html` en el navegador.

---

## Ejemplo de ejecución (fragmento)

```text
--- Caso 2: Paquete voluminoso (volumen predomina) ---
  Codigo de rastreo: PK-002
  Peso fisico: 2.0 kg
  Dimensiones (largo x ancho x alto): 50.0 x 40.0 x 50.0 cm
  Valor declarado: $1500.0
Peso volumetrico calculado: 20.00 kg
Peso cobrable final: 20.00 kg
Costo de envio cotizado: $425.00 (Esperado: $425.00)
Requiere seguro?: false

--- 6A: Cliente preferente, paquete pequeno ---
Tarifa base: $120.00
Tarifa preferente: $102.00 (Esperado: $102.00)
```
