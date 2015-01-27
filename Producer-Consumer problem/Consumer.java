/**
 * 
 * @author Shraddha Atrawalkar
 * 
 *         Consumer Class
 *
 *         This class defines how consumers will build a lamp
 */

class Consumer implements Runnable {

    // define the required components to build 1 lamp
    static final int screw_cons = 4;
    static final int base_cons = 1;
    static final int stand_cons = 1;
    static final int socket_cons = 3;
    static final int lightbulb_cons = 3;

    // Run method for consumer threads
    public void run() {

        while (true) {
            // acquire lock to get screws
            synchronized (ScrewProd.screw_array) {
                // if screws available
                if (ScrewProd.screw_array[0] >= screw_cons) {
                    // acquire lock to get base
                    synchronized (BaseProd.base_array) {
                        // if base available
                        if (BaseProd.base_array[0] >= base_cons) {
                            // acquire lock to get stand
                            synchronized (StandProd.stand_array) {
                                // if stand available
                                if (StandProd.stand_array[0] >= stand_cons) {
                                    // acquire lock to get socket
                                    synchronized (SocketProd.socket_array) {
                                        // if socket available
                                        if (SocketProd.socket_array[0] >= socket_cons) {
                                            // acquire lock to get lightbulb
                                            synchronized (LightBulbProd.light_bulb_array) {
                                                // if lightbulb available
                                                if (LightBulbProd.light_bulb_array[0] >= lightbulb_cons) {

                                                    // Build the lamp and
                                                    // consume from the
                                                    // production
                                                    ScrewProd.screw_array[0] -= screw_cons;
                                                    BaseProd.base_array[0] -= base_cons;
                                                    StandProd.stand_array[0] -= stand_cons;
                                                    SocketProd.socket_array[0] -= socket_cons;
                                                    LightBulbProd.light_bulb_array[0] -= lightbulb_cons;

                                                    // Display which consumer
                                                    // consumed
                                                    System.out.println(Thread
                                                            .currentThread()
                                                            .getName()
                                                            + " built lamp!");
                                                    // let the consumers take
                                                    // chances
                                                    try {
                                                        Thread.sleep(1000);
                                                    } catch (Exception e) {
                                                        e.printStackTrace();
                                                    }

                                                } else {
                                                    // Need more light bulbs
                                                    LightBulbProd.light_bulb_array
                                                            .notifyAll();
                                                    System.out
                                                            .println(Thread
                                                                    .currentThread()
                                                                    .getName()
                                                                    + " Needs more lightbulbs");
                                                    try {
                                                        LightBulbProd.light_bulb_array
                                                                .wait();
                                                    } catch (InterruptedException e) {
                                                        e.printStackTrace();
                                                    }
                                                }
                                            }
                                        } else {
                                            // Need more sockets
                                            SocketProd.socket_array.notifyAll();
                                            System.out.println(Thread
                                                    .currentThread().getName()
                                                    + " Needs more sockets");
                                            try {
                                                SocketProd.socket_array.wait();
                                            } catch (InterruptedException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    }
                                } else {
                                    // Need more stands
                                    StandProd.stand_array.notifyAll();
                                    System.out.println(Thread.currentThread()
                                            .getName() + " Needs more stands");
                                    try {
                                        StandProd.stand_array.wait();
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }

                        } else {
                            // Need more bases
                            BaseProd.base_array.notifyAll();
                            System.out.println(Thread.currentThread().getName()
                                    + " Needs more bases");
                            try {
                                BaseProd.base_array.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                } else {
                    // Need more screws
                    ScrewProd.screw_array.notifyAll();
                    System.out.println(Thread.currentThread().getName()
                            + " Needs more screws");
                    try {
                        ScrewProd.screw_array.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
