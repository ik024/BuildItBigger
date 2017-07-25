package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.ik.androidjokelib.ShowJokeActivity;


public class MainActivity extends AppCompatActivity implements JokeAsyncTask.Callback{

    private InterstitialAd mInterstitialAd;
    private ProgressBar mProgressBar;
    private boolean isFetchingJoke;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);

        if (Config.PRODUCT_FLAVOUR.equals(Constants.FLAVOUR_FREE)) {
            mInterstitialAd = new InterstitialAd(this);
            mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
            mInterstitialAd.loadAd(new AdRequest.Builder().build());
        }
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

    public void tellJoke(View view) {
        //execute only once
        if (!isFetchingJoke) {
            mProgressBar.setVisibility(View.VISIBLE);
            isFetchingJoke = true;

            if (mInterstitialAd != null && mInterstitialAd.isLoaded()) {
                mInterstitialAd.show();
            }
            new JokeAsyncTask().execute(this);
        }
    }


    @Override
    public void jokeFetched(String joke) {
        isFetchingJoke = false;
        mProgressBar.setVisibility(View.GONE);

        Intent intent = new Intent(this, ShowJokeActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(ShowJokeActivity.KEY_JOKE, joke);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
