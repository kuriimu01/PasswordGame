package org.passwordgame;


import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.SwingUtilities;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainFrame extends JFrame {

    JLabel l1;
    JPasswordField pf;
    JButton b1;
    JCheckBox cb;

    public MainFrame() {
        super("The Password Game");
        setSize(600, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon icon = new ImageIcon("./icon/icon.png");
        setIconImage(icon.getImage());

        l1 = new JLabel("Enter your password!", SwingConstants.CENTER);
        pf = new JPasswordField();
        b1 = new JButton("Show Me");
        cb = new JCheckBox("Hide");

        Handler h = new Handler();
        pf.setPreferredSize(new Dimension(600, 45));
        pf.setEchoChar((char) 0);

        pf.getDocument().addDocumentListener(h);
        pf.addActionListener(h);
        b1.addActionListener(h);
        cb.addItemListener(h);

        setLayout(new GridLayout(3, 1));
        add(l1);
        add(pf);
        add(cb);

    }
    class Handler implements ActionListener, DocumentListener, ItemListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource()==b1) {
                SwingUtilities.invokeLater(CatFrame::new);
                dispose();
            }
        }

        @Override
        public void insertUpdate(DocumentEvent e) {
            checkPassword();
        }
        @Override
        public void removeUpdate(DocumentEvent e) {
            checkPassword();
        }
        @Override
        public void changedUpdate(DocumentEvent e) {
            checkPassword();
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
        private void checkPassword() {
            String str = new String(pf.getPassword());

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
                pf.setEditable(false);
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

