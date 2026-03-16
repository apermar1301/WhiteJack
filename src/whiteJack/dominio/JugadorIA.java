package whiteJack.dominio;

/**
 * Representa un jugador controlado por la máquina en BlackJack.
 * 
 * Toma decisiones automáticamente basándose en un umbral configurable: pide
 * carta si su puntuación es menor que el umbral, y se planta en caso contrario.
 * Por defecto el umbral es 17, siguiendo una estrategia conservadora estándar.
 * 
 *
 * 
 * Ejemplo de uso:
 * 
 * 
 * {@code
 * // IA con umbral por defecto (17)
 * IJugador ia = new JugadorIA("CPU");
 *
 * // IA con umbral personalizado (dificultad agresiva)
 * IJugador iaAgresiva = new JugadorIA("CPU Difícil", 19);
 * }
 *
 * @author Álvaro&Oleg
 * @version 1.0
 */
public class JugadorIA implements IJugador {

	// ─── Constantes ───────────────────────────────────────────────────────────

	/** Umbral de puntuación por defecto para pedir carta. */
	private static final int UMBRAL_DEFAULT = 17;

	// ─── Atributos ────────────────────────────────────────────────────────────

	/** Apodo de la IA mostrado en consola. */
	private final String nombre;

	/** Mano actual de la IA. */
	private final Mano mano;

	/**
	 * Umbral de puntuación: la IA pide carta si sus puntos son estrictamente
	 * menores que este valor.
	 */
	private final int umbral;

	/** Indica si la IA se ha plantado en la partida actual. */
	private boolean plantado;

	// ─── Constructores ────────────────────────────────────────────────────────

	/**
	 * Construye una IA con el umbral por defecto (17).
	 *
	 * @param nombre apodo de la IA; no puede ser {@code null} ni vacío
	 * @throws IllegalArgumentException si el nombre es {@code null} o vacío
	 */
	public JugadorIA(String nombre) {
		this(nombre, UMBRAL_DEFAULT);
	}

	/**
	 * Construye una IA con un umbral de puntuación personalizado.
	 *
	 * @param nombre apodo de la IA; no puede ser {@code null} ni vacío
	 * @param umbral puntuación mínima para plantarse; debe estar entre 1 y 21
	 * @throws IllegalArgumentException si el nombre es inválido o el umbral está
	 *                                  fuera de rango
	 */
	public JugadorIA(String nombre, int umbral) {
		if (nombre == null || nombre.isBlank()) {
			throw new IllegalArgumentException("El nombre de la IA no puede ser nulo o vacío.");
		}
		if (umbral < 1 || umbral > 21) {
			throw new IllegalArgumentException("El umbral debe estar entre 1 y 21.");
		}
		this.nombre = nombre;
		this.umbral = umbral;
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
	 * La IA pide carta si su puntuación actual es menor que el umbral configurado y
	 * no se ha pasado de 21. En cualquier otro caso se planta automáticamente.
	 * 
	 */
	@Override
	public DecisionJugador decidir() {
		if (plantado) {
			return DecisionJugador.PLANTARSE;
		}

		int puntos = mano.calcularPuntos();

		if (!mano.sePasa() && puntos < umbral) {
			System.out.println("  🤖 " + nombre + " pide carta. (Puntos: " + puntos + ")");
			return DecisionJugador.CARTA;
		}

		System.out.println("  🤖 " + nombre + " se planta. (Puntos: " + puntos + ")");
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
	 * Vacía la mano y restablece el estado de plantado a {@code false}.
	 * 
	 */
	@Override
	public void reiniciar() {
		mano.vaciar();
		plantado = false;
	}

	/**
	 * Devuelve el umbral de puntuación configurado para esta IA.
	 *
	 * @return umbral de puntuación
	 */
	public int getUmbral() {
		return umbral;
	}

	/**
	 * Devuelve una representación textual de la IA con su nombre, puntuación actual
	 * y umbral configurado.
	 *
	 * @return texto descriptivo de la IA
	 */
	@Override
	public String toString() {
		return nombre + " [IA | umbral: " + umbral + " | puntos: " + mano.calcularPuntos() + "]";
	}
}