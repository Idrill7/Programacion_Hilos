// Esta clase se encuentra dentro del paquete CentroExamenes
package CentroExamenes;

/**
 * @author Alejandro Gonzalez Casado
 * @version 1.0
 *          Para la ejecucion de esta clase Principal haremos un run as java application
 *          O mediante la consola de comandos, nos situaremos en el directorio "bin" y
 *          escribiremos "java CentroExamenes/Principal"
 *          Esta es la clase Principal, es la encargada de lanzar los hilos de
 *          ejecucion creando objetos de tipo ProductorExamenes y Examinador,
 *          Comparten el mismo objeto creado de la clase BufferExamenes
 *          Contiene el main.
 *
 */
public class Principal {
	// Lanzamos IntorruptedException que generan los hilos al ser dormidos con el
	// metodo wait() o notificados con el metodo notify()
	public static void main(String[] args) throws InterruptedException {

	
		// Creamos un objeto de la clase BufferExamenes, generador, que se pasara como referencia a los objetos creados
		BufferExamenes generador = new BufferExamenes();


		// Producimos un nuevo examen mediante la creacion de un objeto de la clase
		// ProductorExamenes y anadimos como referencia el generador, este apunta al objeto de la clase BufferExamenes
		new ProductorExamenes(generador);
		// Creamos un objeto de la clase Examinador,la alumna Rosa, sera pasada como argumento alumno 
		// junto al generador, que apunta al objeto de la clase BufferExamenes
		new Examinador("Rosa", generador);
		// Producimos un nuevo examen mediante la creacion de un objeto de la clase
		// ProductorExamenes y anadimos como referencia el generador, este apunta al objeto de la clase BufferExamenes
		new ProductorExamenes(generador);
		// Creamos un objeto de la clase Examinador,el alumno Miguel, sera pasado como argumento alumno 
		// junto al generador, que apunta al objeto de la clase BufferExamenes
		new Examinador("Miguel", generador);
		// Producimos un nuevo examen mediante la creacion de un objeto de la clase
		// ProductorExamenes y anadimos como referencia el generador, este apunta al objeto de la clase BufferExamenes
		new ProductorExamenes(generador);
		// Creamos un objeto de la clase Examinador,el alumno Carlos, sera pasado como argumento alumno 
		//  junto al generador, que apunta al objeto de la clase BufferExamenes
		new Examinador("Carlos", generador);

	}

}