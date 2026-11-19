# Práctica 8 (Mini-Lab): La Clase Punto
**Introducción a las Ciencias de la Computación (Semestre 2027-1)**  
**Facultad de Ciencias, UNAM**

---

## Datos del alumno

> **Nota de privacidad:** Si publicas tu proyecto en un repositorio público de GitHub, **no es necesario que incluyas esta sección** en este archivo para proteger tu privacidad. Es suficiente con que tus datos completos estén registrados en la entrega directa de Google Classroom.

- **Nombre completo:** Adrian Lopez Mendez
- **Número de cuenta:** 324188758
- **Correo institucional:** adrianlopez.31@ciencias.unam.mx

---

## Descripción de la práctica

En esta práctica (Mini-Lab 8) se implementó la clase `Punto` en Java para modelar puntos en el plano cartesiano $(x, y) \in \mathbb{R}^2$, aplicando los principios de encapsulamiento, uso de la autorreferencia `this`, paso de mensajes y manejo de objetos como parámetros y valores de retorno. Se desarrollaron atributos privados con sus respectivos *getters* y *setters*, constructores (por defecto y con coordenadas), operaciones geométricas (`desplazar`, `distancia euclidiana`, `puntoMedio` y `determinación de cuadrante`), así como el reto de colinealidad de tres puntos mediante producto cruz 2D (`estanAlineados`) y la sobrescritura de `toString` e `equals` con tolerancia numérica. Finalmente, se validó la implementación con las 21 pruebas automatizadas de `PruebaPunto.java` que nos dio el ayudante de laboratorio y se generó su documentación técnica mediante Javadoc.
>
---

## Comandos para compilar, ejecutar y documentar

Ejecuta los siguientes comandos desde la terminal ubicado en la raíz de esta carpeta:

### 1. Compilación
```bash
<javac -d bin/ src/*.java>
```

### 2. Ejecución de la verificación
```bash
<java -cp bin PruebaPunto>
```

### 3. Generación de documentación Javadoc
```bash
<javadoc -d doc -author -version src/Punto.java>
```

---

## Evidencia de ejecución

Copia y pega aquí el resumen final que mostró tu terminal al ejecutar `PruebaPunto`:

```text
================================================================================
  ICC 2027-1 | VERIFICACION AUTOMATIZADA: CLASE PUNTO (MINI-LAB 8)
================================================================================

>>> 1. PROBANDO CONSTRUCTORES Y GETTERS:
  [OK] Constructor por omision inicializa en (0.0, 0.0)
  [OK] Constructor con parametros inicializa en (3.0, 4.0)

>>> 2. PROBANDO SETTERS:
  [OK] Setters modifican correctamente las coordenadas

>>> 3. PROBANDO METODO DESPLAZAR:
  [OK] desplazar(1.5, -1.0) actualiza a (3.5, 2.0)

>>> 4. PROBANDO DISTANCIA EUCLIDIANA:
  [OK] Distancia de (0.0, 0.0) a (3.0, 4.0) es 5.0
  [OK] Distancia de (1.0, 2.0) a (4.0, 6.0) es 5.0
  [OK] Distancia con referencia null regresa -1.0

>>> 5. PROBANDO PUNTO MEDIO (OBJETO RETORNADO):
  [OK] Punto medio entre (1.0, 2.0) y (4.0, 6.0) es (2.5, 4.0)
  [OK] Punto medio con null regresa null

>>> 6. PROBANDO DETERMINACION DE CUADRANTES:
  [OK] Cuadrante de (5.0, 8.0) es I
  [OK] Cuadrante de (-3.0, 4.0) es II
  [OK] Cuadrante de (-2.0, -7.0) es III
  [OK] Cuadrante de (6.0, -1.0) es IV
  [OK] Cuadrante de (0.0, 5.0) en eje es 0
  [OK] Cuadrante del origen (0.0, 0.0) es 0

>>> 7. PROBANDO METODO toString():
  [OK] toString() de (3.0, 4.0) devuelve "(3.0, 4.0)"

>>> 8. PROBANDO METODO EQUALS (IDENTIDAD VS CONTENIDO):
  [OK] p1.equals(new Punto(3.0, 4.0)) retorna true
  [OK] p1.equals(new Punto(3.0, 4.1)) retorna false
  [OK] p1.equals(null) retorna false de forma segura

>>> 9. PROBANDO COLINEALIDAD (RETO: TRES PUNTOS EN UNA RECTA):
  [OK] (1,1), (2,2) y (5,5) estan alineados
  [OK] (1,1), (2,2) y (5,6) NO estan alineados

================================================================================
  RESUMEN DE PRUEBAS: 21 / 21 pruebas superadas con exito.
  ¡FELICIDADES! Todos los metodos de la clase Punto funcionan correctamente.
================================================================================
```

---

## Preguntas de reflexión (1.0 punto)

1. **¿Por qué el método `puntoMedio` crea y regresa un nuevo objeto `Punto`, mientras que `desplazar` modifica al mismo objeto que recibe el mensaje?**
   > Porque representan operaciones distintas sobre el estado de los objetos. `desplazar` es un método diseñado para alterar las coordenadas actuales (`this.x` y `this.y`) de una instancia que existe en el plano. En cambio, `puntoMedio` calcula una nueva coordenada derivada de la relación entre dos puntos sin mover ninguno de los originales; devolver una nueva instancia (`new Punto(xm, ym)`) se queda el estado de ambos puntos y evita errores si se siguen utilizando más adelante en el programa.

2. **Al ejecutar `p1.distancia(p2)`, ¿a qué objeto en memoria hace referencia `this` y cómo accede el método a las coordenadas de `p2`?**
   > `this` hace referencia al objeto receptor que ejecuta el método, es decir, a la instancia en memoria a la que apunta `p1`. Para acceder a las coordenadas de `p2`, el método recibe su referencia mediante el parámetro `Punto otroPunto` y consulta sus valores utilizando getters(`otroPunto.getX()` y `otroPunto.getY()`), además de validar previamente que no sea `null`.

3. **Observa el archivo `PruebaPunto.java`: ¿por qué los atributos `pruebasExitosas`, `totalPruebas` y el método `verificar` están declarados como `static`? ¿Qué ocurriría si les quitáramos la palabra `static` y los llamáramos desde `main`? Menciona además dos métodos estáticos de las bibliotecas estándar de Java que utilizaste en `Punto.java`.**
   > Están declarados como `static` porque pertenecen a la clase `PruebaPunto` en general y no a una instancia en particular; esto permite llevar un conteo global compartido de las pruebas ejecutadas sin necesidad de crear un objeto de `PruebaPunto`. Si les quitáramos `static` e intentáramos llamarlos directamente desde `main` (que también es `static`), ocurriría un error de compilación (`non-static variable/method cannot be referenced from a static context`), ya que dentro de un contexto estático no existe una referencia `this` asociada a una instancia. Los dos métodos estáticos de la biblioteca estándar de Java utilizados en `Punto.java` son `Math.sqrt()` (en `distancia`) y `Math.abs()` (en `estanAlineados` y `equals`).
