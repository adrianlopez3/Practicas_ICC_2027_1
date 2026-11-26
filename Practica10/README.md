# Práctica 10 (Mini-Lab): Arreglos de Tipos Primitivos (`EstacionMeteorologica`)
**Introducción a las Ciencias de la Computación (Semestre 2027-1)**  
**Facultad de Ciencias, UNAM**

---

## Datos del alumno

> **Nota de privacidad:** Si publicas tu proyecto en un repositorio público de GitHub, **no es necesario que incluyas esta sección** en este archivo para proteger tu privacidad. Es suficiente con que tus datos completos estén registrados en la entrega directa de Google Classroom.

- **Nombre completo:** Lopez Mendez Adrian / Romero Arzate Omar
- **Número de cuenta:** 324188758 / 121006480
- **Correo institucional:** omarromeroa@ciencias.unam.mx / adrianlopez.31@ciencias.unam.mx

---

## Descripción de la práctica

Breve descripción de los ejercicios realizados.
> Se completó la clase `EstacionMeteorologica`, que guarda en un arreglo `double[]` la temperatura de cada día de un periodo de monitoreo y con esos datos calcula el clima:
>
> - **Parte 1:** atributos privados, constantes y los dos constructores. Todas las casillas empiezan en `-300.0` (día sin lectura). Se puede registrar una temperatura solo si el día existe y el valor está entre -90.0 y 60.0 °C, y consultar la de cualquier día.
> - **Parte 2:** promedio, temperatura máxima (también cuando todas son negativas), cuántos días superan un umbral de calor y el resumen con `toString()`.
> - **Parte 3:** nueva enumeración `Escala` (`CELSIUS` y `FAHRENHEIT`), conversión de Celsius a Fahrenheit y promedio en la escala que se pida.
> - **Parte 4 (Retos):** primer día con helada (≤ 0.0 °C) y mayor cambio de temperatura entre dos días seguidos.
> - **Parte 5:** se activaron las pruebas de la Parte 3, se agregó una prueba personalizada en `PruebaEstacionMeteorologica` y se documentó todo con Javadoc.

---

## Comandos para compilar, ejecutar y documentar

Ejecuta los siguientes comandos desde la terminal ubicado en la raíz de esta carpeta:

### 1. Compilación
```bash
javac -d bin src/*.java
```
Para compilar todos los archivos de `src/` y guardar el resultado (`.class`) en `bin/`.

### 2. Ejecución de las pruebas
```bash
java -cp bin PruebaEstacionMeteorologica
```
Para ejecutar las 22 pruebas automatizadas y, al final, la prueba personalizada.

### 3. Generación de documentación Javadoc
```bash
javadoc -d doc -author -version src/*.java
```
Para generar la documentación en la carpeta `doc/`

---

## Evidencia de ejecución

Copia y pega aquí el resumen final de pruebas y la salida de tu prueba personalizada al ejecutar `PruebaEstacionMeteorologica`:

```text
================================================================================
  ICC 2027-1 | VERIFICACION AUTOMATIZADA: ESTACION METEOROLOGICA (MINI-LAB 10)
================================================================================

>>> 1. PROBANDO CONSTRUCTORES, INICIALIZACION Y ACCESO SEGURO:
  [OK] Constructor por omision asigna "Estacion UNAM" y 7 dias
  [OK] Constructor por omision inicializa las casillas en -300.0 (sin lectura)
  [OK] Constructor con parametros valida nombre null y numDias <= 0 (usa 7)
  [OK] registrarTemperatura guarda valor valido en [0] y mantiene sin captura [5] en -300.0
  [OK] registrarTemperatura rechaza indices fuera de rango (-1 y 10)
  [OK] registrarTemperatura rechaza lecturas fuera de [-90.0, 60.0]
  [OK] obtenerTemperatura devuelve -300.0 en indices fuera de rango (-1 y 10)

>>> 2. PROBANDO ESTADISTICAS BASICAS Y toString():
  [OK] obtenerTemperatura(3) coincide con el ejemplo de la tabla (23.0)
  [OK] calcularPromedio de {18.5, 21.0, 19.5, 23.0, 17.0} es 19.8
  [OK] obtenerMaxima de {18.5, 21.0, 19.5, 23.0, 17.0} es 23.0
  [OK] obtenerMaxima funciona cuando TODAS las lecturas son negativas (-3.2)
  [OK] contarDiasCalurosos(> 20.0) en CU-UNAM devuelve 2
  [OK] contarDiasCalurosos(> 30.0) devuelve 0 cuando ninguno supera el umbral
  [OK] toString() devuelve el resumen estadistico exacto

>>> 3. PROBANDO CONVERSION A FAHRENHEIT Y ESCALAS:
  [OK] convertirAFahrenheit(0.0) devuelve 32.0 y convertirAFahrenheit(20.0) devuelve 68.0
  [OK] calcularPromedio(Escala.CELSIUS) devuelve 19.8
  [OK] calcularPromedio(Escala.FAHRENHEIT) devuelve 67.64

>>> 4. PROBANDO RETOS (HELADA Y VARIACION CONSECUTIVA):
  [OK] buscarPrimerDiaHelada en {12.0, 4.5, 0.0, -2.5, 3.0} devuelve indice 2
  [OK] buscarPrimerDiaHelada devuelve -1 si no hubo temperaturas <= 0.0
  [OK] mayorCambioConsecutivo en {18.0, 20.0, 11.5, 15.0, 22.0} es 8.5
  [OK] mayorCambioConsecutivo detecta el mayor cambio en el ultimo par (13.0)
  [OK] mayorCambioConsecutivo con 1 solo dia devuelve 0.0 sin excepcion

================================================================================
  RESUMEN DE PRUEBAS: 22 / 22 pruebas superadas con exito.
================================================================================

>>> 5. PRUEBA PERSONALIZADA:
  Estacion: Lopez & Romero | Dias: 6 | Promedio: 9.5 C | Maxima: 20.5 C
  Promedio en Celsius: 9.5 C
  Promedio en Fahrenheit: 49.1 F
```

