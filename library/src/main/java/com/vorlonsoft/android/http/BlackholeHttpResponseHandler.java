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

package com.vorlonsoft.android.http;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpResponse;

/**
 * Blank implementation of ResponseHandlerInterface, which ignores all contents returned by
 * remote HTTP endpoint, and discards all various log messages
 * <p>&nbsp;</p>
 * Use this implementation, if you deliberately want to ignore all response, because you cannot
 * pass null ResponseHandlerInterface into AsyncHttpClient implementation
 */
public class BlackholeHttpResponseHandler extends AsyncHttpResponseHandler {

    @Override
    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

    }

    @Override
    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

    }

    @Override
    public void onProgress(long bytesWritten, long totalSize) {

    }

    @Override
    public void onCancel() {

    }

    @Override
    public void onFinish() {

    }

    @Override
    public void onPostProcessResponse(ResponseHandlerInterface instance, HttpResponse response) {

    }

    @Override
    public void onPreProcessResponse(ResponseHandlerInterface instance, HttpResponse response) {

    }

    @Override
    public void onRetry(int retryNo) {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onUserException(Throwable error) {

    }
}
