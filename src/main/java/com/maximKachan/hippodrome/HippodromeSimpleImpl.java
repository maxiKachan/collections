package com.maximKachan.hippodrome;

import java.util.List;

public class HippodromeSimpleImpl implements Hippodrome, Runnable{
    private final List<Horse> horses;

    public HippodromeSimpleImpl(List<Horse> horses) {
        this.horses = horses;
    }

    public List<Horse> getHorses() {
        return horses;
    }

    //this method has problem when it's used in multithreading
    public void run() {
        for (int i = 1; i <= 100; i++){
            System.out.println(i);

            move();
            print();
            try {
                Thread.sleep(200);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        printWinner();
    }

    public void move(){
        for (Horse horse : horses){
            horse.move();
        }
    }

    public void print(){
        for (Horse horse : horses){
            horse.print();
            System.out.println();
        }
        System.out.println();
    }

    public void printWinner(){
        Horse printWinner = getWinner();
        System.out.println("Winner is " + printWinner.getName() + "!");
    }

    public Horse getWinner(){
        Horse winner = horses.get(0);
        for(Horse horse : horses){
            if (horse.getDistance() > winner.getDistance()){
                winner = horse;
            }
        }
        return winner;
    }
}
