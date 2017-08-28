package com.motrss.Fragment;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.motrss.R;
import com.motrss.activity.ApiClient;
import com.motrss.activity.BookUberActivity;
import com.motrss.activity.FirstScreenActivity;
import com.motrss.activity.Main2Activity;
import com.motrss.activity.MainActivity;
import com.motrss.core.UberAuthTokenResponse;
import com.motrss.core.retrofit.response.EstimatedPrice;
import com.motrss.core.retrofit.ApiInterface;
import com.motrss.core.retrofit.RetrofitConfig;
import com.motrss.core.retrofit.request.UberEstimateRequest;
import com.motrss.core.retrofit.response.EstimatePriceGet;
import com.motrss.core.retrofit.response.RequestEstimateFare;
import com.motrss.uber.UberConfig;
import com.uber.sdk.android.core.UberSdk;
import com.uber.sdk.android.core.auth.AccessTokenManager;
import com.uber.sdk.android.core.auth.AuthenticationError;
import com.uber.sdk.android.core.auth.LoginButton;
import com.uber.sdk.android.core.auth.LoginCallback;
import com.uber.sdk.android.core.auth.LoginManager;
import com.uber.sdk.core.auth.AccessToken;
import com.uber.sdk.core.auth.Scope;
import com.uber.sdk.rides.client.SessionConfiguration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentTab extends Fragment {

    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefreshLayout;
    /*  String[] FarePrice={"145-170","115-150","100-120","200-250"};
      String[] Time={"5 min","10 min","20 min","5 min"};
      int[] Image={R.drawable.first,R.drawable.second,R.drawable.third,R.drawable.first};
      String[] place={"Micro","Sedan","Micro","Sedan"};*/
    List<EstimatedPrice> estimatedPriceList;
    RetrofitConfig retrofitConfig;
    ApiInterface apiInterface;
  private   UberConfig uberConfig;
    public FragmentTab() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_tab, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.firstRecyclerView);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);
        estimatedPriceList = new ArrayList<>();
       /* for (int i=0;i<FarePrice.length;i++){
            EstimatedPrice modelClass=new EstimatedPrice(FarePrice[i],place[i],Image[i],Time[i]);
            modelClasses.add(modelClass);
        }*/

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
/**/
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<EstimatePriceGet> call = apiService.getUberEstimate("37.7752315", "-122.418075", "37.7752415", "-122.518075");
        System.out.println("check call--->   " + call.request() + "  //   " + call.toString());
        call.enqueue(new Callback<EstimatePriceGet>() {
            @Override
            public void onResponse(Call<EstimatePriceGet> call, Response<EstimatePriceGet> response) {
                //      System.out.println("Response of estimate price get---- in activity>>>   " + response.);
                //   estimatedPriceList= response.body().getPrice();

            }

            @Override
            public void onFailure(Call<EstimatePriceGet> call, Throwable t) {
                // Log error here since request failed
                System.out.println("=====>>>    " + t.toString());
            }
        });

