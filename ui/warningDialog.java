package ui;

import java.awt.*;
public class warningDialog {
  public  void showWarning(Frame parent, String message) {
        Dialog dialog = new Dialog(parent, "Warning", true);
        dialog.setLayout(new BorderLayout());

        Label label = new Label(message, Label.CENTER);
        Button okButton = new Button("OK");

        okButton.addActionListener(e -> dialog.dispose());

        dialog.add(label, BorderLayout.CENTER);
        dialog.add(okButton, BorderLayout.SOUTH);

        dialog.setSize(300, 150);
        dialog.setLocationRelativeTo(parent);
        dialog.setVisible(true);
    }
}
