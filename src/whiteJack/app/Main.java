package whiteJack.app;

import java.util.List;

import whiteJack.consola.EntradaConsola;
import whiteJack.consola.IEntrada;
import whiteJack.consola.ISalida;
import whiteJack.consola.SalidaConsola;
import whiteJack.dominio.GestorJugadores;
import whiteJack.dominio.GestorPartida;
import whiteJack.dominio.IJugador;

/**
 * Punto de entrada principal de la aplicación BlackJack.
 *
 * Actúa como raíz de composición (Composition Root): es el único lugar donde se
 * instancian las implementaciones concretas de las interfaces, que luego se
 * inyectan por constructor en el resto de componentes (DIP).
 *
 * La estructura de arranque es:
 *
 * 1. Crear implementaciones concretas de I/O. 2. Mostrar bienvenida y menú
 * principal. 3. Configurar jugadores y cartas iniciales. 4. Crear y arrancar
 * GestorPartida. 5. Preguntar si se quiere jugar otra partida.
 *
 * @author Alvaro&Oleg
 * @version 1.0
 */
public class Main {

	/**
	 * Método principal de la aplicación.
	 *
	 * @param args argumentos de línea de comandos (no se usan)
	 */
	public static void main(String[] args) {

		// ── Raíz de composición: únicas instancias concretas del proyecto ──
		IEntrada entrada = new EntradaConsola();
		ISalida salida = new SalidaConsola();

		salida.mostrarBienvenida();

		boolean seguirJugando = true;

		while (seguirJugando) {
			salida.mostrarMenu();
			int opcion = entrada.leerEntero("  Elige una opción: ", 1, 2);

			switch (opcion) {
			case 1 -> ejecutarPartida(entrada, salida);
			case 2 -> seguirJugando = false;
			}
		}

		salida.mostrarMensaje(" ¡Hasta la próxima!");
	}

	// ─── Métodos privados ─────────────────────────────────────────────────────

	/**
	 * Configura y ejecuta una partida completa de BlackJack.
	 *
	 * Delega la configuración de jugadores en GestorJugadores y la ejecución de la
	 * partida en GestorPartida. Al terminar pregunta si se desea jugar otra
	 * partida.
	 *
	 * @param entrada mecanismo de entrada inyectado
	 * @param salida  mecanismo de salida inyectado
	 */
	private static void ejecutarPartida(IEntrada entrada, ISalida salida) {
		// 1. Configurar jugadores
		GestorJugadores gestorJugadores = new GestorJugadores(entrada, salida);

		int modo = gestorJugadores.seleccionarModo();
		int cartasIniciales = gestorJugadores.seleccionarCartasIniciales();
		List<IJugador> jugadores = gestorJugadores.configurarJugadores(modo);

		salida.mostrarSeparador();
		salida.mostrarMensaje(" ¡Todo listo! Comenzando partida...");
		salida.mostrarSeparador();

		// 2. Crear y arrancar la partida
		GestorPartida partida = new GestorPartida(jugadores, cartasIniciales, entrada, salida);

		partida.iniciar();
	}
}