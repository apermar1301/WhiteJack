package whiteJack.dominio;

/**
 * Representa el resultado final de una partida de BlackJack.
 * 
 * Se utiliza como valor de retorno en {@link blackjack.game.EvaluadorGanador}
 * para desacoplar la lógica de evaluación de la presentación del resultado.
 * 
 *
 * 
 * Casos contemplados:
 * 
 * 
 * {@link #GANA_JUGADOR_1} — el jugador 1 tiene mejor puntuación.
 * {@link #GANA_JUGADOR_2} — el jugador 2 tiene mejor puntuación.
 * {@link #GANA_IA} — la IA gana la partida. {@link #GANA_CRUPIER} — el crupier
 * gana la partida. {@link #EMPATE} — puntuaciones iguales o ambos se pasan.
 * 
 *
 * @author Álvaro&Oleg
 * @version 1.0
 */
public enum ResultadoPartida {

	/** El jugador 1 gana la partida. */
	GANA_JUGADOR_1,

	/** El jugador 2 gana la partida. */
	GANA_JUGADOR_2,

	/** La IA gana la partida (mejora opcional). */
	GANA_IA,

	/** El crupier gana la partida (mejora opcional). */
	GANA_CRUPIER,

	/** Empate: misma puntuación o ambos se pasan. */
	EMPATE;

	// ─── Métodos ──────────────────────────────────────────────────────────────

	/**
	 * Devuelve un mensaje legible del resultado para mostrar por consola.
	 *
	 * @param nombreJ1 apodo del jugador 1
	 * @param nombreJ2 apodo del jugador 2
	 * @return texto descriptivo del resultado
	 */
	public String getMensaje(String nombreJ1, String nombreJ2) {
		return switch (this) {
		case GANA_JUGADOR_1 -> " ¡" + nombreJ1 + " gana la partida!";
		case GANA_JUGADOR_2 -> " ¡" + nombreJ2 + " gana la partida!";
		case GANA_IA -> " La IA gana la partida.";
		case GANA_CRUPIER -> " El crupier gana la partida.";
		case EMPATE -> " ¡Empate!";
		};
	}
}