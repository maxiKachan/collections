package com.maximKachan.hippodrome;

import java.util.List;

public class HippodromeSynchImpl implements Hippodrome, Runnable {
    private final List<Horse> horses;
    private int mills;

    public HippodromeSynchImpl(List<Horse> horses) {
        this.horses = horses;
    }

    public HippodromeSynchImpl(List<Horse> horses, int mills) {
        this.horses = horses;
        this.mills = mills;
    }

    public List<Horse> getHorses() {
        return horses;
    }

    public void run() {
        for (int i = 1; i <= 100; i++) {
            move();
            print();
            try {
                Thread.sleep(mills);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        printWinner();
    }

    public void move() {
        for (Horse horse : horses) {
            horse.move();
        }
    }

    public void print() {
        synchronized (HippodromeSynchImpl.class) {
            for (Horse horse : horses) {
                horse.print();
                System.out.println();
            }
            System.out.println();
        }
    }

    public void printWinner() {
        synchronized (HippodromeSynchImpl.class) {
            Horse printWinner = getWinner();
            System.out.println("Winner is " + printWinner.getName() + "!" + printWinner.getDistance());
        }
    }

    public Horse getWinner() {
        Horse winner = horses.get(0);
        for (Horse horse : horses) {
            if (horse.getDistance() > winner.getDistance()) {
                winner = horse;
            }
        }
        return winner;
    }
}
