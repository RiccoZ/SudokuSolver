import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.geom.Line2D;
import java.io.IOException;
import java.util.ArrayList;

public class frame extends JFrame{

    Font font1 = new Font("SansSerif", Font.BOLD, 50);
    Font font2 = new Font("SansSerif", Font.BOLD, 15);
    ArrayList<JTextField> fieldarr = new ArrayList<JTextField>();
    JTextPane tfield = new JTextPane();

    public frame() {
        setBounds(100,100,900,800);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        tfield.setBounds(725,450,125,50);
        tfield.setFont(font2);
        tfield.setEditable(false);

        for(int i = 0; i < 9; i++) {
            for(int z = 0; z < 9; z++) {
                JTextField field = new JTextField();
                field.setBounds(50 + 75*z,50 + 75*i,50,50);
                field.getDocument().addDocumentListener(new DocumentListener() {
                    @Override
                    public void insertUpdate(DocumentEvent e) {
                        try{
                            if(field.getText().length() == 1) {
                                int t = Integer.parseInt(field.getText());
                                Robot robot = new Robot();
                                robot.keyPress(KeyEvent.VK_TAB);
                                robot.keyRelease(KeyEvent.VK_TAB);
                            }
                        }catch(Exception f) {
                        }
                    }

                    @Override
                    public void removeUpdate(DocumentEvent e) {
                    }

                    @Override
                    public void changedUpdate(DocumentEvent e) {
                    }
                });
                field.setFont(font1);
                fieldarr.add(field);
                add(field);
            }
        }

        JButton btnsolve = new JButton("Lösen");

        btnsolve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[][] arr = new int[9][9];

                int p = 0;
                    for(int i = 0; i < 9; i++) {
                        for (int z = 0; z < 9; z++) {
                            if (!(fieldarr.get(p).getText().isEmpty())) {
                                arr[z][i] = Integer.parseInt(fieldarr.get(p).getText());
                                fieldarr.get(p).setForeground(Color.RED);

                            }else {
                                arr[z][i] = -1;
                            }
                            p++;
                        }
                    }

                Solver s = new Solver(arr);

                if(s.checkfull()) {
                    update(s.solve());
                    for(p = 0; p < 81; p++) {
                        fieldarr.get(p).setEditable(false);
                    }
                    tfield.setText("Sudoku gelöst!");
                    tfield.setForeground(Color.green);
                } else {
                    tfield.setText("Sudoku nicht lösbar!");
                    tfield.setForeground(Color.red);
                    for(p = 0; p < 81; p++) {
                        fieldarr.get(p).setForeground(Color.black);
                    }
                }
            }
        });

        btnsolve.setBounds(725,650,125,50);

        JButton btnreset = new JButton("Leeren");

        btnreset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reset();
            }
        });

        btnreset.setBounds(725,550,125,50);

        add(btnsolve);
        add(btnreset);
        add(tfield);
    }

    public void update(int[][] arr) {
        int p = 0;
        for(int i = 0; i < 9; i++) {
            for (int z = 0; z < 9; z++) {
                if(arr[z][i] == 0) {
                    fieldarr.get(p).setText("");
                }else {
                    fieldarr.get(p).setText(String.valueOf(arr[z][i]));
                }
                p++;
            }
        }
    }

    private void reset() {
        int p = 0;
        int[][] arr = new int[9][9];
        for(int i = 0; i < 9; i++) {
            for (int z = 0; z < 9; z++) {
                arr[i][z] = 0;
                fieldarr.get(p).setEditable(true);
                fieldarr.get(p).setForeground(Color.BLACK);
                p++;
            }
        }

        update(arr);
        tfield.setText("Sudoku geleert!");
        tfield.setForeground(Color.green);
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        Line2D lin1 = new Line2D.Float(270, 50, 270, 750);
        g2.draw(lin1);
        Line2D lin2 = new Line2D.Float(495, 50, 495, 750);
        g2.draw(lin2);
        Line2D lin3 = new Line2D.Float(25, 290, 725, 290);
        g2.draw(lin3);
        Line2D lin4 = new Line2D.Float(25, 520, 725, 520);
        g2.draw(lin4);
    }

}
