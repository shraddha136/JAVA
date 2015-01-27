/**
 * 
 * @author Shraddha Atrawalkar
 * 
 *         Socket Producer Class
 *
 *         This class defines production of socket
 */

public class SocketProd implements Runnable {

    static int socket_array[];// array to store count of sockets produced
    int prod_sockets_max;// maximum sockets that can be produced
    int prod_each_sockets;// sockets produced at a time

    /**
     * constructor to define maximum sockets that can be produced, the array to
     * store the count and sockets produced in each batch
     */
    public SocketProd() {
        prod_sockets_max = 21;
        prod_each_sockets = 7;
        socket_array = new int[1];
    }

    // method to define execution of the thread
    @Override
    public void run() {
        while (true) {
            synchronized (socket_array) {
                if (socket_array[0] > prod_sockets_max) {
                    /**
                     * production full; ask socket producer notify everyone and
                     * wait
                     */
                    socket_array.notifyAll();
                    System.out.println(Thread.currentThread().getName()
                            + ": Reached max production:" + prod_sockets_max);

                    /**
                     * wait till consumed
                     */
                    try {
                        socket_array.wait();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                /**
                 * Keep producing till max production reached
                 */
                System.out.println(Thread.currentThread().getName()
                        + "is Producing: " + prod_each_sockets + " sockets");
                socket_array[0] += prod_each_sockets;

                // to restrict the production to max components
                if (prod_each_sockets > prod_sockets_max) {
                    socket_array[0] = prod_sockets_max;
                }
                System.out.println(Thread.currentThread().getName() + " : "
                        + "Updated sockets Count : " + socket_array[0]);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                socket_array.notifyAll();
            }
        }

    }

}
