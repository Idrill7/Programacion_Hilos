// Esta clase se encuentra dentro del paquete CentroExamenes
package CentroExamenes;


/**
 * 
 * Esta es la clase Examinador, en ella simulamos la realizacion de un examen
 * mediante el metodo run() y por ello implementamos Runnable en la clase.
 *
 */
public class Examinador implements Runnable {
	// Se guarda una referencia en el atributo hilo que apunta a un objeto de la clase Thread 
	private Thread hilo;
	// Se guarda una referencia de un objeto de la clase BufferExamenes en el atributo buffer
	private BufferExamenes buffer;

	/**
	 *
	 * Devolvemos un objeto dela clase Thread
	 * @return hilo
	 */
	//public Thread getHilo() {

		//return hilo;

	//}

	/**
	 * Pasamos como parametros un String que sera el alumno que realiza el examen Y
	 * la referencia del buffer que apunta a un objeto  de la clase BufferExamenes
	 * Es el metodo constructor de la clase Examinador
	 * @param alumno
	 *            que es pasado por argumento del objeto
	 * @param generador
	 *            que hace referencia al buffer
	 */
	public Examinador(String alumno, BufferExamenes generador) {

		// Construye el hilo. El nombre sera el nombre del alumno. 
		// El this hace referencia al objeto que se crea de la misma clase Examinador
		hilo = new Thread(this, alumno);
		// Establece el valor de la propiedad buffer, apunta al buffer pero del
		// objeto en concreto donde apunta generador
		this.buffer = generador;
		// Inicia el hilo mediante el metodo start(), de forma que se ejecutara el
		// metodo run()
		hilo.start();
	}
	
	// Metodo de la clase Runnable que se sobreescribe
	@Override
	// Cuando se ejecuta el metodo start() del hilo, se ejecuta el metodo
	// sincronizado, run()
	// El modificador synchronized se utiliza para indicar que un fragmento de
	// código está sincronizado, es decir, que solamente un hilo puede acceder a
	// dicho método a la vez.
	public synchronized void run() {
		// En la variable codgioExamen se almacena la el examen, que es apuntado con la referencia al buffer,
		// suministrado por el metodo consumirExamen(), este metodo es de la clase BufferExamenes 
		String codigoExamen = this.buffer.consumirExamen();
		// Si la cola no esta vacia, no apunta a null, podra simular el examen porque
		// podra consumir el examen generado por un objeto de la clase ProductorExamenes
		if (codigoExamen != null) {
			// Se simula aqui un examen de 10 preguntas cuyas respuestas se seleccionaran al
			// azar entre A, B, C, D o - (sin contestar).
			// Creamos la respuesta como un String nulo, guardara la respuesta
			String respuesta = null;
			for (int i = 1; i <= 10; i++) {

				// Creamos la variable que guarda el numero aleatorio entre 1 y 6, el 6 siendo
				// excluido
				int random = (int) Math.floor(Math.random() * 5 + 1);
				/**
				 * Este switch va generando respuesta aleatorias segun el numero que sale
				 * aleatoriamente
				 */
				switch (random) {

				case 1:
					respuesta = "A";
					break;
				case 2:
					respuesta = "B";
					break;
				case 3:
					respuesta = "C";
					break;
				case 4:
					respuesta = "D";
					break;
				case 5:
					respuesta = "-";
					break;
				default:
					System.out.println("Respuesta no valida");
				}
				// Cada vez que se lanza un numero aleatorio, se imprime por pantalla el
				// codigoExamen que ha consumido, se obtiene el nombre del hilo y por ultimo se
				// anade el numero de la pregunta y la respuesta
				System.out.println(codigoExamen + ";" + hilo.getName() + "; Pregunta " + i + ";" + respuesta);
			}
		}
		// En caso de que devuelva un nulo porque la cola este vacia, devolvera el
		// siguiente mensaje
		else {

			System.out.println("Agotado tiempo de espera y " + "no hay mas examenes");

		}

	}

}