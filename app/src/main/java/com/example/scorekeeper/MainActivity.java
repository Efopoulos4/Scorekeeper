package com.example.scorekeeper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private int mScore1;
    private int mScore2;
    private TextView mScoreText1;
    private TextView mScoreText2;
    static final String STATE_SCORE_1 = "Team 1 Score";
    static final String STATE_SCORE_2 = "Team 2 Score";

    private ImageButton mMinusButton1;
    private ImageButton mMinusButton2;
    private ImageButton mPlusButton1;
    private ImageButton mPlusButton2;

    int[] plusImages = {R.drawable.ic_plus, R.drawable.ic_plus_night};
    int[] minusImages = {R.drawable.ic_minus, R.drawable.ic_minus_night};

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt(STATE_SCORE_1, mScore1);
        outState.putInt(STATE_SCORE_2, mScore2);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mScoreText1 = (TextView) findViewById(R.id.score1);
        mScoreText2 = (TextView) findViewById(R.id.score2);
        if (savedInstanceState != null) {
            mScore1 = savedInstanceState.getInt(STATE_SCORE_1);
            mScore2 = savedInstanceState.getInt(STATE_SCORE_2);
            mScoreText1.setText(String.valueOf(mScore1));
            mScoreText2.setText(String.valueOf(mScore2));
        }
        mMinusButton1 = findViewById(R.id.decTeam1);
        mMinusButton2 = findViewById(R.id.decTeam2);
        mPlusButton1 = findViewById(R.id.incTeam1);
        mPlusButton2 = findViewById(R.id.incTeam2);
        int nightMode = AppCompatDelegate.getDefaultNightMode();
        if (nightMode == AppCompatDelegate.MODE_NIGHT_NO) {
            day_night_mode(0);
        } else if (nightMode == AppCompatDelegate.MODE_NIGHT_YES) {
            day_night_mode(1);
            // Every time the app starts the AppCompatDelegate isn't set to a
            // specified mode but we want the day mode, so for
            // MODE_NIGHT_UNSPECIFIED we pass the value 0 (day mode)
        } else if (nightMode == AppCompatDelegate.MODE_NIGHT_UNSPECIFIED) {
            day_night_mode(0);
        }
    }

    /**
     * @param i specifies whether we want to set night or day mode for the image buttons
     *          when i=0 we turn into day mode and when i=1 to night mode
     */
    private void day_night_mode(int i) {
        mPlusButton1.setImageResource(plusImages[i]);
        mPlusButton2.setImageResource(plusImages[i]);
        mMinusButton1.setImageResource(minusImages[i]);
        mMinusButton2.setImageResource(minusImages[i]);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        // Change the label of the menu based on the state of the app.
        int nightMode = AppCompatDelegate.getDefaultNightMode();
        if (nightMode == AppCompatDelegate.MODE_NIGHT_YES) {
            menu.findItem(R.id.night_mode).setTitle(R.string.day_mode);
        } else {
            menu.findItem(R.id.night_mode).setTitle(R.string.night_mode);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.night_mode) {
            int nightMode = AppCompatDelegate.getDefaultNightMode();
            if (nightMode == AppCompatDelegate.MODE_NIGHT_YES) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            }
        }
        return true;
    }

    public void decScore(View view) {
        int viewID = view.getId();
        switch (viewID) {
            case R.id.decTeam1:
                mScore1--;
                mScoreText1.setText(String.valueOf(mScore1));
                break;
            case R.id.decTeam2:
                mScore2--;
                mScoreText2.setText(String.valueOf(mScore2));
        }
    }

    public void incScore(View view) {
        int viewID = view.getId();
        switch (viewID) {
            case R.id.incTeam1:
                mScore1++;
                mScoreText1.setText(String.valueOf(mScore1));
                break;
            case R.id.incTeam2:
                mScore2++;
                mScoreText2.setText(String.valueOf(mScore2));
        }
    }
}