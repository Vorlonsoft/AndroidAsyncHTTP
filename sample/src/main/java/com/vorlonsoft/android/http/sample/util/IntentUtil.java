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

package com.vorlonsoft.android.http.sample.util;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.message.BasicHeader;

public class IntentUtil {

    public static String[] serializeHeaders(Header[] headers) {
        if (headers == null) {
            return new String[0];
        }
        String[] rtn = new String[headers.length * 2];
        int index = -1;
        for (Header h : headers) {
            rtn[++index] = h.getName();
            rtn[++index] = h.getValue();
        }
        return rtn;
    }

    public static Header[] deserializeHeaders(String[] serialized) {
        if (serialized == null || serialized.length % 2 != 0) {
            return new Header[0];
        }
        Header[] headers = new Header[serialized.length / 2];
        for (int i = 0, h = 0; h < headers.length; i++, h++) {
            headers[h] = new BasicHeader(serialized[i], serialized[++i]);
        }
        return headers;
    }

}
