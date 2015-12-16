package tech.sidespell.prelimexam;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SeekBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

    SeekBar mSeekBar;
    ToggleButton mToggleButton;
    TextView mTvCount;
    RadioGroup rbtnGroup;
    RadioButton rbtnInc;
    RadioButton rbtnDec;
    int progressSeekBar;
    int numberStart = 0;

    boolean increment = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mSeekBar = (SeekBar) findViewById(R.id.seekBar);
        mToggleButton = (ToggleButton) findViewById(R.id.toggleButton);
        mTvCount = (TextView) findViewById(R.id.tvCount);
        rbtnGroup= (RadioGroup) findViewById(R.id.radioGroup);
        rbtnInc = (RadioButton) findViewById(R.id.radioButton);
        rbtnDec = (RadioButton) findViewById(R.id.radioButton2);

        mToggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mToggleButton.isChecked()){

                    mToggleButton.setTextOn(getString(R.string.text_start));
                    int id = rbtnGroup.getCheckedRadioButtonId();
                    if(id == R.id.radioButton){
                        increment = true;
                    }
                    else {
                        increment = false;
                    }

                    progressSeekBar = mSeekBar.getProgress();
                    updateTextVIew(progressSeekBar);

                }
                else{

                    mToggleButton.setTextOff(getString(R.string.text_stop));
                }
            }
            });



        mSeekBar.setOnSeekBarChangeListener(this);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        progressSeekBar = mSeekBar.getProgress();
        updateTextVIew(progressSeekBar);
    }
    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
    }
    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        progressSeekBar = mSeekBar.getProgress();
        updateTextVIew(progressSeekBar);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



    //pass the progress
    public void updateTextVIew(int numberProgress){
        final Handler handler = new Handler();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if(increment) {
                    numberStart += 1;
                    mTvCount.setText(numberStart + "");
                }
                else {
                    numberStart -= 1;
                    mTvCount.setText(numberStart + "");
                }
            }
        };

        handler.postDelayed(runnable, numberProgress);
    }
}