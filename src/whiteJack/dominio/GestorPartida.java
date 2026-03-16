package whiteJack.dominio;

import java.util.ArrayList;
import java.util.List;

import whiteJack.consola.IEntrada;
import whiteJack.consola.ISalida;

/**
 * Orquesta el flujo completo de una partida de BlackJack.
 * 
 * Gestiona el bucle de rondas respetando la regla de "juego justo": primero
 * recoge las decisiones de <strong>todos</strong> los jugadores y solo después
 * reparte las cartas solicitadas, evitando que ningún jugador tenga ventaja
 * sobre los demás.
 * 
 *
 * 
 * Flujo de una partida:
 * 
 * 
 * Reparto de cartas iniciales. Bucle de rondas hasta que todos se planten o se
 * pueda determinar ganador En cada ronda: mostrar estado → recoger decisiones →
 * repartir → comprobar fin Mostrar resultado final
 * 
 *
 * @author Alvaro&Oleg
 * @version 1.0
 */
public class GestorPartida {

	// ─── Atributos ────────────────────────────────────────────────────────────

	/** Lista de jugadores participantes en la partida. */
	private final List<IJugador> jugadores;

	/** Baraja de la partida actual. */
	private final Baraja baraja;

	/** Mecanismo de entrada para leer decisiones. */
	private final IEntrada entrada;

	/** Mecanismo de salida para mostrar información. */
	private final ISalida salida;

	/** Evaluador que determina el ganador al final. */
	private final EvaluadorGanador evaluador;

	/** Número de cartas que se reparten inicialmente a cada jugador. */
	private final int cartasIniciales;

	/** Número de ronda actual. */
	private int numeroRonda;

	// ─── Constructor ──────────────────────────────────────────────────────────

	/**
	 * Construye el gestor de partida con todas sus dependencias.
	 *
	 * @param jugadores       lista de jugadores participantes; mínimo 2
	 * @param cartasIniciales número de cartas iniciales por jugador (1 o 2)
	 * @param entrada         mecanismo de entrada; no puede ser {@code null}
	 * @param salida          mecanismo de salida; no puede ser {@code null}
	 * @throws IllegalArgumentException si algún parámetro es inválido
	 */
	public GestorPartida(List<IJugador> jugadores, int cartasIniciales, IEntrada entrada, ISalida salida) {
		if (jugadores == null || jugadores.size() < 2) {
			throw new IllegalArgumentException("Se necesitan al menos 2 jugadores.");
		}
		if (cartasIniciales < 1 || cartasIniciales > 2) {
			throw new IllegalArgumentException("Las cartas iniciales deben ser 1 o 2.");
		}
		if (entrada == null || salida == null) {
			throw new IllegalArgumentException("Las dependencias de I/O no pueden ser nulas.");
		}
		this.jugadores = jugadores;
		this.cartasIniciales = cartasIniciales;
		this.entrada = entrada;
		this.salida = salida;
		this.baraja = new Baraja();
		this.evaluador = new EvaluadorGanador();
		this.numeroRonda = 1;
	}

	// ─── Métodos públicos ─────────────────────────────────────────────────────

	/**
	 * Inicia y ejecuta la partida completa de BlackJack.
	 * 
	 * Reparte las cartas iniciales y ejecuta el bucle de rondas hasta que la
	 * partida finalice. Al terminar muestra el resultado.
	 * 
	 */
	public void iniciar() {
		repartirCartasIniciales();
		ejecutarBucleRondas();
		mostrarFinal();
	}

	// ─── Métodos privados: flujo principal ────────────────────────────────────

	/**
	 * Reparte las cartas iniciales a todos los jugadores.
	 *
	 * @see #cartasIniciales
	 */
	private void repartirCartasIniciales() {
		salida.mostrarMensaje("  Repartiendo " + cartasIniciales + " carta(s) inicial(es)...");
		for (IJugador jugador : jugadores) {
			for (int i = 0; i < cartasIniciales; i++) {
				jugador.getMano().añadirCarta(baraja.repartirCarta());
			}
		}
	}

	/**
	 * Ejecuta el bucle principal de rondas hasta que la partida termine.
	 * 
	 * En cada iteración: muestra el estado, recoge decisiones, reparte cartas y
	 * comprueba si la partida ha terminado.
	 * 
	 */
	private void ejecutarBucleRondas() {
		while (true) {
			salida.mostrarCabeceraRonda(numeroRonda);
			salida.mostrarEstadoJugadores(jugadores);

			if (partidaTerminada()) {
				break;
			}

			List<DecisionJugador> decisiones = recogerDecisiones();
			repartirCartasPedidas(decisiones);

			numeroRonda++;
		}
	}

	/**
	 * Recoge las decisiones de todos los jugadores que aún no se han plantado ni
	 * pasado, respetando la regla de juego justo: primero se pregunta a todos y
	 * solo después se reparten cartas.
	 *
	 * @return lista de decisiones en el mismo orden que {@code jugadores}
	 */
	private List<DecisionJugador> recogerDecisiones() {
		List<DecisionJugador> decisiones = new ArrayList<>();

		for (IJugador jugador : jugadores) {
			if (jugador.estaPlantado() || jugador.getMano().sePasa()) {
				decisiones.add(DecisionJugador.PLANTARSE);
			} else {
				DecisionJugador decision = jugador.decidir();
				decisiones.add(decision);
			}
		}

		return decisiones;
	}

	/**
	 * Reparte una carta a cada jugador que haya pedido carta en esta ronda.
	 *
	 * @param decisiones lista de decisiones en el mismo orden que {@code jugadores}
	 */
	private void repartirCartasPedidas(List<DecisionJugador> decisiones) {
		for (int i = 0; i < jugadores.size(); i++) {
			if (decisiones.get(i) == DecisionJugador.CARTA) {
				jugadores.get(i).getMano().añadirCarta(baraja.repartirCarta());
			}
		}
	}

	/**
	 * Comprueba si la partida ha terminado.
	 * 
	 * La partida termina cuando todos los jugadores se han plantado o alguno se ha
	 * pasado de 21 (lo que permite determinar ganador).
	 * 
	 *
	 * @return {@code true} si la partida debe finalizar
	 */
	private boolean partidaTerminada() {
		boolean todosPlantados = jugadores.stream().allMatch(j -> j.estaPlantado() || j.getMano().sePasa());

		boolean algunoPasado = jugadores.stream().anyMatch(j -> j.getMano().sePasa());

		return todosPlantados || algunoPasado;
	}

	/**
	 * Evalúa el resultado y muestra el estado final de la partida.
	 * 
	 * Usa solo los dos primeros jugadores para la evaluación, compatible con la
	 * lógica de {@link EvaluadorGanador}.
	 * 
	 */
	private void mostrarFinal() {
		// Para partidas con crupier (3 jugadores), evaluamos j1 vs último
		List<IJugador> paraEvaluar = jugadores.size() == 2 ? jugadores
				: List.of(jugadores.get(0), jugadores.get(jugadores.size() - 1));

		var resultado = evaluador.evaluar(paraEvaluar);
		salida.mostrarResultadoFinal(jugadores, resultado);
		entrada.esperarEnter("  Pulsa Enter para continuar...");
	}
}