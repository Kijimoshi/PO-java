package sssnake;

import java.awt.Button;
import javax.swing.JFrame;

public class Snake extends JFrame  {
    public Snake() {

    Button Klik1 = new Button("Save"); 
    Klik1.setSize(165, 50);
    Klik1.setLocation(5,520);
    add(Klik1);
    //Klik1.addActionListener((ActionListener) this);
    
    Button Klik2 = new Button("Load"); 
    Klik2.setSize(170, 50);
    Klik2.setLocation(170,520);
    Klik2.setVisible(true);
    add(Klik2);
    //Klik2.addActionListener((ActionListener) this);
    
    Button Klik3 = new Button("Reset"); 
    Klik3.setSize(170, 50);
    Klik3.setLocation(340,520);
    Klik3.setVisible(true);
    add(Klik3);
    //Klik3.addActionListener((ActionListener) this);
    
    setFocusable(false);

    add(new Board());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(520, 600);
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

