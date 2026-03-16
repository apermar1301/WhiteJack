package whiteJack.dominio;

/**
 * Representa las decisiones que puede tomar un jugador durante su turno.
 *
 * Se utiliza como valor de retorno en IJugador.decidir() para desacoplar la
 * lógica de decisión del flujo de la partida.
 *
 * @author Alvaro&Oleg
 * @version 1.0
 */
public enum DecisionJugador {

	/**
	 * El jugador solicita una carta adicional.
	 */
	CARTA,

	/**
	 * El jugador decide no recibir más cartas esta partida.
	 */
	PLANTARSE;

	// ─── Métodos ──────────────────────────────────────────────────────────────

	/**
	 * Devuelve una descripción legible de la decisión.
	 *
	 * @return texto descriptivo de la decisión
	 */
	@Override
	public String toString() {
		return switch (this) {
		case CARTA -> "Pedir carta";
		case PLANTARSE -> "Plantarse";
		};
	}
}