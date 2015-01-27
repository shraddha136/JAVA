/**
 * 
 * @author Shraddha Atrawalkar
 * 
 *         Lamp Manufacturing Class
 *
 *         This class defines threads for 2 consumers building lamps by
 *         collecting different components
 */

public class Lamp_Manufacturing {

    public static void main(String[] args) {
        /**
         * create instances of producers and consumers
         */
        Thread tscrew = new Thread(new ScrewProd(), "Screw Producer");
        Thread tbase = new Thread(new BaseProd(), "Base Producer");
        Thread tstand = new Thread(new StandProd(), "Stand Producer");
        Thread tsocket = new Thread(new SocketProd(), "Socket Producer");
        Thread tlight = new Thread(new LightBulbProd(), "LightBulb Producer");
        Thread tcons1 = new Thread(new Consumer(), "Consumer1");
        Thread tcons2 = new Thread(new Consumer(), "Consumer2");

        // start threads for components and 2 consumers to start collecting
        // components and building lamps
        tscrew.start();
        tbase.start();
        tstand.start();
        tsocket.start();
        tlight.start();
        tcons1.start();
        tcons2.start();
    }
}
