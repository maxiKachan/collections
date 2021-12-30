package com.maximKachan.hippodrome;

import java.util.List;

public class Race {
    private final int length;
    private List<? extends RaceHorse> horses;

    public Race(int length) {
        this.length = length;
    }

    public Race(int length, List<RaceHorse> horses) {
        this.length = length;
        this.horses = horses;
    }

    public int getLength() {
        return length;
    }

    public List<? extends RaceHorse> getHorses() {
        return horses;
    }

    public void setHorses(List<? extends RaceHorse> horses) {
        this.horses = horses;
    }
}
