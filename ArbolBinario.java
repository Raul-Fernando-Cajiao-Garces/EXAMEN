class Nodo {
    Producto producto;
    Nodo izquierdo;
    Nodo derecho;

    public Nodo(Producto producto) {
        this.producto = producto;
        this.izquierdo = null;
        this.derecho = null;
    }
}

public class ArbolBinario {
    private Nodo raiz;

    public void insertar(Producto producto) {
        raiz = insertarRecursivo(raiz, producto);
    }

    private Nodo insertarRecursivo(Nodo actual, Producto producto) {
        if (actual == null) {
            return new Nodo(producto);
        }
        if (producto.getCodigo().compareTo(actual.producto.getCodigo()) < 0) {
            actual.izquierdo = insertarRecursivo(actual.izquierdo, producto);
        } else if (producto.getCodigo().compareTo(actual.producto.getCodigo()) > 0) {
            actual.derecho = insertarRecursivo(actual.derecho, producto);
        }
        return actual;
    }

    public void mostrarArbol() {
        ejecutarInOrden(raiz);
    }

    private void ejecutarInOrden(Nodo nodo) {
        if (nodo != null) {
            ejecutarInOrden(nodo.izquierdo);
            nodo.producto.mostrarDatos();
            ejecutarInOrden(nodo.derecho);
        }
    }

    public void limpiarArbol() {
        this.raiz = null;
    }
}