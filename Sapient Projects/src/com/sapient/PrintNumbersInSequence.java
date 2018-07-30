package com.sapient;

public class PrintNumbersInSequence {
    public volatile int count = 1;
    private Object obj = new Object();
    private volatile int threadIdToRun = 1;

    public static void main(String[] args) {
        PrintNumbersInSequence printNumbersInSequence = new PrintNumbersInSequence();
        Thread t1 = new Thread(printNumbersInSequence.new Number(1));
        Thread t2 = new Thread(printNumbersInSequence.new Number(2));
        Thread t3 = new Thread(printNumbersInSequence.new Number(3));

        t1.start();
        t2.start();
        t3.start();
    }

    class Number implements Runnable {
        private int threadId = 0;

        public Number(int threadId) {
            this.threadId = threadId;
            //this.threadIdToRun = threadIdToRun;
        }

        @Override
        public void run() {
            try {
                while (count <= 20) {
                    synchronized (obj) {
                        if (threadId != threadIdToRun) {
                            obj.wait();
                        } else {
                            System.out.println("Thread " + threadId + " printed " + count);
                            count++;

                            if (threadId == 1)
                                threadIdToRun = 2;
                            else if (threadId == 2)
                                threadIdToRun = 3;
                            else if (threadId == 3)
                                threadIdToRun = 1;

                            obj.notifyAll();
                        }

                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}


