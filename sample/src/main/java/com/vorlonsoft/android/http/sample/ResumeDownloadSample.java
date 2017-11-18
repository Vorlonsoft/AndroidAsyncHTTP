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

package com.vorlonsoft.android.http.sample;

import android.util.Log;

import com.vorlonsoft.android.http.AsyncHttpClient;
import com.vorlonsoft.android.http.RangeFileAsyncHttpResponseHandler;
import com.vorlonsoft.android.http.RequestHandle;
import com.vorlonsoft.android.http.ResponseHandlerInterface;

import java.io.File;
import java.io.IOException;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpEntity;

public class ResumeDownloadSample extends SampleParentActivity {

    private static final String LOG_TAG = "ResumeDownloadSample";
    private File downloadTarget;

    private File getDownloadTarget() {
        try {
            if (downloadTarget == null) {
                downloadTarget = File.createTempFile("download_", "_resume", getCacheDir());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Couldn't create cache file to download to");
        }
        return downloadTarget;
    }

    @Override
    public ResponseHandlerInterface getResponseHandler() {
        return new RangeFileAsyncHttpResponseHandler(getDownloadTarget()) {
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, File file) {
                debugStatusCode(LOG_TAG, statusCode);
                debugHeaders(LOG_TAG, headers);
                debugThrowable(LOG_TAG, throwable);
                if (file != null) {
                    addView(getColoredView(LIGHTGREEN, "Download interrupted (" + statusCode + "): (bytes=" + file.length() + "), path: " + file.getAbsolutePath()));
                }
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, File file) {
                debugStatusCode(LOG_TAG, statusCode);
                debugHeaders(LOG_TAG, headers);
                if (file != null) {
                    addView(getColoredView(LIGHTGREEN, "Request succeeded (" + statusCode + "): (bytes=" + file.length() + "), path: " + file.getAbsolutePath()));
                }
            }
        };
    }

    @Override
    public String getDefaultHeaders() {
        return "Range=bytes=10-20";
    }

    @Override
    public String getDefaultURL() {
        return PROTOCOL + "www.google.com/images/srpr/logo11w.png";
    }

    @Override
    public boolean isRequestHeadersAllowed() {
        return true;
    }

    @Override
    public boolean isRequestBodyAllowed() {
        return false;
    }

    @Override
    public int getSampleTitle() {
        return R.string.title_resume_download;
    }

    @Override
    public RequestHandle executeSample(AsyncHttpClient client, String URL, Header[] headers, HttpEntity entity, ResponseHandlerInterface responseHandler) {
        return client.get(this, URL, headers, null, responseHandler);
    }
}
