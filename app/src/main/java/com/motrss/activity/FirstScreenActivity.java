package com.motrss.activity;

import android.content.Intent;
import android.graphics.Movie;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.motrss.Fragment.FragmentTab;
import com.motrss.Fragment.LyftProduct;
import com.motrss.R;
import com.motrss.core.retrofit.ApiInterface;
import com.motrss.core.retrofit.RetrofitConfig;
import com.motrss.core.retrofit.response.EstimatePriceGet;
import com.motrss.uber.UberConfig;
import com.uber.sdk.android.core.UberSdk;
import com.uber.sdk.android.core.auth.AccessTokenManager;
import com.uber.sdk.android.core.auth.AuthenticationError;
import com.uber.sdk.android.core.auth.LoginCallback;
import com.uber.sdk.android.core.auth.LoginManager;
import com.uber.sdk.core.auth.AccessToken;
import com.uber.sdk.core.auth.Scope;
import com.uber.sdk.rides.client.SessionConfiguration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FirstScreenActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_screen);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
       tabLayout.setupWithViewPager(viewPager);
        Window window = getWindow();

// clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

// finally change the color
        window.setStatusBarColor(ContextCompat.getColor(getApplicationContext(),R.color.colorStatus));
        RetrofitConfig retrofitConfig = new RetrofitConfig(this);
        retrofitConfig.changeApiBaseUrl("https://api.uber.com/");
        ApiInterface apiInterface = retrofitConfig.createService(ApiInterface.class);
        apiInterface.getUberEstimate("37.7752315","-122.418075","37.7752415","-122.518075").enqueue(new Callback<EstimatePriceGet>() {
            @Override
            public void onResponse(Call<EstimatePriceGet> call, Response<EstimatePriceGet> response) {
                System.out.println("second call---  --- in activity>>>   " + response.body());
             /*   System.out.println("Response of estimate price get---- in activity>>>   " + response.body());
               estimatedPriceList = response.body().getPrice();
                System.out.println("Size of list---   "+estimatedPriceList.size());
                FirstAdapter mAdapter = new FirstAdapter( estimatedPriceList);
                recyclerView.setAdapter(mAdapter);*/
                // contract.onUberProductsSuccess(response.body());
            }

            @Override
            public void onFailure(Call<EstimatePriceGet> call, Throwable t) {
                System.out.println("ttt---->   "+t.toString());
            }

        });


    }
    public class ViewPagerAdapter extends FragmentPagerAdapter {

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            if (position == 0)
            {
                fragment = new FragmentTab();
            }
            else if (position == 1)
            {
                fragment = new LyftProduct();
            }

            return fragment;
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            String title = null;
            if (position == 0)
            {
                title = "Uber Rides";
            }
            else if (position == 1)
            {
                title = "Lyft Rides";
            }
            return title;
        }
    }

    UberConfig uberConfig = new UberConfig(FirstScreenActivity.this,FirstScreenActivity.this);
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        uberConfig.getLoginManager().onActivityResult(FirstScreenActivity.this, requestCode, resultCode, data);
    }

}
