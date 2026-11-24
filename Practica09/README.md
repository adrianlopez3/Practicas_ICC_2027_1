# Práctica 9: Agregación, Clonación de Objetos y Guías de Envío
**Introducción a las Ciencias de la Computación (Semestre 2027-1)**  
**Facultad de Ciencias, UNAM**

---

## Datos del alumno

> **Nota de privacidad:** Si publicas tu proyecto en un repositorio público de GitHub, **no es necesario que incluyas esta sección** en este archivo para proteger tu privacidad. Es suficiente con que tus datos completos estén registrados en la entrega directa de Google Classroom.

- **Nombres completos:** Romero Arzate Omar / Lopez Mendez Adrian
- **Números de cuenta:** 121006480 / 324188758
- **Correos institucionales:** omarromeroa@ciencias.unam.mx / adrianlopez.31@ciencias.unam.mx

---

## Descripción de la práctica y decisiones de diseño en los métodos y clases

Describe brevemente los atributos, constructores y métodos que decidiste diseñar para `Persona.java`, así como tu implementación de `Envio.java`:

 Este proyecto modela una paquetería. Una **guía de envío** (`Envio`) junta a un remitente, un destinatario y un paquete. La guía no crea a las personas ni copia el paquete: solo guarda una referencia a cada uno (agregación).

  **Parte 1** -Paquete: Aqui nuestro constructor por copia replica atributo por atributo (todos son primitivos o String), lo que vuelve al clon un objeto independiente; si recibe null usa los valores por omisión ("ND-000", 1.0 kg, 10 cm, $100, no express). `toString()` regresa la ficha del paquete.

  **Parte 2** -Persona: Aquí nuestra clase tiene tres atributos privados: `identificador`, `nombreCompleto` y `ubicacion`. Tiene constructor por omisión, con parámetros y por copia, y ninguno deja atributos nulos o vacíos: si falta un dato, se usa un valor por omisión ("PER-00", "Sin nombre", "Sin ubicacion"). Los setters validan con el método auxiliar privado `esCadenaValida`. `mismaUbicacionQue` compara la ubicación sin importar mayúsculas y `esLaMismaPersona` compara el identificador; ambos regresan `false` si reciben `null`.
  
  **Parte 3** -Envio: Aquí nuestra guía tiene como atributos de instancia `folioGuia`, `remitente`, `destinatario` y `paquete`, y un atributo de clase `static int totalGuiasHechas` con su método `static getTotalGuiasHechas()`. Cada constructor llama al método auxiliar privado `generarFolio()`, que sube el contador y arma el folio "GUIA-XXX-N" con 2 o 3 caracteres al azar y el consecutivo. Si llega una referencia `null` o un paquete inválido, se usa un objeto por omisión. El costo total es la tarifa de `CotizadorEnvio` más `CARGO_FORANEO` cuando el envío es foráneo. `generarGuiaRetorno()` copia el paquete con el constructor por copia, le antepone "RET-" e invierte remitente y destinatario.
  
  **Parte 4** -PruebaEnvio y MenuPostal: Aquí nuestras pruebas usan personajes ficticios (Tyrion, Sansa y Jon) en dos ciudades distintas. En el menú cada opción llama a un método auxiliar para que el `switch` quede limpio, y las opciones 2 a 5 avisan si todavía no hay guía activa. La guía que se genera en las opciones 1 a 4 pasa a ser la nueva guía activa.



---

## Comandos para compilar, ejecutar y documentar

Ejecuta los siguientes comandos desde la terminal ubicado en la raíz de esta carpeta (`practica09/`):

### 1. Compilación
```bash
javac -d bin src/*.java
```
Para compilar todos los archivos en `src/` y guardar el resultado en `bin/`
 
### 2. Ejecución de pruebas y simulación (`PruebaEnvio`)
```bash
java -cp bin PruebaEnvio
```
 
### 3. Ejecución del menú interactivo (`MenuPostal`)
```bash
java -cp bin MenuPostal
```
 
### 4. Generación de documentación Javadoc
```bash
javadoc -d doc -author -version src/*.java
```

---

## Evidencia de ejecución

### 1. Salida de `PruebaEnvio`
Copia y pega aquí la salida final que mostró tu terminal al ejecutar `PruebaEnvio`:

