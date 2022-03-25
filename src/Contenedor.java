public class Contenedor {
    private int base = 0;
    private int top = 0;
    private int pos = 0;

    private Producto[] productos = new Producto[10];

    public synchronized Producto push() {
        pos++;
        Producto producto = new Producto(pos);

        while (top == productos.length) {
            try {
                System.out.println("El contenedor se encuentra lleno");
                wait();
            } catch (InterruptedException error) {
                System.out.println("Error " + error);
            }
        }

        productos[top] = producto;
        top++;
        notifyAll();
        return producto;
    }

    public synchronized Producto pop() {
        Producto producto = null;

        while (top == base) {
            try {
                System.out.println("El contenedor se encuentra vacio. Esperando...");
                wait();
            } catch (InterruptedException error) {
                System.out.println("Error " + error);
            }
        }

        top--;
        producto = productos[top];
        productos[top] = null;
        notifyAll();
        return producto;
    }
}
