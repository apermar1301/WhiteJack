package whiteJack.dominio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Representa la mano de un jugador: el conjunto de cartas que tiene en juego.
 * 
 * Gestiona el cálculo de puntos aplicando la regla del AS de BlackJack: el AS
 * vale 11 por defecto, pero se reduce a 1 (restando 10) tantas veces como sea
 * necesario para evitar pasarse de 21.
 * 
 *
 * 
 * Ejemplo de cálculo:
 * 
 * 
 * {@code
 * // AS + AS + 9 → 11 + 11 + 9 = 31 → resta 10 → 21 
 * // AS + AS + K → 11 + 11 + 10 = 32 → resta 10 → 22 → resta 10 → 12 
 * }
 *
 * @author Álvaro&Oleg
 * @version 1.0
 */
public class Mano {

	// ─── Atributos ────────────────────────────────────────────────────────────

	/** Cartas actuales en la mano. */
	private final List<Carta> cartas;

	// ─── Constructor ──────────────────────────────────────────────────────────

	/**
	 * Construye una mano vacía.
	 */
	public Mano() {
		this.cartas = new ArrayList<>();
	}

	// ─── Métodos públicos ─────────────────────────────────────────────────────

	/**
	 * Añade una carta a la mano.
	 *
	 * @param carta carta a añadir; no puede ser {@code null}
	 * @throws IllegalArgumentException si la carta es {@code null}
	 */
	public void añadirCarta(Carta carta) {
		if (carta == null) {
			throw new IllegalArgumentException("La carta no puede ser null.");
		}
		cartas.add(carta);
	}

	/**
	 * Calcula la puntuación total de la mano aplicando la regla del AS.
	 * 
	 * El AS vale 11 por defecto. Si la suma supera 21 y hay ases en la mano, se
	 * reduce cada AS de 11 a 1 (restando 10) hasta que la suma sea 21 o menos, o
	 * hasta que no queden ases que reducir.
	 * 
	 *
	 * @return puntuación total de la mano
	 */
	public int calcularPuntos() {
		int total = 0;
		int ases = 0;

		for (Carta carta : cartas) {
			total += carta.getValorBase();
			if (carta.esAs()) {
				ases++;
			}
		}

		while (total > 21 && ases > 0) {
			total -= 10;
			ases--;
		}

		return total;
	}

	/**
	 * Indica si la mano se ha pasado de 21.
	 *
	 * @return {@code true} si la puntuación supera 21
	 */
	public boolean sePasa() {
		return calcularPuntos() > 21;
	}

	/**
	 * Indica si la mano tiene exactamente 21 puntos (BlackJack o 21 natural).
	 *
	 * @return {@code true} si la puntuación es exactamente 21
	 */
	public boolean tieneVeintiuno() {
		return calcularPuntos() == 21;
	}

	/**
	 * Devuelve una vista no modificable de las cartas en la mano.
	 *
	 * @return lista inmutable de cartas
	 */
	public List<Carta> getCartas() {
		return Collections.unmodifiableList(cartas);
	}

	/**
	 * Devuelve el número de cartas en la mano.
	 *
	 * @return cantidad de cartas
	 */
	public int numerDeCartas() {
		return cartas.size();
	}

	/**
	 * Elimina todas las cartas de la mano. Útil para reiniciar una partida sin
	 * crear un nuevo objeto.
	 */
	public void vaciar() {
		cartas.clear();
	}

	/**
	 * Devuelve la representación textual de la mano: cartas separadas por coma y
	 * puntuación total al final.
	 * 
	 * Ejemplo: {@code A♠, 10♥, 5♦ → 16 puntos}
	 *
	 *
	 * @return texto con las cartas y la puntuación
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < cartas.size(); i++) {
			sb.append(cartas.get(i));
			if (i < cartas.size() - 1) {
				sb.append(", ");
			}
		}

		sb.append(" → ").append(calcularPuntos()).append(" puntos");

		if (sePasa()) {
			sb.append(" (¡SE HA PASADO!)");
		}

		return sb.toString();
	}
}