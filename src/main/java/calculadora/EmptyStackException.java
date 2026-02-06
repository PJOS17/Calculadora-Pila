package calculadora;

/**
 * Excepción lanzada cuando se intenta hacer pop o peek en una pila vacía
 */
public class EmptyStackException extends Exception {
    
    public EmptyStackException() {
        super("La pila está vacía");
    }
    
    public EmptyStackException(String message) {
        super(message);
    }
}
