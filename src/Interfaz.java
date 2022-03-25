import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Interfaz extends JFrame {
    private Productor[] productoresArray = new Productor[10];
    private Consumidor[] consumidorArray = new Consumidor[10];
    private int posXProductores, posYProductores;
    private int posXConsumidores, posYConsumidores;
    private Contenedor contenedorArray = new Contenedor();
    private int contadorProductores, contadorConsumidores;

    private Icon imageIconConsumidor = new ImageIcon("src/Imagenes/Consumidor.gif");
    private Icon imageIconDormido = new ImageIcon("src/Imagenes/Dormido.gif");
    private Icon imageIconInventario = new ImageIcon("src/Imagenes/Invantario.png");
    private Icon imageIconProducto = new ImageIcon("src/Imagenes/Producto.png");


    public Interfaz() {
        setBounds(600, 450, 800, 550);
        setTitle("Productor - Consumidor -> Kevin Carrillo");
        contadorConsumidores = 0;
        contadorProductores = 0;

        posXProductores = 10;  posYProductores = 20;
        posXConsumidores = 520;  posYConsumidores = 20;



        JLabel TxtProductores = new JLabel("Productor");
        TxtProductores.setBounds(150, 450, 100, 50);
        add(TxtProductores);
        JLabel TxtInventario = new JLabel("Inventario");
        TxtInventario.setBounds(370, 450, 100, 50);
        add(TxtInventario);
        JLabel TxtConsumidor = new JLabel("Consumidor");
        TxtConsumidor.setBounds(580, 450, 100, 50);
        add(TxtConsumidor);

        // x = 90... y = 100

//        posX = 320;
//        posY = 50;
//        // x = 90... y = 100
//        for (int x = 1; x <= 7; x++) {
//            if (x != 1 && x != 6) posY += 90;
//            if (x % 6 == 0) {
//                posX = 420;
//                posY = 50;
//            }
//            Icon imageIcon = new ImageIcon("src/Imagenes/Inventario.png");
//            JLabel txtInventario = new JLabel();
//            txtInventario.setIcon(imageIcon);
//            txtInventario.setBounds(posX, posY, 70, 70);
//            txtInventario.updateUI();
//            add(txtInventario);
//        }
//
//        int posX = 320;
//        int posY = 20;
//        // x = 90... y = 100
//        for (int x = 1; x <= 7; x++) {
//            if (x != 1 && x != 6) posY += 90;
//            if (x % 6 == 0) {
//                posX = 420;
//                posY = 20;
//            }
//            Icon imageIcon = new ImageIcon("src/Imagenes/Producto.png");
//            JLabel txtProducto = new JLabel();
//            txtProducto.setIcon(imageIcon);
//            txtProducto.setBounds(posX, posY, 70, 70);
//            add(txtProducto);
//        }
//
//
//        posX = 520;
//        posY = 20;
//        // x = 90... y = 100
//        for (int x = 1; x <= 6; x++) {
//            if (x != 1 && x != 6) posY += 90;
//            if (x % 6 == 0) {
//                posX = 620;
//                posY = 20;
//            }
//            Icon imageIcon = new ImageIcon("src/Imagenes/Consumidor.gif");
//            JLabel txtConsumidor = new JLabel();
//            txtConsumidor.setIcon(imageIcon);
//            txtConsumidor.setBounds(posX, posY, 70, 70);
//            txtConsumidor.updateUI();
//            add(txtConsumidor);
//        }


        JLabel Txt = new JLabel("");
        Txt.setBounds(370, 350, 100, 50);
        add(Txt);

        JPanel laminaBotones = new JPanel();
        JPanel laminaProductores = new JPanel();

        ponerBoton(laminaBotones, "Agrgegar", new ActionListener() {
            public void actionPerformed(ActionEvent evento) {
                if (contadorProductores < 10) {
                    Productor productor = new Productor(contenedorArray);
                    productoresArray[contadorProductores] = productor;
//                    productoresArray[contadorProductores].run();
                    laminaProductores.add(productoresArray[contadorProductores].getLayout());
                    contadorProductores++;
                }
                laminaProductores.updateUI();
            }
        });

        ponerBoton(laminaBotones, "Eliminar", new ActionListener() {
            public void actionPerformed(ActionEvent evento) {
                if (contadorProductores > 0) {
                    //productoresArray[contadorProductores - 1] = null;
                    posYProductores -= 90;

                    if (productoresArray.length - 1 == 5) {
                        posYProductores = 470;
                        posXProductores = 10;
                    }

                    laminaProductores.remove(productoresArray[contadorProductores - 1].getLayout());
                    contadorProductores--;
                }
                laminaProductores.updateUI();
            }
        });

        ponerBoton(laminaBotones, "Aumentar", new ActionListener() {
            public void actionPerformed(ActionEvent evento) {

            }
        });

        ponerBoton(laminaBotones, "Disminuir", new ActionListener() {
            public void actionPerformed(ActionEvent evento) {

            }
        });

        ponerBoton(laminaBotones, "Agregar", new ActionListener() {
            public void actionPerformed(ActionEvent evento) {
                if (contadorConsumidores < 10) {
                    Consumidor consumidor = new Consumidor(contenedorArray);
                    consumidorArray[contadorConsumidores] = consumidor;
                    contadorConsumidores++;
                }
                imprimirConsumidores();
            }
        });

        ponerBoton(laminaBotones, "Eliminar", new ActionListener() {
            public void actionPerformed(ActionEvent evento) {

            }
        });

        add(laminaBotones, BorderLayout.SOUTH);
        laminaBotones.getAlignmentY();
        add(laminaProductores, BorderLayout.CENTER);
        laminaProductores.getAlignmentY();
    }

    public void ponerBoton(Container c, String titulo, ActionListener oyente) {
        JButton boton = new JButton(titulo);
        c.add(boton);
        boton.addActionListener(oyente);
    }

    public void imprimirProductor() {
        int pos = contadorProductores -1;
            if (pos!= 0 && pos != 5) posYProductores += 90;
            if (pos % 5 == 0) {
                posXProductores = 50;
                posYProductores = 20;
            }

            productoresArray[pos].getLayout().setBounds(posXProductores, posYProductores, 70, 70);
            add(productoresArray[pos].getLayout());
            productoresArray[pos].run();
    }

    public void imprimirConsumidores(){
        int pos = contadorConsumidores - 1;
        if (pos!= 0 && pos != 5) posYConsumidores += 90;
        if (pos % 5 == 0) {
            posXConsumidores = 50;
            posYConsumidores = 20;
        }

        consumidorArray[pos].getLayout().setBounds(posXConsumidores, posYConsumidores, 70, 70);
        add(consumidorArray[pos].getLayout());
    }
}
