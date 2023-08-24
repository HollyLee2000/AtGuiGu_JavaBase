package chapter10_thread.Extend_Thread;
//练习：创建两个分线程，其中一个线程遍历100以内的偶数，另一个线程遍历100以内的奇数
//方法1：分别继承两次Thread类
class EvenNumberPrint extends Thread{
    @Override
    public void run() {
        for(int i=0; i<=100; i++){
            if(i%2==0){
                System.out.println(Thread.currentThread().getName()+": "+i);
            }
        }
    }
}

class OddNumberPrint extends Thread{
    @Override
    public void run() {
        for(int i=0; i<=100; i++){
            if(i%2!=0){
                System.out.println(Thread.currentThread().getName()+": "+i);
            }
        }
    }
}


public class Test_way1 {
    public static void main(String[] args) {
        EvenNumberPrint t1 = new EvenNumberPrint();
        OddNumberPrint t2 = new OddNumberPrint();
        t1.start();
        t2.start();
    }
}
