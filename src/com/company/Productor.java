package com.company;

import java.util.TreeMap;

public class Productor implements Runnable {

    private String Nombre;
    private Inventario inventario;

    public Productor(String nombre, Inventario inventario) {
        this.Nombre = nombre;
        this.inventario = inventario;
    }

    private String getProducerName() {
        return Nombre;
    }

    @Override
    public void run() {

        while (!Thread.currentThread().isInterrupted()) {
            Producto pro = inventario.push();
            if (pro==null)
                break;
            try {
                System.out.println("El productor #"+getProducerName() + "ah creado el elemento " + pro);
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

        }
        System.out.println("El productor #"+Nombre+" fue eliminado");
    }
}
