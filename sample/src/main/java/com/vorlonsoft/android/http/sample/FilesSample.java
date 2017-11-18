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
import com.vorlonsoft.android.http.RequestHandle;
import com.vorlonsoft.android.http.RequestParams;
import com.vorlonsoft.android.http.ResponseHandlerInterface;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Random;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpEntity;

public class FilesSample extends PostSample {

    public static final String LOG_TAG = "FilesSample";

    @Override
    public int getSampleTitle() {
        return R.string.title_post_files;
    }

    @Override
    public boolean isRequestBodyAllowed() {
        return false;
    }

    @Override
    public RequestHandle executeSample(AsyncHttpClient client, String URL, Header[] headers, HttpEntity entity, ResponseHandlerInterface responseHandler) {
        try {
            RequestParams params = new RequestParams();
            final String contentType = RequestParams.APPLICATION_OCTET_STREAM;
            params.put("fileOne", createTempFile("fileOne", 1020), contentType, "fileOne");
            params.put("fileTwo", createTempFile("fileTwo", 1030), contentType);
            params.put("fileThree", createTempFile("fileThree", 1040), contentType, "customFileThree");
            params.put("fileFour", createTempFile("fileFour", 1050), contentType);
            params.put("fileFive", createTempFile("fileFive", 1060), contentType, "testingFileFive");
            params.setHttpEntityIsRepeatable(true);
            params.setUseJsonStreamer(false);
            return client.post(this, URL, params, responseHandler);
        } catch (FileNotFoundException fnfException) {
            Log.e(LOG_TAG, "executeSample failed with FileNotFoundException", fnfException);
        }
        return null;
    }

    public File createTempFile(String namePart, int byteSize) {
        try {
            File f = File.createTempFile(namePart, "_handled", getCacheDir());
            FileOutputStream fos = new FileOutputStream(f);
            Random r = new Random();
            byte[] buffer = new byte[byteSize];
            r.nextBytes(buffer);
            fos.write(buffer);
            fos.flush();
            fos.close();
            return f;
        } catch (Throwable t) {
            Log.e(LOG_TAG, "createTempFile failed", t);
        }
        return null;
    }
}
