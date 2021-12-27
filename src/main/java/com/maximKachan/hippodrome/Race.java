package com.maximKachan.hippodrome;

import java.util.List;

public class Race {
    private final int length;
    private List<RunHorse> horses;

    public Race(int length) {
        this.length = length;
    }

    public Race(int length, List<RunHorse> horses) {
        this.length = length;
        this.horses = horses;
    }

    public int getLength() {
        return length;
    }

    public List<RunHorse> getHorses() {
        return horses;
    }

    public void setHorses(List<RunHorse> horses) {
        this.horses = horses;
    }
}
