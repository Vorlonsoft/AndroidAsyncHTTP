Android Async HTTP library
====================================
An fast, small, asynchronous, callback-based HTTP client for Android. Original project [android-async-http](https://github.com/loopj/android-async-http) (The Apache Software License 2.0.) was developed by James Smith.

Features
--------

- Make **asynchronous** HTTP requests, handle responses in **anonymous callbacks**
- HTTP requests happen **outside the UI thread**
- Requests use a **threadpool** to cap concurrent resource usage
- GET/POST **params builder** (RequestParams)
- **Multipart file uploads** with no additional third party libraries
- Tiny size overhead to your application, only **104kb** for everything
- Automatic smart **request retries** optimized for spotty mobile connections
- Automatic **gzip** response decoding support for super-fast requests
- Optional built-in response parsing into **JSON** (JsonHttpResponseHandler)
- Optional **persistent cookie store**, saves cookies into your app's SharedPreferences

Install
-------

You can download from Maven Central.

${latest.version} is ![Maven Badges](https://maven-badges.herokuapp.com/maven-central/com.vorlonsoft/android-async-http/badge.svg)

Gradle
```groovy
dependencies {
  implementation 'com.vorlonsoft:android-async-http:{latest.version}'
}
```

Support
-------

Android Async HTTP library supports API level 9 and up.

Examples
--------

For inspiration and testing on device we've provided Sample Application.  
See individual samples [here on Github](https://github.com/Vorlonsoft/AndroidAsyncHTTP/tree/master/sample/src/main/java/com/vorlonsoft/android/http/sample)  
To run Sample application, simply clone the repository and run this command, to install it on connected device  

```java
gradle :sample:installDebug
```

Already in use in following apps
--------------------------------

* [Rossiya.pro Mail](https://play.google.com/store/apps/details?id=com.vorlonsoft.rossiyapro)

* ...

Contribute
----------

1. Fork it
2. Create your feature branch (`git checkout -b my-new-feature`)
3. Commit your changes (`git commit -am 'Added some feature'`)
4. Push to the branch (`git push origin my-new-feature`)
5. Create new Pull Request