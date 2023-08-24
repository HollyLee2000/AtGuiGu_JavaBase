package chapter10_thread.Implement_runnable;
//练习：创建两个分线程，其中一个线程遍历100以内的偶数，另一个线程遍历100以内的奇数
//方法3：提供Runnable接口匿名实现类的匿名对象

public class Test_way3 {
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=1; i<=100; i++){
                    if(i%2==0){
                        System.out.println(Thread.currentThread().getName()+": "+i);
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=1; i<=100; i++){
                    if(i%2!=0){
                        System.out.println(Thread.currentThread().getName()+": "+i);
                    }
                }
            }
        }).start();
    }
}
