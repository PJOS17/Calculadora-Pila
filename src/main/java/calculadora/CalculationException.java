package calculadora;

/**
 * Excepción lanzada cuando ocurre un error en la evaluación de una expresión postfix
 */
public class CalculationException extends Exception {
    
    public CalculationException() {
        super("Error en la evaluación de la expresión");
    }
    
    public CalculationException(String message) {
        super(message);
    }
    
    public CalculationException(String message, Throwable cause) {
        super(message, cause);
    }
}
