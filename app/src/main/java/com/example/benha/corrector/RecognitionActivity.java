package com.example.benha.corrector;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.dolby.dap.DolbyAudioProcessing;
import com.dolby.dap.OnDolbyAudioProcessingEventListener;

/**
 * Created by Stefano on 10/24/2015.
 */
public class RecognitionActivity extends AppCompatActivity implements OnDolbyAudioProcessingEventListener {
    private RecognitionFragment recognitionFragment;
    private final String TAG = "Recognition Activity";
    DolbyAudioProcessing dolbyAudioProcessing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (recognitionFragment == null) {
            recognitionFragment = RecognitionFragment.newInstance();
        }
        if(savedInstanceState == null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.recognition_container, recognitionFragment);
            ft.commit();
        }

        dolbyAudioProcessing = DolbyAudioProcessing.getDolbyAudioProcessing(getApplicationContext(), DolbyAudioProcessing.PROFILE.VOICE, this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        dolbyAudioProcessing.release();
    }

    @Override
    public void onDolbyAudioProcessingEnabled(boolean on) {
        Log.i(TAG, "onDolbyAudioProcessingEnabled(" + on + ")");
    }

    @Override
    public void onDolbyAudioProcessingProfileSelected(DolbyAudioProcessing.PROFILE profile) {
        Log.i(TAG, "onDolbyAudioProcessingProfileSelected(" + profile + ")");
    }

    @Override
    public void onDolbyAudioProcessingClientConnected() {
        Log.i(TAG, "onDolbyAudioProcessingClientConnected()");
    }

    @Override
    public void onDolbyAudioProcessingClientDisconnected() {
        Log.w(TAG, "onDolbyAudioProcessingClientDisconnected()");
    }
}