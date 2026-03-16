package whiteJack.consola;

import java.util.List;

import whiteJack.dominio.IJugador;
import whiteJack.dominio.ResultadoPartida;

/**
 * Implementación de ISalida que presenta la información por consola usando
 * colores ANSI y formato visual estructurado.
 *
 * Los códigos ANSI funcionan en terminales modernas (Linux, macOS, Windows
 * Terminal, IntelliJ, Eclipse con plugin ANSI). Si la terminal no soporta ANSI,
 * los códigos aparecerán como texto plano sin afectar a la lógica del juego.
 *
 * @author Alvaro&Oleg
 * @version 1.0
 */
public class SalidaConsola implements ISalida {

	// ─── Códigos ANSI ─────────────────────────────────────────────────────────

	private static final String RESET = "\u001B[0m";
	private static final String NEGRITA = "\u001B[1m";
	private static final String ROJO = "\u001B[31m";
	private static final String VERDE = "\u001B[32m";
	private static final String AMARILLO = "\u001B[33m";
	private static final String CIAN = "\u001B[36m";
	private static final String BLANCO = "\u001B[37m";
	private static final String MAGENTA = "\u001B[35m";

	/** Carácter usado para los separadores horizontales. */
	private static final String SEPARADOR = "═".repeat(50);

	// ─── Implementación de ISalida ────────────────────────────────────────────

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void mostrarBienvenida() {
		System.out.println(CIAN + NEGRITA);
		System.out.println("  ╔══════════════════════════════════════════════╗");
		System.out.println("  ║         🃏  W H I T E J A C K  🃏        	 ║");
		System.out.println("  ║              Álvaro & Oleh                   ║");
		System.out.println("  ╚══════════════════════════════════════════════╝");
		System.out.println(RESET);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void mostrarMenu() {
		System.out.println(AMARILLO + NEGRITA + "  MENÚ PRINCIPAL" + RESET);
		System.out.println(BLANCO + "  1. Jugar");
		System.out.println("  2. Salir" + RESET);
		System.out.println();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void mostrarCabeceraRonda(int numeroRonda) {
		System.out.println();
		System.out.println(AMARILLO + NEGRITA + SEPARADOR);
		System.out.println("  RONDA " + numeroRonda);
		System.out.println(SEPARADOR + RESET);
	}

	/**
	 * {@inheritDoc}
	 *
	 * Muestra para cada jugador: apodo, cartas y puntuación. Si el jugador se ha
	 * pasado de 21, su línea aparece en rojo. Si tiene exactamente 21, aparece en
	 * verde.
	 */
	@Override
	public void mostrarEstadoJugadores(List<IJugador> jugadores) {
		System.out.println();
		for (IJugador jugador : jugadores) {
			int puntos = jugador.getMano().calcularPuntos();
			boolean sePasa = jugador.getMano().sePasa();
			boolean veintiuno = jugador.getMano().tieneVeintiuno();

			String color = sePasa ? ROJO : veintiuno ? VERDE : BLANCO;

			String estado = sePasa ? "  ¡SE HA PASADO!" : veintiuno ? "  ¡BLACKJACK!" : "";

			System.out.printf(color + "  %-12s: %s → %d puntos%s%n" + RESET, jugador.getNombre(),
					jugador.getMano().getCartas(), puntos, estado);
		}
		System.out.println();
	}

	/**
	 * {@inheritDoc}
	 *
	 * Muestra el estado final de todos los jugadores y el resultado de la partida
	 * con formato destacado.
	 */
	@Override
	public void mostrarResultadoFinal(List<IJugador> jugadores, ResultadoPartida resultado) {
		System.out.println();
		System.out.println(CIAN + NEGRITA + SEPARADOR);
		System.out.println("  RESULTADO FINAL");
		System.out.println(SEPARADOR + RESET);

		mostrarEstadoJugadores(jugadores);

		String nombreJ1 = jugadores.size() > 0 ? jugadores.get(0).getNombre() : "Jugador 1";
		String nombreJ2 = jugadores.size() > 1 ? jugadores.get(1).getNombre() : "Jugador 2";

		System.out.println(VERDE + NEGRITA);
		System.out.println("  " + resultado.getMensaje(nombreJ1, nombreJ2));
		System.out.println(RESET);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void mostrarMensaje(String mensaje) {
		System.out.println(BLANCO + "  " + mensaje + RESET);
	}

	/**
	 * {@inheritDoc}
	 *
	 * Muestra el mensaje en rojo para destacarlo visualmente.
	 */
	@Override
	public void mostrarError(String mensaje) {
		System.out.println(ROJO + "  ⚠ " + mensaje + RESET);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void mostrarSeparador() {
		System.out.println(MAGENTA + "  " + SEPARADOR + RESET);
	}
}