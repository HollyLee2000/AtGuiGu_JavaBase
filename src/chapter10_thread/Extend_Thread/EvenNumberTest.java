package chapter10_thread.Extend_Thread;

//使用继承Thread类的方法创建一个分线程1，用于遍历100以内的偶数
public class EvenNumberTest {
    public static void main(String[] args) {
//        创建当前Thread子类的对象
        PrintNumber t1 = new PrintNumber();
//        通过对象调用start方法
        t1.start();
//        这里不能用t1.run()替换t1.start，t1.run()只是调用一个普通的方法，仍然属于main线程
//        不能让已经start的线程再次执行start，否则会报IllegalThreadStateException的异常
//        t1.start();
//main方法线程执行的操作
        for(int i=1; i<=10000; i++){
            if(i%2==0){
                System.out.println(Thread.currentThread().getName()+": "+i);
            }
        }
    }
}

//创建一个继承Thread类的子类
class PrintNumber extends Thread{
    @Override
    public void run() {
        for(int i=1; i<=10000; i++){
            if(i%2==0){
                System.out.println(Thread.currentThread().getName()+": "+i);
            }
        }
    }
}

