package com.company;

public class Consumidor extends Thread {
    private String Nombre = null;
    private Inventario inventario = null;

    public Consumidor(String nombre, Inventario inventario) {
        this.Nombre = nombre;
        this.inventario = inventario;
    }

    public void setConsumerName(String consumerName) {
        this.Nombre = consumerName;
    }

    public String getConsumerName() {
        return Nombre;
    }

    @Override
    public void run() {

        while (!Thread.currentThread().isInterrupted()) {
            try {
                Producto pro = inventario.pop();
                if (pro==null)
                    break;
                System.out.println("El consumidor #" + getConsumerName() + " ha consumido el elemento " + pro);
                Thread.sleep(4000);

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

        }
        System.out.println("El consumidor #" + Nombre + " fue eliminado");
    }
}