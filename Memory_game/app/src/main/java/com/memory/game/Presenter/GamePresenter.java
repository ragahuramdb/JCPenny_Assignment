package com.memory.game.Presenter;

import com.memory.game.Model.GameModel;

import java.util.LinkedHashSet;
import java.util.Random;

public class GamePresenter {

    private GameModel gameModel;
    private View view;
    private Random rn;
    int max = 9,min=1;

    public GamePresenter(View view) {
        this.gameModel = new GameModel();
        this.view = view;
        rn = new Random();
    }

    //GENERATE NEW VALID RANDOM
    public void generateRandomNumber(){
        boolean isRnGenerated = false;
        int rnGenerated = 0;
        while(!isRnGenerated) {
            rnGenerated = rn.nextInt(max - min + 1) + min;
            if(!gameModel.getHs().contains(rnGenerated)){
                isRnGenerated = true;
                gameModel.setNumberToFined(rnGenerated);
                view.updateRandomNumberTextView(""+rnGenerated);
            }
        }
    }

    //SHOW THE GENERATED RANDOM NUMBER IN TEXT VIEW
    public void updateNumberPadTextView(){
        boolean isSetMax = false;
        int rnGenerated = 0;
        while(!isSetMax) {
            rnGenerated = rn.nextInt(max - min + 1) + min;
            if(!gameModel.getHs().contains(rnGenerated)){
                gameModel.setHs(rnGenerated);
                if(gameModel.getHs().size()==max){
                    isSetMax = true;
                    view.setRandomNumbers(gameModel.getHs());
                }
            }
        }
    }

    //RESET RANDOM NUMBER HASH SET FOR FRESH START
    public void resetVar(){
        gameModel.resetAll();
    }

    //CLEAR MEMORY
    public void clearRandomNumberSet(){
        gameModel.clearHs();
    }

    //VALIDATE USER ACTION
    public void validateSelection(int selectedNumber){
        gameModel.setReveledCnt();
        if(!gameModel.getHs().contains(selectedNumber)){
            gameModel.setHs(selectedNumber);
        }
        if(selectedNumber==gameModel.getNumberToFined()){
            gameModel.setCorrectAnsCount();
            view.showMessage("CORRECT ANSWER",true,gameModel.getReveledCnt()==max?false:true,gameModel.getCorrectAnsCount());
        }else{
            view.showMessage("WRONG PICK",false,gameModel.getReveledCnt()==max?false:true,gameModel.getCorrectAnsCount());
        }
    }

    public interface View{
        void updateRandomNumberTextView(String numberToFind);
        void setRandomNumbers(LinkedHashSet<Integer> hashSetnumberToDisplay);
        void showMessage(String msg,boolean resultStatus,boolean gameStatus,int correctAnsCnt);
    }
}
