/**
 * 
 * @author Shraddha Atrawalkar
 * 
 *         Lightbulb Producer Class
 *
 *         This class defines production of lightbulb
 */

public class LightBulbProd implements Runnable {

    static int light_bulb_array[];// array to store count of lightbulbs produced
    int prod_light_bulb_max;// maximum bulbs that can be produced
    int prod_each_light_bulb;// lightbulbs produced at a time

    /**
     * constructor to define maximum light bulbs that can be produced, the array
     * to store the count and light bulbs produced in each batch
     */
    public LightBulbProd() {
        prod_light_bulb_max = 12;
        prod_each_light_bulb = 4;
        light_bulb_array = new int[1];
    }

    // method to define execution of the thread
    @Override
    public void run() {
        while (true) {
            synchronized (light_bulb_array) {
                if (light_bulb_array[0] > prod_light_bulb_max) {
                    /**
                     * production full; ask light_bulb producer notify everyone
                     * and wait
                     */
                    light_bulb_array.notifyAll();
                    System.out
                            .println(Thread.currentThread().getName()
                                    + ": Reached max production:"
                                    + prod_light_bulb_max);

                    /**
                     * wait till consumed
                     */
                    try {
                        light_bulb_array.wait();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                /**
                 * Keep producing till max production reached
                 */
                System.out.println(Thread.currentThread().getName()
                        + "is Producing: " + prod_each_light_bulb
                        + " light_bulb");
                light_bulb_array[0] += prod_each_light_bulb;

                // to restrict the production to max components
                if (prod_each_light_bulb > prod_light_bulb_max) {
                    light_bulb_array[0] = prod_light_bulb_max;
                }
                System.out.println(Thread.currentThread().getName() + " : "
                        + "Updated lightbulb Count : " + light_bulb_array[0]);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                light_bulb_array.notifyAll();
            }
        }

    }

}
