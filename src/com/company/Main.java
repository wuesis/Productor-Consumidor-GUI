package com.company;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;

public class Main {

    public static void main(String[] args) throws InterruptedException, InvocationTargetException {


        Runnable initFrame = new Runnable() {
            @Override
            public void run() {
                new GUI();
            }
        };

        SwingUtilities.invokeAndWait(initFrame);



    }
}
