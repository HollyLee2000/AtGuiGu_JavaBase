package chapter10_thread.Thread_communication;

//生产者&消费者。生产者(Productor)将产品交给店员(Clerk)，而消费者(Customer)从店员处取走产品，店员一次只能持有固定数量的产品(比如:20)，
//如果生产者试图生产更多的产品，店员会叫生产者停一下，如果店中有空位放产品了再通知生产者继续生产;如果店中没有产品了，店员会告诉消费者等一下，
//如果店中有产品了再通知消费者来取走产品。

class Clerk{
    private int product;
    public Clerk(int pro){
        product = pro;
    }
    public synchronized void addProduct(){
        System.out.println(Thread.currentThread().getName()+"准备生产产品......");
        if(product==20){
            System.out.println("仓库容量为20，已经装不下了，请稍等......");
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }else{
            product++;
            System.out.println(Thread.currentThread().getName()+"生产了第"+product+"件商品");
            notifyAll();
        }

    }
    public synchronized void minusProduct(){
        System.out.println(Thread.currentThread().getName()+"请求购买产品......");
        if(product==0){
            System.out.println("已经卖光了，请稍等......");
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }else{
            System.out.println(Thread.currentThread().getName()+"消费了第"+product+"件商品");
            product--;
            notifyAll();
        }
    }
}

class Producer extends Thread{
    private Clerk clerk;
    public Producer(Clerk clk){
        clerk = clk;
    }
    public Producer(Clerk clk, String name){
        super(name);
        clerk = clk;
    }

    @Override
    public void run() {
        while(true){
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clerk.addProduct();
        }
    }
}

class Consumer extends Thread{
    private Clerk clerk;
    public Consumer(Clerk clk){
        clerk = clk;
    }
    public Consumer(Clerk clk, String name){
        super(name);
        clerk = clk;
    }

    @Override
    public void run() {
        while(true){
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clerk.minusProduct();
        }
    }
}

public class ProducerConsumerTest {
    public static void main(String[] args) {
        Clerk clk = new Clerk(20);
        Producer pro1 = new Producer(clk, "生产者1");
        Producer pro2 = new Producer(clk, "生产者2");
        Consumer cos1 = new Consumer(clk, "消费者1");
        Consumer cos2 = new Consumer(clk, "消费者2");
        pro1.start();
        cos1.start();
        cos2.start();
//        pro2.start();
    }
}
