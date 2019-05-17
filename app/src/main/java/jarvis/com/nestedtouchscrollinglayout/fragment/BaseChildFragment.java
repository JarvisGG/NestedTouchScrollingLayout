package jarvis.com.nestedtouchscrollinglayout.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import jarvis.com.library.NestedTouchScrollingLayout;

/**
 * @author Jarvis.
 * @since 10-16-2018
 */
public abstract class BaseChildFragment extends Fragment {

    private NestedTouchScrollingLayout mParent;

    private ViewPager mPager;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mParent = (NestedTouchScrollingLayout) getChildView().getParent();
        mParent.registerNestScrollChildCallback(new NestedTouchScrollingLayout.INestChildScrollChange() {
            @Override
            public void onNestChildScrollChange(float deltaY, float velocityY) {

            }

            @Override
            public void onNestChildScrollRelease(float deltaY, int velocityY) {

                int targetPos = mPager.getCurrentItem();
                if (Math.abs(deltaY) > 325) {
                    if (deltaY < 0 && targetPos + 1 < mPager.getAdapter().getCount()) {
                        targetPos += 1;
                    } else if (deltaY > 0 && targetPos - 1 >= 0) {
                        targetPos -= 1;
                    }
                }
                mPager.setCurrentItem(targetPos);
            }

            @Override
            public void onFingerUp(float velocityY) {

            }

            @Override
            public void onNestChildHorizationScroll(MotionEvent event, float deltaX, float deltaY) {

            }

            @Override
            public void onNestScrollingState(int state) {

            }
        });

        mPager = (ViewPager) getChildView().getParent().getParent();
    }

    abstract View getChildView();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mParent.clearNestScrollChildCallback();
    }
}
