package com.maximKachan.hippodrome;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.FutureTask;

public class HorsesRaceTest {
    private static Race race;

    public static void main(String[] args) {
        race = new Race(1000);

        race.setHorses(CallHorse.horseFactory(20, 10, race.getLength()));
        startHorses();
        HashSet<RaceHorse> finishedHorse = new HashSet<>();
        do {
            for (RaceHorse horse : race.getHorses()) {
                if (horse.isFinished()) finishedHorse.add(horse);
            }
        } while (finishedHorse.size() != race.getHorses().size());
        printResult();
    }

    private static List<RaceHorse> registerHorses(){
        List<RaceHorse> horses = new ArrayList<>();
        for(int i = 1; i <= 20; i++){
            RunHorse horse = new RunHorse("Number " + i, 5 + Math.random() * 5, race.getLength());
            horses.add(horse);
        }
        return horses;
    }

    private static void startHorses(){
        for (RaceHorse raceHorse : race.getHorses()){
            if (raceHorse instanceof RunHorse){
            RunHorse runHorse = (RunHorse) raceHorse;
            Thread thread = new Thread(runHorse);
            thread.start();
            }
            if (raceHorse instanceof CallHorse){
                CallHorse callHorse = (CallHorse) raceHorse;
                FutureTask<CallHorse> task = new FutureTask<>(callHorse);
                new Thread(task).start();
            }
        }
    }

    private static void printResult(){
        List<? extends RaceHorse> list = race.getHorses();
        Collections.sort(list);
        for (RaceHorse horse : list){
            System.out.printf("Horse: %9s Steps: %d Speed: %.2f\n", horse.getName(), horse.getStep(), horse.getSpeed());
        }
    }
}
