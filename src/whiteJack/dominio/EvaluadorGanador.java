package whiteJack.dominio;

import java.util.List;

/**
 * Evalúa el resultado final de una partida de BlackJack.
 *
 * Aplica las reglas de victoria siguiendo este orden de prioridad:
 *
 * Si ambos jugadores se pasan de 21 → empate. Si solo uno se pasa → gana el que
 * no se ha pasado. Si ninguno se pasa → gana el de mayor puntuación. Si tienen
 * la misma puntuación → empate.
 *
 * Esta clase solo evalúa; no modifica el estado de ningún jugador ni interactúa
 * con la consola (SRP).
 *
 * @author Alvaro&Oleg
 * @version 1.0
 */
public class EvaluadorGanador {

	/**
	 * Evalúa el resultado de la partida dados los jugadores participantes.
	 *
	 * Soporta partidas de 2 jugadores. Si la lista contiene un Crupier, se evalúa
	 * como el jugador 2.
	 *
	 * @param jugadores lista de jugadores al final de la partida; debe contener
	 *                  exactamente 2 elementos
	 * @return resultado de la partida según las reglas de BlackJack
	 * @throws IllegalArgumentException si la lista no contiene exactamente 2
	 *                                  jugadores
	 */
	public ResultadoPartida evaluar(List<IJugador> jugadores) {
		if (jugadores == null || jugadores.size() != 2) {
			throw new IllegalArgumentException("Se necesitan exactamente 2 jugadores para evaluar la partida.");
		}

		IJugador j1 = jugadores.get(0);
		IJugador j2 = jugadores.get(1);

		boolean j1SePasa = j1.getMano().sePasa();
		boolean j2SePasa = j2.getMano().sePasa();

		int puntosJ1 = j1.getMano().calcularPuntos();
		int puntosJ2 = j2.getMano().calcularPuntos();

		// Caso 1: ambos se pasan → empate
		if (j1SePasa && j2SePasa) {
			return ResultadoPartida.EMPATE;
		}

		// Caso 2: solo j1 se pasa → gana j2
		if (j1SePasa) {
			return determinarGanadorJ2(j2);
		}

		// Caso 3: solo j2 se pasa → gana j1
		if (j2SePasa) {
			return ResultadoPartida.GANA_JUGADOR_1;
		}

		// Caso 4: ninguno se pasa → compara puntuaciones
		if (puntosJ1 > puntosJ2) {
			return ResultadoPartida.GANA_JUGADOR_1;
		}
		if (puntosJ2 > puntosJ1) {
			return determinarGanadorJ2(j2);
		}

		// Caso 5: misma puntuación → empate
		return ResultadoPartida.EMPATE;
	}

	// ─── Métodos privados ─────────────────────────────────────────────────────

	/**
	 * Determina el resultado correcto cuando gana el jugador 2, distinguiendo entre
	 * jugador humano, IA o crupier.
	 *
	 * @param j2 jugador que ocupa la posición 2 en la partida
	 * @return resultado específico según el tipo de jugador
	 */
	private ResultadoPartida determinarGanadorJ2(IJugador j2) {
		return switch (j2) {
		case Crupier c -> ResultadoPartida.GANA_CRUPIER;
		case JugadorIA i -> ResultadoPartida.GANA_IA;
		default -> ResultadoPartida.GANA_JUGADOR_2;
		};
	}
}