package calculadora;

/**
 * ADT (Abstract Data Type) para evaluación de expresiones en notación Postfix (Polaca Inversa)
 * 
 * La expresión postfix se evalúa de izquierda a derecha:
 * - Operando: push al stack
 * - Operador: pop dos operandos, aplicar operación y push resultado
 */
public class PostfixCalculator {
    
    private Stack<Integer> stack;
    
    /**
     * Constructor que recibe una instancia de Stack
     * Permite usar diferentes implementaciones de Stack intercambiablemente
     * @param stack instancia de Stack para almacenar operandos
     */
    public PostfixCalculator(Stack<Integer> stack) {
        this.stack = stack;
    }
    
    /**
     * Evalúa una expresión en notación postfix
     * 
     * @param expression expresión postfix como String separada por espacios
     * @return resultado de la evaluación
     * @throws CalculationException si hay error en la evaluación
     */
    public int evaluate(String expression) throws CalculationException {
        stack = new Vector<>(); // Reinicializa la pila para cada evaluación
        
        if (expression == null || expression.trim().isEmpty()) {
            throw new CalculationException("Expresión vacía");
        }
        
        String[] tokens = expression.split("\\s+");
        
        try {
            for (String token : tokens) {
                if (isOperator(token)) {
                    evaluateOperation(token);
                } else if (isOperand(token)) {
                    stack.push(Integer.parseInt(token));
                } else {
                    throw new CalculationException("Token inválido: " + token);
                }
            }
            
            if (stack.isEmpty()) {
                throw new CalculationException("Expresión postfix vacía");
            }
            
            int result = stack.pop();
            
            if (!stack.isEmpty()) {
                throw new CalculationException("Demasiados operandos en la pila");
            }
            
            return result;
            
        } catch (EmptyStackException e) {
            throw new CalculationException("Operandos insuficientes para completar la operación");
        } catch (NumberFormatException e) {
            throw new CalculationException("Error al convertir operando a número entero");
        }
    }
    
    /**
     * Verifica si un token es un operador aritmético
     * @param token token a verificar
     * @return true si es operador, false en caso contrario
     */
    private boolean isOperator(String token) {
        return token.equals("+") || token.equals("-") || 
               token.equals("*") || token.equals("/");
    }
    
    /**
     * Verifica si un token es un operando (número entero)
     * @param token token a verificar
     * @return true si es operando, false en caso contrario
     */
    private boolean isOperand(String token) {
        try {
            Integer.parseInt(token);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    /**
     * Evalúa una operación extrayendo dos operandos del stack
     * Preserva el orden de los operandos: operandoA operador operandoB
     * 
     * @param operator operador aritmético
     * @throws EmptyStackException si no hay suficientes operandos
     * @throws CalculationException si hay división entre cero
     */
    private void evaluateOperation(String operator) throws EmptyStackException, CalculationException {
        int operandB = stack.pop();
        int operandA = stack.pop();
        int result = 0;
        
        switch (operator) {
            case "+":
                result = operandA + operandB;
                break;
            case "-":
                result = operandA - operandB;
                break;
            case "*":
                result = operandA * operandB;
                break;
            case "/":
                if (operandB == 0) {
                    throw new CalculationException("División entre cero");
                }
                result = operandA / operandB;
                break;
        }
        
        stack.push(result);
    }
}
