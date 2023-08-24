package chapter10_thread.Extend_Thread;
//练习：创建两个分线程，其中一个线程遍历100以内的偶数，另一个线程遍历100以内的奇数
//方法2：创建Thread匿名子类的匿名对象


public class Test_way2 {
    public static void main(String[] args) {
        new Thread(){
            public void run() {
                for(int i=0; i<=100; i++){
                    if(i%2==0){
                        System.out.println(Thread.currentThread().getName()+": "+i);
                    }
                }
            }
        }.start();

        new Thread(){
            public void run() {
                for(int i=0; i<=100; i++){
                    if(i%2!=0){
                        System.out.println(Thread.currentThread().getName()+": "+i);
                    }
                }
            }
        }.start();
    }
}
