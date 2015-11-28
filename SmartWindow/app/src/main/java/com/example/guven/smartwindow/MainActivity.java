package com.example.guven.smartwindow;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;
import android.view.*;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private boolean isLightOpen = false;
    private boolean isWindowOpen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button buttonConnect = (Button) findViewById(R.id.button_connect);
        buttonConnect.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                TextView myAwesomeTextView = (TextView)findViewById(R.id.textView_Output);
                myAwesomeTextView.setText("Connected!");
            }
        });

        final Button buttonTurnOnOffLight = (Button) findViewById(R.id.button_light_On_Off);
        buttonTurnOnOffLight.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click

                new Thread(new Runnable(){
                    @Override
                    public void run() {
                        try {
                            NetbeastController netbeastController = new NetbeastController();
                            boolean operationResult = false;
                            if(isLightOpen)
                            {
                                operationResult = netbeastController.TurnOffLight();
                                if(operationResult)
                                {
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            buttonTurnOnOffLight.setText(R.string.turnOnLight);
                                        }
                                    });
                                }
                            }
                            else
                            {
                                operationResult = netbeastController.TurnOnLight();
                                if(operationResult)
                                {
                                    runOnUiThread(new Runnable() {
                                                      @Override
                                                      public void run() {
                                                buttonTurnOnOffLight.setText(R.string.turnOffLight);
                                                      }
                                    });
                                }
                            }

                            isLightOpen = !isLightOpen;
                            if(operationResult)
                            {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {

                                        TextView myAwesomeTextView = (TextView)findViewById(R.id.textView_Output);
                                        myAwesomeTextView.setText("Connected!");
                                    }
                                });
                            }
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                }).start();
            }
        });

    }
}
