package jarvis.com.nestedtouchscrollinglayout;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import jarvis.com.library.NestedTouchScrollingLayout;

/**
 * @author Jarvis.
 * @since 12-05-2018
 */
public class AppbarLayoutActivity extends BaseActivity {
    private int mContainerItemsCount = 20;
    private int mInnerItemsCount = 30;

    public static int mHalfWindowHeight = 400; // dp

    public static int mVelocityYBound = 1300;

    private RecyclerView mContainerRecycler;

    private NestedTouchScrollingLayout mNestedTouchScrollingLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_applayout);

        findViewById(R.id.btn_open).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mNestedTouchScrollingLayout.expand();
            }
        });

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
                outRect.bottom = 30;
            }
        });

        mNestedTouchScrollingLayout = findViewById(R.id.wrapper);
        mNestedTouchScrollingLayout.registerNestScrollChildCallback(new NestedTouchScrollingLayout.INestChildScrollChange() {
            @Override
            public void onNestChildScrollChange(float deltaY, float velocityY) {

            }

            @Override
            public void onNestChildScrollRelease(final float deltaY, final int velocityY) {
                int totalYRange = mNestedTouchScrollingLayout.getMeasuredHeight();

                int helfLimit = (totalYRange - DisplayUtils.dpToPixel(AppbarLayoutActivity.this, mHalfWindowHeight)) / 2;

                int hideLimit = totalYRange - DisplayUtils.dpToPixel(AppbarLayoutActivity.this, mHalfWindowHeight) / 2;

                int helfHeight = totalYRange - DisplayUtils.dpToPixel(AppbarLayoutActivity.this, mHalfWindowHeight);

                if (velocityY > mVelocityYBound && velocityY > 0) {
                    if (Math.abs(deltaY) > helfHeight) {
                        mNestedTouchScrollingLayout.hiden();
                    } else {
                        mNestedTouchScrollingLayout.peek(mNestedTouchScrollingLayout.getMeasuredHeight() - DisplayUtils.dpToPixel(AppbarLayoutActivity.this,400));
                    }
                } else if (velocityY < -mVelocityYBound && velocityY < 0) {
                    if (Math.abs(deltaY) < helfHeight) {
                        mNestedTouchScrollingLayout.expand();
                    } else {
                        mNestedTouchScrollingLayout.peek(mNestedTouchScrollingLayout.getMeasuredHeight() - DisplayUtils.dpToPixel(AppbarLayoutActivity.this,400));
                    }
                } else {
                    if (Math.abs(deltaY) > hideLimit) {
                        mNestedTouchScrollingLayout.hiden();
                    } else if (Math.abs(deltaY) > helfLimit) {
                        mNestedTouchScrollingLayout.peek(mNestedTouchScrollingLayout.getMeasuredHeight() - DisplayUtils.dpToPixel(AppbarLayoutActivity.this, 400));
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

            @Override
            public void onNestScrollingState(int state) {

            }
        });

        mNestedTouchScrollingLayout.setSheetDirection(NestedTouchScrollingLayout.SheetDirection.BOTTOM);
        mNestedTouchScrollingLayout
                .post(new Runnable() {
                    @Override
                    public void run() {
                        mNestedTouchScrollingLayout.recover(mNestedTouchScrollingLayout.getMeasuredHeight(), null, 0);
                    }
                });
    }

    class ContainerAdapter extends RecyclerView.Adapter<AppbarLayoutActivity.ContainerViewHolder> {

        private Context mContext;
        private LayoutInflater mInflater;

        public ContainerAdapter(Context context) {
            this.mContext = context;
            this.mInflater = LayoutInflater.from(mContext);
        }

        @NonNull
        @Override
        public AppbarLayoutActivity.ContainerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = mInflater.inflate(R.layout.recycer_item, viewGroup, false);
            return new AppbarLayoutActivity.ContainerViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull AppbarLayoutActivity.ContainerViewHolder containerViewHolder, int i) {
            containerViewHolder.mRecycler.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
            switch (i % 5) {
                case 0:
                    containerViewHolder.mRecycler.setAdapter(new AppbarLayoutActivity.InnerAdapter(mContext, 0xFFCCFF));
                    break;
                case 1:
                    containerViewHolder.mRecycler.setAdapter(new AppbarLayoutActivity.InnerAdapter(mContext, 0x9966CC));
                    break;
                case 2:
                    containerViewHolder.mRecycler.setAdapter(new AppbarLayoutActivity.InnerAdapter(mContext, 0x33FF33));
                    break;
                case 3:
                    containerViewHolder.mRecycler.setAdapter(new AppbarLayoutActivity.InnerAdapter(mContext, 0x33FFFF));
                    break;
                case 4:
                    containerViewHolder.mRecycler.setAdapter(new AppbarLayoutActivity.InnerAdapter(mContext, 0xFFFF00));
                    break;
                default:
                    break;
            }

            containerViewHolder.mRecycler.addItemDecoration(new RecyclerView.ItemDecoration() {
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
                    outRect.bottom = 10;
                }
            });

        }

        @Override
        public int getItemCount() {
            return mContainerItemsCount;
        }
    }

    class ContainerViewHolder extends RecyclerView.ViewHolder {

        public RecyclerView mRecycler;

        public ContainerViewHolder(@NonNull View itemView) {
            super(itemView);
            mRecycler = itemView.findViewById(R.id.recycler_id);
        }
    }



    public class InnerAdapter extends RecyclerView.Adapter<AppbarLayoutActivity.InnerViewHolder> {

        private Context mContext;
        private LayoutInflater mInflater;
        private @ColorInt
        int mBgColor;

        public InnerAdapter(Context context, @ColorInt int color) {
            mContext = context;
            mInflater = LayoutInflater.from(mContext);
            mBgColor = color;
        }

        @NonNull
        @Override
        public AppbarLayoutActivity.InnerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = mInflater.inflate(R.layout.main_item, viewGroup, false);
            return new AppbarLayoutActivity.InnerViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull AppbarLayoutActivity.InnerViewHolder innerViewHolder, int i) {
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