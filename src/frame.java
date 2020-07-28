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
    ArrayList<JTextField> fieldarr = new ArrayList<JTextField>();

    public frame() {
        setBounds(100,100,900,800);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);


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
                }
            }
        });

        btnsolve.setBounds(725,650,125,50);

        add(btnsolve);
    }

    public void update(int[][] arr) {
        int p = 0;
        for(int i = 0; i < 9; i++) {
            for (int z = 0; z < 9; z++) {
                fieldarr.get(p).setText(String.valueOf(arr[z][i]));
                fieldarr.get(p).setEditable(false);
                p++;
            }
        }
    }

    private void reset() {

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
