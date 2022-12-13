# Android-Issues

what is Deep Link?
Deep Link is help to navigate specific destination directly. also, which help to share datas/parameters
using this intent calling url to others apps.
--------------------------------------------------------------------
WEBVIEW and Custom Chrome browser and External Chrome browser app
--------------------------------------------------------------------
 Deep Linking not working in webview Android
--------------------------------------------------------------------
Issue:
 Installed App not opening from webview Android.
 Deep Linking functionality not working in android app webview.

Solution:

 we have to separately write the code to identify the intent url format under the shouldOverrideUrlLoading() webview client code.

 so, we have to check url.startsWith("intent://") this url format, then, set the URI Schema Intent,

 then, start the activity with help of context. please find the complete source code in this project.

Ex: Initial First url loaded example https://www.stackoverflow.com

    in this page having one openWhatsup button. button click listener added this Deep Link url format below
    
    Ex: we want to open our whatsup app
    
    intent://page=whatsupDeepDrop|linkstatus=success#intent|package=com.whatsup.com

Env:

Development Language: Kotlin

IDE: Android Studio(BumbleBee) with build Gradle 7.1.0 version and JDK/JRE Java11 enabled in below path

Path: AndroidStudio-File-Settings-Build,Execution,Deployment-BuildTools-Gradle-we have to select 11 version Jre file from Program Files under c folder.

added android.useAndroidX=true in gradle.properties
--------------------------------------------------------------------

https://developer.android.com/guide/navigation/navigation-deep-link
