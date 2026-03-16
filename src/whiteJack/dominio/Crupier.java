package whiteJack.dominio;

/**
 * Representa al crupier en BlackJack.
 *
 * El crupier sigue reglas fijas e inamovibles del BlackJack clásico:
 *
 * Pide carta obligatoriamente si tiene 16 o menos puntos. Se planta
 * obligatoriamente si tiene 17 o más puntos.
 *
 * A diferencia de JugadorIA, el umbral del crupier no es configurable ya que
 * forma parte de las reglas oficiales del juego.
 *
 * Ejemplo de uso:
 *
 * IJugador crupier = new Crupier(); crupier.getMano().añadirCarta(carta);
 * DecisionJugador decision = crupier.decidir();
 *
 * @author Alvaro&Oleg
 * @version 1.0
 */
public class Crupier implements IJugador {

	// ─── Constantes ───────────────────────────────────────────────────────────

	/** Nombre por defecto del crupier mostrado en consola. */
	private static final String NOMBRE_DEFAULT = "Crupier";

	/**
	 * Umbral fijo del crupier: se planta con 17 o más, pide carta con 16 o menos.
	 */
	private static final int UMBRAL_CRUPIER = 17;

	// ─── Atributos ────────────────────────────────────────────────────────────

	/** Mano actual del crupier. */
	private final Mano mano;

	/** Indica si el crupier se ha plantado en la partida actual. */
	private boolean plantado;

	// ─── Constructor ──────────────────────────────────────────────────────────

	/**
	 * Construye un crupier con nombre por defecto y mano vacía.
	 */
	public Crupier() {
		this.mano = new Mano();
		this.plantado = false;
	}

	// ─── Implementación de IJugador ───────────────────────────────────────────

	/**
	 * {@inheritDoc}
	 *
	 * Devuelve siempre NOMBRE_DEFAULT.
	 */
	@Override
	public String getNombre() {
		return NOMBRE_DEFAULT;
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
	 * Aplica las reglas fijas del crupier: pide carta si tiene UMBRAL_CRUPIER o
	 * menos puntos, se planta en caso contrario. Si ya está plantado o se ha
	 * pasado, devuelve DecisionJugador.PLANTARSE directamente.
	 */
	@Override
	public DecisionJugador decidir() {
		if (plantado) {
			return DecisionJugador.PLANTARSE;
		}

		int puntos = mano.calcularPuntos();

		if (!mano.sePasa() && puntos < UMBRAL_CRUPIER) {
			System.out.println("  🎰 " + NOMBRE_DEFAULT + " pide carta. (Puntos: " + puntos + ")");
			return DecisionJugador.CARTA;
		}

		System.out.println("  🎰 " + NOMBRE_DEFAULT + " se planta. (Puntos: " + puntos + ")");
		plantado = true;
		return DecisionJugador.PLANTARSE;
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
	 * Vacía la mano y restablece el estado de plantado a false.
	 */
	@Override
	public void reiniciar() {
		mano.vaciar();
		plantado = false;
	}

	/**
	 * Devuelve una representación textual del crupier con su puntuación actual.
	 *
	 * @return texto descriptivo del crupier
	 */
	@Override
	public String toString() {
		return NOMBRE_DEFAULT + " [puntos: " + mano.calcularPuntos() + "]";
	}
}