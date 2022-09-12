package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;

public class GUI extends JFrame {



    public static final Icon ICON_CONSUMMER = new ImageIcon("src/Imagenes/Consumidor.gif");
    public static final Icon ICON_SLEEP = new ImageIcon("src/Imagenes/Dormido.gif");
    public static final Icon ICON_INVENTORY = new ImageIcon("src/Imagenes/Inventario.png");
    public static final Icon ICON_PRODUCT = new ImageIcon("src/Imagenes/Producto.png");
    public static final Icon ICON_PRODUCER = new ImageIcon("src/Imagenes/Productor.gif");

    static LinkedList<JLabel> jProductor = new LinkedList<JLabel>();
    static LinkedList<JLabel> jConsummer = new LinkedList<JLabel>();
    static LinkedList<JLabel> jInventory = new LinkedList<JLabel>();
    static LinkedList<JButton> buttonsControl = new LinkedList<JButton>();

    static LinkedList<Thread> productoresCorriendo = new LinkedList<Thread>();

    static LinkedList<Thread> consumidoresCorriendo = new LinkedList<Thread>();

    static Inventario inventario;


    private int contadorProductores = 0, contadorProductoresTotales = 0, contadorConsumidores = 0, contadorConsumidoresTotales = 0, contadorInventory = 0;


    public static JPanel productoPanelGrid = new JPanel();
    public static JPanel inventoryPanelGrid = new JPanel();
    public static JPanel consumerPanelGrid = new JPanel();

    public GUI() {

        super("Productor consumidor");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1080, 720);
        setBounds(600, 450, 1080, 720);
        setVisible(true);
        setLayout(new BorderLayout());
        inventario = new Inventario();

        //Button panel
        JPanel buttonsPanel = new JPanel();
        JPanel leftButtonPanel = new JPanel();
        JPanel centerButtonPanel = new JPanel();
        JPanel rightButtonPanel = new JPanel();

        //Product panel
        JPanel productorPanel = new JPanel();
        //Inventory panel
        JPanel inventoryPanel = new JPanel();
        //Consumidor panel
        JPanel consumerPanel = new JPanel();


        buttonsPanel.setLayout(new BorderLayout());
        productoPanelGrid.setLayout(new GridLayout(5, 2, 5, 5));
        inventoryPanelGrid.setLayout(new GridLayout(5, 2, 5, 5));
        consumerPanelGrid.setLayout(new GridLayout(5, 2, 5, 5));


        ponerBoton(leftButtonPanel, "++Productor", new ActionListener() {
            public void actionPerformed(ActionEvent evento) {
                pushITEM(jProductor, productoPanelGrid, ICON_PRODUCER);
                contadorProductores++;
                contadorProductoresTotales++;
                var productor = new Productor(contadorProductoresTotales + " ", inventario);
                productoresCorriendo.add(new Thread(productor));
                productoresCorriendo.getLast().start();
                buttonsControl.get(1).setEnabled(true);

            }
        });
        ponerBoton(leftButtonPanel, "--Productor", new ActionListener() {
            public void actionPerformed(ActionEvent evento) {
                popItem(jProductor, productoPanelGrid);
                contadorProductores--;
                var threadP = productoresCorriendo.pop();
                threadP.interrupt();
                if (contadorProductores < 1)
                    buttonsControl.get(1).setEnabled(false);
                repaint();


            }
        });

        ponerBoton(centerButtonPanel, "++Inventario", new ActionListener() {
            public void actionPerformed(ActionEvent evento) {
                pushITEM(jInventory, inventoryPanelGrid, ICON_INVENTORY);
                contadorInventory++;
                Inventario.tope++;
                buttonsControl.get(3).setEnabled(true);

            }
        });
        ponerBoton(centerButtonPanel, "--Inventario", new ActionListener() {
            public void actionPerformed(ActionEvent evento) {
                popItem(jInventory, inventoryPanelGrid);
                contadorInventory--;
                Inventario.tope--;
                if (contadorInventory < 1)
                    buttonsControl.get(3).setEnabled(false);
                repaint();
            }
        });

