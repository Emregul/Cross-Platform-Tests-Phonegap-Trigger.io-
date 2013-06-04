.. CS263 PhoneGap Tutorial documentation master file, created by
   sphinx-quickstart on Mon May 06 21:34:02 2013.
   You can adapt this file completely to your liking, but it should at least
   contain the root `toctree` directive.

Welcome to CS263 PhoneGap Tutorial's documentation!
===================================================

**PhoneGap with JQuery Mobile and nodeJS**

Resources
..........

* PhoneGap: http://phonegap.com/

* jQuery Mobile: http://jquerymobile.com/

* nodejs: http://nodejs.org/

* Android SDK: http://developer.android.com/sdk/installing/index.html

Installation Notes
..................
* Windows 8
* PhoneGap 2.5
* nodejs 0.10.5
* jQuery Mobile 1.2
* Android 4.2
* Eclipse 4.2.2

Installing PhoneGap
..................

Download and install Android SDK.
Add the path for Android SDK platform-tools and tools to Environment Variables::

	;C:\Development\android-sdk-windows\platform-tools;C:\Development\android-sdk-windows\tools

Then you need to download PhoneGap from  http://phonegap.com/ and extract it to Android Development folder.

Creating a PhoneGap App
..................
Go to android bin folder of PhoneGap and execute create command with following inputs.::

	./create <project_folder_path> <package_name> <project_name>

Now launch Eclipse and import this project by selecting Android Project from Existing Code from File/New/Project and specify the folder that was created by the previous script.

Working on the Application
..................
PhoneGap source code resides in the assets/www folder. The folder is set up similar to a regular html webpage project. Index.html is our mainpage where we design the application.

About the Demo Application(PhoneGap-Tutorial, source-code above)
..................
For this tutorial, I have worked on a simple application that accesses Camera/Accelerometer/Geolocation information of our smartphone. What our app does is that it shows you pictures that are taken nearby your location on a map and you can also upload a photo of your own along with the geolocation information to be shown on someone else's app. 

So basically the whole UI consists of two buttons and a div element that is transformed into a map using Google Map API. We also have a nodejs and a mysql server running in the back-end that I will not be talking about during this tutorial.(Hint: You can see the internal IP of the server in ajax calls! But Sssh!) 

Using external Javascript Libraries
..................
To install javascript libraries download them under the assets/js folder and import them in your index.html.::

	<script type="text/javascript" src="js/jquery.ui.map.js"></script>
	<script type="text/javascript" src="js/jquery.ui.map.services.js"></script>
	<script type="text/javascript" src="js/jquery.ui.map.extensions.js"></script>

Accessing Native Camera Functions
..................
Accessing camera using PhoneGap is quite simple, we can launch the camera and get the picture by calling the navigator.camera. ::

	navigator.camera.getPicture(onPhotoDataSuccess, onFail, { quality: 50,
        destinationType: destinationType.FILE_URI });

onPhotoDataSuccess is a callback function that is called when the picture is succesfully taken.(OnFail is the same but for baad situations) For destinationType you can either use File_URI to get the photo's location in the internal/external storage or use Data_URI to actually retrieve the data of the image and  process it in your application.

We can also access the phone's internal media library by specifying an additional argument called sourceType and passing pictureSource.PHOTOLIBRARY as the value to this argument. :: 

	navigator.camera.getPicture(onPhotoURISuccess, onFail, { quality: 50, 
        destinationType: destinationType.FILE_URI,
        sourceType: source });

In the demo application the camera information is accessed when a button is clicked, therefore we can use these functions in our button event handlers. :: 

	function capturePhoto() {
      // Take picture using device camera and retrieve image as base64-encoded string
		navigator.camera.getPicture(onPhotoDataSuccess, onFail, { quality: 50,
        destinationType: destinationType.FILE_URI });
		
    }
    function getPhoto(source) {	
      // Retrieve image file location from specified source
      navigator.camera.getPicture(onPhotoURISuccess, onFail, { quality: 50, 
        destinationType: destinationType.FILE_URI,
        sourceType: source });
    }

Lets create some buttons. :: 

	<button onclick="capturePhoto();">Capture Photo</button> <br>
    <button onclick="getPhoto(pictureSource.PHOTOLIBRARY);">From Photo Library</button><br>
	
You can also assign these button's event handlers in javascript part as well.

Now let's look at the callback functions. So in our demo application when a photo is succesfully uploaded, we want to send it to the server along with it's geolocation data. So our callback function should handle this functionality.::

	function onPhotoDataSuccess(imageURI) {
			
       var options = new FileUploadOptions();
       options.fileKey="file";
       options.fileName=imageURI.substr(imageURI.lastIndexOf('/')+1);
       options.mimeType="image/jpeg";
       options.chunkedMode = false;

       // Transfer picture to server
       var ft = new FileTransfer();
       ft.upload(imageURI, "http://169.231.8.99:1337/upload", OnUploadSuccess(), function(error) {
           console.log("Upload failed: Code = "+error.code);            	
        }, options);
    }

