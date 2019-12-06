
# RxNetworkInfo
Observe Android network connectivity state as Rx Streams

## Installation 

**gradle: **

**Step 1.**  Add the JitPack repository to your build file


Add it in your root build.gradle at the end of repositories:

```css
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

**Step 2.**  Add the dependency

```css
	dependencies {
	        implementation 'com.github.Oziomajnr:RxNetworkInfo:0.0.1'
```
      
**maven:**   

**Step 1.**  Add the JitPack repository to your build file


```markup
	<repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
	</repositories>
```

**Step 2.**  Add the dependency

```markup
	<dependency>
	    <groupId>com.github.Oziomajnr</groupId>
	    <artifactId>RxNetworkInfo</artifactId>
	    <version>0.0.1</version>
	</dependency>

```

## How to use

Get the instance of `InternetConnectionHelper`  and subscribe to the internetConnected Flowable

e.g 

    disposable.add(internetConnectionHelper.internetConnectionAvailable.observeOn(  
        AndroidSchedulers.mainThread()  
    ).subscribe {  
      Toast.makeText(this, it.toString(), Toast.LENGTH_LONG).show()  
        })
See [Demo Activity](https://github.com/Oziomajnr/RxNetworkInfo/blob/master/app/src/main/java/ogbe/ozioma/com/rxnetworkinfo/MainActivity.kt) for a concrete example 

 


 