```
================================================================================
  ICC 2027-1 | PRACTICA 9: AGREGACION, CLONACION Y GUIAS DE ENVIO
================================================================================

>>> PARTE 1: REPRESENTACION TEXTUAL Y CLONACION DE PAQUETE
  [OK]    Paquete.toString() incluye el codigo de rastreo
  [OK]    Constructor por copia de Paquete crea instancia independiente en memoria
  [OK]    Modificar el codigo del paquete clonado no altera el paquete plantilla original
  [OK]    Constructor por copia con null asigna valores predeterminados validos

>>> PARTE 2: VERIFICACION DE LA CLASE PERSONA
  [OK]    mismaUbicacionQue(): X y Y estan en la misma ciudad (sin importar mayusculas)
  [OK]    mismaUbicacionQue(): X y Z estan en distinta ciudad
  [OK]    mismaUbicacionQue(null) devuelve false
  [OK]    esLaMismaPersona(): mismo identificador aunque sean objetos distintos
  [OK]    esLaMismaPersona(): identificador distinto devuelve false
  [OK]    esLaMismaPersona(null) devuelve false
  [OK]    Persona.toString() incluye identificador, nombre y ubicacion
  [OK]    Constructor con cadenas nulas o vacias asigna valores por omision
  [OK]    setNombreCompleto() con dato valido actualiza el nombre
  Persona X: [PER-01] Tyrion Lannister (Desembarco del Rey)
  Persona Y: [PER-02] Sansa Stark (desembarco del rey)
  Persona Z: [PER-03] Jon Snow (Invernalia)

>>> PARTE 3: VERIFICACION DE ENVIO Y FOLIOS AUTOMATICOS
  [OK]    Al inicio se han emitido 0 guias
  Folio guia local  : GUIA-EA0-1
  Folio guia foranea: GUIA-S1-2
  [OK]    Ambos folios no son nulos y son distintos
  [OK]    Los folios tienen el formato GUIA-XXX-N
  [OK]    Numeracion consecutiva: la primera guia es 1 y la segunda es 2
  [OK]    El total de guias emitidas ahora es 2
  [OK]    esEnvioForaneo(): guia entre X y Y es LOCAL
  [OK]    esEnvioForaneo(): guia entre X y Z es FORANEA
  [OK]    esAutoEnvio(): X enviando a Y no es envio personal
  [OK]    calcularCostoTotal() local es igual a la tarifa base ($120.0)
  [OK]    calcularCostoTotal() foraneo suma el cargo de $80.0
  [OK]    cambiarDestinatario(null) se rechaza y no cambia al destinatario
  [OK]    cambiarDestinatario(Z) se acepta y la guia pasa a ser foranea
  [OK]    cambiarDestinatario() hacia una persona con el mismo ID que X se vuelve auto envio
  [OK]    Cambiar el destinatario no genera folios nuevos (siguen siendo 2 guias)

>>> PARTE 4: SIMULACION DE ENVIOS, CLONACION DE GUIA Y RETORNO

 a) Tyrion Lannister envia el kit promocional a Sansa Stark
/////////////////////////////
 GUIA DE ENVIO: GUIA-SN-3
/////////////////////////////
 Reminente: [PER-01] Tyrion Lannister (Desembarco del Rey)
 Destinatario: [PER-02] Sansa Stark (desembarco del rey)
 Tipo de ruta: LOCAL
 Auto envio: NO
 /// paquete ///
 + Codigo de rastreo: KIT-101
 + Dimensiones: 20.0 x 20.0 x 20.0 cm (alto x ancho x largo)
 + Peso real: 4.00 kg
 + Peso volumetrico: 1.60 kg
 + Peso cobrable: 4.00 kg
 + Valor declarado: $500.00 MXN
 + Modalidad: NORMAL
 /// Costos ///
 Tarifa base: $120.00
 Cargo foraneo: $0.00
 Costo total: $120.00
/////////////////////////////


 b) Tyrion Lannister envia un segundo paquete a Sansa Stark clonando la guia GUIA-SN-3
/////////////////////////////
 GUIA DE ENVIO: GUIA-1EN-4
/////////////////////////////
 Reminente: [PER-01] Tyrion Lannister (Desembarco del Rey)
 Destinatario: [PER-02] Sansa Stark (desembarco del rey)
 Tipo de ruta: LOCAL
 Auto envio: NO
 /// paquete ///
 + Codigo de rastreo: PKG-202
 + Dimensiones: 40.0 x 30.0 x 30.0 cm (alto x ancho x largo)
 + Peso real: 12.00 kg
 + Peso volumetrico: 7.20 kg
 + Peso cobrable: 12.00 kg
 + Valor declarado: $1500.00 MXN
 + Modalidad: NORMAL
 /// Costos ///
 Tarifa base: $246.00
 Cargo foraneo: $0.00
 Costo total: $246.00
/////////////////////////////

  [OK]    Guia clonada conserva al mismo remitente (misma referencia)
  [OK]    Guia clonada conserva al mismo destinatario (misma referencia)
  [OK]    Guia clonada lleva el nuevo paquete PKG-202
  [OK]    Guia clonada tiene un folio nuevo y consecutivo

 b.2) Tyrion Lannister clona el kit promocional para enviarlo a Jon Snow en otra ciudad
/////////////////////////////
 GUIA DE ENVIO: GUIA-8Z-5
/////////////////////////////
 Reminente: [PER-01] Tyrion Lannister (Desembarco del Rey)
 Destinatario: [PER-03] Jon Snow (Invernalia)
 Tipo de ruta: FORANEO
 Auto envio: NO
 /// paquete ///
 + Codigo de rastreo: KIT-103
 + Dimensiones: 20.0 x 20.0 x 20.0 cm (alto x ancho x largo)
 + Peso real: 4.00 kg
 + Peso volumetrico: 1.60 kg
 + Peso cobrable: 4.00 kg
 + Valor declarado: $500.00 MXN
 + Modalidad: NORMAL
 /// Costos ///
 Tarifa base: $120.00
 Cargo foraneo: $80.00
 Costo total: $200.00
/////////////////////////////

  [OK]    El kit clonado para Z es otro objeto con los mismos datos fisicos
  [OK]    El kit original conserva su codigo KIT-101
  [OK]    La guia hacia Z es foranea y cobra el cargo adicional

 c) Sansa Stark solicita devolver el primer paquete a Tyrion Lannister
/////////////////////////////
 GUIA DE ENVIO: GUIA-M5-6
/////////////////////////////
 Reminente: [PER-02] Sansa Stark (desembarco del rey)
 Destinatario: [PER-01] Tyrion Lannister (Desembarco del Rey)
 Tipo de ruta: LOCAL
 Auto envio: NO
 /// paquete ///
 + Codigo de rastreo: RET-KIT-101
 + Dimensiones: 20.0 x 20.0 x 20.0 cm (alto x ancho x largo)
 + Peso real: 4.00 kg
 + Peso volumetrico: 1.60 kg
 + Peso cobrable: 4.00 kg
 + Valor declarado: $500.00 MXN
 + Modalidad: NORMAL
 /// Costos ///
 Tarifa base: $120.00
 Cargo foraneo: $0.00
 Costo total: $120.00
/////////////////////////////

  [OK]    En la guia de retorno el remitente es Y
  [OK]    En la guia de retorno el destinatario es X
  [OK]    El paquete de retorno es una copia independiente del original
  [OK]    El paquete de retorno tiene el prefijo RET- y el original no cambio
  [OK]    La guia de retorno tiene un folio nuevo y consecutivo

  Total acumulado de guias emitidas en el sistema: 6

================================================================================
  RESUMEN DE PRUEBAS: 39 / 39 pruebas superadas con exito
================================================================================

```

