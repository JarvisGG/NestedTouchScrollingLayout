package jarvis.com.nestedtouchscrollinglayout;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.TextView;

import jarvis.com.library.NestedTouchScrollingLayout;

/**
 * @author yyf @ Zhihu Inc.
 * @since 10-16-2018
 */
public class ViewAndRecyclerViewActivity extends BaseActivity  {

    private int mInnerItemsCount = 60;

    private RecyclerView mContainerRecycler;

    private NestedTouchScrollingLayout mNestedTouchScrollingLayout;

    private FrameLayout mWrapper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_recyclerview);

        mWrapper = findViewById(R.id.top_wrapper);

        mContainerRecycler = findViewById(R.id.container_rv);
        mContainerRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mContainerRecycler.setAdapter(new InnerAdapter(this, 0x9966CC));

        mContainerRecycler.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.onDraw(c, parent, state);
            }

            @Override
            public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.onDrawOver(c, parent, state);
            }

            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                int pos = mContainerRecycler.getChildAdapterPosition(view);
                if (pos == 0) {
                    outRect.top = 30;
                }
                outRect.bottom = 30;
            }
        });

        mNestedTouchScrollingLayout = findViewById(R.id.wrapper);
        mNestedTouchScrollingLayout.setSheetDirection(NestedTouchScrollingLayout.SheetDirection.BOTTOM);

        mNestedTouchScrollingLayout.registerNestScrollChildCallback(new NestedTouchScrollingLayout.INestChildScrollChange() {
            @Override
            public void onNestChildScrollChange(float deltaY) {
                FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) mWrapper.getLayoutParams();
                params.height = (int) (DisplayUtils.dpToPixel(ViewAndRecyclerViewActivity.this, 400) + deltaY);
                mWrapper.setLayoutParams(params);
            }

            @Override
            public void onNestChildScrollRelease(final float deltaY, final int velocityY) {
                mNestedTouchScrollingLayout.recover(0, new Runnable() {
                    @Override
                    public void run() {
                        Log.i("NestedTouchScrollingLayout ---> ", "deltaY : " + deltaY + " velocityY : " + velocityY);
                    }
                });
            }

            @Override
            public void onFingerUp(float velocityY) {

            }

            @Override
            public void onNestChildHorizationScroll(MotionEvent event, float deltaX, float deltaY) {

            }
        });
    }

    public class InnerAdapter extends RecyclerView.Adapter<InnerViewHolder> {

        private Context mContext;
        private LayoutInflater mInflater;
        private @ColorInt int mBgColor;

        public InnerAdapter(Context context, @ColorInt int color) {
            mContext = context;
            mInflater = LayoutInflater.from(mContext);
            mBgColor = color;
        }

        @NonNull
        @Override
        public InnerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = mInflater.inflate(R.layout.main_item, viewGroup, false);
            return new InnerViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull InnerViewHolder innerViewHolder, int i) {
            innerViewHolder.tv.setText("Jarvis ----> " + i);
        }

        @Override
        public int getItemCount() {
            return mInnerItemsCount;
        }
    }

    public class InnerViewHolder extends RecyclerView.ViewHolder {

        public TextView tv;

        public InnerViewHolder(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.main_tv);
        }
    }

}
