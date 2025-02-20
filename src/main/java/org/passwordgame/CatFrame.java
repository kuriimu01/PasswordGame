package org.passwordgame;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class CatFrame extends JFrame {
    JLabel l1;
    JLabel imageLabel;
    static ImageIcon catImageIcon;

    CatFrame() {
        super("meow 🐈 ");

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        l1 = new JLabel("мяв", SwingConstants.CENTER);
        imageLabel = new JLabel();

        setLayout(new BorderLayout());
        add(l1, BorderLayout.NORTH);
        add(imageLabel, BorderLayout.CENTER);

        loadImage();

        pack();
        setVisible(true);
    }

    private void loadImage() {
        SwingWorker<Void, Void> worker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() {
                CatApi ca = new CatApi();
                ca.loadImage();

                catImageIcon = new ImageIcon("cat_image.jpg");
                return null;
            }

            @Override
            protected void done() {
                if (catImageIcon != null) {
                    imageLabel.setIcon(catImageIcon);
                    pack();
                    setLocationRelativeTo(null);
                } else {
                    l1.setText("Failed to load cat image.");
                }
            }
        };

        worker.execute();
    }}

