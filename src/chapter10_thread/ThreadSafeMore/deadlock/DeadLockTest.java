package chapter10_thread.ThreadSafeMore.deadlock;

public class DeadLockTest {
    public static void main(String[] args) {
        StringBuilder s1 = new StringBuilder();
        StringBuilder s2 = new StringBuilder();
        new Thread(){
            @Override
            public void run() {
                synchronized (s1){
                    s1.append("ab");

                    try {
                        Thread.sleep(50);  //增加死锁的可能性
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (s2){
                        s2.append("12");
                        System.out.println(s1);
                        System.out.println(s2);
                    }
                }
            }
        }.start();

        new Thread(){
            @Override
            public void run() {
                synchronized (s2){
                    s2.append("34");
                    try {
                        Thread.sleep(50);  //增加死锁的可能性
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (s1){
                        s1.append("cd");
                        System.out.println(s1);
                        System.out.println(s2);
                    }
                }
            }
        }.start();


    }
}
