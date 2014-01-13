package sssnake;

import javax.swing.JFrame;

public class Snake extends JFrame {

    public Snake() {

        add(new Board());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(520, 690);
        setLocationRelativeTo(null);
        setTitle("Snake 2014");

        setResizable(false);
        setVisible(true);
    }

    public static void main(String[] args) {
        Watek1 w1 = new Watek1();
        Watek2 w2 = new Watek2();
 
        (new Thread(w1)).start();
        (new Thread(w2)).start();
        
    }
    
}