/**/
        retrofitConfig = new RetrofitConfig(getActivity().getApplicationContext());
        retrofitConfig.changeApiBaseUrl("https://api.uber.com/");
        apiInterface = retrofitConfig.createService(ApiInterface.class);
    /*    apiInterface.getUberAuthToken(this.map).enqueue(new Callback<UberAuthTokenResponse>() {
            @Override
            public void onResponse(Call<UberAuthTokenResponse> call, Response<UberAuthTokenResponse> response) {
                System.out.println("Auth Response---->>>   " + response.body());
            }

            @Override
            public void onFailure(Call<UberAuthTokenResponse> call, Throwable t) {

            }
        });*/
   /*     LinkedHashMap<String, String> map1 = new LinkedHashMap<>();
        map1.put("latitude", "22.7196");
        map1.put("longitude", "75.8577");
   */
        UberEstimateRequest uberEstimateRequest = new UberEstimateRequest();
        uberEstimateRequest.setStart_latitude("37.7752315");
        uberEstimateRequest.setStart_longitude("-122.418075");

        /*uberEstimateRequest.setEnd_latitude("37.7752415");
        uberEstimateRequest.setEnd_longitude("-122.518075");
*/
        System.out.println("uberestimate request--->   " + uberEstimateRequest);

        retrofitConfig.changeApiBaseUrl("https://api.uber.com/");
        apiInterface.getUberEstimate("37.7752315", "-122.418075", "37.7752415", "-122.518075").enqueue(new Callback<EstimatePriceGet>() {
            @Override
            public void onResponse(Call<EstimatePriceGet> call, Response<EstimatePriceGet> response) {
                System.out.println("second call---  --- in activity>>>   " + response.body());
                System.out.println("Response of estimate price get11111--- in activity>>>   " + response.body().getPrice().get(0).getName());
                estimatedPriceList = response.body().getPrice();
                System.out.println("Size of list---   " + estimatedPriceList.size());
                FirstAdapter mAdapter = new FirstAdapter(estimatedPriceList);
                recyclerView.setAdapter(mAdapter);
                // contract.onUberProductsSuccess(response.body());
            }

            @Override
            public void onFailure(Call<EstimatePriceGet> call, Throwable t) {
                System.out.println("ttt---->   " + t.toString());
            }

/*
            @Override
            public void onResponse(Call<ProductsMaster> call, Response<ProductsMaster> response) {

                //  contract.onUberProductsSuccess(response.body().getList());
            }

            @Override
            public void onFailure(Call<ProductsMaster> call, Throwable t) {
                contract.onUberProductsFailure(t.getMessage());
            }*/
        });
        return view;
    }

    public class FirstAdapter extends RecyclerView.Adapter<FirstAdapter.MyViewHolder> {

        private List<EstimatedPrice> moviesList;

        public class MyViewHolder extends RecyclerView.ViewHolder {
            public ImageView image;
            TextView farePrice, time, place, book;

            public MyViewHolder(View view) {
                super(view);
                image = (ImageView) view.findViewById(R.id.imageView);
                farePrice = (TextView) view.findViewById(R.id.fareprice);
                time = (TextView) view.findViewById(R.id.time);
                place = (TextView) view.findViewById(R.id.place);
                book = (TextView) view.findViewById(R.id.book_btn);
            }
        }

        public FirstAdapter(List<EstimatedPrice> moviesList) {
            this.moviesList = moviesList;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.firs_listitems, parent, false);

            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, int position) {
            // ModelClass movie = moviesList.get(position);
            holder.farePrice.setText(moviesList.get(position).getFarePrice());
            holder.time.setText(moviesList.get(position).getETA());
            holder.place.setText(moviesList.get(position).getName());
            holder.book.setTag(moviesList.get(position).getProductId());

            holder.book.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {



                    Intent intent = new Intent(getActivity(), BookUberActivity.class);
                    intent.putExtra("book", "yes");
                    intent.putExtra("product_id",holder.book.getTag().toString() );
                    intent.putExtra("start_latitude", "yes");
                    intent.putExtra("start_longitude", "yes");
                    intent.putExtra("end_latitude", "yes");
                    intent.putExtra("end_longitude", "yes");

                    startActivity(intent);

                    /*Intent i= new Intent(getActivity(), MainActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    getActivity().getBaseContext().startActivity(i);*/
/**/
                   /* retrofitConfig.changeApiBaseUrl("https://api.uber.com/");
                    apiInterface.getRequestEstimateFare("request","37.7752315", "-122.418075", "37.7752415", "-122.518075").enqueue(new Callback<RequestEstimateFare>() {
                        @Override
                        public void onResponse(Call<RequestEstimateFare> call, Response<RequestEstimateFare> response) {
                            System.out.println("getRequestEstimateFare call---  --- in activity>>>   " + response.body() + "   /   " + call.toString());
                            System.out.println("Response of getRequestEstimateFare--- in activity>>>   " + response.body().getFare());
                            // estimatedPriceList = response.body().getFare();
                            System.out.println("Size of list---   " + estimatedPriceList.size());
                            FirstAdapter mAdapter = new FirstAdapter(estimatedPriceList);
                            recyclerView.setAdapter(mAdapter);
                            // contract.onUberProductsSuccess(response.body());
                        }

                        @Override
                        public void onFailure(Call<RequestEstimateFare> call, Throwable t) {
                            System.out.println("ttt---->   " + t.toString());
                        }

                    });*/
               /*     LinkedHashMap<String, String> map1 = new LinkedHashMap<>();
                    map1.put("client_id", "LWOUTh3AUBkVtaI-cK58-t_pspsvRFfk");
                    map1.put("response_type", "code");
                    retrofitConfig.changeApiBaseUrl("https://login.uber.com/oauth/");
                    apiInterface.getUberAuthToken(map1).enqueue(new Callback<UberAuthTokenResponse>() {
                        @Override
                        public void onResponse(Call<UberAuthTokenResponse> call, Response<UberAuthTokenResponse> response) {
                            System.out.println("getRequestEstimateFare call---  --- in activity>>>   " + response.body() + "   /   " + call.toString());
                       //     System.out.println("Response of getRequestEstimateFare--- in activity>>>   " + response.body().getFare());

                        }

                        @Override
                        public void onFailure(Call<UberAuthTokenResponse> call, Throwable t) {
                            System.out.println("ttt---->   " + t.toString());
                        }

                    });*/


                    /**/
                }
            });
            //  holder.image.setImageResource(movie.getImage());
        }

        @Override
        public int getItemCount() {
            return moviesList.size();
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
