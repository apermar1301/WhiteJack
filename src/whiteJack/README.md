# 🃏 WhiteJack — BlackJack Simplificado

> Práctica de **Entornos de Desarrollo — 1º DAM**  
> Módulo: UML + POO + JavaDoc + SOLID  
> Repositorio: [https://github.com/apermar1301/WhiteJack](https://github.com/apermar1301/WhiteJack)

---

## 📋 Descripción

**WhiteJack** es una implementación en consola de un BlackJack simplificado para **2 o más jugadores**, desarrollada en **Java 21** aplicando principios de Programación Orientada a Objetos y principios SOLID.

El juego permite enfrentarse a otros jugadores humanos, a una IA con distintos niveles de dificultad y al Crupier clásico, con una presentación visual mejorada mediante **colores ANSI** en consola.
# 🃏 WhiteJack — BlackJack Simplificado

> Práctica de **Entornos de Desarrollo — 1º DAM**  
> Módulo: UML + POO + JavaDoc + SOLID  
> Repositorio: [https://github.com/apermar1301/WhiteJack](https://github.com/apermar1301/WhiteJack)

---

## 📋 Descripción

**WhiteJack** es una implementación en consola de un BlackJack simplificado para **2 o más jugadores**, desarrollada en **Java 21** aplicando principios de Programación Orientada a Objetos y principios SOLID.

El juego permite enfrentarse a otros jugadores humanos, a una IA con distintos niveles de dificultad y al Crupier clásico, con una presentación visual mejorada mediante **colores ANSI** en consola.

---

## 🎮 Reglas del juego

- El objetivo es acercarse a **21 puntos** sin pasarse.
- Se juega con una **baraja estándar de 52 cartas** barajada aleatoriamente.
- **Valores de las cartas:**
  - Cartas del `2` al `10` → su valor numérico.
  - `J`, `Q`, `K` → 10 puntos.
  - `AS` → 1 u 11 puntos, eligiendo siempre el valor más favorable para el jugador.

### Condiciones de victoria

| Situación | Resultado |
|---|---|
| Ninguno se pasa | Gana el de mayor puntuación |
| Misma puntuación | Empate |
| Ambos se pasan | Empate |
| Solo uno se pasa | Gana el que no se ha pasado |

---

## ✨ Funcionalidades implementadas

### Obligatorias
- ✅ Partida completa con **2 jugadores**
- ✅ Menú principal (Jugar / Salir)
- ✅ Solicitud de **apodos** y **cartas iniciales** (1 o 2)
- ✅ Juego por **rondas numeradas**
- ✅ Regla de **juego justo**: se pregunta a todos los jugadores antes de repartir
- ✅ Cálculo correcto del **AS** como 1 u 11
- ✅ Control de **entradas inválidas** en consola
- ✅ Documentación **JavaDoc** completa
- ✅ Diagrama **UML** de clases

### Mejoras opcionales implementadas
- 🤖 **Jugador vs IA** con tres niveles de dificultad (Fácil, Normal, Difícil)
- 🎰 **Jugador vs Crupier** con reglas clásicas (se planta con 17+)
- 👥 **Jugador vs IA + Crupier** simultáneamente
- 🎨 **Colores ANSI** en consola para mejor presentación visual
- 🕹️ **4 modos de juego** seleccionables al inicio de cada partida

---

## 🕹️ Modos de juego

| Modo | Descripción |
|---|---|
| `1` | Dos jugadores humanos |
| `2` | Jugador humano vs IA |
| `3` | Jugador humano vs Crupier |
| `4` | Jugador humano vs IA y Crupier |

### Niveles de dificultad de la IA

| Nivel | Comportamiento |
|---|---|
| Fácil | Se planta con 14 o más puntos |
| Normal | Se planta con 17 o más puntos |
| Difícil | Se planta con 19 o más puntos |

---

## 🖥️ Ejemplo de partida

```
  ╔══════════════════════════════════════════════╗
  ║         🃏  B L A C K J A C K  🃏            ║
  ║              Versión simplificada            ║
  ╚══════════════════════════════════════════════╝

  MENÚ PRINCIPAL
  1. Jugar
  2. Salir

  Elige una opción: 1

  Selecciona el modo de juego:
    1. Dos jugadores humanos
    2. Jugador vs IA
    3. Jugador vs Crupier
    4. Jugador vs IA y Crupier
  Elige modo (1-4): 1

  Apodo Jugador 1: Pablo
  Apodo Jugador 2: Ana
  Cartas iniciales (1 o 2): 2

══════════════════════════════════════════════════
  RONDA 1
══════════════════════════════════════════════════

  Pablo       : [10♠, 6♦] → 16 puntos
  Ana         : [9♥, 7♣]  → 16 puntos

Pablo, ¿quieres carta (C) o plantarte (P)? C
Ana, ¿quieres carta (C) o plantarte (P)? P

══════════════════════════════════════════════════
  RONDA 2
══════════════════════════════════════════════════

  Pablo       : [10♠, 6♦, 5♣] → 21 puntos ⭐ ¡BLACKJACK!
  Ana         : [9♥, 7♣]      → 16 puntos

Pablo, ¿quieres carta (C) o plantarte (P)? P
Ana, ¿quieres carta (C) o plantarte (P)? C

══════════════════════════════════════════════════
  RESULTADO FINAL
══════════════════════════════════════════════════

  Pablo       : [10♠, 6♦, 5♣] → 21 puntos
  Ana         : [9♥, 7♣, 8♦]  → 24 puntos 💥 ¡SE HA PASADO!

  🏆 ¡Pablo gana la partida!
```

---

## 🗂️ Estructura del proyecto

```
WhiteJack/
│
├── src/
│   └── whitejack/
│       ├── app/
│       │   └── Main.java                  # Punto de entrada y raíz de composición
│       │
│       ├── consola/                       # Capa de I/O (interfaces e implementaciones)
│       │   ├── IEntrada.java
│       │   ├── ISalida.java
│       │   ├── EntradaConsola.java
│       │   └── SalidaConsola.java
│       │
│       └── dominio/                       # Modelo, jugadores y lógica de juego
│           ├── enums/
│           │   ├── Palo.java
│           │   ├── Figura.java
│           │   ├── DecisionJugador.java
│           │   └── ResultadoPartida.java
│           ├── Carta.java
│           ├── Mano.java
│           ├── Baraja.java
│           ├── IJugador.java
│           ├── JugadorHumano.java
│           ├── JugadorIA.java
│           ├── Crupier.java
│           ├── EvaluadorGanador.java
│           ├── GestorJugadores.java
│           └── GestorPartida.java
│
├── uml/
│   └── diagrama_clases.pdf                # Diagrama UML exportado
│
└── README.md
```

---

## ▶️ Instalación y ejecución

### Requisitos

- **Java 21** o superior
- **Eclipse IDE** (recomendado) o cualquier IDE compatible con Java

### Pasos

1. Clona el repositorio:
   ```bash
   git clone https://github.com/apermar1301/WhiteJack.git
   ```

2. Abre Eclipse y selecciona `File → Import → Existing Projects into Workspace`.

3. Selecciona la carpeta del proyecto clonado.

4. Ejecuta la clase `whitejack.app.Main` como `Java Application`.

---

## 🏗️ Arquitectura y diseño

El proyecto sigue una arquitectura **MVC simplificada en 3 capas**:

```
┌─────────────────────────────────┐
│     whitejack.consola (VIEW)    │  Solo imprime y recoge input
├─────────────────────────────────┤
│   whitejack.dominio (CONTROL)   │  Orquesta el flujo del juego
├─────────────────────────────────┤
│   whitejack.dominio (MODEL)     │  Entidades y reglas de negocio
└─────────────────────────────────┘
```

### Principios SOLID aplicados

| Principio | Aplicación |
|---|---|
| **SRP** — Single Responsibility | Cada clase tiene una única responsabilidad: `Mano` calcula puntos, `EvaluadorGanador` evalúa, `SalidaConsola` presenta |
| **DIP** — Dependency Inversion | `GestorPartida` y `GestorJugadores` dependen de `IJugador`, `IEntrada` e `ISalida`, nunca de implementaciones concretas |
| **ISP** — Interface Segregation | `IEntrada` e `ISalida` son interfaces separadas y específicas; ningún componente implementa más de lo que necesita |

### Diagrama UML

El diagrama de clases completo está disponible en [`uml/diagrama_clases.pdf`](uml/diagrama_clases.pdf).

---

## 📄 Documentación JavaDoc

Todas las clases y métodos públicos están documentados con JavaDoc, incluyendo `@param`, `@return` y `@throws` donde corresponde.

Para generar la documentación HTML en Eclipse:

1. `Project → Generate Javadoc`
2. Selecciona el proyecto y las clases a documentar
3. Indica la carpeta de destino (`doc/`)

---

## 🛠️ Tecnologías utilizadas

| Tecnología | Versión | Uso |
|---|---|---|
| Java | 21 | Lenguaje principal |
| Eclipse IDE | 2024+ | Entorno de desarrollo |
| Git + GitHub | — | Control de versiones |
| draw.io | — | Diagrama UML |
| ANSI Escape Codes | — | Colores en consola |

---

## 👤 Autor

Desarrollado como práctica del módulo **Entornos de Desarrollo** — 1º DAM.
---

## 🎮 Reglas del juego

- El objetivo es acercarse a **21 puntos** sin pasarse.
- Se juega con una **baraja estándar de 52 cartas** barajada aleatoriamente.
- **Valores de las cartas:**
  - Cartas del `2` al `10` → su valor numérico.
  - `J`, `Q`, `K` → 10 puntos.
  - `AS` → 1 u 11 puntos, eligiendo siempre el valor más favorable para el jugador.

### Condiciones de victoria

| Situación | Resultado |
|---|---|
| Ninguno se pasa | Gana el de mayor puntuación |
| Misma puntuación | Empate |
| Ambos se pasan | Empate |
| Solo uno se pasa | Gana el que no se ha pasado |

---

## ✨ Funcionalidades implementadas

- ✅ Partida completa con **2 jugadores**
- ✅ Menú principal (Jugar / Salir)
- ✅ Solicitud de **apodos** y **cartas iniciales** (1 o 2)
- ✅ Juego por **rondas numeradas**
- ✅ Regla de **juego justo**: se pregunta a todos los jugadores antes de repartir
- ✅ Cálculo correcto del **AS** como 1 u 11
- ✅ Control de **entradas inválidas** en consola
- ✅ Documentación **JavaDoc** completa
- ✅ Diagrama **UML** de clases



## 🕹️ Modos de juego

| Modo | Descripción |
|---|---|
| `1` | Dos jugadores humanos |
| `2` | Jugador humano vs IA |
| `3` | Jugador humano vs Crupier |
| `4` | Jugador humano vs IA y Crupier |

### Niveles de dificultad de la IA

| Nivel | Comportamiento |
|---|---|
| Fácil | Se planta con 14 o más puntos |
| Normal | Se planta con 17 o más puntos |
| Difícil | Se planta con 19 o más puntos |

---

## 🖥️ Ejemplo de partida

```
  ╔══════════════════════════════════════════════╗
  ║         🃏  B L A C K J A C K  🃏            ║
  ║              Versión simplificada            ║
  ╚══════════════════════════════════════════════╝

  MENÚ PRINCIPAL
  1. Jugar
  2. Salir

  Elige una opción: 1

  Selecciona el modo de juego:
    1. Dos jugadores humanos
    2. Jugador vs IA
    3. Jugador vs Crupier
    4. Jugador vs IA y Crupier
  Elige modo (1-4): 1

  Apodo Jugador 1: Pablo
  Apodo Jugador 2: Ana
  Cartas iniciales (1 o 2): 2

══════════════════════════════════════════════════
  RONDA 1
══════════════════════════════════════════════════

  Pablo       : [10♠, 6♦] → 16 puntos
  Ana         : [9♥, 7♣]  → 16 puntos

Pablo, ¿quieres carta (C) o plantarte (P)? C
Ana, ¿quieres carta (C) o plantarte (P)? P

══════════════════════════════════════════════════
  RONDA 2
══════════════════════════════════════════════════

  Pablo       : [10♠, 6♦, 5♣] → 21 puntos ⭐ ¡BLACKJACK!
  Ana         : [9♥, 7♣]      → 16 puntos

Pablo, ¿quieres carta (C) o plantarte (P)? P
Ana, ¿quieres carta (C) o plantarte (P)? C

══════════════════════════════════════════════════
  RESULTADO FINAL
══════════════════════════════════════════════════

  Pablo       : [10♠, 6♦, 5♣] → 21 puntos
  Ana         : [9♥, 7♣, 8♦]  → 24 puntos 💥 ¡SE HA PASADO!

  🏆 ¡Pablo gana la partida!
```

---

## 🗂️ Estructura del proyecto

```
WhiteJack/
│
├── src/
│   └── whitejack/
│       ├── app/
│       │   └── Main.java                  # Punto de entrada y raíz de composición
│       │
│       ├── consola/                       # Capa de I/O (interfaces e implementaciones)
│       │   ├── IEntrada.java
│       │   ├── ISalida.java
│       │   ├── EntradaConsola.java
│       │   └── SalidaConsola.java
│       │
│       └── dominio/                       # Modelo, jugadores y lógica de juego
│           ├── enums/
│           │   ├── Palo.java
│           │   ├── Figura.java
│           │   ├── DecisionJugador.java
│           │   └── ResultadoPartida.java
│           ├── Carta.java
│           ├── Mano.java
│           ├── Baraja.java
│           ├── IJugador.java
│           ├── JugadorHumano.java
│           ├── JugadorIA.java
│           ├── Crupier.java
│           ├── EvaluadorGanador.java
│           ├── GestorJugadores.java
│           └── GestorPartida.java
│
├── uml/
│   └── diagrama_clases.pdf                # Diagrama UML exportado
│
└── README.md
```

---

## ▶️ Instalación y ejecución

### Requisitos

- **Java 21** o superior
- **Eclipse IDE** (recomendado) o cualquier IDE compatible con Java

### Pasos

1. Clona el repositorio:
   ```bash
   git clone https://github.com/apermar1301/WhiteJack.git
   ```

2. Abre Eclipse y selecciona `File → Import → Existing Projects into Workspace`.

3. Selecciona la carpeta del proyecto clonado.

4. Ejecuta la clase `whitejack.app.Main` como `Java Application`.

---

## 🏗️ Arquitectura y diseño

```
┌─────────────────────────────────┐
│     whitejack.consola           │  
├─────────────────────────────────┤
│   whitejack.dominio             │  
├─────────────────────────────────┤
│   whitejack.dominio             │  
└─────────────────────────────────┘
```



---

## 📄 Documentación JavaDoc

Todas las clases y métodos públicos están documentados con JavaDoc, incluyendo `@param`, `@return` y `@throws` donde corresponde.





## 👤 Autores

Álvaro & Oleh
Desarrollado como práctica del módulo **Entornos de Desarrollo** — 1º DAM.