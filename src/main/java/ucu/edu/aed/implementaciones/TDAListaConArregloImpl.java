package ucu.edu.aed.implementaciones;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Predicate;

import ucu.edu.aed.tda.TDALista;

public class TDAListaConArregloImpl<T> implements TDALista<T> {

    protected Object[] elementos;
    protected int size = 0;

    public TDAListaConArregloImpl() {
        elementos = new Object[10];
    }

    @Override
    public void agregar(T elem) {
        asegurarCapacidad(size + 1);
        elementos[size++] = elem;
    }

    @Override
    public void agregar(int index, T elem) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("index=" + index + ", size=" + size);
        }
        asegurarCapacidad(size + 1);
        for (int i = size; i > index; i--) {
            elementos[i] = elementos[i - 1];
        }
        elementos[index] = elem;
        size++;
    }

    @Override
    public T obtener(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("index=" + index + ", size=" + size);
        }
        return (T) elementos[index];
    }

    @Override
    public T remover(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("index=" + index + ", size=" + size);
        }
        T removed = (T) elementos[index];
        for (int i = index; i < size - 1; i++) {
            elementos[i] = elementos[i + 1];
        }
        elementos[--size] = null; // avoid memory leak
        return removed;
    }

    @Override
    public boolean remover(T elem) {
        int idx = indiceDe(elem);
        if (idx == -1) return false;
        remover(idx);
        return true;
    }

    @Override
    public boolean contiene(T elem) {
        return indiceDe(elem) != -1;
    }

    @Override
    public int indiceDe(T elem) {
        for (int i = 0; i < size; i++) {
            Object o = elementos[i];
            if (o == null) {
                if (elem == null) return i;
                else continue;
            }
            if (o.equals(elem)) return i;
        }
        return -1;
    }

    @Override
    public T buscar(Predicate<T> criterio) {
        for (int i = 0; i < size; i++) {
            T elemento = (T) elementos[i];
            if (criterio.test(elemento)) return elemento;
        }
        return null;
    }

    @Override
    public TDALista<T> ordenar(Comparator<T> comparator) {
        Object[] tmp = Arrays.copyOf(elementos, size);
        Arrays.sort(tmp, (o1, o2) -> comparator.compare((T) o1, (T) o2));
        TDAListaConArregloImpl<T> res = new TDAListaConArregloImpl<>();
        res.elementos = Arrays.copyOf(tmp, Math.max(tmp.length, 10));
        res.size = tmp.length;
        return res;
    }

    @Override
    public int tamanio() {
        return size;
    }

    @Override
    public boolean esVacio() {
        return size == 0;
    }

    @Override
    public void vaciar() {
        for (int i = 0; i < size; i++) {
            elementos[i] = null;
        }
        size = 0;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            sb.append(elementos[i]);
            if (i + 1 < size) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    protected void asegurarCapacidad(int minCapacity) {
        if (minCapacity <= elementos.length) return;
        int newCap = Math.max(minCapacity, elementos.length << 1);
        elementos = Arrays.copyOf(elementos, newCap);
    }


}
