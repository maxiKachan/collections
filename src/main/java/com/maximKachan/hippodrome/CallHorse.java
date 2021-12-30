package com.maximKachan.hippodrome;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class CallHorse extends RaceHorse implements Callable<CallHorse>{
    public CallHorse(String name, double speed, int trackLength) {
        super(name, speed, trackLength);
    }

    @Override
    public CallHorse call() throws Exception {
        while (!isFinished()){
            move();
            setStep(getStep() + 1);
        }
        setFinished(true);
        return this;
    }

    public static List<CallHorse> horseFactory(int n, double nominalSpeed, int trackLength){
        List<CallHorse> horses = new ArrayList<>();
        for(int i = 0; i < n; i++){
            CallHorse raceHorse = new CallHorse("Horse " + i, nominalSpeed + 5 * Math.random(), trackLength);
            horses.add(raceHorse);
        }
        return horses;
    }
}
