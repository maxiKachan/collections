package com.maximKachan.hippodrome;

import java.util.ArrayList;
import java.util.List;

public class HippodromeTest {
    public static void main(String[] args) throws InterruptedException {
        List<Horse> horses1 = createHorses();
        List<Horse> horses2 = createHorses();
        Runnable runnable1 = new HippodromeCompetitionImpl(300,horses1, 20);
        Thread thread1 = new Thread(runnable1);
        thread1.start();
        thread1.join();
    }

    private static List<Horse> createHorses(){
        List<Horse> horses = new ArrayList<>();
        Horse horse1 = new Horse("Машка", 3, 0);
        Horse horse2 = new Horse("Дашка", 3, 0);
        Horse horse3 = new Horse("Зорька", 3, 0);
        horses.add(horse1);
        horses.add(horse2);
        horses.add(horse3);
        return horses;
    }
}
