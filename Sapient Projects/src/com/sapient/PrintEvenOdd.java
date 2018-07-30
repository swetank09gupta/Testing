package com.sapient;

public class PrintEvenOdd {
    private int count = 1;
    private int threadIdToRun = 1;
    private Object obj = new Object();

    public static void main(String[] args) {
        PrintEvenOdd printEvenOdd = new PrintEvenOdd();
        Thread t1 = new Thread(printEvenOdd.new Printer(1));
        Thread t2 = new Thread(printEvenOdd.new Printer(2));

        t1.start();
        t2.start();
    }

    class Printer implements Runnable {
        private int threadId;

        Printer(int threadId) {
            this.threadId = threadId;
        }

        @Override
        public void run() {
            try {
                synchronized (obj) {
                    while (count <= 20) {
                        if (threadId != threadIdToRun) {
                            obj.wait();
                        } else {
                            System.out.println("Thread " + threadId + " Printed " + count);
                            count++;

                            if (threadId == 1) {
                                threadIdToRun = 2;
                            } else if (threadId == 2) {
                                threadIdToRun = 1;
                            }
                            obj.notifyAll();
                        }
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