        ponerBoton(rightButtonPanel, "++Consumidor", new ActionListener() {
            public void actionPerformed(ActionEvent evento) {
                pushITEM(jConsummer, consumerPanelGrid, ICON_CONSUMMER);
                contadorConsumidores++;
                contadorConsumidoresTotales++;
                var consumidor = new Consumidor(contadorConsumidoresTotales + "", inventario);
                consumidoresCorriendo.add(new Thread(consumidor));
                consumidoresCorriendo.getLast().start();

                buttonsControl.get(5).setEnabled(true);
            }
        });

        ponerBoton(rightButtonPanel, "--Consumidor", new ActionListener() {
            public void actionPerformed(ActionEvent evento) {
                popItem(jConsummer, consumerPanelGrid);
                contadorConsumidores--;
                var threadC = consumidoresCorriendo.pop();
                threadC.interrupt();
                if (contadorConsumidores < 1)
                    buttonsControl.get(5).setEnabled(false);
                repaint();
            }
        });

        buttonsControl.get(1).setEnabled(false);
//        buttonsControl.get(3).setEnabled(false);
        buttonsControl.get(5).setEnabled(false);

        for (int i = 0; i < Inventario.tope; i++) {
            pushITEM(jInventory, inventoryPanelGrid, ICON_INVENTORY);
            contadorInventory++;
        }


        //Set all the button panels
        buttonsPanel.add(leftButtonPanel, BorderLayout.WEST);
        buttonsPanel.add(centerButtonPanel, BorderLayout.CENTER);
        buttonsPanel.add(rightButtonPanel, BorderLayout.EAST);

        //Add all the grids
        productorPanel.add(productoPanelGrid);
        inventoryPanel.add(inventoryPanelGrid);
        consumerPanel.add(consumerPanelGrid);

        //Add all the main panels
        add(consumerPanel, BorderLayout.EAST);
        add(inventoryPanel, BorderLayout.CENTER);
        add(productorPanel, BorderLayout.WEST);
        add(buttonsPanel, BorderLayout.NORTH);

        buttonsPanel.getAlignmentY();
    }


    public void ponerBoton(Container container, String name, ActionListener listener) {
        JButton boton = new JButton(name);
        buttonsControl.add(boton);
        container.add(boton);
        boton.addActionListener(listener);
    }

    public static void pushITEM(LinkedList<JLabel> type, Container container, Icon icon) {
        JLabel label = new JLabel();
        type.add(label);
        label.setIcon(icon);
        container.add(label);
        container.revalidate();
    }

    public void popItem(LinkedList<JLabel> type, Container container) {
        JLabel targetLabel = type.get(type.size() - 1);
        container.remove(targetLabel);
        type.remove(targetLabel);
    }


    public static void pushproduct(Container container) {
        for (var item : jInventory) {
            if (item.getIcon() == ICON_INVENTORY) {
                item.setIcon(ICON_PRODUCT);
                break;
            }
        }
        container.revalidate();
    }

    public static void popProduct(Container container) {
        for (var item : jInventory) {
            if (item.getIcon() == ICON_PRODUCT) {
                item.setIcon(ICON_INVENTORY);
                break;
            }
        }
        container.revalidate();
    }

    public static void amomir(Container container, boolean pc) {
        if (pc) {
            for (var item : jProductor) {
                item.setIcon(ICON_SLEEP);
            }
        } else {
            for (var item : jConsummer) {
                item.setIcon(ICON_SLEEP);
            }
        }
        container.revalidate();
    }

    public static void atrabajar(Container container, boolean pc) {
        if (pc) {
            for (var item : jProductor) {
                item.setIcon(ICON_PRODUCER);
            }
        } else {
            for (var item : jConsummer) {
                item.setIcon(ICON_CONSUMMER);
            }
        }
        container.revalidate();
    }

}
