
public class Load {
    /**
     * Starts the Load Generation
     * @param args Command line arguments, ignored
     */
    public static void main(String[] args) {
        int numCore = 2;
        int numThreadsPerCore = 3;
        double load = 0.99;
        final long duration = 100000;
        for (int thread = 0; thread < 1; thread++) {
            new BusyThread("Thread" + thread, load, duration).start();
        }
        
        System.out.println(Thread.activeCount());
    }

    /**
     * Thread that actually generates the given load
     * @author Sriram
     */
    private static class BusyThread extends Thread {
        private double load;
        private long duration;

        /**
         * Constructor which creates the thread
         * @param name Name of this thread
         * @param load Load % that this thread should generate
         * @param duration Duration that this thread should generate the load for
         */
        public BusyThread(String name, double load, long duration) {
            super(name);
            this.load = load;
            this.duration = duration;
        }

        /**
         * Generates the load when run
         */
        @Override
        public void run() {
            long startTime = System.currentTimeMillis();
            // Loop for the given duration
			while (System.currentTimeMillis()-startTime <=100) {
			    // Every 100ms, sleep for the percentage of unladen time
//                    if (System.currentTimeMillis() % 100 == 0) {
//                        Thread.sleep((long) Math.floor((1 - load) * 100));
//                    }
			}
        }
    }
}