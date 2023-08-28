package chapter10_thread.practice_deposit;

//题目：两个储户分别向同一个账户存3000元，每次1000存3次，每次存完打印账户余额
//这里使用继承Thread类

class Account{
    private double money;
    Account(double money){
        this.money = money;
    }
    //这个案例中因为只有一个账户，所以可以使用this
    //方法1，使用同步方法
    public synchronized void deposite(double mny){
        this.money += mny;
        System.out.println(Thread.currentThread().getName()+"存了钱，余额为"+money+"元");
    }
    //方法1，使用同步代码块
//    public void deposite(double mny){
//        synchronized (this){
//            this.money += mny;
//            System.out.println(Thread.currentThread().getName()+"存了钱，余额为"+money+"元");
//        }
//    }
}

class Coustomer extends Thread{
    private Account acc;
    public Coustomer(Account acc){
        this.acc = acc;
    }
    public Coustomer(Account acc, String name){
        super(name);
        this.acc = acc;
    }


    @Override
    public void run() {
        for(int i=0;i<3;i++){
            //睡一下增加其他进程进来的机会
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            acc.deposite(1000);
        }
    }
}

public class DepositExtend {
    public static void main(String[] args) {
        Account acc = new Account(0);
        Coustomer cos1 = new Coustomer(acc, "甲");
        Coustomer cos2 = new Coustomer(acc, "乙");
        cos1.start();
        cos2.start();
    }
}
