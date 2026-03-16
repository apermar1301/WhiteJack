package whiteJack.consola;

import java.util.Scanner;

import whiteJack.dominio.DecisionJugador;

/**
 * Implementación de IEntrada que lee datos desde la consola usando Scanner.
 *
 * Gestiona internamente todas las entradas inválidas: repite la solicitud hasta
 * obtener un valor correcto, sin propagar excepciones al llamador.
 *
 * Se instancia una única vez en Main y se inyecta por constructor en los
 * componentes que la necesiten (DIP).
 *
 * @author Alvaro&Oleg
 * @version 1.0
 */
public class EntradaConsola implements IEntrada {

	// ─── Atributos ────────────────────────────────────────────────────────────

	/** Scanner compartido para toda la vida de la aplicación. */
	private final Scanner scanner;

	// ─── Constructor ──────────────────────────────────────────────────────────

	/**
	 * Construye una instancia que lee desde System.in.
	 */
	public EntradaConsola() {
		this.scanner = new Scanner(System.in);
	}

	// ─── Implementación de IEntrada ───────────────────────────────────────────

	/**
	 * {@inheritDoc}
	 *
	 * Repite la solicitud si el usuario introduce una cadena vacía o compuesta solo
	 * de espacios.
	 */
	@Override
	public String leerTexto(String mensaje) {
		String entrada;
		do {
			System.out.print(mensaje);
			entrada = scanner.nextLine().trim();
			if (entrada.isEmpty()) {
				System.out.println("  ⚠ El texto no puede estar vacío. Inténtalo de nuevo.");
			}
		} while (entrada.isEmpty());
		return entrada;
	}

	/**
	 * {@inheritDoc}
	 *
	 * Repite la solicitud si el valor no es un entero o está fuera del rango [min,
	 * max].
	 */
	@Override
	public int leerEntero(String mensaje, int min, int max) {
		int valor;
		while (true) {
			System.out.print(mensaje);
			String linea = scanner.nextLine().trim();
			try {
				valor = Integer.parseInt(linea);
				if (valor >= min && valor <= max) {
					return valor;
				}
				System.out.println("  ⚠ Introduce un número entre " + min + " y " + max + ".");
			} catch (NumberFormatException e) {
				System.out.println("  ⚠ Entrada inválida. Introduce un número entero.");
			}
		}
	}

	/**
	 * {@inheritDoc}
	 *
	 * Acepta "C" (carta) y "P" (plantarse), ignorando mayúsculas/minúsculas. Repite
	 * la solicitud ante cualquier otra entrada.
	 */
	@Override
	public DecisionJugador leerDecision(String nombreJugador) {
		while (true) {
			System.out.print(nombreJugador + ", ¿quieres carta (C) o plantarte (P)? ");
			String entrada = scanner.nextLine().trim().toUpperCase();
			switch (entrada) {
			case "C" -> {
				return DecisionJugador.CARTA;
			}
			case "P" -> {
				return DecisionJugador.PLANTARSE;
			}
			default -> System.out.println("  ⚠ Opción inválida. Introduce C (carta) o P (plantarse).");
			}
		}
	}

	/**
	 * {@inheritDoc}
	 *
	 * Muestra el mensaje y espera a que el usuario pulse Enter.
	 */
	@Override
	public void esperarEnter(String mensaje) {
		System.out.print(mensaje);
		scanner.nextLine();
	}
}