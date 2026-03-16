package whiteJack.dominio;

/**
 * Representa los cuatro palos de una baraja estándar francesa. Cada palo
 * incluye su símbolo unicode para mostrar por consola.
 *
 * @author Álvaro&Oleg
 * @version 1.0
 */
public enum Palo {

	/** Palo de picas. */
	PICAS("♠"),

	/** Palo de corazones. */
	CORAZONES("♥"),

	/** Palo de diamantes. */
	DIAMANTES("♦"),

	/** Palo de tréboles. */
	TREBOLES("♣");

	// ─── Atributos ────────────────────────────────────────────────────────────

	/** Símbolo unicode del palo. */
	private final String simbolo;

	// ─── Constructor ──────────────────────────────────────────────────────────

	/**
	 * Construye un palo con su símbolo visual.
	 *
	 * @param simbolo símbolo unicode que representa el palo
	 */
	Palo(String simbolo) {
		this.simbolo = simbolo;
	}

	// ─── Métodos ──────────────────────────────────────────────────────────────

	/**
	 * Devuelve el símbolo unicode del palo.
	 *
	 * @return símbolo del palo (ej: "♠")
	 */
	public String getSimbolo() {
		return simbolo;
	}

	/**
	 * Devuelve el símbolo unicode como representación textual del palo.
	 *
	 * @return símbolo del palo
	 */
	@Override
	public String toString() {
		return simbolo;
	}
}