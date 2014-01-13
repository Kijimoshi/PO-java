/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sssnake;

public class Watek2
    implements Runnable {
    @Override
    public void run() {
        synchronized(this){
            System.out.println("ROZPOCZETO GRE");
        }
    }
}