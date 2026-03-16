package whiteJack.dominio;

/**
 * Representa una carta de la baraja estándar francesa.
 *
 * Es un tipo inmutable: una vez creada, su figura y palo no pueden cambiar. Se
 * implementa como record de Java 21 para aprovechar la generación automática de
 * constructor, getters, equals, hashCode y toString.
 *
 * Ejemplo de uso:
 *
 * Carta carta = new Carta(Figura.AS, Palo.PICAS); System.out.println(carta); //
 * A♠ System.out.println(carta.getValorBase()); // 11
 *
 * @param figura figura de la carta (DOS, TRES, ... AS)
 * @param palo   palo de la carta (PICAS, CORAZONES, DIAMANTES, TREBOLES)
 *
 * @author Alvaro&Oleg
 * @version 1.0
 */
public record Carta(Figura figura, Palo palo) {

	// ─── Métodos ──────────────────────────────────────────────────────────────

	/**
	 * Devuelve el valor base de la carta según su figura.
	 *
	 * Para el AS, el valor base es 11. La lógica de reducirlo a 1 cuando la mano
	 * supera 21 reside en Mano.
	 *
	 * @return valor base numérico de la carta
	 */
	public int getValorBase() {
		return figura.getValorBase();
	}

	/**
	 * Indica si esta carta es un AS.
	 *
	 * @return true si la figura es AS
	 */
	public boolean esAs() {
		return figura == Figura.AS;
	}

	/**
	 * Devuelve la representación visual de la carta combinando la etiqueta de la
	 * figura y el símbolo del palo.
	 *
	 * Ejemplo: A♠, 10♥, J♦
	 *
	 * @return texto con figura y palo concatenados
	 */
	@Override
	public String toString() {
		return figura.getEtiqueta() + palo.getSimbolo();
	}
}