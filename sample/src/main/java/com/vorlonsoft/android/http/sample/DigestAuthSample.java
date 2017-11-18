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

import android.net.Uri;
import android.os.Bundle;
import android.widget.EditText;

import com.vorlonsoft.android.http.AsyncHttpClient;
import com.vorlonsoft.android.http.RequestHandle;
import com.vorlonsoft.android.http.ResponseHandlerInterface;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.auth.AuthScope;
import cz.msebera.android.httpclient.auth.UsernamePasswordCredentials;

public class DigestAuthSample extends GetSample {

    private EditText usernameField, passwordField;

    @Override
    public String getDefaultURL() {
        return PROTOCOL + "httpbin.org/digest-auth/auth/user/passwd2";
    }

    @Override
    public int getSampleTitle() {
        return R.string.title_digest_auth;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        usernameField = new EditText(this);
        passwordField = new EditText(this);
        usernameField.setHint("Username");
        passwordField.setHint("Password");
        usernameField.setText("user");
        passwordField.setText("passwd2");
        customFieldsLayout.addView(usernameField);
        customFieldsLayout.addView(passwordField);
    }

    @Override
    public RequestHandle executeSample(AsyncHttpClient client, String URL, Header[] headers, HttpEntity entity, ResponseHandlerInterface responseHandler) {
        setCredentials(client, URL);
        return client.get(this, URL, headers, null, responseHandler);
    }

    @Override
    public boolean isCancelButtonAllowed() {
        return true;
    }

    @Override
    public boolean isRequestHeadersAllowed() {
        return true;
    }

    @Override
    public boolean isRequestBodyAllowed() {
        return false;
    }

    private void setCredentials(AsyncHttpClient client, String URL) {
        Uri parsed = Uri.parse(URL);
        client.clearCredentialsProvider();
        client.setCredentials(
                new AuthScope(parsed.getHost(), parsed.getPort() == -1 ? 80 : parsed.getPort()),
                new UsernamePasswordCredentials(
                        usernameField.getText().toString(),
                        passwordField.getText().toString()
                )
        );
    }
}