### 2. Ejemplo de ejecución interactiva (`MenuPostal`)
Copia y pega aquí un ejemplo sencillo de interacción en terminal ejecutando `MenuPostal`:

```
================================================================
  SISTEMA INTERACTIVO DE GUIAS POSTALES | ICC 2027-1
================================================================

--- MENU PRINCIPAL ---
1. Emitir nueva guia de envio
2. Clonar guia activa con un nuevo paquete (mismo remitente y destinatario)
3. Clonar paquete de la guia activa para enviarlo a otro destinatario
4. Generar guia de retorno (devolucion al remitente)
5. Mostrar detalle y costo de la guia activa
0. Salir
Seleccione una opcion: 5
Aviso: No hay ninguna guia activa.

--- MENU PRINCIPAL ---
1. Emitir nueva guia de envio
2. Clonar guia activa con un nuevo paquete (mismo remitente y destinatario)
3. Clonar paquete de la guia activa para enviarlo a otro destinatario
4. Generar guia de retorno (devolucion al remitente)
5. Mostrar detalle y costo de la guia activa
0. Salir
Seleccione una opcion: 1

--- Captura de datos del REMITENTE ---
  Identificador corto (ej. PER-01): PER-01
  Nombre completo: Tyrion Lannister
  Ciudad o codigo postal: Desembarco del Rey

--- Captura de datos del DESTINATARIO ---
  Identificador corto (ej. PER-01): PER-03
  Nombre completo: Jon Snow
  Ciudad o codigo postal: Invernalia

--- Captura de datos del Paquete ---
  Codigo de rastreo (ej. PKG-500): PKG-500
  Peso fisico en kg (ej. 3.5): 3.5
  Alto en cm (ej. 20): 20
  Ancho en cm (ej. 20): 20
  Largo en cm (ej. 30): 30
  Valor declarado en MXN (ej. 800): 800
  ¿Es envio express? (s/n): n

Guia emitida con exito:
/////////////////////////////
 GUIA DE ENVIO: GUIA-IW-1
/////////////////////////////
 Reminente: [PER-01] Tyrion Lannister (Desembarco del Rey)
 Destinatario: [PER-03] Jon Snow (Invernalia)
 Tipo de ruta: FORANEO
 Auto envio: NO
 /// paquete ///
 + Codigo de rastreo: PKG-500
 + Dimensiones: 20.0 x 20.0 x 30.0 cm (alto x ancho x largo)
 + Peso real: 3.50 kg
 + Peso volumetrico: 2.40 kg
 + Peso cobrable: 3.50 kg
 + Valor declarado: $800.00 MXN
 + Modalidad: NORMAL
 /// Costos ///
 Tarifa base: $120.00
 Cargo foraneo: $80.00
 Costo total: $200.00
/////////////////////////////


--- MENU PRINCIPAL ---
1. Emitir nueva guia de envio
2. Clonar guia activa con un nuevo paquete (mismo remitente y destinatario)
3. Clonar paquete de la guia activa para enviarlo a otro destinatario
4. Generar guia de retorno (devolucion al remitente)
5. Mostrar detalle y costo de la guia activa
0. Salir
Seleccione una opcion: 4

Guia de retorno generada con exito:
/////////////////////////////
 GUIA DE ENVIO: GUIA-UE-2
/////////////////////////////
 Reminente: [PER-03] Jon Snow (Invernalia)
 Destinatario: [PER-01] Tyrion Lannister (Desembarco del Rey)
 Tipo de ruta: FORANEO
 Auto envio: NO
 /// paquete ///
 + Codigo de rastreo: RET-PKG-500
 + Dimensiones: 20.0 x 20.0 x 30.0 cm (alto x ancho x largo)
 + Peso real: 3.50 kg
 + Peso volumetrico: 2.40 kg
 + Peso cobrable: 3.50 kg
 + Valor declarado: $800.00 MXN
 + Modalidad: NORMAL
 /// Costos ///
 Tarifa base: $120.00
 Cargo foraneo: $80.00
 Costo total: $200.00
/////////////////////////////


--- MENU PRINCIPAL ---
1. Emitir nueva guia de envio
2. Clonar guia activa con un nuevo paquete (mismo remitente y destinatario)
3. Clonar paquete de la guia activa para enviarlo a otro destinatario
4. Generar guia de retorno (devolucion al remitente)
5. Mostrar detalle y costo de la guia activa
0. Salir
Seleccione una opcion: 0
Saliendo del sistema postal...
```

---

## Preguntas de reflexión 

1. **¿En qué se diferencia clonar un `Paquete` (`new Paquete(kitBase)`) de clonar una guía de `Envio` (`new Envio(guiaBase, nuevoPaquete)`) respecto a qué objetos se comparten y cuáles se duplican en memoria?**
   > [Escribe aquí tu respuesta]

2. **Para generar el `folioGuia` con un número consecutivo global y consultar cuántas guías se han emitido en total, ¿qué modificador tuviste que utilizar en ese atributo y en su método de consulta? ¿Por qué no funcionaría si el contador fuera un atributo normal de instancia?**
   > [Escribe aquí tu respuesta]

3. **Si una guía `envio1` se descarta (`envio1 = null;`), ¿qué ocurre en memoria con los objetos `Persona` (remitente/destinatario) y `Paquete` que estaban asociados a ella?**
   > [Escribe aquí tu respuesta]
