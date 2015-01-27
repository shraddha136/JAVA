/**
 * 
 * @author Shraddha Atrawalkar
 * 
 *         Base Producer Class
 *
 *         This class defines production of base
 */

public class BaseProd implements Runnable {

    static int base_array[];// array to store count of bases produced
    int prod_bases_max;// maximum bases that can be produced
    int prod_each_bases;// bases produced at a time

    /**
     * constructor to define maximum bases that can be produced, the array to
     * store the count and bases produced in each batch
     */
    public BaseProd() {
        prod_bases_max = 8;
        prod_each_bases = 2;
        base_array = new int[1];
    }

    // method to define execution of the thread
    @Override
    public void run() {
        while (true) {
            synchronized (base_array) {
                if (base_array[0] > prod_bases_max) {
                    /**
                     * production full; ask base producer notify everyone and
                     * wait
                     */
                    base_array.notifyAll();
                    System.out.println(Thread.currentThread().getName()
                            + ": Reached max production:" + prod_bases_max);

                    /**
                     * wait till consumed
                     */
                    try {
                        base_array.wait();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                /**
                 * Keep producing till max production reached
                 */
                System.out.println(Thread.currentThread().getName()
                        + "is Producing: " + prod_each_bases + " bases");
                base_array[0] += prod_each_bases;

                // to restrict the production to max components
                if (prod_each_bases > prod_bases_max) {
                    base_array[0] = prod_bases_max;
                }
                System.out.println(Thread.currentThread().getName() + " : "
                        + "Updated bases Count : " + base_array[0]);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                base_array.notifyAll();
            }
        }

    }

}
