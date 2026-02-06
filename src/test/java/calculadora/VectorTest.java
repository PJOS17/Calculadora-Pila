package calculadora;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas unitarias para la clase Vector (implementación de Stack)
 */
public class VectorTest {
    
    private Vector<Integer> stack;
    
    @BeforeEach
    public void setUp() {
        stack = new Vector<>();
    }
    
    @Test
    public void testNewStackIsEmpty() {
        assertTrue(stack.isEmpty(), "Una pila nueva debe estar vacía");
    }
    
    @Test
    public void testNewStackHasZeroSize() {
        assertEquals(0, stack.size(), "Una pila nueva debe tener tamaño 0");
    }
    
    @Test
    public void testPushIncrementsSize() {
        stack.push(10);
        assertEquals(1, stack.size(), "El tamaño debe ser 1 después de push");
    }
    
    @Test
    public void testPushMultipleElements() {
        stack.push(1);
        stack.push(2);
        stack.push(3);
        assertEquals(3, stack.size(), "El tamaño debe ser 3 después de 3 push");
    }
    
    @Test
    public void testIsEmptyAfterPush() {
        stack.push(5);
        assertFalse(stack.isEmpty(), "La pila no debe estar vacía después de push");
    }
    
    @Test
    public void testPeekDoesNotRemoveElement() throws EmptyStackException {
        stack.push(42);
        int peeked = stack.peek();
        assertEquals(42, peeked, "Peek debe retornar el elemento");
        assertEquals(1, stack.size(), "El tamaño no debe cambiar con peek");
    }
    
    @Test
    public void testPopRemovesElement() throws EmptyStackException {
        stack.push(10);
        int popped = stack.pop();
        assertEquals(10, popped, "Pop debe retornar el elemento");
        assertEquals(0, stack.size(), "El tamaño debe ser 0 después de pop");
        assertTrue(stack.isEmpty(), "La pila debe estar vacía después de pop");
    }
    
    @Test
    public void testLIFOOrder() throws EmptyStackException {
        stack.push(1);
        stack.push(2);
        stack.push(3);
        
        assertEquals(3, stack.pop(), "Pop debe retornar el último elemento pushado");
        assertEquals(2, stack.pop(), "Pop debe retornar el penúltimo elemento");
        assertEquals(1, stack.pop(), "Pop debe retornar el primer elemento");
    }
    
    @Test
    public void testPopEmptyStackThrowsException() {
        assertThrows(EmptyStackException.class, () -> {
            stack.pop();
        }, "Pop en pila vacía debe lanzar EmptyStackException");
    }
    
    @Test
    public void testPeekEmptyStackThrowsException() {
        assertThrows(EmptyStackException.class, () -> {
            stack.peek();
        }, "Peek en pila vacía debe lanzar EmptyStackException");
    }
    
    @Test
    public void testVectorExpandsCapacity() {
        int initialCapacity = stack.getCapacity();
        
        for (int i = 0; i < initialCapacity + 1; i++) {
            stack.push(i);
        }
        
        assertTrue(stack.getCapacity() > initialCapacity, 
                   "La capacidad debe aumentar cuando se alcanza el límite");
    }
    
    @Test
    public void testPushAndPopLargeNumbers() throws EmptyStackException {
        stack.push(1000000);
        stack.push(-500);
        stack.push(0);
        
        assertEquals(0, stack.pop());
        assertEquals(-500, stack.pop());
        assertEquals(1000000, stack.pop());
    }
    
    @Test
    public void testGenericType() throws EmptyStackException {
        Vector<String> stringStack = new Vector<>();
        stringStack.push("hola");
        stringStack.push("mundo");
        
        assertEquals("mundo", stringStack.pop());
        assertEquals("hola", stringStack.pop());
    }
}
