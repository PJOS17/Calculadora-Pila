package calculadora;

/**
 * Implementación de pila (Stack) usando un vector de tamaño variable
 * Utiliza genéricos para permitir almacenar cualquier tipo de dato
 * @param <T> tipo de dato genérico
 */
public class Vector<T> implements Stack<T> {
    
    private static final int INITIAL_CAPACITY = 10;
    private T[] elements;
    private int size;
    
    /**
     * Constructor que inicializa el vector con capacidad inicial
     */
    @SuppressWarnings("unchecked")
    public Vector() {
        this.elements = (T[]) new Object[INITIAL_CAPACITY];
        size = 0;
    }
    
    /**
     * Añade un elemento al tope de la pila
     * Aumenta la capacidad del vector si es necesario
     * @param element elemento a añadir
     */
    @Override
    public void push(T element) {
        if (size == elements.length) {
            expandCapacity();
        }
        elements[size] = element;
        size++;
    }
    
    /**
     * Extrae y devuelve el elemento del tope de la pila
     * @return elemento del tope
     * @throws EmptyStackException si la pila está vacía
     */
    @Override
    public T pop() throws EmptyStackException {
        if (isEmpty()) {
            throw new EmptyStackException("No se puede hacer pop en una pila vacía");
        }
        size--;
        T element = elements[size];
        elements[size] = null; // Permitir que se haga garbage collection
        return element;
    }
    
    /**
     * Devuelve el elemento del tope sin extraerlo
     * @return elemento del tope
     * @throws EmptyStackException si la pila está vacía
     */
    @Override
    public T peek() throws EmptyStackException {
        if (isEmpty()) {
            throw new EmptyStackException("No se puede hacer peek en una pila vacía");
        }
        return elements[size - 1];
    }
    
    /**
     * Verifica si la pila está vacía
     * @return true si está vacía, false en caso contrario
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }
    
    /**
     * Devuelve el número de elementos en la pila
     * @return tamaño de la pila
     */
    @Override
    public int size() {
        return size;
    }
    
    /**
     * Aumenta la capacidad del vector cuando se llena
     * Duplica la capacidad actual
     */
    @SuppressWarnings("unchecked")
    private void expandCapacity() {
        T[] newElements = (T[]) new Object[elements.length * 2];
        for (int i = 0; i < size; i++) {
            newElements[i] = elements[i];
        }
        elements = newElements;
    }
    
    /**
     * Devuelve la capacidad actual del vector
     * Útil para pruebas y depuración
     * @return capacidad del vector
     */
    public int getCapacity() {
        return elements.length;
    }
}
