package factory.model;

import java.util.concurrent.LinkedBlockingQueue;

public class MyThreadPool {
    private final int poolSize;
    private final LinkedBlockingQueue<Runnable> taskQueue;

    public MyThreadPool(int poolSize, String name) {
        this.poolSize = poolSize;
        taskQueue = new LinkedBlockingQueue<>();

        for (int i = 0; i < poolSize; i++) {
            MyTread thread = new MyTread();
            thread.setName(name + "-" + i);
            thread.setDaemon(true);
            thread.start();
        }
    }

    public int getTasksLen() {
        return taskQueue.size();
    }

    public int getPoolSize() {
        return poolSize;
    }

    public void execute(Runnable task) {
        synchronized (taskQueue) {
            taskQueue.add(task);
            taskQueue.notify();
        }
    }

    public synchronized boolean doHaveTasks() {
        return !taskQueue.isEmpty();
    }

    private class MyTread extends Thread {
        public void run() {
            Runnable task;

            while (!isInterrupted()) {
                synchronized (taskQueue) {
                    while (taskQueue.isEmpty()) {
                        try {
                            taskQueue.wait();
                        } catch (InterruptedException e) {
                            System.out.println("An error occurred while queue is waiting: " + e.getMessage());
                        }
                    }
                    task = taskQueue.poll();
                }
                try {
                    task.run();
                } catch (RuntimeException e) {
                    System.out.println("Thread pool is interrupted due to an issue: " + e.getMessage());
                }
            }
        }
    }
}
