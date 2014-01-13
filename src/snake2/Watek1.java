
package snake2;

public class Watek1
    implements Runnable {
         @Override
         public void run() {
              synchronized(this){
                 new Snake();
              }
         }
        
    }


