public class Producto {
    private String codigo;
    private String nombre;
    private String categoria;
    private double precio;
    private int stock;

    public Producto(String codigo, String nombre, String categoria, double precio, int stock) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.categoria = categoria;
        this.precio = precio;
        this.stock = stock;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public double getPrecio() {
        return precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void mostrarDatos() {
        System.out.println("Código: " + codigo + " | Nombre: " + nombre + " | Cat: " + categoria + " | Precio: $"
                + precio + " | Stock: " + stock);
    }
}