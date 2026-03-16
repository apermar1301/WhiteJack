package whiteJack.consola;

import java.util.List;

import whiteJack.dominio.IJugador;
import whiteJack.dominio.ResultadoPartida;

/**
 * Contrato para la presentación de información en BlackJack.
 *
 * Desacopla la lógica del juego del mecanismo concreto de salida (consola,
 * archivo, interfaz gráfica...), cumpliendo con los principios ISP (Interface
 * Segregation) y DIP (Dependency Inversion).
 *
 * Ningún método de esta interfaz debe leer input del usuario; esa
 * responsabilidad pertenece exclusivamente a IEntrada.
 *
 * @author Alvaro&Oleg
 * @version 1.0
 */
public interface ISalida {

	/**
	 * Muestra la pantalla de bienvenida al inicio del programa.
	 */
	void mostrarBienvenida();

	/**
	 * Muestra el menú principal con sus opciones numeradas.
	 */
	void mostrarMenu();

	/**
	 * Muestra la cabecera de una ronda con su número.
	 *
	 * @param numeroRonda número de la ronda actual (empieza en 1)
	 */
	void mostrarCabeceraRonda(int numeroRonda);

	/**
	 * Muestra el estado actual de todos los jugadores: apodo, cartas en mano y
	 * puntuación.
	 *
	 * @param jugadores lista de jugadores en la partida
	 */
	void mostrarEstadoJugadores(List<IJugador> jugadores);

	/**
	 * Muestra el resultado final de la partida: cartas, puntuaciones y ganador o
	 * empate.
	 *
	 * @param jugadores lista de jugadores al final de la partida
	 * @param resultado resultado evaluado de la partida
	 */
	void mostrarResultadoFinal(List<IJugador> jugadores, ResultadoPartida resultado);

	/**
	 * Muestra un mensaje informativo genérico por consola.
	 *
	 * @param mensaje texto a mostrar
	 */
	void mostrarMensaje(String mensaje);

	/**
	 * Muestra un mensaje de error o advertencia destacado visualmente.
	 *
	 * @param mensaje texto del error o advertencia
	 */
	void mostrarError(String mensaje);

	/**
	 * Muestra un separador visual horizontal para estructurar la información en
	 * pantalla.
	 */
	void mostrarSeparador();
}