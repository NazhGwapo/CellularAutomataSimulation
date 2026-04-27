package ui;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
public class warningDialog {
  public  void showWarning(Frame parent, String message) {
        Dialog dialog = new Dialog(parent, "Warning", true);
        dialog.setLayout(new BorderLayout());

        Label label = new Label(message, Label.CENTER);
        Button okButton = new Button("OK");

        okButton.addActionListener(e -> dialog.dispose());

        dialog.addWindowListener( new WindowAdapter() 
            {
            @Override
            public void windowClosing(WindowEvent e)
            {
                dialog.dispose();
            }
            });

        dialog.add(label, BorderLayout.CENTER);
        dialog.add(okButton, BorderLayout.SOUTH);

        dialog.setSize(300, 150);
        dialog.setLocationRelativeTo(parent);
        dialog.setVisible(true);
    }
}
