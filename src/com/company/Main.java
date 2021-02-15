package com.company;

/*
Deadlock - это заблокированный ресурс при многопоточности
т.е. один поток ссылается на ресурс другого потока и наоборот,
таким образом что они мешают друг другу и блокируют данные (зависание)

                    Никогда так не делать!!!
 */
public class Main {

    public static void main(String[] args) {
        ResA resA = new ResA();
        ResB resB = new ResB();
        resA.rb = resB;
        resB.ra = resA;

        MyPotokA myPotokA = new MyPotokA();
        MyPotokB myPotokB = new MyPotokB();
        myPotokA.resA = resA;
        myPotokB.resB = resB;

        myPotokA.start();
        myPotokB.start();
    }
}


// Наши потоки
class MyPotokA extends Thread{
    ResA resA;

    public void run(){
        System.out.println(resA.getI());
    }
}

class MyPotokB extends Thread{
    ResB resB;

    public void run(){
        System.out.println(resB.getI());
    }
}

// Наши ресурсы, которые запрашивают данные друг друга
class ResA{
    ResB rb;

    public synchronized int getI(){
        return rb.retn();
    }

    public synchronized int retn(){
        return 1;
    }
}

class ResB{
    ResA ra;

    public synchronized int getI(){
        return ra.retn();
    }

    public synchronized int retn(){
        return 2;
    }
}