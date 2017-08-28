package com.motrss.Fragment;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lyft.lyftbutton.RideTypeEnum;
import com.lyft.networking.ApiConfig;
import com.lyft.networking.LyftApiFactory;
import com.lyft.networking.apiObjects.CostEstimate;
import com.lyft.networking.apiObjects.CostEstimateResponse;
import com.lyft.networking.apis.LyftPublicApi;
import com.motrss.R;
import com.motrss.model.LyftProductDetails;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LyftProduct extends Fragment {

    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefreshLayout;
    private ApiConfig apiConfig1;

    ArrayList<LyftProductDetails> lyftProductDetail ;;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment_tab, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.firstRecyclerView);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
      //  recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        lyftProductDetail= new ArrayList<LyftProductDetails>() ;;
        ApiConfig apiConfig = new ApiConfig.Builder()
                .setClientId("TyzaevHZs0sL")
                .setClientToken("NZzosQq2/p99oXzTtIZEBt2OQPzzBUM5jn9KIJKOPI6/ySs8pz8xAWDpcIEK8T+VWbInjLJU0ytZTvY/T+hKS2/TgHi3MvOWIlSVrK9kM9NUTeY4VjlNO4Y=")
                .build();

        // apiConfig1 = new ApiConfig("TyzaevHZs0sL","NZzosQq2/p99oXzTtIZEBt2OQPzzBUM5jn9KIJKOPI6/ySs8pz8xAWDpcIEK8T+VWbInjLJU0ytZTvY/T+hKS2/TgHi3MvOWIlSVrK9kM9NUTeY4VjlNO4Y=");
        LyftPublicApi lyftPublicApi = new LyftApiFactory(apiConfig).getLyftPublicApi();
        Call<CostEstimateResponse> rideTypesCall = lyftPublicApi.getCosts(37.7833, -122.4167, RideTypeEnum.CLASSIC.toString(), 37.7794703, -122.4233223);

        rideTypesCall.enqueue(new Callback<CostEstimateResponse>() {


            @Override
            public void onResponse(Call<CostEstimateResponse> call, Response<CostEstimateResponse> response) {
                CostEstimateResponse result = response.body();
                for (CostEstimate costEstimate : result.cost_estimates) {
                    lyftProductDetail.add(new LyftProductDetails(costEstimate.display_name, String.valueOf(costEstimate.estimated_cost_cents_min / 100), String.valueOf(costEstimate.estimated_cost_cents_max / 100), String.valueOf(costEstimate.estimated_duration_seconds / 60), costEstimate.ride_type));
                    Log.d("MyApp", "Min: " + String.valueOf(costEstimate.estimated_cost_cents_min / 100) + "$");
                    Log.d("MyApp", "Max: " + String.valueOf(costEstimate.estimated_cost_cents_max / 100) + "$");
                    Log.d("MyApp", "Distance: " + String.valueOf(costEstimate.estimated_distance_miles) + " miles");
                    Log.d("MyApp", "Duration: " + String.valueOf(costEstimate.estimated_duration_seconds / 60) + " minutes");
                }

                FirstAdapter mAdapter = new FirstAdapter(lyftProductDetail);
                recyclerView.setAdapter(mAdapter);
             /*   for(RideType rideType : result.ride_types) {
                    Log.d("MyApp", rideType.display_name);
result.
                }*/
            }

            @Override
            public void onFailure(Call<CostEstimateResponse> call, Throwable t) {
                Log.d("MyApp", t.toString());
            }


        });

        return view;
    }


    public class FirstAdapter extends RecyclerView.Adapter<FirstAdapter.MyViewHolder> {

        private List<LyftProductDetails> lyftProductDetail;

        public class MyViewHolder extends RecyclerView.ViewHolder {
            public ImageView image;
            TextView farePrice, time, place;

            public MyViewHolder(View view) {
                super(view);
                image = (ImageView) view.findViewById(R.id.imageView);
                farePrice = (TextView) view.findViewById(R.id.fareprice);
                time = (TextView) view.findViewById(R.id.time);
                place = (TextView) view.findViewById(R.id.place);

            }
        }

        public FirstAdapter(List<LyftProductDetails> lyftProductDetail) {
            this.lyftProductDetail = lyftProductDetail;

            System.out.println("adapter--->   "+lyftProductDetail.size());
        }


        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.firs_listitems, parent, false);

            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            // ModelClass movie = moviesList.get(position);
            holder.farePrice.setText(lyftProductDetail.get(position).getMinCost() + " - " + lyftProductDetail.get(position).getMaxCost());
            holder.time.setText(lyftProductDetail.get(position).getDuration());
            holder.place.setText(lyftProductDetail.get(position).getDisplayName());
            //  holder.image.setImageResource(movie.getImage());
        }


        @Override
        public int getItemCount() {
            return lyftProductDetail.size();
        }
    }


    public class DividerItemDecoration extends RecyclerView.ItemDecoration {

        private final int[] ATTRS = new int[]{
                android.R.attr.listDivider
        };

        public static final int HORIZONTAL_LIST = LinearLayoutManager.HORIZONTAL;

        public static final int VERTICAL_LIST = LinearLayoutManager.VERTICAL;

        private Drawable mDivider;

        private int mOrientation;

        public DividerItemDecoration(Context context, int orientation) {
            final TypedArray a = context.obtainStyledAttributes(ATTRS);
            mDivider = a.getDrawable(0);
            a.recycle();
            setOrientation(orientation);
        }

        public void setOrientation(int orientation) {
            if (orientation != HORIZONTAL_LIST && orientation != VERTICAL_LIST) {
                throw new IllegalArgumentException("invalid orientation");
            }
            mOrientation = orientation;
        }

        @Override
        public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
            if (mOrientation == VERTICAL_LIST) {
                drawVertical(c, parent);
            } else {
                drawHorizontal(c, parent);
            }
        }

        public void drawVertical(Canvas c, RecyclerView parent) {
            final int left = parent.getPaddingLeft();
            final int right = parent.getWidth() - parent.getPaddingRight();

            final int childCount = parent.getChildCount();
            for (int i = 0; i < childCount; i++) {
                final View child = parent.getChildAt(i);
                final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                        .getLayoutParams();
                final int top = child.getBottom() + params.bottomMargin;
                final int bottom = top + mDivider.getIntrinsicHeight();
                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(c);
            }
        }

        public void drawHorizontal(Canvas c, RecyclerView parent) {
            final int top = parent.getPaddingTop();
            final int bottom = parent.getHeight() - parent.getPaddingBottom();

            final int childCount = parent.getChildCount();
            for (int i = 0; i < childCount; i++) {
                final View child = parent.getChildAt(i);
                final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                        .getLayoutParams();
                final int left = child.getRight() + params.rightMargin;
                final int right = left + mDivider.getIntrinsicHeight();
                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(c);
            }
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            if (mOrientation == VERTICAL_LIST) {
                outRect.set(0, 0, 0, mDivider.getIntrinsicHeight());
            } else {
                outRect.set(0, 0, mDivider.getIntrinsicWidth(), 0);
            }
        }
    }


}
