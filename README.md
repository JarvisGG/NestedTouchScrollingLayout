NestedTouchScrollingLayout
===========================
[![](https://jitpack.io/v/JarvisGG/NestedTouchScrollingLayout.svg)](https://jitpack.io/#JarvisGG/NestedTouchScrollingLayout)
![Platform](https://img.shields.io/badge/platform-android-blue.svg)
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
|normal|webview|
|![demo3](https://github.com/JarvisGG/NestedTouchScrollingLayout/blob/master/captures/demo3.gif "demo3")|![demo4](https://github.com/JarvisGG/NestedTouchScrollingLayout/blob/master/captures/demo4.gif "demo4")|
|bottomsheet normal|bottomsheet appbarlayout|
|![demo8](https://github.com/JarvisGG/NestedTouchScrollingLayout/blob/master/captures/demo8.gif "demo3")|![demo9](https://github.com/JarvisGG/NestedTouchScrollingLayout/blob/master/captures/demo9.gif "demo4")|
|webview recyclerview|imageview recyclerview|
|![demo6](https://github.com/JarvisGG/NestedTouchScrollingLayout/blob/master/captures/demo6.gif "demo4")||
|scene1|scene2|

### Usage example

#### normal use
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
// 设置手指下拉阻尼
mNestedTouchScrollingLayout.setDampingDown(2.0f / 5);
// 设置手指上拉阻尼
mNestedTouchScrollingLayout.setDampingUp(3.0f / 5);

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
	// 手指抬起时机
	@Override
	public void onFingerUp(float velocityY) {

	}

	// 横向拖拽
	@Override
	public void onNestChildHorizationScroll(MotionEvent event, float deltaX, float deltaY) {
	
	}
	
	// 当前 SheetView 运动状态
	@Override
    public void onNestScrollingState(int state) {

    }
});
```
#### bottomsheet use
``` xml
 <jarvis.com.library.NestedTouchScrollingLayout
	android:id="@+id/wrapper"
	android:layout_marginTop="30dp"
	android:layout_width="match_parent"
	android:layout_height="match_parent">

	<android.support.v7.widget.RecyclerView
		android:background="#fff"
		android:id="@+id/container_rv"
		android:layout_width="match_parent"
		android:layout_height="match_parent" />

</jarvis.com.library.NestedTouchScrollingLayout>
```
``` java
// 临界速度，根据业务而定
public static int mVelocityYBound = 1300;

// 规定 sheetView 弹起方向
mNestedTouchScrollingLayout.setSheetDirection(NestedTouchScrollingLayout.SheetDirection.BOTTOM);

mNestedTouchScrollingLayout.registerNestScrollChildCallback(new NestedTouchScrollingLayout.INestChildScrollChange() {
	
	
	@Override
	public void onNestChildScrollChange(float deltaY, float velocityY) {

	}

	@Override
	public void onNestChildScrollRelease(float deltaY, int velocityY) {
		int totalYRange = mNestedTouchScrollingLayout.getMeasuredHeight();
		int helfLimit = (totalYRange - DisplayUtils.dpToPixel(BottomSheetActivity.this, 400)) / 2;
		int hideLimit = totalYRange - DisplayUtils.dpToPixel(BottomSheetActivity.this, 400) / 2;
		int helfHeight = totalYRange - DisplayUtils.dpToPixel(BottomSheetActivity.this, 400);
		if (velocityY > mVelocityYBound && velocityY > 0) {
			if (Math.abs(deltaY) > helfHeight) {
				mNestedTouchScrollingLayout.hiden();
			} else {
				mNestedTouchScrollingLayout.peek(mNestedTouchScrollingLayout.getMeasuredHeight() - DisplayUtils.dpToPixel(BottomSheetActivity.this,400));
			}
		} else if (velocityY < -mVelocityYBound && velocityY < 0) {
			if (Math.abs(deltaY) < helfHeight) {
				mNestedTouchScrollingLayout.expand();
			} else {
				mNestedTouchScrollingLayout.peek(mNestedTouchScrollingLayout.getMeasuredHeight() - DisplayUtils.dpToPixel(BottomSheetActivity.this,400));
			}
		} else {
			if (Math.abs(deltaY) > hideLimit) {
				mNestedTouchScrollingLayout.hiden();
			} else if (Math.abs(deltaY) > helfLimit) {
				mNestedTouchScrollingLayout.peek(mNestedTouchScrollingLayout.getMeasuredHeight() - DisplayUtils.dpToPixel(BottomSheetActivity.this, 400));
			} else {
				mNestedTouchScrollingLayout.expand();
			}
		}
	}

	@Override
	public void onFingerUp(float velocityY) {

	}

	@Override
	public void onNestChildHorizationScroll(MotionEvent event, float deltaX, float deltaY) {

	}
});
```

### Next
- [x] hold all touch event, and dispath touch event to child view.
- [x] fix ACTION_UP dispatch child click event.
- [x] support bottomsheet.
- [x] support CoordinatorLayout (AppbarLayout).
- [x] add damping draging.
- [ ] add blur cover.

### Usage
方式 1:
``` Gradle
repositories {
    // ...
    maven { url "https://jitpack.io" }
}

dependencies {
    implementation 'com.github.JarvisGG:NestedTouchScrollingLayout:1.2.4'
}
```
方式 2:
``` Gradle
repositories {
    // ...
    jcenter()
}
dependencies {
    implementation 'com.jarvis.library.NestedTouchScrollingLayout:library:1.2.4'
}
```


> Tip
>> star star star ！！！！:blush:

### LICENSE
![](https://upload.wikimedia.org/wikipedia/commons/thumb/f/f8/License_icon-mit-88x31-2.svg/128px-License_icon-mit-88x31-2.svg.png)

This library is under the MIT license. check the [LICENSE](https://opensource.org/licenses/MIT) file for more detail.

Copyright (c) 2018 Jarvis
