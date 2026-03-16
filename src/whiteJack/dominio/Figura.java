package whiteJack.dominio;

/**
 * Representa las trece figuras de una baraja estándar francesa. Cada figura
 * almacena su valor base para el cálculo de puntos en BlackJack.
 *
 * El AS tiene valor base 11. La lógica de reducirlo a 1 cuando el jugador se
 * pasa de 21 reside en Mano.
 *
 * @author Alvaro&Oleg
 * @version 1.0
 */
public enum Figura {

	DOS("2", 2), TRES("3", 3), CUATRO("4", 4), CINCO("5", 5), SEIS("6", 6), SIETE("7", 7), OCHO("8", 8), NUEVE("9", 9),
	DIEZ("10", 10),

	/** Jota — vale 10 puntos. */
	JOTA("J", 10),

	/** Reina — vale 10 puntos. */
	REINA("Q", 10),

	/** Rey — vale 10 puntos. */
	REY("K", 10),

	/**
	 * As — valor base 11. Puede reducirse a 1 según el contexto de la mano.
	 */
	AS("A", 11);

	// ─── Atributos ────────────────────────────────────────────────────────────

	/** Etiqueta visual de la figura (ej: "J", "Q", "A", "7"). */
	private final String etiqueta;

	/** Valor base de la figura para el cálculo de puntos. */
	private final int valorBase;

	// ─── Constructor ──────────────────────────────────────────────────────────

	/**
	 * Construye una figura con su etiqueta visual y valor base.
	 *
	 * @param etiqueta  texto corto que representa la figura (ej: "A", "K", "7")
	 * @param valorBase valor numérico base de la figura en BlackJack
	 */
	Figura(String etiqueta, int valorBase) {
		this.etiqueta = etiqueta;
		this.valorBase = valorBase;
	}

	// ─── Métodos ──────────────────────────────────────────────────────────────

	/**
	 * Devuelve la etiqueta visual de la figura.
	 *
	 * @return etiqueta (ej: "A", "K", "10", "7")
	 */
	public String getEtiqueta() {
		return etiqueta;
	}

	/**
	 * Devuelve el valor base de la figura. Para el AS, este valor es 11; la
	 * reducción a 1 la gestiona Mano.
	 *
	 * @return valor base numérico
	 */
	public int getValorBase() {
		return valorBase;
	}

	/**
	 * Devuelve la etiqueta visual como representación textual de la figura.
	 *
	 * @return etiqueta de la figura
	 */
	@Override
	public String toString() {
		return etiqueta;
	}
}