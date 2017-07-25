package com.udacity.gradle.builditbigger;

import android.text.TextUtils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static junit.framework.Assert.assertTrue;


@RunWith(JUnit4.class)
public class JokeAsyncTaskTest {


    @Test
    public void testJokeAsyncTask() throws InterruptedException {
        final Object obj = new Object();
        JokeAsyncTask task = new JokeAsyncTask();
        task.execute(new JokeAsyncTask.Callback() {
            @Override
            public void jokeFetched(String joke) {
                assertTrue(!TextUtils.isEmpty(joke));
                synchronized (obj) {
                    obj.notify();
                }
            }
        });
        synchronized (obj) {
            obj.wait();
        }

    }
}