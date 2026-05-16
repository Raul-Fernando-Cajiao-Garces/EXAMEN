import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        GestionProductos gestion = new GestionProductos();

        gestion.cargarArchivo();

        int opcion = 0;

        do {
            System.out.println("\n--- MENU DE TIENDA TECNOLÓGICA ---");
            System.out.println("1. Registrar Producto");
            System.out.println("2. Buscar Producto");
            System.out.println("3. Vender Producto (Descontar Stock)");
            System.out.println("4. Mostrar Lista (ArrayList)");
            System.out.println("5. Mostrar Árbol Binario (Ordenado por Código)");
            System.out.println("6. Ordenar ArrayList (Nombre, Precio o Stock)");
            System.out.println("7. Calcular Stock Total General (Recursivo)");
            System.out.println("8. Guardar Cambios (.txt)");
            System.out.println("9. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese código único: ");
                    String cod = teclado.nextLine();

                    String nom = "";
                    while (nom.equals("")) {
                        System.out.print("Ingrese nombre: ");
                        nom = teclado.nextLine().trim();
                        if (nom.equals("")) {
                            System.out.println(" El nombre no puede estar vacío.");
                        }
                    }

                    System.out.print("Ingrese categoría: ");
                    String cat = teclado.nextLine();

                    double pre = 0;
                    while (pre <= 0) {
                        System.out.print("Ingrese precio (mayor a 0): ");
                        pre = teclado.nextDouble();
                        if (pre <= 0) {
                            System.out.println("El precio debe ser mayor a 0.");
                        }
                    }

                    int stk = -1;
                    while (stk < 0) {
                        System.out.print("Ingrese stock (no negativo): ");
                        stk = teclado.nextInt();
                        if (stk < 0) {
                            System.out.println(" El stock no puede ser negativo.");
                        }
                    }
                    teclado.nextLine();

                    Producto nuevo = new Producto(cod, nom, cat, pre, stk);
                    if (gestion.registrarProducto(nuevo)) {
                        System.out.println(" Guardado en memoria.");
                    } else {
                        System.out.println("Error: Ese código ya existe.");
                    }
                    break;

                case 2:
                    System.out.print("Ingrese código a buscar: ");
                    String busqueda = teclado.nextLine();
                    Producto encontrado = gestion.buscarPorCodigo(busqueda);
                    if (encontrado != null) {
                        encontrado.mostrarDatos();
                    } else {
                        System.out.println("Producto no encontrado.");
                    }
                    break;

                case 3:
                    System.out.print("Ingrese código del producto: ");
                    String codVenta = teclado.nextLine();
                    System.out.print("Cantidad a vender: ");
                    int cantVenta = teclado.nextInt();
                    teclado.nextLine();

                    if (gestion.venderProducto(codVenta, cantVenta)) {
                        System.out.println("Venta realizada exitosamente.");
                    }
                    break;

                case 4:
                    System.out.println("PRODUCTOS EN ARRAYLIST:");
                    gestion.mostrarReporteArrayList();
                    break;

                case 5:
                    System.out.println("RODUCTOS EN ÁRBOL BINARIO (POR CÓDIGO):");
                    gestion.mostrarReporteArbol();
                    break;

                case 6:
                    System.out.println("¿Cómo desea ordenar la lista?");
                    System.out.println("1. Por Nombre\n2. Por Precio\n3. Por Stock");
                    int criterio = teclado.nextInt();
                    teclado.nextLine();

                    if (criterio == 1) {
                        gestion.ordenarPorNombre();
                        System.out.println(" Ordenado por Nombre.");
                    } else if (criterio == 2) {
                        gestion.ordenarPorPrecio();
                        System.out.println(" Ordenado por Precio.");
                    } else if (criterio == 3) {
                        gestion.ordenarPorStock();
                        System.out.println(" Ordenado por Stock.");
                    }
                    gestion.mostrarReporteArrayList();
                    break;

                case 7:
                    int totalStock = gestion.calcularStockTotal();
                    System.out.println("El Stock Total de la tienda (Vía Recursiva) es: " + totalStock);
                    break;

                case 8:
                    gestion.guardarArchivo();
                    break;

                case 9:
                    System.out.println("Saliendo del sistema...");
                    break;

                default:
                    System.out.println(" Opción inválida.");
            }
        } while (opcion != 9);

        teclado.close();
    }
}