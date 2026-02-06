# Calculadora Postfix - Expresiones en Notación Polaca Inversa

## Descripción del Proyecto

Este proyecto implementa una calculadora que evalúa expresiones en notación postfix (polaca inversa) usando pilas genéricas en Java.

## Requisitos Implementados

✓ **Utilización de genéricos**: Interfaz `Stack<T>` y clase `Vector<T>` completamente genéricas

✓ **Diseño del ADT para pilas**: Interfaz `Stack` define el contrato de una pila

✓ **Implementación con vector de tamaño variable**: Clase `Vector<T>` con expansión automática

✓ **Control de versiones**: Proyecto configurado con Git (iniciarlo con `git init`)

✓ **JUnit**: Suite completa de pruebas unitarias en `src/test/java`

✓ **ADT para calculadora**: Clase `PostfixCalculator` independiente de la implementación de Stack

## Estructura del Proyecto

```
CalculadoraPila/
├── src/
│   ├── main/java/calculadora/
│   │   ├── Stack.java              # Interfaz genérica de pila
│   │   ├── EmptyStackException.java # Excepción para pila vacía
│   │   ├── Vector.java             # Implementación de Stack con vector
│   │   ├── PostfixCalculator.java  # Evaluador de expresiones postfix
│   │   ├── CalculationException.java# Excepción de cálculo
│   │   └── Main.java               # Programa principal
│   └── test/java/calculadora/
│       ├── VectorTest.java         # Pruebas unitarias de Vector
│       └── PostfixCalculatorTest.java # Pruebas unitarias de PostfixCalculator
├── pom.xml                         # Configuración de Maven
├── datos.txt                       # Archivo de entrada con expresiones postfix
├── README.md                       # Este archivo
└── .gitignore                      # Ignorar archivos de compilación
```

## Características

### 1. Interfaz Stack<T> Genérica
Define las operaciones básicas de una pila:
- `push(T element)` - Añade un elemento
- `pop()` - Extrae el elemento del tope
- `peek()` - Visualiza el elemento del tope sin extraerlo
- `isEmpty()` - Verifica si está vacía
- `size()` - Retorna el número de elementos

### 2. Clase Vector<T>
Implementación de Stack usando un array dinámico:
- Capacidad inicial de 10 elementos
- Expandible automáticamente (duplica la capacidad)
- Permitiendo cualquier tipo de dato gracias a genéricos
- Liberación de memoria eliminando referencias después de pop

### 3. Clase PostfixCalculator
Evaluador de expresiones en notación postfix:
- Toma una expresión como String separada por espacios
- Soporta operadores: +, -, *, /
- Maneja errores: división por cero, operandos insuficientes, tokens inválidos
- Independiente de la implementación de Stack (usa la interfaz)

### 4. Programa Principal (Main)
- Lee expresiones desde el archivo `datos.txt`
- Evalúa cada expresión línea por línea
- Muestra el resultado o error correspondiente
- Demuestra el uso de la calculadora

## Cómo Compilar

### Con Maven:
```bash
mvn clean compile
```

### Con Maven (crear JAR ejecutable):
```bash
mvn clean package
```

## Cómo Ejecutar

### Ejecutar programa principal:
```bash
mvn exec:java -Dexec.mainClass="calculadora.Main"
```

O si tiene el JAR compilado:
```bash
java -cp target/calculadora-postfix-1.0.0.jar calculadora.Main
```

### Ejecutar pruebas unitarias:
```bash
mvn test
```

## Ejemplo de Uso

### Archivo datos.txt:
```
1 2 + 4 * 3 +
3 4 + 2 *
5 3 - 2 /
```

### Salida esperada:
```
Línea 1: 1 2 + 4 * 3 +
Resultado: 15

Línea 2: 3 4 + 2 *
Resultado: 14

Línea 3: 5 3 - 2 /
Resultado: 1
```

## Evaluación de Expresiones Postfix

La evaluación se realiza de izquierda a derecha:

### Ejemplo: 1 2 + 4 * 3 +

| Entrada | Operación | Pila (crece a la derecha) |
|---------|-----------|---------------------------|
| 1 | push operando | 1 |
| 2 | push operando | 1, 2 |
| + | Sumar: pop(), pop(), push(resultado) | 3 |
| 4 | push operando | 3, 4 |
| * | Multiplicar: pop(), pop(), push(resultado) | 12 |
| 3 | push operando | 12, 3 |
| + | Sumar: pop(), pop(), push(resultado) | 15 |

### Resultado: 15

## Manejo de Errores

El programa maneja correctamente:
- **División entre cero**: Lanza `CalculationException`
- **Operandos insuficientes**: Lanza `CalculationException`
- **Tokens inválidos**: Lanza `CalculationException`
- **Expresión vacía o nula**: Lanza `CalculationException`
- **Demasiados operandos**: Lanza `CalculationException`
- **Pila vacía**: Lanza `EmptyStackException`

## Diseño Principal

### Características de Diseño:

1. **Patrón Strategy**: PostfixCalculator acepta cualquier implementación de Stack
2. **Separación de responsabilidades**: Cada clase tiene un propósito claro
3. **Genéricos**: Reutilización de código para diferentes tipos de datos
4. **Manejo de excepciones**: Excusiones específicas para diferentes tipos de errores
5. **Pruebas exhaustivas**: 24 casos de prueba que validan la funcionalidad

## Versión de Java

El proyecto requiere **Java 11 o superior**.

## Dependencias

- **JUnit 5 (Jupiter)**: Framework de pruebas unitarias

## Notas Importantes

- Cada operando debe ser un dígito entero de un solo valor
- Los operandos y operadores deben estar separados por espacios
- Para usar ArrayList en lugar de Vector, simplemente reemplace `new Vector<>()` con `new ArrayList<>()` en Main (asume que ArrayList implementa Stack)
- El proyecto está preparado para control de versiones con Git

## Autor

Proyecto de programación - Semestre 3
