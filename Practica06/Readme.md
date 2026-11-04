# Práctica 6 --- Ciclos y Entrada de datos
> Cierre del proyecto Cabaña del Misterio
## Valar Morghulis 

Este proyecto contiene tres programas interactivos escritos en Java:

- **BinarioInteractivo**: Convierte un número entero positivo a su
  representación en binario. Valida la entrada y, al terminar, pregunta si se
  quiere usar de nuevo el programa.
- **PiInteractivo**: Aproxima el valor de pi con el método Monte Carlo. El
  usuario decide cuántos dardos lanzar y el programa cuenta cuántos caen dentro
  del círculo para estimar pi.
- **CabanaDelMisterio**: Sistema de préstamos. Pide el nombre del cliente, su
  tipo (`P` preferente, `E` estándar o `R` riesgoso), el capital, la tasa anual
  y el plazo en meses, y ajusta la tasa según el tipo de cliente. Después
  muestra un menú para cotizar con interés simple o compuesto, compararlos o
  cambiar el capital. Termina al elegir `0`, al cometer 3 errores o al llegar a
  5 operaciones efectivas.

## Estructura

```
Practica06/
|-- Readme.md (este documento)
|-- src/    código fuente (.java)
|-- bin/    archivos compilados (.class)
|-- docs/   reporte en pdf
|-- Images/ imagenes de algunas pruebas
```

## Cómo compilar y ejecutar

Desde la carpeta `Practica06`:

```
javac -d bin src/*.java
```
Para compilar todos los archivos en `src/` y guardar el resultado en `bin/`

```
java -cp bin/ BinarioInteractivo
java -cp bin/ PiInteractivo
java -cp bin/ CabanaDelMisterio
```
Para ejecutar un *bytecode* de la clase elegida.

> Si en tu terminal los acentos o la ñ se ven raros, compila con
> `javac -encoding UTF-8 -d bin src/*.java`.

## Ejemplos de ejecución

Lo que escribe el usuario aparece después de los dos puntos de cada pregunta.

### BinarioInteractivo

```text
>>>Conversor a Binario<<<
 Ingresa un numero positivo: -5
Ingresa un numero positivo
>>>Conversor a Binario<<<
 Ingresa un numero positivo: 10
El numero 10 en binario es:1010
¿Quieres volver a usar el programa? (s/n): n
Programa finalizado.
```

### PiInteractivo

```text
>>>Pi Interactivo<<<
 ¿Cuántos dardos quieres lanzar? :1000000
Estimación de pi: 3.141836
```
El valor exacto cambia en cada ejecución porque los dardos son aleatorios, pero
con más dardos se acerca más a 3.141593.

### CabanaDelMisterio

```text
Bienvenido de nuevo a la Cabaña del misterio
 Ingresa el nombre del cliente:
Tyrion
Ingresa tu tipo de cliente: E
Ingresa el capital prestado: 10000
Ingresa la tasa anual:12
Ingresa el plazo en meses: 6
###Sistema de prestamos de la Cabaña del Misterio###
Las opciones disponibles son :
1) Cotizar con interes simple.
2) Cotizar con interes compuesto.
3) Comparar interes simple y compuesto.
4) Cambiar el capital del prestamo.
0) Salir.
> Por favor, elige una opción : 1

### Opción 1 : Cotización de interés simple ###

> Cliente: Tyrion
> Tipo de cliente: Estándar
> Tasa aplicada = 12%
> Prestamo = $ 10000.00
> Meses de pago = 6

> Interés simple = $600.00
> Total con interés simple = $10600.00

> Por favor, elige una opción : 0
!Hasta luego! Tyrion Gracias por usar el sistema de prestamos de la Cabaña del Misterio.

El capital con el que terminó el préstamo es de: $10000.00
El programa termino por que se ingreso el 0
Se relizaron 1 operacion(es) efectiva(s) y
se cometieron 0 error(es).
```
## Valar Dohaeris