As you can see we are using a FileTransfer(),offered kindly by PhoneGap, class to handle the upload operation. We specify the URL and another callback function that is called when the file is sent to the server.

Accessing Native Geolocation Functions
..................
PhoneGap provides a simple way to access the Geolocation information of the phone. This is again accessed by a simple call to navigator.geolocation object::

	navigator.geolocation.getCurrentPosition(onGeolocationSuccess, onError);
	
Again, we have two callback functions that handle what happens when we succesfully get the information and that's it. 

You can start gathering information about geolocation:

1)When the device is loaded.(or create a button event)::

	document.addEventListener("deviceready",onDeviceReady,true);
	function onDeviceReady() {
        navigator.geolocation.getCurrentPosition(onGeolocationSuccess, onError);
    }

2)Check geolocation data within an interval(defined by frequency in this case every 3 seconds.).::

	navigator.geolocation.watchPosition(onGeolocationSuccess, onError, { frequency: 3000 });

Now all we need to define is what happens when we succesfully get the location.::

	 function onGeolocationSuccess(position) {
        var element = document.getElementById('geolocation');
        device_lat = position.coords.latitude ;
        device_long = position.coords.longitude  ;
        element.innerHTML = 'Latitude: '           + position.coords.latitude              + '<br />' +
                            'Longitude: '          + position.coords.longitude             + '<br />' +
                            'Timestamp: '          +                                   position.timestamp          + '<br />';
    $('#map_canvas_2').gmap({'disableDefaultUI':true, 'callback': function(map) {
						var self = this;
						self.watchPosition(function(positionx, status) {
							if ( status === 'OK' ) {
								var latlng = new google.maps.LatLng(position.coords.latitude, position.coords.longitude);
								if ( !self.get('markers').client ) {
									self.addMarker({ 'id': 'client', 'position': latlng, 'bounds': true });
								} else {
									self.get('markers').client.setPosition(latlng);
									map.panTo(latlng);
								}
							}
						});
					}});
    }

This might seem like a rather long function but all PhoneGap provides are these::

	device_lat = position.coords.latitude;
    device_long = position.coords.longitude;

Rest of the code is just Google Maps API magic that turns a div element into a map canvas, drops a marker on your location and centers the map to this marker.

Accessing Native Accelerometer Functions
..................
Accessing acceloremeter information is quite similar to Geolocation, if not almost the same.
You can get the accelerometer either by using:

getAcceleration.::

	navigator.accelerometer.getAcceleration(shakeSuccess, onShakeFail);
	
or by setting a timer and using watchAcceleration.::

	navigator.accelerometer.watchAcceleration(shakeSuccess, onShakeFail, {frequency: 150});
	
And then again we have to define the shakeSuccess callback function. So let's think about this function a little bit. In this demo application we want to retrieve latest upload photos' information from our server and display them on our map. Phonegap by itself does not have a shake event registered so we need to define this ourselves. Luckily, this is just a simple calculation. We need to check how much the device has moved since the last time this function was called and if it is above a certain threshold then we can retrieve the information from the server.
Let's define our variables.::

	var threshold = 4;
  	var lastTime = new Date();
  	var lastX = null;
  	var lastY = null;
  	var lastZ = null;

We can change the threshold value as we like, 4 suits best for my purposes.	Now onward to the function!::

	function shakeSuccess (current) {
  	
    var currentTime, timeDifference, deltaX = 0, deltaY = 0, deltaZ = 0;
 
    if ((lastX === null) && (lastY === null) && (lastZ === null)) {
      lastX = current.x;
      lastY = current.y;
      lastZ = current.z;
      return;
    }
 
    deltaX = Math.abs(lastX - current.x);//compare values
    deltaY = Math.abs(lastY - current.y);
    deltaZ = Math.abs(lastZ - current.z);
    //check if we have passed a threshold
    if (((deltaX > threshold) && (deltaY > threshold)) || ((deltaX > threshold) && (deltaZ > threshold)) || ((deltaY > threshold) && (deltaZ > threshold))) {
      currentTime = new Date();
      timeDifference = currentTime.getTime() - lastTime.getTime();
      
      if (timeDifference > 200) {
        lastTime = new Date();
        var element = document.getElementById('accelerometer');
        element.innerHTML = 'shook' + 
                            'Timestamp: '      +currentTime+ '<br />';
                            
        $.ajax({
		url: "http://169.231.8.99:1337/get_updates",
		data: {jsonData: "{ \"long\": " + device_lat + ", \"lat\":" + device_long+" }"}
		}).done(function(data) {
		
		for (var i= 0;i<data.length;i++)
		{
		 $('#map_canvas_2').addMarker({ 'id': 'client', 'position': data[i].latlng, 'bounds': true });
		}
		});                    
       
      } 
    }
  	};

Again it may seem scary, but as far as PhoneGap goes, it only provides you with the current X,Y,Z values from accelerometer, the rest we have to do ourselves(or seek help in the interwebz!). Again if we are succesfull we make an ajax call and get the latest locations, show them on or map and that's it.!
