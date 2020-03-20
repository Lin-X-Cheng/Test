package Queue;

import com.sun.xml.internal.bind.v2.model.core.ID;

import java.text.BreakIterator;
import java.util.Scanner;

public class StopCar {
    public static void main(String[] args) {
        Parking parking=new Parking(4);//有效数据是3
        char key=' ';
        Scanner sc=new Scanner(System.in);
        while (true){
            System.out.print("p(print):打印车队\t\t");
            System.out.print("a(add):添加车辆\t\t");
            System.out.println("r(remove):取出车辆");
            System.out.print("h(head):查看第一辆车\t\t");
            System.out.print("s(size):查看车辆个数\t\t");
            System.out.print("e(eixt):退出程序");
            key=sc.next().charAt(0);
            switch (key){
                case('p') :
                    try {
                        parking.showParking();
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case ('a'):
                    System.out.println("请输入你的车辆编号 车型品牌");
                    String no=sc.next();
                    String brand=sc.next();
                    parking.add(new Car(no,brand));
                    break;
                case ('s'):
                    System.out.println("车辆数量为："+parking.size());
                    break;
                case ('h'):
                    try {
                        System.out.println("队列的第一辆车是："+parking.head());
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case ('r'):
                    try {
                        Car res=parking.remove();
                        System.out.println("开出的车辆是："+res.toString());
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case ('e'):
                    System.out.println("程序退出...");
                    System.exit(0);
                default:
                    break;
            }
    }
}


 static class Parking{
    /*private int maxSize;
    private int front;
    private int rear;
    private Car [] arr;*/
    public  int maxSize;//队列的最大长度
    public int front;//队列的头
    public int rear;//队列尾
    public Car [] arr;//存放数据 模拟队列（车库）

     //创建车库构造器
    public Parking(int MaxSize){
        this.maxSize=MaxSize;
        arr=new Car[maxSize];
        int front=0;//队列的头 指向队列当前位置
        int rear=0;//队列尾 指向队列当前位置后一个位置
    }
    //判断队列是否满
    public boolean isFull(){
        return (rear+1)%maxSize==front;
    }
    //判断队列是否空
    public boolean isEmpty(){
        return rear==front;
    }
    //增加车辆到队列
    public void add(Car car){
        if(isFull()){
            System.out.println("车库已满 没有停车位了");
            return;
        }
        arr[rear]=car;
        rear=(rear+1)%maxSize;
        System.out.println(car.toString()+" 车入库");
    }
    //减少队列车辆
    public Car remove(){
        if(isEmpty()){
            System.out.println("车库空 无车");
            return null;
        }
        Car c=arr[front];
        front=(front+1)%maxSize;
        return c;
    }

    /*public void showParking(){
        if(isEmpty()){
            System.out.println("车库空 没有车");
            return;
        }
        for(int i=0;i<arr.length;i++){
            if(arr[i]!=null){
                System.out.println(arr[i].toString());
            }
        }
    }*/
    //队列车辆数量
    public int size(){
        return (rear+maxSize-front)%maxSize;
    }
    //打印队列车辆
    public void showParking(){
        if(isEmpty()){
            System.out.println("车库空 没有车");
            return;
        }
        for(int i=front;i<front+size();i++){
                System.out.println(arr[i%maxSize].toString());
        }
    }
    public Car head(){
        if(isEmpty()){
            System.out.println("车库空 没有车");
            return null;
        }
        return arr[front];
    }
}

}
class Car {
    public String id;//车牌号
    public String brand;//车子类型

    public Car(){
    }

    public Car(String id, String brand) {
        this.id = id;
        this.brand = brand;
    }

    public String toString() {
       return "车牌号:"+this.id +" 车型:" + this.brand;
    }

}


