package com.company;

import java.util.LinkedList;
import java.util.TreeMap;

public class Inventario {

    //private int base = 0;
    private int contador = 0;
    public static int tope = 5;

    private static LinkedList<Producto> productosl;

    public Inventario() {
        productosl = new LinkedList<Producto>();
    }

    private Producto[] productos = new Producto[10];

    public synchronized Producto push() {
        try {
            GUI.pushproduct(GUI.productoPanelGrid);
            while (productosl.size() >= tope) {
                System.out.println("El almacen esta lleno, Esperando...");
                GUI.amomir(GUI.productoPanelGrid, true);
                wait();
                GUI.atrabajar(GUI.productoPanelGrid, true);
            }
            contador++;
            productosl.add(new Producto(contador));

            notifyAll();
            return productosl.getLast();
        } catch (InterruptedException error) {
            return null;
        }
    }

    public synchronized Producto pop() {
        try {
            Producto producto = null;
            GUI.popProduct(GUI.productoPanelGrid);

            while (productosl.size() <= 0) {

                System.out.println("El almacen se encuentra vacio, Esperando...");
                GUI.amomir(GUI.consumerPanelGrid,false);
                wait();
                GUI.atrabajar(GUI.consumerPanelGrid, false);
            }

            producto = productosl.getFirst();
            productosl.pop();
            notifyAll();
            return producto;

        } catch (InterruptedException error) {
            return null;
        }
    }
}