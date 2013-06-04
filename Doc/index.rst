.. DhtmlxTouch documentation master file, created by
   sphinx-quickstart on Mon May  6 09:43:56 2013.
   You can adapt this file completely to your liking, but it should at least
   contain the root `toctree` directive.

CS263 : Project Documentation (Hybrid Mobile Application Development)
==========================================

.. toctree::
   :maxdepth: 2

.. _PhoneGap: http://phonegap.com/
.. _Trigger.IO: http://trigger.io/
.. _DHTMLX Touch: http://dhtmlx.com/touch/
.. _jQuery Mobile: http://jquerymobile.com/

We have used two frameworks(`PhoneGap`_ and `Trigger.IO`_ ) for developing hybrid mobile application development for android platform. Source mobile web applications were developed using `DHTMLX Touch`_ and `jQuery Mobile`_ , which are javascript frameworks for creating HTML5-based mobile web applications. In this documentation, we enlist the installation of the two hybrid mobile application development framework and javascript frameworks for developing mobile web applications.


===========================================================
Installing Hybrid Mobile Application Development Frameworks
==========================================================
.. _Installing PhoneGap: https://cs263-phonegap-technology-tutorial.readthedocs.org/en/latest/
.. _Download Trigger.IO Toolkit: https://trigger.io/forge/toolkit/

+ PhoneGap Installation
    Documentation for installing PhoneGap can be found here `Installing PhoneGap`_.
    
+ Trigger.IO
    Trigger.IO is primarily a cloud build service for creating cross-platform hybrid mobile applications. Since the build service is available via cloud, it requires minimal effort in terms of installation. Following are the steps for installation:
        - Download Trigger.IO Toolkit for OS of your choice from here `Download Trigger.IO Toolkit`_
        - Install the toolkit.
        - After installtion, launch the toolkit which in turn would open a new tab/window in your default browser with interface to cloud build service.

=======================================================================
Installing Javascript Frameworks for Mobile Web Application Development
=======================================================================
.. _Installing DHTMLX Touch: https://dhtmlx-touch-installation-documentation.readthedocs.org/en/latest/
.. _Download jQuery Mobile Libraries: http://code.jquery.com/mobile/1.3.1/jquery.mobile-1.3.1.min.js

+ DHTMLX Touch Installtion
    Documentation for installing PhoneGap can be found here `Installing DHTMLX Touch`_.

+ jQuery Mobile Installation
    JqueryMobile libraries can be downloaded from here `Download JqueryMobile Libraries`_
    After downloading JqueryMobile libraries, importing them into your applications is pretty straightforward. In order to use jQuery Mobile basic functionalities, you need to include jquery.mobile-1.3.1.min.js

=====================================================
Applications
=====================================================

.. _Bridge Application: https://github.com/Emregul/Cross-Platform-Tests-Phonegap-Trigger.io-/tree/master/Bridge

.. _CSS Animation Application: https://github.com/Emregul/Cross-Platform-Tests-Phonegap-Trigger.io-/tree/master/CSSAnimation

.. _jQuery Animation Application: https://github.com/Emregul/Cross-Platform-Tests-Phonegap-Trigger.io-/tree/master/JQueryAnimation

.. _Local Tweets Application: https://github.com/Emregul/Cross-Platform-Tests-Phonegap-Trigger.io-/tree/master/Local%20Tweets%20Application

.. _Concurrent Request Benchmarking: https://github.com/Emregul/Cross-Platform-Tests-Phonegap-Trigger.io-/tree/master/Concurrent%20HTTP_Request_Benchmarking

.. _Sequential Request Benchmarking: https://github.com/Emregul/Cross-Platform-Tests-Phonegap-Trigger.io-/tree/master/Sequential%20HTTP_Request_Benchmarking


We have developed several mobile web applications for benchmarking purpose in order to compare the two hybrid mobile application development frameworks namely PhoneGap and Trigger.IO.
In this section, we provide description about the applications developed for benchmarking these frameworks.
    + Local Tweets (Github Link: `Local Tweets Application`_ )
        This mobile web applications is developed using DHTMLX Touch framework. When user searches for a particular term in this application, tweets are loaded for that particular search term restricted by 50mi/100mi of user's current location.
        This application is used to compare the touch sensitivity and responsiveness.
        
        
    + Bridge Application (Github Link: `Bridge Application`_ )
        Bridge App is the application we created to understand how these mobile cross platform frameworks(PhoneGap and Trigger.IO) actually work internally and to test whether we can implement a simple framework on our own. We used Android SDK's Webview container to display our HTML5 content and worked with the javascript interface to provide communication between the javascript and java part of things.
        This demo application will show you how you would go about transforming your HTML5 Application into a native mobile application that can access native calls and smartphone information without using the cross platform frameworks mentioned in the subsequent parts of the tutorial. It demonstrates how you can call an Android function from the HTML5 Content and vice versa. The native call that we are trying to access is the Geolocation information, which will be displayed in the HTML part loaded by the WebView.
        Github Link:

    + CSSAnimation/jQueryAnimation App (Github Links: `jQuery Animation Application`_ and `CSS Animation Application`_ )
        These two applications explain how you would go about using web animations efficiently in the web applications you create for PhoneGap/TriggerIO. It has been noted in several blog posts that using the wrong type of animation can result in a sluggish user interface experience, which the user will definitely notice. To this end, to have a native-like feel in the performance, while using these frameworks, it is important to use the right type of animation, mainly to choose between CSS3 and JQuery-mobile.
        In this demo we test the performances of animations provided by these technologies/libraries in mobile applications. At each millisecond we aim to create a new div element that first fades in and then fades out. The whole animation takes 1 second(0.5 for each fade sequence) for both of these technologies. Depending on the performance of the animation technology that is used, we expect to have different number of boxes generated in 1 second. (It would be 1000 boxes in 1 second in an ideal framework). Then we compare the number of boxes generated in average to compare them. (Spoiler CSS3 wins by 20 BPS)

    + HTTP Requests Benchmark (Github Links: `Concurrent Request Benchmarking`_ and `Sequential Request Benchmarking`_ )
        This benchmark is built on top of the "local tweets" application. There are two modes for this benchmark - concurrent requests and sequential requests. Tweets are requested via HTTP GET requests which are made concurrently ranging from 10 requests to 1000 requests. For Sequential HTTP Requests benchmarking, a batch of requests are completed one by one(measurements are done for request batch size ranging from 10 to 1000).
        
=====================================================
Building the applications
=====================================================
In this tutorial we have created several applications to test these cross platform mobile frameworks.

    + Building via Phonegap-Build
        - Go to build.phonegap.com
        - Signup for an Adobe ID or signin with your github account
        - Upload the application you downloaded/forked from the github repository above.
        - Click build and download the resulting apk.
        - Install it to your smartphone or run it in an emulator.

    + Building via Trigger.IO
        - Launch Trigger Toolkit
        - Create a new Project/New App
        - Run it as Android/IOS or a Web Application
        - Click build and download the resulting apk.
        - Install it to your smartphone or run it in an emulator.

    + Installing via Importing to Eclipse
        - Any of the above app is an android application, which can be installed and run by importing it to Eclipse.
        - Download the source code from the Github repo above. (Android SDK as well if you haven't).
        - Open Eclipse.
        - Click File/New/Other
        - Select Android Project from Existing Code under the Android folder.
        - Specify the directory for the source code you have downloaded.
        - Run either using an emulator or by plugging a Smartphone.
    