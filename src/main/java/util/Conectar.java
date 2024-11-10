package util;

import java.io.Serializable;

/**
 * Clase de utilidad que implementa el patrón de diseño Singleton para gestionar una única instancia
 * de la conexión. Esta clase es responsable de asegurar que solo exista una instancia de la clase
 * {@code Conectar} en toda la aplicación.
 * 
 * La clase implementa {@code Serializable}, lo que permite que la instancia de la clase sea serializable
 * en caso de que sea necesario guardarla o transmitirla a través de diferentes capas de la aplicación.
 * 
 * El patrón Singleton es utilizado para garantizar que solo exista una instancia de esta clase durante
 * toda la ejecución del programa.
 */
public class Conectar implements Serializable {
    
    // Instancia única de la clase Conectar, inicializada perezosamente.
    private static Conectar instancia;

    /**
     * Constructor privado para evitar la creación de nuevas instancias desde fuera de la clase.
     * Esto asegura que solo se pueda acceder a la instancia a través del método {@link #getConectar()}.
     */
    private Conectar() {
        // No se realiza ninguna operación en el constructor.
    }

    /**
     * Obtiene la instancia única de la clase {@code Conectar}.
     * Si la instancia no ha sido creada previamente, la crea de forma perezosa.
     * 
     * @return La instancia única de la clase {@code Conectar}.
     */
    public static Conectar getConectar() {
        if (instancia == null) {
            instancia = new Conectar(); // Se crea la instancia solo cuando es necesario.
        }
        return instancia;
    }
}
