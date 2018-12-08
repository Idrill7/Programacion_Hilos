   // Esta clase se encuentra dentro del paquete CentroExamenes
package CentroExamenes;

// Importamos del java.util la libreria de las LinkedList 
import java.util.LinkedList;
// importamos del java.util la libreria de las Queue 
import java.util.Queue;

/**
 * Esta clase crea y gestiona la cola(Queue), que es una LinkedList y tiene capacidad para
 * apuntar a muchos objetos de tipo String, inicialmente apuntan a Null, pero a
 * medida que se vayan creando examenes, estos se iran anadiendose a la misma.
 * 
 * Tambien tiene dos metodos sincronizados, fabricarNuevoExamen que anade un
 * examen a la cola y consumirExamen que remueve un examen de la cola
 */
public class BufferExamenes {
	// Creamos una referencia  colaExamenes private de la clase Queue que recibira String
	private Queue<String> colaExamenes;

	// Metodo publico de la clase BufferExamenes, el constructor de la cola
	public BufferExamenes() {

		colaExamenes = new LinkedList<String>();

	}

	/**
	 * Este metodo esta sincronizado, tendra como parametro el String codigo, que se
	 * crea en la clase ProductorExamenes mediante la funcion Run(). El modificador
	 * synchronized se utiliza para indicar que un fragmento de código está
	 * sincronizado, es decir, que solamente un hilo puede acceder a dicho método a
	 * la vez.
	 * 
	 * @param codigo
	 */

	public synchronized void fabricarNuevoExamen(String codigo) {

		// Aqui se fabrica un nuevo examen.
		// Un hilo de la clase ProductorExamenes se encargara de fabricarlo y pasarlo
		// como argumento a este metodo.

		// Se anade a la colaExamenes el codigo del examen
		// Llamando al metodo offer() e introduciendo como parametro el codigo,fabricado
		// en la clase productorExamenes en el metodo run()
		// offer() inserta el elemento a la cola si es posible sin violar restricciones de
		// la capacidad de la cola
		// , es mas optimo que el metodo add() debido a que este ultimo genera
		// excepciones
		colaExamenes.offer(codigo);
		// Anade el codigo pasado como argumento a la cola y libera el hilo que esta
		// intentando consumir un nuevo examen, mediante notify() notifica a los demas
		// hilos para que despierten y pueda ser consumido el examen. Mientras la cola
		// este vacia, bloquearemos
		// el metodo consumirExamen() mediante el metodo wait() hasta que se fabrique un
		// nuevo examen y se ejecute el notify()
		notify();
	}

	/**
	 * Este metodo esta sincronizado y se encargara de suministrar un examen a cada
	 * hilo de tipo Examinador que lo solicite. El modificador synchronized se
	 * utiliza para indicar que un fragmento de código está sincronizado, es decir,
	 * que solamente un hilo puede acceder a dicho método a la vez.
	 * 
	 * @return String examen
	 */
	public synchronized String consumirExamen() {

		// Para suministrar el examen habra antes que esperar hasta que haya algun
		// examen para consumir en la cola.
		// Creamos la variable para contar los intentos de consumir un examen
		//int intentos = 0;
		// Creamos la variable examen
		String examen;
		// En este while hacemos que mientras la cola este vacia, se esperen los hilos
		// consumidores y cuando haya algun examen, los hilos consumidores los remuevan
		// de la cola
		// Si se crean menos objetos ProductorExamenes que objetos Examinador, el ultimo
		// examinador podria
		// entrar en un bucle infinito dentro de la estructura mientras espera a que el
		// metodo fabricarNuevoExamen() fabrique uno
		// por lo que establecemos un contador de intentos para limitar el numero de
		// repeticiones del while,20 exactamente
		while (colaExamenes.isEmpty()) {
			// Aumentamos en uno el contador
			//intentos++;
			// Se introduce las sentencias en un bloque trycatch para capturar las excepciones
			try {
				// Se pausa hasta que se haya fabricado algun examen.
				// Cuando se ejecute el metodo notify() de fabricarNuevoExamen() se acabara el
				// metodo wait() 
				wait();
			} catch (InterruptedException e) {
				// Si hay alguntipo de excepcion, se imprime por consola
				System.out.println(e.getMessage());
			}
		}
		
		// Si la cola no esta vacia o  el numero de intentos o es mayor a 20 
		// sale de while y entra en el if, donde se remueve un examen de la cola y lo retorna 
		if (!colaExamenes.isEmpty()) {
			// Guardamos en una variable el examen  que es removido  de la cola mediante el metodo poll() 
			examen = colaExamenes.poll();
			// y lo retornamos como String
			return examen;
		} else {
			// Si no queda examen, devuelve nulo
			return null;
		}

	}

}
