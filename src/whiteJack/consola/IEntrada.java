package whiteJack.consola;

import whiteJack.dominio.DecisionJugador;

/**
 * Contrato para la lectura de datos de entrada en BlackJack.
 *
 * Desacopla la lógica del juego del mecanismo concreto de entrada (consola,
 * archivo, red, tests...), cumpliendo con los principios ISP (Interface
 * Segregation) y DIP (Dependency Inversion).
 *
 * Todas las implementaciones deben garantizar que los métodos nunca devuelven
 * null y controlan entradas inválidas internamente, repitiéndolas hasta obtener
 * un valor correcto.
 *
 * @author Alvaro&Oleg
 * @version 1.0
 */
public interface IEntrada {

	/**
	 * Lee una línea de texto no vacía.
	 *
	 * Debe repetir la solicitud si el usuario introduce una cadena vacía o
	 * compuesta solo de espacios.
	 *
	 * @param mensaje texto que se muestra al usuario antes de leer
	 * @return texto introducido por el usuario, sin espacios extremos
	 */
	String leerTexto(String mensaje);

	/**
	 * Lee un número entero dentro de un rango inclusivo.
	 *
	 * Debe repetir la solicitud si el valor introducido no es un número entero o
	 * está fuera del rango [min, max].
	 *
	 * @param mensaje texto que se muestra al usuario antes de leer
	 * @param min     valor mínimo aceptado (inclusivo)
	 * @param max     valor máximo aceptado (inclusivo)
	 * @return entero válido dentro del rango indicado
	 */
	int leerEntero(String mensaje, int min, int max);

	/**
	 * Lee la decisión de un jugador: pedir carta o plantarse.
	 *
	 * Debe aceptar las opciones "C" (carta) y "P" (plantarse), ignorando
	 * mayúsculas/minúsculas, y repetir la solicitud ante cualquier otra entrada.
	 *
	 * @param nombreJugador apodo del jugador que debe decidir, usado para
	 *                      personalizar el mensaje
	 * @return DecisionJugador.CARTA o DecisionJugador.PLANTARSE
	 */
	DecisionJugador leerDecision(String nombreJugador);

	/**
	 * Espera a que el usuario pulse Enter para continuar.
	 *
	 * Útil para pausar la ejecución entre rondas y dar tiempo al jugador de leer la
	 * información mostrada.
	 *
	 * @param mensaje texto que se muestra antes de esperar
	 */
	void esperarEnter(String mensaje);
}