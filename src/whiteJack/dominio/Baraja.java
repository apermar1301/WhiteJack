package whiteJack.dominio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Representa una baraja estándar francesa de 52 cartas.
 *
 * Al construirse genera las 52 cartas (4 palos × 13 figuras) y las baraja
 * automáticamente. Las cartas se reparten desde el final de la lista para
 * simular sacar cartas de la parte superior del mazo.
 *
 * Ejemplo de uso:
 *
 * Baraja baraja = new Baraja(); Carta carta = baraja.repartirCarta();
 * System.out.println(baraja.cartasRestantes()); // 51
 *
 * @author Alvaro&Oleg
 * @version 1.0
 */
public class Baraja {

	// ─── Atributos ────────────────────────────────────────────────────────────

	/** Mazo de cartas disponibles para repartir. */
	private final List<Carta> cartas;

	// ─── Constructor ──────────────────────────────────────────────────────────

	/**
	 * Construye una baraja completa de 52 cartas y la baraja aleatoriamente.
	 */
	public Baraja() {
		this.cartas = new ArrayList<>(52);
		inicializar();
		barajar();
	}

	// ─── Métodos privados ─────────────────────────────────────────────────────

	/**
	 * Genera las 52 cartas combinando todos los palos con todas las figuras.
	 */
	private void inicializar() {
		for (Palo palo : Palo.values()) {
			for (Figura figura : Figura.values()) {
				cartas.add(new Carta(figura, palo));
			}
		}
	}

	/**
	 * Baraja las cartas aleatoriamente usando Collections.shuffle.
	 */
	private void barajar() {
		Collections.shuffle(cartas);
	}

	// ─── Métodos públicos ─────────────────────────────────────────────────────

	/**
	 * Reparte la carta de la parte superior del mazo (última de la lista).
	 *
	 * @return la carta repartida
	 * @throws IllegalStateException si la baraja está vacía
	 */
	public Carta repartirCarta() {
		if (estaVacia()) {
			throw new IllegalStateException("La baraja está vacía: no quedan cartas por repartir.");
		}
		return cartas.removeLast();
	}

	/**
	 * Reparte varias cartas del mazo de una vez.
	 *
	 * @param cantidad número de cartas a repartir
	 * @return lista con las cartas repartidas
	 * @throws IllegalArgumentException si la cantidad es menor que 1
	 * @throws IllegalStateException    si no hay suficientes cartas en la baraja
	 */
	public List<Carta> repartirCartas(int cantidad) {
		if (cantidad < 1) {
			throw new IllegalArgumentException("La cantidad debe ser al menos 1.");
		}
		if (cantidad > cartasRestantes()) {
			throw new IllegalStateException("No hay suficientes cartas. Quedan: " + cartasRestantes());
		}

		List<Carta> repartidas = new ArrayList<>(cantidad);
		for (int i = 0; i < cantidad; i++) {
			repartidas.add(repartirCarta());
		}
		return repartidas;
	}

	/**
	 * Indica si la baraja se ha quedado sin cartas.
	 *
	 * @return true si no quedan cartas por repartir
	 */
	public boolean estaVacia() {
		return cartas.isEmpty();
	}

	/**
	 * Devuelve el número de cartas que quedan en la baraja.
	 *
	 * @return cantidad de cartas restantes
	 */
	public int cartasRestantes() {
		return cartas.size();
	}

	/**
	 * Devuelve una representación textual de la baraja indicando cuántas cartas
	 * quedan.
	 *
	 * @return texto con el número de cartas restantes
	 */
	@Override
	public String toString() {
		return "Baraja [cartas restantes: " + cartasRestantes() + "]";
	}
}