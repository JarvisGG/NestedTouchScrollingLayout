package jarvis.com.nestedtouchscrollinglayout;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainActivity extends BaseActivity {

    Class[] demoClasses = new Class[] {
            RecyclerViewActivity.class,
            WebViewActivity.class,
            ViewPagerActivity.class,
            BottomSheetActivity.class,
            AppbarLayoutActivity.class
    };

    private RecyclerView mRecycler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecycler = findViewById(R.id.main_rv);

        mRecycler.addItemDecoration(new RecyclerView.ItemDecoration() {
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

        mRecycler.setAdapter(new InnerAdapter(this));
        mRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    class InnerAdapter extends RecyclerView.Adapter<InnerViewHolder> {

        private Context mContext;
        private LayoutInflater mInflater;

        public InnerAdapter(Context context) {
            this.mContext = context;
            this.mInflater = LayoutInflater.from(mContext);
        }

        @NonNull
        @Override
        public InnerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = mInflater.inflate(R.layout.main_item, viewGroup, false);
            return new InnerViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull InnerViewHolder innerViewHolder, final int i) {
            innerViewHolder.tv.setText(demoClasses[i].getSimpleName());
            innerViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this, demoClasses[i]);
                    MainActivity.this.startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return demoClasses.length;
        }
    }

    class InnerViewHolder extends RecyclerView.ViewHolder {

        TextView tv;
        InnerViewHolder(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.main_tv);
        }
    }
}
