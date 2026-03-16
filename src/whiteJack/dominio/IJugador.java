package whiteJack.dominio;

import whiteJack.consola.IEntrada;

/**
 * Contrato que debe cumplir cualquier tipo de jugador en BlackJack.
 * 
 * Permite tratar de forma uniforme jugadores humanos, IAs y el crupier,
 * cumpliendo con el principio DIP: los componentes del juego dependen de esta
 * abstracción, nunca de implementaciones concretas.
 * 
 *
 * 
 * Implementaciones disponibles:
 * 
 * 
 * {@link JugadorHumano} — decide mediante entrada por consola.
 * {@link JugadorIA} — decide automáticamente con lógica simple. {@link Crupier}
 * — sigue las reglas fijas del crupier clásico
 * 
 *
 * @author Álvaro&Oleg
 * @version 1.0
 */
public interface IJugador {

	/**
	 * Devuelve el apodo o nombre del jugador.
	 *
	 * @return nombre del jugador, nunca {@code null} ni vacío
	 */
	String getNombre();

	/**
	 * Devuelve la mano actual del jugador.
	 *
	 * @return mano del jugador con sus cartas y puntuación
	 */
	Mano getMano();

	/**
	 * Solicita al jugador que tome una decisión para la ronda actual.
	 * 
	 * En jugadores humanos, lee la decisión desde consola mediante
	 * {@link IEntrada}. En jugadores automáticos (IA, crupier), aplica su lógica
	 * interna sin interacción con el usuario.
	 * 
	 *
	 * @return {@link DecisionJugador#CARTA} si quiere otra carta,
	 *         {@link DecisionJugador#PLANTARSE} si decide no pedir más
	 */
	DecisionJugador decidir();

	/**
	 * Indica si el jugador ya se ha plantado en esta partida.
	 * 
	 * Un jugador plantado no vuelve a ser preguntado en rondas posteriores, aunque
	 * la partida continúe para otros jugadores.
	 * 
	 *
	 * @return {@code true} si el jugador se ha plantado
	 */
	boolean estaPlantado();

	/**
	 * Marca al jugador como plantado.
	 * 
	 * Una vez plantado, {@link #estaPlantado()} devuelve {@code true} y el jugador
	 * no recibirá más cartas ni será preguntado.
	 * 
	 */
	void plantarse();

	/**
	 * Reinicia el estado del jugador para una nueva partida: vacía la mano y
	 * cancela el estado de plantado.
	 */
	void reiniciar();
}