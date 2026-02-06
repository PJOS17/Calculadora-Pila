package calculadora;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Programa principal para evaluar expresiones en notación postfix desde un archivo
 * 
 * El archivo de entrada debe llamarse "datos.txt" y contener una expresión postfix por línea
 */
public class Main {
    
    private static final String DATA_FILE = "datos.txt";
    
    public static void main(String[] args) {
        // Crear instancia de calculadora usando la implementación Vector de Stack
        PostfixCalculator calculator = new PostfixCalculator(new Vector<>());
        
        try {
            processFile(calculator);
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
    }
    
    /**
     * Lee el archivo datos.txt y evalúa cada expresión postfix
     * 
     * @param calculator instancia de PostfixCalculator para evaluar expresiones
     * @throws IOException si hay error al leer el archivo
     */
    private static void processFile(PostfixCalculator calculator) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(DATA_FILE))) {
            String line;
            int lineNumber = 1;
            
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                
                if (line.isEmpty()) {
                    continue;
                }
                
                System.out.println("Línea " + lineNumber + ": " + line);
                
                try {
                    int result = calculator.evaluate(line);
                    System.out.println("Resultado: " + result);
                } catch (CalculationException e) {
                    System.err.println("Error: " + e.getMessage());
                }
                
                System.out.println();
                lineNumber++;
            }
        }
    }
}
