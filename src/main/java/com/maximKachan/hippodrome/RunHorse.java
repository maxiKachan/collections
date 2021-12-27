package com.maximKachan.hippodrome;

public class RunHorse extends Horse implements Runnable, Comparable<RunHorse>{
    private final int trackLength;
    private int step;
    private boolean finished = false;

    public RunHorse(String name, double speed, int trackLength) {
        super(name, speed, 0);
        this.trackLength = trackLength;
    }

    @Override
    public void run() {
        while (!isFinished()){
            move();
            step++;
        }
        finished = true;
    }

    @Override
    public int compareTo(RunHorse o) {
        return step - o.step;
    }

    public void move(){
        setDistance(super.getDistance() + getSpeed() * Math.random());
    }

    private boolean isFinished(){
        return super.getDistance() >= trackLength;
    }

    public int getStep() {
        return step;
    }

    public int getTrackLength() {
        return trackLength;
    }

    public boolean getFinished(){
        return finished;
    }
}
