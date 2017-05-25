package com.kumar.user.seekbarappwiththread;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    SeekBar skBar;
    TextView textView;
    boolean isRunning=false;
    int CounterUp=0;
    int MaxCounter=100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        skBar= (SeekBar) findViewById(R.id.seekBar);
        skBar.setMax(MaxCounter);
        textView= (TextView) findViewById(R.id.textView);
    }

    class MyThread extends Thread{
        @Override
        public void run() {
            while (isRunning){
                if (CounterUp<=MaxCounter){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            skBar.setProgress(CounterUp);
                            textView.setText("Counter = " + CounterUp);
                        }
                    });
                    CounterUp=CounterUp + 1;
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void buStart(View view) {
        isRunning=true;
        MyThread myThread=new MyThread();
        myThread.start();
    }

    public void buStop(View view) {
        isRunning=false;
    }
}
