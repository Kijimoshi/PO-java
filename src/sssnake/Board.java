package sssnake;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements ActionListener {

    private final int WIDTH = 500;
    private final int HEIGHT = 500;
    private final int KROPKA_WIELKOSC = 10;
    private final int KROPKI = 2500;
    private final int LOS_POZ = 30;
    private final int DELAY = 100;

    private int x[] = new int[KROPKI];
    private int y[] = new int[KROPKI];

    private int kropeczki;
    private int cel_x;
    private int cel_y;

    private boolean left = false;
    private boolean right = true;
    private boolean up = false;
    private boolean down = false;
    private boolean wPolu = true;

    private Timer timer;
    private Image zielona;
    private Image cel;
    private Image czerwona;


    public Board() {
        addKeyListener(new TAdapter());

        setBackground(Color.LIGHT_GRAY);

        ImageIcon green = new ImageIcon(this.getClass().getResource("green.png"));
        zielona = green.getImage();

        ImageIcon blue = new ImageIcon(this.getClass().getResource("blue.png"));
        cel = blue.getImage();

        ImageIcon red = new ImageIcon(this.getClass().getResource("red.png"));
        czerwona = red.getImage();

        setFocusable(true);
        inicjalizuj();
    }


    public void inicjalizuj() {
        kropeczki = 3;

        for (int i = 0; i < kropeczki; i++) {
            x[i] = 50 - i*10;
            y[i] = 50;
        }

        naniesCel();

        timer = new Timer(DELAY, this);
        timer.start();
    }

    @Override
    public void paint(Graphics graph) {
        super.paint(graph);

        if (wPolu) {

            graph.drawImage(cel, cel_x, cel_y, this);

            for (int z = 0; z < kropeczki; z++) {
                if (z == 0)  
                    graph.drawImage(czerwona, x[z], y[z], this);
                else 
                    graph.drawImage(zielona, x[z], y[z], this);
            }

            Toolkit.getDefaultToolkit().sync();
            graph.dispose();

        } else {
            koniecGry(graph);
        }
    }

    public void koniecGry(Graphics g) {
        String info = "Koniec gry";
        Font small = new Font("Arial", Font.BOLD, 20);
        FontMetrics metr = this.getFontMetrics(small);

        g.setColor(Color.black);
        g.setFont(small);
        g.drawString(info, (WIDTH - metr.stringWidth(info)) / 2, HEIGHT / 2);
    }

    public void sprawdzCel() {
        if ((x[0] == cel_x) && (y[0] == cel_y)) {
            kropeczki++;
            naniesCel();
        }
    }

    public void ruszaj() {
        for (int z = kropeczki; z > 0; z--) {
            x[z] = x[(z - 1)];
            y[z] = y[(z - 1)];
        }
        if (left) {
            x[0] -= KROPKA_WIELKOSC;
        }
        if (right) {
            x[0] += KROPKA_WIELKOSC;
        }
        if (up) {
            y[0] -= KROPKA_WIELKOSC;
        }
        if (down) {
            y[0] += KROPKA_WIELKOSC;
        }
    }


    public void sprawdzZderzenie() {
        for (int z = kropeczki; z > 0; z--) {
            if ((z > 4) && (x[0] == x[z]) && (y[0] == y[z])) {
                wPolu = false;
            }
        }

        if (y[0] > HEIGHT) {
            wPolu = false;
        }
        if (y[0] < 0) {
            wPolu = false;
        }
        if (x[0] > WIDTH) {
            wPolu = false;
        }
        if (x[0] < 0) {
            wPolu = false;
        }
    }

    public void naniesCel() {
        int r = (int) (Math.random() * LOS_POZ);
        cel_x = ((r * KROPKA_WIELKOSC));
        r = (int) (Math.random() * LOS_POZ);
        cel_y = ((r * KROPKA_WIELKOSC));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (wPolu) {
            sprawdzCel();
            sprawdzZderzenie();
            ruszaj();
        }
        repaint();
    }


    private class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            int key = e.getKeyCode();

            if ((key == KeyEvent.VK_LEFT) && (!right)) {
                left = true;
                up = false;
                down = false;
            }

            if ((key == KeyEvent.VK_RIGHT) && (!left)) {
                right = true;
                up = false;
                down = false;
            }

            if ((key == KeyEvent.VK_UP) && (!down)) {
                up = true;
                right = false;
                left = false;
            }

            if ((key == KeyEvent.VK_DOWN) && (!up)) {
                down = true;
                right = false;
                left = false;
            }
        }
    }
}