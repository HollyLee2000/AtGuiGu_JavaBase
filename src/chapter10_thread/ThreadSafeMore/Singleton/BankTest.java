package chapter10_thread.ThreadSafeMore.Singleton;

//回顾单例模式的特点：
//单例类只能有一个实例
//单例类必须自己创建自己的唯一实例
//单例类必须给其他对象提供这一实例

// 懒汉模式（线程不安全）
//懒汉模式，顾名思义就是懒，没有对象需要调用它的时候不去实例化，有人来向它要对象的时候再实例化对象，因为懒
// 懒汉式单例类.在第一次调用的时候实例化自己
//public class Singleton{
//    private Singleton() {}
//    private static Singleton single=null;
//    //静态工厂方法
//    public static Singleton getInstance() {
//         if (single == null) {
//             single = new Singleton();
//         }
//        return single;
//    }
//}
//
// 懒汉模式（线程安全）
//加了个关键字
//
//synchronized
//
//public class Singleton {
//    private static Singleton single=null;
//    private Singleton (){}
//    public static synchronized Singleton getInstance() {
//    if (instance == null) {
//        instance = new Singleton();
//    }
//    return single;
//    }
//}
//但是这样做的效率很低

//饿汉模式
//饿汉模式，就是它很饿，它的对象早早的就创建好了（懒汉是有人管它要了再创建）
//
//
////饿汉式单例类.在类初始化时，已经自行实例化
//public class Singleton {
//    private Singleton() {}
//    private static final Singleton single = new Singleton();
//    //静态工厂方法
//    public static Singleton getInstance() {
//        return single;
//    }
//}

//线程不安全的懒汉式：
public class BankTest {
    static Bank b1 = null;  //static函数里只能操作static属性
    static Bank b2 = null;
    public static void main(String[] args) {
        Thread t1 = new Thread(){
            @Override
            public void run() {
                b1 = Bank.getInstance();
            }
        };

        Thread t2 = new Thread(){
            @Override
            public void run() {
                b2 = Bank.getInstance();
            }
        };

        t1.start();
        t2.start();
        try {
            t1.join();  //保证主线程先运行它们再往下走
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(b1);
        System.out.println(b2);
        System.out.println(b1==b2);  //多运行几次，就会显示false，出现安全问题
    }
}

//懒汉式存在线程安全问题，可能两个线程同时进来new Bank()
class Bank{
    private Bank(){}
    private static Bank instance = null;
//有问题：
    //    public static Bank getInstance(){
//        if(instance == null){
//            instance = new Bank();
//        }
//        return instance;
//    }


//    public static synchronized Bank getInstance(){  //线程安全方法1：声明为同步方法即可解决问题
//        if(instance == null){
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            instance = new Bank();
//        }
//        return instance;
//    }

//    public static Bank getInstance(){  //线程安全方法2：加同步代码块
//        synchronized (Bank.class){
//            if(instance == null){
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                instance = new Bank();
//            }
//            return instance;
//        }
//    }

    public static Bank getInstance(){  //线程安全方法3：为了保证有Bank后就不再访问同步锁，提升效率，多加了一层判断
        //但是这样又有可能造成指令重排问题，即instance = new Bank()还未完全执行完时，instance已经不为null了但还没完全创建好，此时另
        //一个线程会拿着这个未完全创建好的instance返回。解决办法：将instance声明为volatile：private static volatile Bank instance = null;
        if(instance == null){
            synchronized (Bank.class){
                if(instance == null){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    instance = new Bank();
                }
            }
        }
        return instance;
    }

}
