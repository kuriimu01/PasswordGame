package org.passwordgame;

import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.Button;
import java.awt.Checkbox;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class MainFrame extends Frame {

    Label l1;
    TextField tf;
    Button b1;
    Checkbox cb;
    public MainFrame() {
        super("Password checker");

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        l1 = new Label("Enter your password!", 1);

        tf = new TextField(20);
        b1 = new Button("Show Me");
        cb = new Checkbox("Hide your password");

        Handler h = new Handler();
        tf.addTextListener(h);
        tf.addActionListener(h);
        b1.addActionListener(h);
        cb.addItemListener(h);

        setLayout(new GridLayout(3, 1));
        add(l1);
        add(tf);
        add(cb);

    }
    class Handler implements ActionListener, TextListener, ItemListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            CatFrame cf = new CatFrame();
            cf.setSize(CatFrame.catImage.getWidth(cf), CatFrame.catImage.getHeight(cf));
            cf.setVisible(true);
            cf.setLocationRelativeTo(null);
            dispose();
        }
        @Override
        public void itemStateChanged(ItemEvent e) {
            if (!tf.echoCharIsSet()) {
                tf.setEchoChar('*');
            } else {
                tf.setEchoChar((char) 0);
            }
        }
        @Override
        public void textValueChanged(TextEvent e) {
            String str = tf.getText();
            if (str.length() < 5) {
                l1.setText("Rule 1: Your password must be at least 5 characters");
            } else if (!hasNumber(str)) {
                l1.setText("Rule 2: Your password must include a number.");
            } else if (!hasUpperCase(str)) {
                l1.setText("Rule 3: Your password must include an uppercase letter.");
            } else if (!hasSpecialCharacter(str)) {
                l1.setText("Rule 4: Your password must include a special character.");
            } else {
                l1.setText("You win! Enjoy your cat picture!");
                add(b1);
                setLayout(new GridLayout(4, 1));
                setVisible(true);
            }
        }

        public boolean hasUpperCase(String str) {
            Pattern pattern = Pattern.compile("[A-Z]");
            Matcher matcher = pattern.matcher(str);
            return matcher.find();
        }

        public boolean hasNumber(String str) {
            Pattern pattern = Pattern.compile("\\d");
            Matcher matcher = pattern.matcher(str);
            return matcher.find();
        }

        public boolean hasSpecialCharacter(String str) {
            Pattern pattern = Pattern.compile("[^a-zA-Z0-9]");
            Matcher matcher = pattern.matcher(str);
            return matcher.find();
        }
    }
}

