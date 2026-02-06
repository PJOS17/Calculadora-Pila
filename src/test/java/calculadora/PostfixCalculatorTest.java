package calculadora;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas unitarias para la clase PostfixCalculator
 */
public class PostfixCalculatorTest {
    
    private PostfixCalculator calculator;
    
    @BeforeEach
    public void setUp() {
        calculator = new PostfixCalculator(new Vector<>());
    }
    
    @Test
    public void testSimpleAddition() throws CalculationException {
        int result = calculator.evaluate("1 2 +");
        assertEquals(3, result, "1 + 2 debe ser 3");
    }
    
    @Test
    public void testSimpleSubtraction() throws CalculationException {
        int result = calculator.evaluate("5 3 -");
        assertEquals(2, result, "5 - 3 debe ser 2");
    }
    
    @Test
    public void testSimpleMultiplication() throws CalculationException {
        int result = calculator.evaluate("4 3 *");
        assertEquals(12, result, "4 * 3 debe ser 12");
    }
    
    @Test
    public void testSimpleDivision() throws CalculationException {
        int result = calculator.evaluate("9 3 /");
        assertEquals(3, result, "9 / 3 debe ser 3");
    }
    
    @Test
    public void testComplexExpression1() throws CalculationException {
        // 1 2 + 4 * 3 + = (1+2)*4+3 = 3*4+3 = 12+3 = 15
        int result = calculator.evaluate("1 2 + 4 * 3 +");
        assertEquals(15, result, "((1 + 2) * 4) + 3 debe ser 15");
    }
    
    @Test
    public void testComplexExpression2() throws CalculationException {
        // 3 4 + 2 * = (3+4)*2 = 7*2 = 14
        int result = calculator.evaluate("3 4 + 2 *");
        assertEquals(14, result, "(3 + 4) * 2 debe ser 14");
    }
    
    @Test
    public void testComplexExpression3() throws CalculationException {
        // 5 3 - 2 / = (5-3)/2 = 2/2 = 1
        int result = calculator.evaluate("5 3 - 2 /");
        assertEquals(1, result, "(5 - 3) / 2 debe ser 1");
    }
    
    @Test
    public void testNegativeNumbers() throws CalculationException {
        int result = calculator.evaluate("5 3 - 2 *");
        assertEquals(4, result, "(5 - 3) * 2 debe ser 4");
    }
    
    @Test
    public void testSingleOperand() throws CalculationException {
        int result = calculator.evaluate("42");
        assertEquals(42, result, "Un operando soloe debe retornar su valor");
    }
    
    @Test
    public void testDivisionByZероThrowsException() {
        assertThrows(CalculationException.class, () -> {
            calculator.evaluate("5 0 /");
        }, "División entre cero debe lanzar CalculationException");
    }
    
    @Test
    public void testInsufficientOperandsThrowsException() {
        assertThrows(CalculationException.class, () -> {
            calculator.evaluate("1 +");
        }, "Operandos insuficientes debe lanzar CalculationException");
    }
    
    @Test
    public void testInvalidTokenThrowsException() {
        assertThrows(CalculationException.class, () -> {
            calculator.evaluate("1 2 a +");
        }, "Token inválido debe lanzar CalculationException");
    }
    
    @Test
    public void testEmptyExpressionThrowsException() {
        assertThrows(CalculationException.class, () -> {
            calculator.evaluate("");
        }, "Expresión vacía debe lanzar CalculationException");
    }
    
    @Test
    public void testNullExpressionThrowsException() {
        assertThrows(CalculationException.class, () -> {
            calculator.evaluate(null);
        }, "Expresión nula debe lanzar CalculationException");
    }
    
    @Test
    public void testTooManyOperandsThrowsException() {
        assertThrows(CalculationException.class, () -> {
            calculator.evaluate("1 2 3 +");
        }, "Demasiados operandos debe lanzar CalculationException");
    }
    
    @Test
    public void testMultipleOperations() throws CalculationException {
        // 2 3 + 4 5 + * = (2+3) * (4+5) = 5 * 9 = 45
        int result = calculator.evaluate("2 3 + 4 5 + *");
        assertEquals(45, result, "(2 + 3) * (4 + 5) debe ser 45");
    }
    
    @Test
    public void testLargeNumbers() throws CalculationException {
        int result = calculator.evaluate("100 200 +");
        assertEquals(300, result, "100 + 200 debe ser 300");
    }
}
