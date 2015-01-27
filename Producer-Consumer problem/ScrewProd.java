/**
 * 
 * @author Shraddha Atrawalkar
 * 
 *         Screw Producer Class
 *
 *  This class defines production of screws 
 */

public class ScrewProd implements Runnable {

    static int screw_array[];//array to store count of screws produced
    int prod_screws_max;// maximum screws that can be produced
    int prod_each_screws;// screws produced at a time

    /**
     * constructor to define maximum screws that can be produced, the array to
     * store the count and screws produced in each batch
     */
    public ScrewProd() {
        prod_screws_max = 20;
        prod_each_screws = 4;
        screw_array = new int[1];
    }

    //method to define execution of the thread
    @Override
    public void run() {
        while (true) {
            synchronized (screw_array) {
                if (screw_array[0] > prod_screws_max) {
                    /**
                     * production full; ask screw producer notify everyone and
                     * wait
                     */
                    screw_array.notifyAll();
                    System.out.println(Thread.currentThread().getName()
                            + ": Reached max production:" + prod_screws_max);

                    /**
                     * wait till consumed
                     */
                    try {
                        screw_array.wait();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                /**
                 * Keep producing till max production reached
                 */
                System.out.println(Thread.currentThread().getName()
                        + "is Producing: " + prod_each_screws + " screws");
                screw_array[0] += prod_each_screws;
                
              //to restrict the production to max components
                if (prod_each_screws > prod_screws_max) {
                    screw_array[0] = prod_screws_max;
                }
                System.out.println(Thread.currentThread().getName() + " : "
                        + "Updated screw Count : " + screw_array[0]);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                screw_array.notifyAll();
            }
        }

    }

}