---

## Preguntas de reflexión (1.0 punto)

1. **En la Parte 3.2 definiste el método `convertirAFahrenheit(double celsius)`. ¿Lo implementaste como método de instancia o como método de clase (`static`)? Justifica tu decisión de diseño explicando qué ventajas o implicaciones tiene la opción que elegiste.**
   > Lo hicimos como **método de clase (`static`)**. Para convertir solo hace falta el número que recibe (F = C × 1.8 + 32); no usa el nombre ni las temperaturas de ninguna estación, así que el resultado es el mismo sin importar qué estación lo use.
   >
   > - **Ventaja:** se puede usar sin crear una estación, por ejemplo `EstacionMeteorologica.convertirAFahrenheit(25.0)`, y deja claro que es una fórmula general y no un dato de cada estación.
   > - **Implicaciones:** dentro del método no se pueden usar los atributos del objeto. Las pruebas lo llaman desde un objeto (`estCU.convertirAFahrenheit(...)`) y funciona igual, aunque lo recomendado es llamarlo con el nombre de la clase.

2. **En el método `obtenerMaxima()`, ¿por qué es incorrecto inicializar la variable auxiliar del máximo en `0.0`? Explica qué problema concreto ocurre en estaciones donde todas las temperaturas son negativas y cómo lo resolviste.**
   > Porque 0.0 no es una lectura de la estación, es un valor inventado. El método solo cambia el máximo cuando encuentra una temperatura **mayor**, y si todas son negativas (como en el Nevado: -8.5, -3.2, -12.0 y -4.0) ninguna es mayor que 0.0. Entonces el máximo nunca cambia y el método regresa 0.0, una temperatura que nunca se registró, cuando la respuesta correcta es -3.2.
   >
   > Lo resolvimos empezando con la primera temperatura del arreglo (`temperaturas[0]`) como máximo y comparándola con las demás. Así el resultado siempre es una temperatura que sí está en el arreglo, sean positivas o negativas.

3. **Sobre la Parte 4 (Retos) y el manejo de días sin lectura (`-300.0`): explica brevemente cómo diseñaste la lógica de alguno de los dos retos (o qué limitaciones tendría calcular el promedio de la Parte 2 si un usuario consulta el promedio cuando solo se han registrado algunos días del arreglo).**
   > El `-300.0` no es una temperatura real, solo significa «ese día no se midió», así que en los retos lo trato como un vacio:
   >
   > - **`mayorCambioConsecutivo()`:** compara cada día con el siguiente (`i` con `i + 1`), por eso el ciclo llega solo hasta el penúltimo día (`i < length - 1`); si llegara al último, `i + 1` se saldría del arreglo. Con un solo día el ciclo no se ejecuta y regresa 0.0.
   > - **`buscarPrimerDiaHelada()`:** recorre desde el día 0 y regresa el índice en cuanto encuentra una temperatura ≤ 0.0 que no sea `-300.0` (si no, un día sin medir contaría como helada). Si termina sin encontrar ninguna, regresa -1.
   >
   > El promedio de la Parte 2, en cambio, suma todas las casillas, incluidas las de `-300.0`. Si solo se han registrado algunos días, el resultado no tiene sentido: con 2 días de 20 °C en una estación de 7 días da -208.6 °C. Para corregirlo habría que sumar solo los días con lectura y dividir entre cuántos son.

---

## Comentarios adicionales sobre tu experiencia

Agrega aquí cualquier observación pertinente sobre el desarrollo de la práctica (por ejemplo: qué partes te costaron más trabajo, en dónde te atoraste o si algún caso específico no lograste resolver).
> Las 22 pruebas automatizadas pasan y la prueba personalizada funciona. Lo que nos pidió más cuidado fue:
>
> - No confundir el `-300.0` (día sin lectura) con `0.0`, que sí es una temperatura real.
> - El límite del ciclo en `mayorCambioConsecutivo()`, para no salirse del arreglo al comparar con el día siguiente.
> - Decidir si `convertirAFahrenheit` debía ser `static` o de instancia.
>
> Nos esta costando trabajo recordar tantos principios o "normas" al momento de programar pero nada que leer las diapositivas o apuntes de clase no arreglen en estos tiempo dificiles de paro que tenemos 

