import javax.swing.*;

public class Productor implements Runnable {
    private Contenedor contenedor;
    private JLabel layout;
    private boolean despierto;
    private Icon imageIconProductor = new ImageIcon("src/Imagenes/Productor.gif");
    private Icon imageIconDormido = new ImageIcon("src/Imagenes/Dormido.gif");

    public Productor(Contenedor contenedor) {
        this.contenedor = contenedor;
        this.despierto = true;
        this.layout = new JLabel();
    }

    public JLabel getLayout() {
        if (isDespierto()) {
            layout.setIcon(imageIconProductor);
        } else {
            layout.setIcon(imageIconDormido);
        }

        return layout;
    }

    @Override
    public void run() {
        while (true) {
            Producto producto = contenedor.push();
            try {
                Thread.sleep(200);
            } catch (InterruptedException error) {
                return;
            }
            System.out.println("Se agrego un producto");
        }
    }

    public boolean isDespierto() {
        return despierto;
    }

    public void setDespierto(boolean despierto) {
        this.despierto = despierto;
    }
}
