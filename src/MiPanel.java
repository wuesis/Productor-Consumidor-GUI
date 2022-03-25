import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MiPanel extends JPanel {
    private JTextField campo;
    public MiPanel(){
        campo = new JTextField(20);
        add(campo);

        JButton miBoton = new JButton("Agregar");
        TextoIngresado miEvento = new TextoIngresado();
        miBoton.addActionListener(miEvento);
        add(miBoton);
    }


    private class TextoIngresado implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println(campo.getText().trim());
        }
    }
}
