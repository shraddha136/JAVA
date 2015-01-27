/**
 * 
 * @author Shraddha Atrawalkar
 * 
 *         Stand Producer Class
 *
 *         This class defines production of stand
 */

public class StandProd implements Runnable {

    static int stand_array[];// array to store count of stands produced
    int prod_stand_max;// maximum stands that can be produced
    int prod_each_stand;// stands produced at a time

    /**
     * constructor to define maximum stands that can be produced, the array to
     * store the count and stands produced in each batch
     */
    public StandProd() {
        prod_stand_max = 16;
        prod_each_stand = 4;
        stand_array = new int[1];
    }

    // method to define execution of the thread
    @Override
    public void run() {
        while (true) {
            synchronized (stand_array) {
                if (stand_array[0] > prod_stand_max) {
                    /**
                     * production full; ask stand producer notify everyone and
                     * wait
                     */
                    stand_array.notifyAll();
                    System.out.println(Thread.currentThread().getName()
                            + ": Reached max production:" + prod_stand_max);

                    /**
                     * wait till consumed
                     */
                    try {
                        stand_array.wait();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                /**
                 * Keep producing till max production reached
                 */
                System.out.println(Thread.currentThread().getName()
                        + "is Producing: " + prod_each_stand + " stand");
                stand_array[0] += prod_each_stand;

                // to restrict the production to max components
                if (prod_each_stand > prod_stand_max) {
                    stand_array[0] = prod_stand_max;
                }
                System.out.println(Thread.currentThread().getName() + " : "
                        + "Updated stand Count : " + stand_array[0]);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                stand_array.notifyAll();
            }
        }

    }

}
