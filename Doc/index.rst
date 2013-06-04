Installing Demo Applications:
..................

In this tutorial we have created several applications to test these cross platform mobile frameworks. 

You can build them using:

Installing via Importing to Eclipse
..................

1)Bridge App is an android application, which can be installed and run by importing it to Eclipse.

2)Download the source code from the Github repo above. (Android SDK as well if you haven't)

3)Open Eclipse.

4)Click File/New/Other

5)Select Android Project from Existing Code under the Android folder.

6)Specify the directory for the source code you have downloaded.

7)Run either using an emulator or by plugging a Smartphone.

Installing via Phonegap-Build
..................

1)Go to build.phonegap.com

2)Signup for an Adobe ID or signin with your github account

3)Upload the application you downloaded/forked from the github repository above.

4)Click build and download the resulting apk.

5)Install it to your smartphone or run it in an emulator.

Installing via Trigger.IO
..................

1)Launch Trigger Toolkit

2)Create a new Project/New App

3)Run it as Android/IOS or a Web Application

4)Click build and download the resulting apk.

5)Install it to your smartphone or run it in an emulator.



Bridge App
..................

Bridge App is the application we created to understand how these mobile cross platform frameworks(PhoneGap and Trigger.IO) actually work internally and to test whether we can implement a simple framework on our own. We used Android SDK's Webview container to display our HTML5 content and worked with the javascript interface to provide communication between the javascript and java part of things. 

This demo application will show you how you would go about transforming your HTML5 Application into a native mobile application that can access native calls and smartphone information without using the cross platform frameworks mentioned in the subsequent parts of the tutorial. It demonstrates how you can call an Android function from the HTML5 Content and vice versa. The native call that we are trying to access is the Geolocation information, which will be displayed in the HTML part loaded by the WebView.    


CSSAnimation/JQueryAnimation App
..................

These two applications explain how you would go about using web animations efficiently in the web applications you create for PhoneGap/TriggerIO. It has been noted in several blog posts that using the wrong type of animation can result in a sluggish user interface experience, which the user will definitely notice. To this end, to have a native-like feel in the performance, while using these frameworks, it is important to use the right type of animation, mainly to choose between CSS3 and JQuery-mobile. 

In this demo we test the performances of animations provided by these technologies/libraries in mobile applications. At each millisecond we aim to create a new div element that first fades in and then fades out. The whole animation takes 1 second(0.5 for each fade sequence) for both of these technologies. Depending on the performance of the animation technology that is used, we expect to have different number of boxes generated in 1 second. (It would be 1000 boxes in 1 second in an ideal framework). Then we compare the number of boxes generated in average to compare them. (Spoiler CSS3 wins by 20 BPS) 