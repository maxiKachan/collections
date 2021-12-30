package com.maximKachan.hippodrome;

import java.util.ArrayList;
import java.util.List;

public class RunHorse extends RaceHorse implements Runnable{

    public RunHorse(String name, double speed, int trackLength) {
        super(name, speed, trackLength);
    }

    @Override
    public void run() {
        while (!isFinished()){
            move();
            setStep(getStep() + 1);
        }
        setFinished(true);
    }

    public static List<RunHorse> horseFactory(int n, double nominalSpeed, int trackLength){
        List<RunHorse> horses = new ArrayList<>();
        for(int i = 0; i < n; i++){
            RunHorse raceHorse = new RunHorse("Horse " + i, nominalSpeed + 5 * Math.random(), trackLength);
            horses.add(raceHorse);
        }
        return horses;
    }
}
