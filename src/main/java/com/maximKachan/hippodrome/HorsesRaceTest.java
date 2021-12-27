package com.maximKachan.hippodrome;

import java.util.*;

public class HorsesRaceTest {
    private static Race race;

    public static void main(String[] args) {
        race = new Race(1000);

        race.setHorses(registerHorses());
        startHorses();
        HashSet<RunHorse> finishedHorse = new HashSet<>();
        do {
            for (RunHorse horse : race.getHorses()) {
                if (horse.getFinished()) finishedHorse.add(horse);
            }
        } while (finishedHorse.size() != race.getHorses().size());
        printResult();
    }

    private static List<RunHorse> registerHorses(){
        List<RunHorse> horses = new ArrayList<>();
        for(int i = 1; i <= 20; i++){
            RunHorse horse = new RunHorse("Number " + i, 5 + Math.random() * 5, race.getLength());
            horses.add(horse);
        }
        return horses;
    }

    private static void startHorses(){
        for (Horse horse : race.getHorses()){
            RunHorse runHorse = (RunHorse) horse;
            Thread thread = new Thread(runHorse);
            thread.start();
        }
    }

    private static void printResult(){
        List<RunHorse> list = race.getHorses();
        Collections.sort(list);
        for (RunHorse horse : list){
            System.out.printf("Horse: %s Steps: %d Speed: %.2f\n", horse.getName(), horse.getStep(), horse.getSpeed());
        }
    }
}
