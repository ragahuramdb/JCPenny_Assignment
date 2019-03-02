package com.memory.game.Model;

import java.util.LinkedHashSet;

public class GameModel {
    private LinkedHashSet<Integer> hsRandomNumbers = new LinkedHashSet<Integer>();
    private int numberToFined = 0;
    private int reveledCnt = 0, correctAnsCount = 0;

    public GameModel() {}

    public int getCorrectAnsCount() {
        return correctAnsCount;
    }

    public void setCorrectAnsCount() {
        this.correctAnsCount += 1;
    }

    public int getReveledCnt() {
        return reveledCnt;
    }

    public void setReveledCnt() {
        this.reveledCnt += 1;
    }

    public LinkedHashSet<Integer> getHs() {
        return hsRandomNumbers;
    }

    public void setHs(int rnGenerated) {
        this.hsRandomNumbers.add(rnGenerated);
    }

    public void clearHs() {
        this.hsRandomNumbers.clear();
    }

    public int getNumberToFined() {
        return numberToFined;
    }

    public void setNumberToFined(int numberToFined) {
        this.numberToFined = numberToFined;
    }

    public void resetAll() {
        this.hsRandomNumbers.clear();
        numberToFined = 0;
        reveledCnt = 0;
        correctAnsCount = 0;
    }
}
