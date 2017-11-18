/*
 * Copyright 2017 Vorlonsoft LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.vorlonsoft.android.http.sample.services;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.vorlonsoft.android.http.AsyncHttpClient;
import com.vorlonsoft.android.http.AsyncHttpResponseHandler;
import com.vorlonsoft.android.http.SyncHttpClient;
import com.vorlonsoft.android.http.sample.IntentServiceSample;
import com.vorlonsoft.android.http.sample.util.IntentUtil;

import cz.msebera.android.httpclient.Header;

public class ExampleIntentService extends IntentService {

    public static final String LOG_TAG = "ExampleIntentService";
    public static final String INTENT_URL = "INTENT_URL";
    public static final String INTENT_STATUS_CODE = "INTENT_STATUS_CODE";
    public static final String INTENT_HEADERS = "INTENT_HEADERS";
    public static final String INTENT_DATA = "INTENT_DATA";
    public static final String INTENT_THROWABLE = "INTENT_THROWABLE";

    private final AsyncHttpClient aClient = new SyncHttpClient();

    public ExampleIntentService() {
        super("ExampleIntentService");
    }

    @Override
    public void onStart(Intent intent, int startId) {
        Log.d(LOG_TAG, "onStart()");
        super.onStart(intent, startId);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null && intent.hasExtra(INTENT_URL)) {
            aClient.get(this, intent.getStringExtra(INTENT_URL), new AsyncHttpResponseHandler() {
                @Override
                public void onStart() {
                    sendBroadcast(new Intent(IntentServiceSample.ACTION_START));
                    Log.d(LOG_TAG, "onStart");
                }

                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                    Intent broadcast = new Intent(IntentServiceSample.ACTION_SUCCESS);
                    broadcast.putExtra(INTENT_STATUS_CODE, statusCode);
                    broadcast.putExtra(INTENT_HEADERS, IntentUtil.serializeHeaders(headers));
                    broadcast.putExtra(INTENT_DATA, responseBody);
                    sendBroadcast(broadcast);
                    Log.d(LOG_TAG, "onSuccess");
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                    Intent broadcast = new Intent(IntentServiceSample.ACTION_FAILURE);
                    broadcast.putExtra(INTENT_STATUS_CODE, statusCode);
                    broadcast.putExtra(INTENT_HEADERS, IntentUtil.serializeHeaders(headers));
                    broadcast.putExtra(INTENT_DATA, responseBody);
                    broadcast.putExtra(INTENT_THROWABLE, error);
                    sendBroadcast(broadcast);
                    Log.d(LOG_TAG, "onFailure");
                }

                @Override
                public void onCancel() {
                    sendBroadcast(new Intent(IntentServiceSample.ACTION_CANCEL));
                    Log.d(LOG_TAG, "onCancel");
                }

                @Override
                public void onRetry(int retryNo) {
                    sendBroadcast(new Intent(IntentServiceSample.ACTION_RETRY));
                    Log.d(LOG_TAG, String.format("onRetry: %d", retryNo));
                }

                @Override
                public void onFinish() {
                    sendBroadcast(new Intent(IntentServiceSample.ACTION_FINISH));
                    Log.d(LOG_TAG, "onFinish");
                }
            });
        }
    }
}
