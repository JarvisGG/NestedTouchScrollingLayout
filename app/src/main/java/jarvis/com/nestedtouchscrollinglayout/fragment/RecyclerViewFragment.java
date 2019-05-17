package jarvis.com.nestedtouchscrollinglayout.fragment;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import jarvis.com.library.NestedTouchScrollingLayout;
import jarvis.com.nestedtouchscrollinglayout.R;

/**
 * @author Jarvis.
 * @since 10-16-2018
 */
public class RecyclerViewFragment extends BaseChildFragment {

    public static final String ITEM_COUNT = "item_count";

    private int mInnerItemsCount = 23;

    private RecyclerView mContainerRecycler;

    private NestedTouchScrollingLayout mNestedTouchScrollingLayout;

    private Bundle arg;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        arg = getArguments();
        mInnerItemsCount = arg.getInt(ITEM_COUNT, 0);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recyclerview, container, false);
        mContainerRecycler = view.findViewById(R.id.container_rv);
        mNestedTouchScrollingLayout = view.findViewById(R.id.fragment_wrapper);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mNestedTouchScrollingLayout.registerNestScrollChildCallback(new NestedTouchScrollingLayout.INestChildScrollChange() {
            @Override
            public void onNestChildScrollChange(float deltaY, float velocityY) {

            }

            @Override
            public void onNestChildScrollRelease(float deltaY, int velocityY) {
                mNestedTouchScrollingLayout.recover(0, null, 300);
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

        mContainerRecycler.setLayoutManager(new LinearLayoutManager(RecyclerViewFragment.this.getContext(), LinearLayoutManager.VERTICAL, false));
        mContainerRecycler.setAdapter(new InnerAdapter(RecyclerViewFragment.this.getContext(), 0x9966CC));

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
    }

    @Override
    View getChildView() {
        return mContainerRecycler;
    }

    public class InnerAdapter extends RecyclerView.Adapter<InnerViewHolder> {

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

    public static RecyclerViewFragment newInstance(Bundle args) {
        RecyclerViewFragment fragment = new RecyclerViewFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
