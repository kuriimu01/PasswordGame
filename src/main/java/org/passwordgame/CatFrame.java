package org.passwordgame;

import java.awt.Frame;
import java.awt.Label;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.MediaTracker;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class CatFrame extends Frame {
    Label l1;
    static Image catImage;

    CatFrame() {

        super("meow 🐈 ");

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        CatApi ca = new CatApi();
        ca.loadImage();

        l1 = new Label("Enjoy your cat <3");
        catImage = Toolkit.getDefaultToolkit().getImage("cat_image.jpg");
        MediaTracker track = new MediaTracker(this);

        setLayout(new FlowLayout()) ;

        track.addImage(catImage,0);
        try {
            track.waitForID(0);
        }
        catch(InterruptedException ignored){
        }
        add(l1);
        setVisible(true);
    }
    public void paint(Graphics g){
        g.drawImage(catImage,0,0,catImage.getWidth(this),catImage.getHeight(this),null);
    }
}

