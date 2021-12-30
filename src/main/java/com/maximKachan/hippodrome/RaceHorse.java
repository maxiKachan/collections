package com.maximKachan.hippodrome;

import java.util.ArrayList;
import java.util.List;

public class RaceHorse extends Horse implements Comparable<RaceHorse>{
    private final int trackLength;
    private int step;
    private boolean finished = false;


    public RaceHorse(String name, double speed, int trackLength) {
        super(name, speed, 0);
        this.trackLength = trackLength;
    }

    @Override
    public int compareTo(RaceHorse o) {
        return step - o.getStep();
    }

    public void move(){
        setDistance(super.getDistance() + getSpeed() * Math.random());
    }

    public int getTrackLength() {
        return trackLength;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public boolean isFinished() {
        return super.getDistance() >= trackLength;
    }

    public boolean getFinished(){
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }
}
