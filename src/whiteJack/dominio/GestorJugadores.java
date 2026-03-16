package whiteJack.dominio;

import java.util.ArrayList;
import java.util.List;

import whiteJack.consola.IEntrada;
import whiteJack.consola.ISalida;

/**
 * Gestiona la configuración inicial de una partida de BlackJack.
 *
 * Se encarga de recopilar los datos necesarios antes de que empiece el juego:
 * modo de partida, apodos de los jugadores y número de cartas iniciales.
 * Construye y devuelve la lista de IJugador lista para ser usada por
 * GestorPartida.
 *
 * Su única responsabilidad es la configuración: no conoce el flujo de rondas ni
 * las reglas de victoria.
 * 
 * @author Alvaro&Oleg
 * @version 1.0
 */
public class GestorJugadores {

	// ─── Constantes ───────────────────────────────────────────────────────────

	public static final int MODO_DOS_HUMANOS = 1;
	public static final int MODO_VS_IA = 2;
	public static final int MODO_VS_CRUPIER = 3;
	public static final int MODO_VS_IA_CRUPIER = 4;

	// ─── Atributos ────────────────────────────────────────────────────────────

	private final IEntrada entrada;
	private final ISalida salida;

	// ─── Constructor ──────────────────────────────────────────────────────────

	public GestorJugadores(IEntrada entrada, ISalida salida) {
		if (entrada == null || salida == null) {
			throw new IllegalArgumentException("Las dependencias de I/O no pueden ser nulas.");
		}
		this.entrada = entrada;
		this.salida = salida;
	}

	// ─── Métodos públicos ─────────────────────────────────────────────────────

	/**
	 * Solicita al usuario el modo de juego deseado y lo devuelve. Opciones
	 * disponibles: 1. Dos jugadores humanos 2. Jugador humano vs IA 3. Jugador
	 * humano vs Crupier 4. Jugador humano vs IA y Crupier
	 *
	 * @return constante de modo seleccionada por el usuario
	 */
	public int seleccionarModo() {
		salida.mostrarSeparador();
		salida.mostrarMensaje("Selecciona el modo de juego:");
		salida.mostrarMensaje("  1. Dos jugadores humanos");
		salida.mostrarMensaje("  2. Jugador vs IA");
		salida.mostrarMensaje("  3. Jugador vs Crupier");
		salida.mostrarMensaje("  4. Jugador vs IA y Crupier");
		salida.mostrarSeparador();
		return entrada.leerEntero("  Elige modo (1-4): ", 1, 4);
	}

	/**
	 * Solicita el número de cartas iniciales que se repartirán a cada jugador.
	 *
	 * @return 1 o 2 según la elección del usuario
	 */
	public int seleccionarCartasIniciales() {
		salida.mostrarMensaje("¿Cuántas cartas iniciales por jugador?");
		return entrada.leerEntero("  Cartas iniciales (1 o 2): ", 1, 2);
	}

	/**
	 * Configura y devuelve la lista de jugadores según el modo de juego indicado.
	 * Pide el apodo al usuario para cada jugador humano. Los jugadores automáticos
	 * (IA, crupier) se crean sin intervención del usuario.
	 *
	 * @param modo modo de juego seleccionado; debe ser una de las constantes MODO_*
	 * @return lista de jugadores configurados y listos para jugar
	 * @throws IllegalArgumentException si el modo no es válido
	 */
	public List<IJugador> configurarJugadores(int modo) {
		List<IJugador> jugadores = new ArrayList<>();

		switch (modo) {
		case MODO_DOS_HUMANOS -> {
			salida.mostrarMensaje("── Configuración: Dos jugadores humanos ──");
			jugadores.add(crearJugadorHumano(1));
			jugadores.add(crearJugadorHumano(2));
		}
		case MODO_VS_IA -> {
			salida.mostrarMensaje("── Configuración: Jugador vs IA ──");
			jugadores.add(crearJugadorHumano(1));
			jugadores.add(seleccionarDificultadIA());
		}
		case MODO_VS_CRUPIER -> {
			salida.mostrarMensaje("── Configuración: Jugador vs Crupier ──");
			jugadores.add(crearJugadorHumano(1));
			jugadores.add(new Crupier());
			salida.mostrarMensaje("  🎰 El Crupier está listo.");
		}
		case MODO_VS_IA_CRUPIER -> {
			salida.mostrarMensaje("── Configuración: Jugador vs IA y Crupier ──");
			jugadores.add(crearJugadorHumano(1));
			jugadores.add(seleccionarDificultadIA());
			jugadores.add(new Crupier());
			salida.mostrarMensaje("  🎰 El Crupier está listo.");
		}
		default -> throw new IllegalArgumentException("Modo de juego no válido: " + modo);
		}

		return jugadores;
	}

	// ─── Métodos privados ─────────────────────────────────────────────────────

	/**
	 * Solicita el apodo de un jugador humano y lo construye.
	 *
	 * @param numero número del jugador (1, 2...) para el mensaje
	 * @return jugador humano configurado
	 */
	private IJugador crearJugadorHumano(int numero) {
		String nombre = entrada.leerTexto("  Apodo Jugador " + numero + ": ");
		salida.mostrarMensaje("  ✅ Jugador " + numero + ": " + nombre);
		return new JugadorHumano(nombre, entrada);
	}

	/**
	 * Solicita la dificultad de la IA y la construye con el umbral correspondiente.
	 * Niveles disponibles: 1. Fácil → umbral 14: pide carta hasta 13 2. Normal →
	 * umbral 17: pide carta hasta 16 3. Difícil → umbral 19: pide carta hasta 18
	 *
	 * @return jugador IA configurado con el umbral seleccionado
	 */
	private IJugador seleccionarDificultadIA() {
		salida.mostrarMensaje("  Selecciona la dificultad de la IA:");
		salida.mostrarMensaje("    1. Fácil   (se planta con 14+)");
		salida.mostrarMensaje("    2. Normal  (se planta con 17+)");
		salida.mostrarMensaje("    3. Difícil (se planta con 19+)");

		int dificultad = entrada.leerEntero("  Dificultad (1-3): ", 1, 3);

		return switch (dificultad) {
		case 1 -> {
			salida.mostrarMensaje("  🤖 IA Fácil lista.");
			yield new JugadorIA("CPU Fácil", 14);
		}
		case 2 -> {
			salida.mostrarMensaje("  🤖 IA Normal lista.");
			yield new JugadorIA("CPU Normal", 17);
		}
		default -> {
			salida.mostrarMensaje("  🤖 IA Difícil lista.");
			yield new JugadorIA("CPU Difícil", 19);
		}
		};
	}
}