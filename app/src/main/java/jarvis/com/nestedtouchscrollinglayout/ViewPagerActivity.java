package jarvis.com.nestedtouchscrollinglayout;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import jarvis.com.nestedtouchscrollinglayout.fragment.RecyclerViewFragment;
import jarvis.com.nestedtouchscrollinglayout.fragment.WebViewFragment;

/**
 * @author yyf @ Zhihu Inc.
 * @since 10-16-2018
 */
public class ViewPagerActivity extends BaseActivity {

    private ViewPager mPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager);

        List<Fragment> list = new ArrayList<>();

        Bundle bundle1 = new Bundle();
        bundle1.putString(WebViewFragment.URL, "http://m.meten.com/xxl/adult.html");
        bundle1.putInt(WebViewFragment.POSITION, 0);
        Fragment fg1 = WebViewFragment.newInstance(bundle1);

        Bundle bundle2 = new Bundle();
        bundle2.putInt(RecyclerViewFragment.ITEM_COUNT, 30);
        Fragment fg2 = RecyclerViewFragment.newInstance(bundle2);


        Bundle bundle3 = new Bundle();
        bundle3.putString(WebViewFragment.URL, "http://blood.sdo.com/web3/mobile/");
        bundle3.putInt(WebViewFragment.POSITION, 0);
        Fragment fg3 = WebViewFragment.newInstance(bundle3);

        Bundle bundle4 = new Bundle();
        bundle4.putInt(RecyclerViewFragment.ITEM_COUNT, 18);
        Fragment fg4 = RecyclerViewFragment.newInstance(bundle4);


        list.add(fg1);
        list.add(fg2);
        list.add(fg3);
        list.add(fg4);

        mPager = findViewById(R.id.container_vp);

        mPager.setPageTransformer(true, new ViewPager.PageTransformer() {
            @Override
            public void transformPage(@NonNull View view, float position) {
                int pageWidth = view.getWidth();
                int pageHeight = view.getHeight();
                if (position < -1) {
                    view.setAlpha(0);
                } else if (position <= 1) {
                    view.setAlpha(1);
                    view.setTranslationX(pageWidth * -position);
                    float yPosition = position * pageHeight;
                    view.setTranslationY(yPosition);
                } else {
                    view.setAlpha(0);
                }
            }
        });
        mPager.setOverScrollMode(View.OVER_SCROLL_NEVER);



        InnerAdapter adapter = new InnerAdapter(getSupportFragmentManager(), list);
        mPager.setAdapter(adapter);
    }

    class InnerAdapter extends FragmentStatePagerAdapter {

        private List<Fragment> mList;

        public InnerAdapter(FragmentManager fm, List<Fragment> list) {
            super(fm);
            mList = list;
        }

        @Override
        public Fragment getItem(int position) {
            return mList.get(position);
        }

        @Override
        public int getCount() {
            return mList.size();
        }
    }
}
