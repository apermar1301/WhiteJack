package whiteJack.dominio;

import whiteJack.consola.IEntrada;

/**
 * Representa un jugador humano en BlackJack.
 * 
 * Su decisión en cada ronda se obtiene mediante {@link IEntrada}, lo que
 * desacopla la lógica del jugador del mecanismo concreto de entrada (DIP).
 * 
 *
 * 
 * Ejemplo de uso:
 * 
 * 
 * {@code
 * IEntrada entrada = new EntradaConsola();
 * IJugador jugador = new JugadorHumano("Pablo", entrada);
 * DecisionJugador decision = jugador.decidir();
 * }
 *
 * @author Álvaro&Oleg
 * @version 1.0
 */
public class JugadorHumano implements IJugador {

	// ─── Atributos ────────────────────────────────────────────────────────────

	/** Apodo del jugador. */
	private final String nombre;

	/** Mano actual del jugador. */
	private final Mano mano;

	/** Mecanismo de entrada para leer decisiones del usuario. */
	private final IEntrada entrada;

	/** Indica si el jugador se ha plantado en la partida actual. */
	private boolean plantado;

	// ─── Constructor ──────────────────────────────────────────────────────────

	/**
	 * Construye un jugador humano con su apodo y su fuente de entrada.
	 *
	 * @param nombre  apodo del jugador; no puede ser {@code null} ni vacío
	 * @param entrada mecanismo de entrada para leer decisiones; no puede ser
	 *                {@code null}
	 * @throws IllegalArgumentException si el nombre es {@code null} o vacío, o si
	 *                                  la entrada es {@code null}
	 */
	public JugadorHumano(String nombre, IEntrada entrada) {
		if (nombre == null || nombre.isBlank()) {
			throw new IllegalArgumentException("El nombre del jugador no puede ser nulo o vacío.");
		}
		if (entrada == null) {
			throw new IllegalArgumentException("La entrada no puede ser nula.");
		}
		this.nombre = nombre;
		this.entrada = entrada;
		this.mano = new Mano();
		this.plantado = false;
	}

	// ─── Implementación de IJugador ───────────────────────────────────────────

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getNombre() {
		return nombre;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Mano getMano() {
		return mano;
	}

	/**
	 * {@inheritDoc}
	 *
	 * Lee la decisión del usuario mediante {@link IEntrada#leerDecision}. Si el
	 * jugador ya está plantado, devuelve {@link DecisionJugador#PLANTARSE}
	 * directamente sin preguntar.
	 * 
	 */
	@Override
	public DecisionJugador decidir() {
		if (plantado) {
			return DecisionJugador.PLANTARSE;
		}
		DecisionJugador decision = entrada.leerDecision(nombre);
		if (decision == DecisionJugador.PLANTARSE) {
			plantado = true;
		}
		return decision;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean estaPlantado() {
		return plantado;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void plantarse() {
		this.plantado = true;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * Vacía la mano y restablece el estado de plantado a {@code false}.
	 * 
	 */
	@Override
	public void reiniciar() {
		mano.vaciar();
		plantado = false;
	}

	/**
	 * Devuelve una representación textual del jugador con su nombre y puntuación
	 * actual.
	 *
	 * @return texto con nombre y puntos actuales
	 */
	@Override
	public String toString() {
		return nombre + " [" + mano.calcularPuntos() + " puntos]";
	}
}