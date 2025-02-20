package org.passwordgame;


import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;


public class MainFrame extends JFrame {

    Validator v = new Validator();

    JLabel l1;
    JLabel l2;
    JTextField tf;
    JPasswordField pf;
    JButton b1;
    JCheckBox cb;
    boolean correctUsername = false;
    boolean correctPassword = false;

    public MainFrame() {
        super("The Password Game");
        setSize(600, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon icon = new ImageIcon("./icon/icon.png");
        setIconImage(icon.getImage());

        l1 = new JLabel("Пароль", SwingConstants.CENTER);
        l2 = new JLabel("Ім'я користувача", SwingConstants.CENTER);
        tf = new JTextField();
        pf = new JPasswordField();
        b1 = new JButton("Далі💫");
        cb = new JCheckBox("Приховати");

        Handler h = new Handler();
        pf.setPreferredSize(new Dimension(600, 45));
        pf.setEchoChar((char) 0);

        tf.getDocument().addDocumentListener(h);
        tf.addActionListener(h);
        pf.getDocument().addDocumentListener(h);
        pf.addActionListener(h);
        b1.addActionListener(h);
        cb.addItemListener(h);

        setLayout(new GridLayout(6, 1));
        add(l2);
        add(tf);
        add(l1);
        add(pf);
        add(cb);

    }

    class Handler implements ActionListener, DocumentListener, ItemListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == b1) {
                SwingUtilities.invokeLater(CatFrame::new);
                dispose();
            }
        }

        @Override
        public void insertUpdate(DocumentEvent e) {
            checkFields(e);
            winCheck();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            checkFields(e);
            winCheck();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            checkFields(e);
            winCheck();
        }

        @Override
        public void itemStateChanged(ItemEvent e) {
            if (e.getSource() == cb) {
                if (cb.isSelected()) {
                    pf.setEchoChar('*');
                } else {
                    pf.setEchoChar((char) 0);
                }
            }
        }

        private void checkFields(DocumentEvent e) {
            if (e.getDocument() == tf.getDocument()) {
                checkUsername();
            } else if (e.getDocument() == pf.getDocument()) {
                checkPassword();
            }
        }

        private void checkUsername() {
            String username = tf.getText();

            if (username.length() < 3) {
                l2.setText("Ім'я користувача має містити щонайменше 3 символи.");
                correctUsername = false;
            } else if (username.length() > 20) {
                l2.setText("Ім'я користувача не може перевищувати 20 символів.");
                correctUsername = false;
            } else if (v.hasSpaces(username)) {
                l2.setText("Ім'я користувача не може містити пробіли.");
                correctUsername = false;
            } else if (!v.isValidUsername(username)) {
                l2.setText("Ім'я користувача може містити лише літери, цифри, '_', '-'.");
                correctUsername = false;
            } else {
                l2.setText("Ім'я користувача правильне!");
                correctUsername = true;
            }
        }


        private void checkPassword() {
            String password = new String(pf.getPassword());

            if (!v.hasLowerCase(password)) {
                l1.setText("Пароль повинен містити хоча б одну малу літеру.");
                correctPassword = false;
            } else if (password.length() < 8) {
                l1.setText("Пароль повинен містити щонайменше 8 символів.");
                correctPassword = false;
            } else if (!v.hasNumber(password)) {
                l1.setText("Пароль повинен містити хоча б одну цифру.");
                correctPassword = false;
            } else if (!v.hasUpperCase(password)) {
                l1.setText("Пароль повинен містити хоча б одну велику літеру.");
                correctPassword = false;
            } else if (!v.hasSpecialCharacter(password)) {
                l1.setText("Пароль повинен містити хоча б один спеціальний символ.");
                correctPassword = false;
            } else {
                correctPassword = true;
            }
        }

        private void winCheck() {
            if (correctUsername && correctPassword) {
                l2.setText("Ім'я користувача та пароль правильні! Насолоджуйтесь картинкою кота!");
                l1.setText("");
                add(b1);
                setLayout(new GridLayout(7, 1));
                setVisible(true);
            }
        }
    }
}


