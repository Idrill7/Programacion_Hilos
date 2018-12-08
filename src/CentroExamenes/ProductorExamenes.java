// Esta clase se encuentra dentro del paquete CentroExamenes
package CentroExamenes;

// Importamos de java.time la libreria LocalDateTime para luego obtener la fecha
import java.time.LocalDateTime;

/**
 * Esta es la clase ProductorExamenes, en ella producimos el codigo del examen
 * mediante el metodo run() y por ello implementamos Runnable en la clase.
 * El codigo esta formato por la letra E seguida del numero del examen y el anio
 */
public class ProductorExamenes implements Runnable {
	// Se guarda una referencia de un objeto de la clase BufferExamenes en el atributo buffer
	private BufferExamenes buffer;
	// Creamos una variable estatica que pertenece a la clase y a la region de
	// memoria conocida "memoria inmortal" de java
	private static int numeroExamen = 0;
	// Se guarda una referencia en el atributo hilo que apunta a un objeto de la clase Thread 
	private Thread hilo;

	/**
	 * Pasamos como parametro la referencia del buffer a la clase BufferExamenes
	 * @param buffer
	 */
	public ProductorExamenes(BufferExamenes buffer) {

		// Incrementa el contador de examenes (variable numeroExamen).
		numeroExamen++;
		// Construye el hilo. El nombre sera la letra E seguida del valor de la variable
		// numeroExamen.
		// this hace referencia al propio objeto de la clase Productor, en ese instante
		// de tiempo
		// se le pasara como referencia tambien el numero de examen de la variable numeroExamen
		// 	predecido de la letra E.
		hilo = new Thread(this, "E" + numeroExamen);
		// Establece el valor de la propiedad buffer, apunta al buffer pero del
		// objeto en concreto donde apunta generador
		this.buffer = buffer;
		// Inicia el hilo mediante el metodo start(), de forma que se ejecutara el
		// metodo run()
		hilo.start();
	}
	// Metodo de la clase Runnable que se sobreescribe
	@Override
	// Cuando se ejecuta el metodo start() del hilo, se ejecuta el metodo run en el
	// objeto de la clase Productor creado
	public void run() {
		// Creamos un entero para obtener el anio
		int aa = LocalDateTime.now().getYear();

		// Generacion del codigo de examen.
		// Creamos un string que guardara el nombre del hilo, E junto al numero del examen
		// seguido de un guion y el anio
		String codigo = this.hilo.getName() + "-" + aa;

		// Anade el nuevo codigo al buffer de examenes.
		// Se ejecuta el metodo fabricarNuevoExamen de la clase BufferExamenes
		buffer.fabricarNuevoExamen(codigo);

		// Muestra un mensaje en consola informando sobre el codigo del examen que se
		// acaba de producir.
		System.out.println("Producido examen " + codigo);

	}

}