import javax.swing.*;

public class Consumidor extends Thread {
    private Contenedor contenedor;
    private JLabel layout;
    private boolean despierto;
    private Icon imageIconConsumidor = new ImageIcon("src/Imagenes/Consumidor.gif");
    private Icon imageIconDormido = new ImageIcon("src/Imagenes/Dormido.gif");

    public Consumidor(Contenedor contenedor) {
        this.contenedor = contenedor;
        this.despierto = true;
        this.layout = new JLabel();
    }

    public JLabel getLayout(){
        if(isDespierto()){
            layout.setIcon(imageIconConsumidor);
        } else{
            layout.setIcon(imageIconDormido);
        }

        return layout;
    }

    @Override
    public void run() {
        while (true) {
            Producto producto = contenedor.pop();
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                return;
            }
            System.out.println("Se consumio un producto");
        }
    }

    public boolean isDespierto(){
        return despierto;
    }

    public void setDespierto(boolean despierto){
        this.despierto = despierto;
    }
}
