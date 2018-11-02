NestedTouchScrollingLayout
===========================
[![](https://jitpack.io/v/JarvisGG/NestedTouchScrollingLayout.svg)](https://jitpack.io/#JarvisGG/NestedTouchScrollingLayout)
![Platform](https://img.shields.io/badge/platform-Androd-green.svg)
![SDK](https://img.shields.io/badge/SDK-12%2B-blue.svg)
[![](https://img.shields.io/badge/Author-JarvisGG-7AD6FD.svg)](http:\//jarvisgg.github.io/)

This layout is used to support dispatch touch event.
There has some example gif~

主要用来做 View 的无缝拖拽，详细效果看 gif～
****
	
|Author|Jarvis|
|---|---
|E-mail|yang4130qq@gmail.com


****
## 效果
|![demo1](https://github.com/JarvisGG/NestedTouchScrollingLayout/blob/master/captures/demo1.gif "demo1")|![demo2](https://github.com/JarvisGG/NestedTouchScrollingLayout/blob/master/captures/demo2.gif "demo2")|
|---|---|

###Usage example

``` XML
<jarvis.com.library.NestedTouchScrollingLayout
    android:id="@+id/wrapper"
    android:layout_gravity="center"
    android:layout_width="match_parent"
    android:layout_height="wrap_content">

    <android.support.v7.widget.RecyclerView
        android:id="@+id/container_rv"
        android:layout_width="match_parent"
        android:layout_height="400dp"
        android:background="#fff"
        android:overScrollMode="always">
    </android.support.v7.widget.RecyclerView>

</jarvis.com.library.NestedTouchScrollingLayout>
```

``` Java
mNestedTouchScrollingLayout.registerNestScrollChildCallback(new NestedTouchScrollingLayout.INestChildScrollChange() {
        
        // 当前 Layout 偏移距离
	@Override
	public void onNestChildScrollChange(float deltaY) {

	}
	
	// finger 脱离屏幕 Layout 偏移量，以及当前 Layout 的速度
	@Override
	public void onNestChildScrollRelease(final float deltaY, final int velocityY) {
		mNestedTouchScrollingLayout.recover(0, new Runnable() {
			@Override
			public void run() {
				Log.i("NestedTouchScrollingLayout ---> ", "deltaY : " + deltaY + " velocityY : " + velocityY);
			}
		});
	}

	// 当前是不是横向拖拽
	@Override
	public void onNestChildHorizationScroll(boolean show) {
	
	}
});
```

### Next
- [x] hold all touch event, and dispath touch event to child view.
- [ ] support CoordinatorLayout (AppbarLayout).
- [ ] add damping draging.

### Usage
方式 1:
``` Gradle
repositories {
    // ...
    maven { url "https://jitpack.io" }
}

dependencies {
    implementation 'com.github.JarvisGG:NestedTouchScrollingLayout:v1.0.0'
}
```
方式 2:
``` Gradle
repositories {
    // ...
    jcenter()
}
dependencies {
    implementation 'com.jarvis.library.NestedTouchScrollingLayout:library:1.0.0'
}
```


> Tip
>> star star star ！！！！:blush:

### LICENSE
![](https://upload.wikimedia.org/wikipedia/commons/thumb/f/f8/License_icon-mit-88x31-2.svg/128px-License_icon-mit-88x31-2.svg.png)

This library is under the MIT license. check the [LICENSE](https://opensource.org/licenses/MIT) file for more detail.

Copyright (c) 2018 Jarvis
