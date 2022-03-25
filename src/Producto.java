public class Producto {
    private int idProducto = 0;

    public Producto(int id) {
        this.idProducto = id;
    }

    public int getIdProducto() {
        return this.idProducto;
    }

    public void setIdProducto(int id) {
        this.idProducto = id;
    }

    @Override
    public String toString() {
        return "" + idProducto;
    }
}
