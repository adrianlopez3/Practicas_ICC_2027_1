# Práctica 10 (Mini-Lab): Arreglos de Tipos Primitivos (`EstacionMeteorologica`)
**Introducción a las Ciencias de la Computación (Semestre 2027-1)**  
**Facultad de Ciencias, UNAM**

---

## Datos del alumno

> **Nota de privacidad:** Si publicas tu proyecto en un repositorio público de GitHub, **no es necesario que incluyas esta sección** en este archivo para proteger tu privacidad. Es suficiente con que tus datos completos estén registrados en la entrega directa de Google Classroom.

- **Nombre completo:** [Escribe aquí tu nombre completo]
- **Número de cuenta:** [Escribe aquí tu número de cuenta]
- **Correo institucional:** [tu_correo@ciencias.unam.mx]

---

## Descripción de la práctica

En esta práctica (Mini-Lab 10) se implementó la clase `EstacionMeteorologica`, que almacena en un arreglo de tipo primitivo `double[]` las temperaturas diarias (en °C) de un periodo de monitoreo. Se declararon los atributos privados `nombreEstacion` y `temperaturas`, junto con constantes de clase (`SIN_LECTURA = -300.0`, rango físico `[-90.0, 60.0]`, nombre y días predeterminados). Se programaron los dos constructores, que crean el arreglo e inicializan cada casilla en `-300.0` con un ciclo `for`; los métodos de consulta y registro con validación de índices y rango físico; los métodos estadísticos (`calcularPromedio`, `obtenerMaxima`, `contarDiasCalurosos`) y `toString`. Además se creó la enumeración `Escala` (`CELSIUS`, `FAHRENHEIT`), el método de clase `convertirAFahrenheit` y la sobrecarga `calcularPromedio(Escala)`. Como retos se resolvieron `buscarPrimerDiaHelada` y `mayorCambioConsecutivo`. Finalmente, se verificó todo con las 22 pruebas de `PruebaEstacionMeteorologica.java` (incluyendo las de la Parte 3, ya descomentadas), se agregó una prueba personalizada y se generó la documentación con Javadoc.
>

---

## Comandos para compilar, ejecutar y documentar

Ejecuta los siguientes comandos desde la terminal ubicado en la raíz de esta carpeta:

### 1. Compilación
```bash
javac -d bin src/*.java
```

### 2. Ejecución de las pruebas
```bash
java -cp bin PruebaEstacionMeteorologica
```

### 3. Generación de documentación Javadoc
```bash
javadoc -d doc -author -version src/*.java
```

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
  Estacion: Lopez | Dias: 6 | Promedio: 9.0 C | Maxima: 22.5 C
  Promedio (Celsius): 9.0 C
  Promedio (Fahrenheit): 48.2 F
  Primer dia con helada: 1
  Mayor cambio consecutivo: 22.5 C
```

---

## Preguntas de reflexión (1.0 punto)

1. **En la Parte 3.2 definiste el método `convertirAFahrenheit(double celsius)`. ¿Lo implementaste como método de instancia o como método de clase (`static`)? Justifica tu decisión de diseño explicando qué ventajas o implicaciones tiene la opción que elegiste.**
   > Lo implementé como método de clase (`static`). La conversión `F = C × 1.8 + 32.0` solo depende del parámetro `celsius` y no usa ningún atributo (`nombreEstacion` ni `temperaturas`), por lo que no tiene sentido atarla a un objeto en particular. Ventajas: se puede invocar sin crear una estación (`EstacionMeteorologica.convertirAFahrenheit(20.0)`), deja claro en la firma que el método no lee ni modifica el estado del objeto y puede reutilizarse desde métodos de instancia como `calcularPromedio(Escala)`. La implicación es que dentro de él no se puede usar `this`; además Java permite seguir llamándolo a través de una referencia (`estCU.convertirAFahrenheit(...)`), por eso las pruebas siguen funcionando, aunque lo recomendable es llamarlo con el nombre de la clase.

2. **En el método `obtenerMaxima()`, ¿por qué es incorrecto inicializar la variable auxiliar del máximo en `0.0`? Explica qué problema concreto ocurre en estaciones donde todas las temperaturas son negativas y cómo lo resolviste.**
   > Porque `0.0` no es una lectura real del arreglo, sino un valor arbitrario que puede ser mayor que todas las temperaturas. En una estación de alta montaña como `{-8.5, -3.2, -12.0, -4.0}` ninguna lectura es mayor que `0.0`, así que la condición `temperaturas[i] > maxima` nunca se cumple y el método devolvería `0.0`, una temperatura que nunca se registró (la respuesta correcta es `-3.2`). Lo resolví inicializando el máximo con la primera casilla del arreglo (`maxima = temperaturas[0]`) y recorriendo desde el índice `1`; así el máximo siempre es uno de los valores registrados, sin importar si son positivos o negativos.

3. **Sobre la Parte 4 (Retos) y el manejo de días sin lectura (`-300.0`): explica brevemente cómo diseñaste la lógica de alguno de los dos retos (o qué limitaciones tendría calcular el promedio de la Parte 2 si un usuario consulta el promedio cuando solo se han registrado algunos días del arreglo).**
   > En `mayorCambioConsecutivo()` el ciclo compara la posición `i` con `i + 1`, por eso la condición es `i < temperaturas.length - 1`: el último `i` que se visita es el penúltimo índice y nunca se intenta leer `temperaturas[length]` (lo que lanzaría `ArrayIndexOutOfBoundsException`). La variable `mayorCambio` inicia en `0.0`, que aquí sí es correcto porque una diferencia absoluta nunca es negativa, y si el arreglo tiene un solo día el ciclo no se ejecuta y se devuelve `0.0` directamente. En cuanto al promedio de la Parte 2: como cada casilla sin captura vale `-300.0`, si solo se registraron algunos días esa marca entra a la suma como si fuera una temperatura real y el resultado queda muy distorsionado (por ejemplo, la estación "Ajusco" con 10 días y solo `14.5` registrado daría un promedio de `-268.55`). Para corregirlo habría que sumar y contar únicamente las casillas distintas de `-300.0` y dividir entre esa cantidad (manejando el caso de cero lecturas); lo mismo aplica a `obtenerMaxima`, `buscarPrimerDiaHelada` (un `-300.0` se contaría como helada) y `mayorCambioConsecutivo`.

---

## Comentarios adicionales sobre tu experiencia

Agrega aquí cualquier observación pertinente sobre el desarrollo de la práctica (por ejemplo: qué partes te costaron más trabajo, en dónde te atoraste o si algún caso específico no lograste resolver).
> La práctica fue bastante directa gracias a que los ciclos `for` sobre arreglos siguen el mismo patrón visto en clase (`for (int i = 0; i < arr.length; i++)`). Lo que requirió más atención fue: inicializar correctamente el máximo para soportar temperaturas negativas, cuidar el límite del ciclo en `mayorCambioConsecutivo` para no salirse del arreglo, y decidir si `convertirAFahrenheit` debía ser `static`. Para evitar "números mágicos" declaré constantes de clase (`SIN_LECTURA`, `TEMP_MINIMA`, `TEMP_MAXIMA`, etc.). En la prueba personalizada incluí una lectura de `0.0` y una negativa para verificar también los retos. Todas las pruebas (22/22) se superaron.
