import java.util.ArrayList;
import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class GestionProductos {
    private ArrayList<Producto> listaProductos;
    private ArbolBinario arbol;
    private String nombreArchivo = "productos.txt";

    public GestionProductos() {
        this.listaProductos = new ArrayList<>();
        this.arbol = new ArbolBinario();
    }

    public Producto buscarPorCodigo(String codigo) {
        for (int i = 0; i < listaProductos.size(); i++) {
            if (listaProductos.get(i).getCodigo().equalsIgnoreCase(codigo)) {
                return listaProductos.get(i);
            }
        }
        return null;
    }

    public boolean registrarProducto(Producto p) {
        if (buscarPorCodigo(p.getCodigo()) != null) {
            return false;
        }
        listaProductos.add(p);
        arbol.insertar(p);
        return true;
    }

    public boolean venderProducto(String codigo, int cantidad) {
        Producto p = buscarPorCodigo(codigo);
        if (p == null) {
            System.out.println("❌ Producto no encontrado.");
            return false;
        }
        if (p.getStock() < cantidad || p.getStock() == 0) {
            System.out.println("❌ No hay suficiente Stock para realizar la venta.");
            return false;
        }
        p.setStock(p.getStock() - cantidad);
        return true;
    }

    public int calcularStockTotal() {
        return sumarStockRecursivo(0);
    }

    private int sumarStockRecursivo(int indice) {
        if (indice == listaProductos.size()) {
            return 0;
        }
        return listaProductos.get(indice).getStock() + sumarStockRecursivo(indice + 1);
    }

    public void ordenarPorNombre() {
        for (int i = 0; i < listaProductos.size() - 1; i++) {
            for (int j = 0; j < listaProductos.size() - i - 1; j++) {
                if (listaProductos.get(j).getNombre().compareToIgnoreCase(listaProductos.get(j + 1).getNombre()) > 0) {
                    Producto temp = listaProductos.get(j);
                    listaProductos.set(j, listaProductos.get(j + 1));
                    listaProductos.set(j + 1, temp);
                }
            }
        }
    }

    public void ordenarPorPrecio() {
        for (int i = 0; i < listaProductos.size() - 1; i++) {
            for (int j = 0; j < listaProductos.size() - i - 1; j++) {
                if (listaProductos.get(j).getPrecio() > listaProductos.get(j + 1).getPrecio()) {
                    Producto temp = listaProductos.get(j);
                    listaProductos.set(j, listaProductos.get(j + 1));
                    listaProductos.set(j + 1, temp);
                }
            }
        }
    }

    public void ordenarPorStock() {
        for (int i = 0; i < listaProductos.size() - 1; i++) {
            for (int j = 0; j < listaProductos.size() - i - 1; j++) {
                if (listaProductos.get(j).getStock() > listaProductos.get(j + 1).getStock()) {
                    Producto temp = listaProductos.get(j);
                    listaProductos.set(j, listaProductos.get(j + 1));
                    listaProductos.set(j + 1, temp);
                }
            }
        }
    }

    public void mostrarReporteArrayList() {
        if (listaProductos.isEmpty()) {
            System.out.println("La lista está vacía.");
            return;
        }
        for (int i = 0; i < listaProductos.size(); i++) {
            listaProductos.get(i).mostrarDatos();
        }
    }

    public void mostrarReporteArbol() {
        arbol.mostrarArbol();
    }

    public void guardarArchivo() {
        try {
            File archivo = new File(nombreArchivo);
            PrintWriter escritor = new PrintWriter(archivo);

            for (int i = 0; i < listaProductos.size(); i++) {
                Producto p = listaProductos.get(i);
                escritor.println(p.getCodigo() + "," + p.getNombre() + "," + p.getCategoria() + "," + p.getPrecio()
                        + "," + p.getStock());
            }
            escritor.close();

            System.out.println("💾 ¡Datos guardados con éxito!");
            // Esto te mostrará exactamente en qué carpeta de tu PC se creó el archivo txt
            System.out.println("📍 Búscalo en: " + archivo.getAbsolutePath());

        } catch (Exception e) {
            System.out.println("❌ Error crítico al guardar el archivo: " + e.getMessage());
        }
    }

    public void cargarArchivo() {
        try {
            File archivo = new File(nombreArchivo);

            if (archivo.exists()) {
                Scanner lector = new Scanner(archivo);
                listaProductos.clear();
                arbol.limpiarArbol();

                while (lector.hasNextLine()) {
                    String linea = lector.nextLine();

                    if (linea.trim().equals("")) {
                        continue;
                    }

                    String[] datos = linea.split(",");
                    if (datos.length == 5) {
                        String cod = datos[0];
                        String nom = datos[1];
                        String cat = datos[2];
                        double pre = Double.parseDouble(datos[3]);
                        int stk = Integer.parseInt(datos[4]);

                        Producto p = new Producto(cod, nom, cat, pre, stk);
                        listaProductos.add(p);
                        arbol.insertar(p);
                    }
                }
                lector.close();
                System.out.println("Archivo '" + nombreArchivo + "' detectado. Datos cargados con éxito.");
            } else {
                System.out.println("ℹNota: No hay datos guardados previos. El sistema iniciará vacío.");
            }
        } catch (Exception e) {
            System.out.println(" Error al cargar los datos del archivo: " + e.getMessage());
        }
    }
}