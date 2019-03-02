package com.memory.game.View;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.memory.game.Presenter.GamePresenter;
import com.memory.game.R;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;

public class MainActivity extends AppCompatActivity implements GamePresenter.View {

    GamePresenter presenter;
    View Viewrow01, Viewrow02, Viewrow03, Viewrow11, Viewrow12, Viewrow13, Viewrow21, Viewrow22, Viewrow23;
    TextView TxtFindNumber, Txtmessage;
    ArrayList<TextView> numberPadTextArray = new ArrayList<TextView>();
    Handler mHandler = new Handler();
    int countDown = 5;

    //LOOPER TO COVER THE NUMBERS, IT WILL REPEAT UNTILL 5 SECONDS
    public Runnable runnable = new Runnable() {
        public void run() {
            if (countDown == 0) {
                setAllViewVisibility();
                countDown = 5;
            } else {
                countDown -= 1;
                mHandler.postDelayed(this, 1 * 1000);
                setMessage("Game will begin in " + countDown + " sec");
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new GamePresenter(this);

        numberPadTextArray.add((TextView) findViewById(R.id.Txtrow01));
        numberPadTextArray.add((TextView) findViewById(R.id.Txtrow02));
        numberPadTextArray.add((TextView) findViewById(R.id.Txtrow03));
        numberPadTextArray.add((TextView) findViewById(R.id.Txtrow11));
        numberPadTextArray.add((TextView) findViewById(R.id.Txtrow12));
        numberPadTextArray.add((TextView) findViewById(R.id.Txtrow13));
        numberPadTextArray.add((TextView) findViewById(R.id.Txtrow21));
        numberPadTextArray.add((TextView) findViewById(R.id.Txtrow22));
        numberPadTextArray.add((TextView) findViewById(R.id.Txtrow23));

        TxtFindNumber = findViewById(R.id.TxtFindNumber);
        Txtmessage = findViewById(R.id.Txtmessage);

        Viewrow01 = findViewById(R.id.Viewrow01);
        Viewrow02 = findViewById(R.id.Viewrow02);
        Viewrow03 = findViewById(R.id.Viewrow03);
        Viewrow11 = findViewById(R.id.Viewrow11);
        Viewrow12 = findViewById(R.id.Viewrow12);
        Viewrow13 = findViewById(R.id.Viewrow13);
        Viewrow21 = findViewById(R.id.Viewrow21);
        Viewrow22 = findViewById(R.id.Viewrow22);
        Viewrow23 = findViewById(R.id.Viewrow23);

        mHandler.postDelayed(runnable, 1 * 1000);
        beginGame();
    }

    //SET THE INTIAL MESSAGE AND RANDOM NUMBERS TO GRID VIEW
    private void beginGame() {
        setMessage("Game will begin in 5 sec");
        TxtFindNumber.setText("");
        new Thread(new Runnable() {
            public void run() {
                presenter.updateNumberPadTextView();
            }
        }).start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //RESTART THE GAME
        if (item.getItemId() == R.id.action_restart) {
            try {
                removeHandlerCallBack();
                setAllViewVisibilityGone();
                mHandler.postDelayed(runnable, 1 * 1000);
                presenter.resetVar();
                beginGame();
            } catch (Exception e) {
            }
        }
        return super.onOptionsItemSelected(item);
    }


    //SET THE LAYER VISIBILITY TO TRUE
    private void setAllViewVisibility() {
        Viewrow01.setVisibility(View.VISIBLE);
        Viewrow02.setVisibility(View.VISIBLE);
        Viewrow03.setVisibility(View.VISIBLE);
        Viewrow11.setVisibility(View.VISIBLE);
        Viewrow12.setVisibility(View.VISIBLE);
        Viewrow13.setVisibility(View.VISIBLE);
        Viewrow21.setVisibility(View.VISIBLE);
        Viewrow22.setVisibility(View.VISIBLE);
        Viewrow23.setVisibility(View.VISIBLE);
        generateNewNumber();
    }

    //SET THE LAYER VISIBILITY TO FALSE
    private void setAllViewVisibilityGone() {
        Viewrow01.setVisibility(View.GONE);
        Viewrow02.setVisibility(View.GONE);
        Viewrow03.setVisibility(View.GONE);
        Viewrow11.setVisibility(View.GONE);
        Viewrow12.setVisibility(View.GONE);
        Viewrow13.setVisibility(View.GONE);
        Viewrow21.setVisibility(View.GONE);
        Viewrow22.setVisibility(View.GONE);
        Viewrow23.setVisibility(View.GONE);
    }

    //SHOW THE GAME STATUS
    private void setMessage(final String msg) {
        runOnUiThread(new Thread(new Runnable() {
            public void run() {
                Txtmessage.setText(msg);
            }
        }));
    }

    //GENERATE NEW RANDOM NUMBER TO REVEAL
    private void generateNewNumber() {
        new Thread(new Runnable() {
            public void run() {
                presenter.generateRandomNumber();
            }
        }).start();
    }

    //HANDLE USER SELECTION ACTION
    public void btnClicked(View view) {
        switch (view.getId()) {
            case R.id.row01:
                if (Viewrow01.getVisibility() == View.VISIBLE) {
                    Viewrow01.setVisibility(View.GONE);
                    presenter.validateSelection(Integer.valueOf(numberPadTextArray.get(0).getText().toString()));
                }
                break;
            case R.id.row02:
                if (Viewrow02.getVisibility() == View.VISIBLE) {
                    Viewrow02.setVisibility(View.GONE);
                    presenter.validateSelection(Integer.valueOf(numberPadTextArray.get(1).getText().toString()));
                }
                break;
            case R.id.row03:
                if (Viewrow03.getVisibility() == View.VISIBLE) {
                    Viewrow03.setVisibility(View.GONE);
                    presenter.validateSelection(Integer.valueOf(numberPadTextArray.get(2).getText().toString()));
                }
                break;
            case R.id.row11:
                if (Viewrow11.getVisibility() == View.VISIBLE) {
                    Viewrow11.setVisibility(View.GONE);
                    presenter.validateSelection(Integer.valueOf(numberPadTextArray.get(3).getText().toString()));
                }
                break;
            case R.id.row12:
                if (Viewrow12.getVisibility() == View.VISIBLE) {
                    Viewrow12.setVisibility(View.GONE);
                    presenter.validateSelection(Integer.valueOf(numberPadTextArray.get(4).getText().toString()));
                }
                break;
            case R.id.row13:
                if (Viewrow13.getVisibility() == View.VISIBLE) {
                    Viewrow13.setVisibility(View.GONE);
                    presenter.validateSelection(Integer.valueOf(numberPadTextArray.get(5).getText().toString()));
                }
                break;
            case R.id.row21:
                if (Viewrow21.getVisibility() == View.VISIBLE) {
                    Viewrow21.setVisibility(View.GONE);
                    presenter.validateSelection(Integer.valueOf(numberPadTextArray.get(6).getText().toString()));
                }
                break;
            case R.id.row22:
                if (Viewrow22.getVisibility() == View.VISIBLE) {
                    Viewrow22.setVisibility(View.GONE);
                    presenter.validateSelection(Integer.valueOf(numberPadTextArray.get(7).getText().toString()));
                }
                break;
            case R.id.row23:
                if (Viewrow23.getVisibility() == View.VISIBLE) {
                    Viewrow23.setVisibility(View.GONE);
                    presenter.validateSelection(Integer.valueOf(numberPadTextArray.get(8).getText().toString()));
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        removeHandlerCallBack();
    }

    //REMOVE ALL HANDLERS
    private void removeHandlerCallBack() {
        try {
            countDown = 5;
            if (mHandler != null) {
                mHandler.removeCallbacks(runnable);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateRandomNumberTextView(final String numberToFind) {
        runOnUiThread(new Thread(new Runnable() {
            public void run() {
                setMessage("Reveal the number");
                TxtFindNumber.setText(numberToFind);
            }
        }));
    }

    @Override
    public void setRandomNumbers(final LinkedHashSet<Integer> hashSetnumberToDisplay) {

        runOnUiThread(new Thread(new Runnable() {
            public void run() {
                Iterator it = hashSetnumberToDisplay.iterator();
                int i = 0;
                while (it.hasNext()) {
                    numberPadTextArray.get(i).setText(it.next().toString());
                    i++;
                }
                presenter.clearRandomNumberSet();
            }
        }));
    }

    @Override
    public void showMessage(String msg, boolean resultStatus, boolean gameStatus, int correctAnsCnt) {
        try {
            View parentLayout = findViewById(android.R.id.content);
            Snackbar snackbar = Snackbar.make(parentLayout, msg, Snackbar.LENGTH_LONG)
                    .setAction("CLOSE", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                        }
                    });
            snackbar.setActionTextColor(resultStatus ? getResources().getColor(R.color.colorWhite) :
                    getResources().getColor(R.color.colorWhite));

            View sbView = snackbar.getView();
            if (resultStatus) {
                sbView.setBackgroundColor(ContextCompat.getColor(MainActivity.this, android.R.color.holo_green_dark));
            } else {
                sbView.setBackgroundColor(ContextCompat.getColor(MainActivity.this, android.R.color.holo_red_dark));
            }
            snackbar.show();
        } catch (Exception e) {
        }
        if (gameStatus) {
            generateNewNumber();
        } else {
            setMessage("YOUR SCORE");
            TxtFindNumber.setText(correctAnsCnt + " / 9");
        }
    }
}
