package calculadora;

/**
 * Interfaz genérica para implementación de pilas (Stack)
 * Define las operaciones básicas de una pila
 * @param <T> tipo de dato genérico
 */
public interface Stack<T> {
    
    /**
     * Añade un elemento al tope de la pila
     * @param element elemento a añadir
     */
    void push(T element);
    
    /**
     * Extrae y devuelve el elemento del tope de la pila
     * @return elemento del tope
     * @throws EmptyStackException si la pila está vacía
     */
    T pop() throws EmptyStackException;
    
    /**
     * Devuelve el elemento del tope sin extraerlo
     * @return elemento del tope
     * @throws EmptyStackException si la pila está vacía
     */
    T peek() throws EmptyStackException;
    
    /**
     * Verifica si la pila está vacía
     * @return true si está vacía, false en caso contrario
     */
    boolean isEmpty();
    
    /**
     * Devuelve el número de elementos en la pila
     * @return tamaño de la pila
     */
    int size();
}
