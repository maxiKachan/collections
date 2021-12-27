package com.maximKachan.hippodrome;

import java.util.List;

public class HippodromeCompetitionImpl implements Hippodrome, Runnable {
    private final int lengthRace;
    private final List<Horse> horses;
    private boolean isFinished;
    private final int mills;

    public HippodromeCompetitionImpl(int lengthRace, List<Horse> horses) {
        this.lengthRace = lengthRace;
        this.horses = horses;
        this.mills = 200;
    }

    public HippodromeCompetitionImpl(int lengthRace, List<Horse> horses, int mills) {
        this.lengthRace = lengthRace;
        this.horses = horses;
        this.mills = mills;
    }

    @Override
    public void move() {
        for (Horse horse : horses) {
            horse.move();
        }
    }

    @Override
    public void print() {
        for (Horse horse : horses) {
            horse.print();
            System.out.println();
        }
        System.out.println();
    }

    @Override
    public void printWinner() {
        Horse horse = checkWinner();
        if (horse != null) {
            System.out.println("Winner is " + horse.getName() + "!");
            isFinished = true;
        }
    }

    @Override
    public void run() {
        try {
            while (!isFinished) {
                move();
                print();
                printWinner();
                Thread.sleep(mills);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private Horse checkWinner() {
        for (Horse horse : horses) {
            if (horse.getDistance() > lengthRace) {
                return horse;
            }
        }
        return null;
    }

    public void regHorse(Horse horse){
        horses.add(horse);
    }

    public int getLengthRace() {
        return lengthRace;
    }

    public List<Horse> getHorses() {
        return horses;
    }
}
