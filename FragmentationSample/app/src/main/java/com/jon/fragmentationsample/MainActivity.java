package com.jon.fragmentationsample;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.jon.fragmentationsample.base.BaseMainFragment;
import com.jon.fragmentationsample.base.MySupportActivity;
import com.jon.fragmentationsample.base.MySupportFragment;
import com.jon.fragmentationsample.ui.fragment.account.LoginFragment;
import com.jon.fragmentationsample.ui.fragment.discover.DiscoverFragment;
import com.jon.fragmentationsample.ui.fragment.home.HomeFragment;
import com.jon.fragmentationsample.ui.fragment.shop.ShopFragment;

import me.yokeyword.fragmentation.ISupportFragment;

public class MainActivity extends MySupportActivity implements NavigationView.OnNavigationItemSelectedListener, BaseMainFragment.OnFragmentOpenDrawerListener
        , LoginFragment.OnLoginSuccessListener {

    public static final String TAG = MainActivity.class.getSimpleName();

    // 再点一次退出程序时间设置
    private static final long WAIT_TIME = 2000L;
    private long TOUCH_TIME = 0;

    private DrawerLayout mDrawer;
    private NavigationView mNavigationView;
    private TextView mTvName;   // NavigationView上的名字
    private ImageView mImgNav;  // NavigationView上的头像

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MySupportFragment fragment = findFragment(HomeFragment.class);
        if (fragment == null) {
            loadRootFragment(R.id.fl_container, HomeFragment.newInstance());
        }

        initView();
    }

    private void initView() {
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        mNavigationView = (NavigationView) findViewById(R.id.nav_view);
        mNavigationView.setNavigationItemSelectedListener(this);
        mNavigationView.setCheckedItem(R.id.nav_home);

        LinearLayout llNavHeader = (LinearLayout) mNavigationView.getHeaderView(0);
        mTvName = (TextView) llNavHeader.findViewById(R.id.tv_name);
        mImgNav = (ImageView) llNavHeader.findViewById(R.id.img_nav);
        llNavHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawer.closeDrawer(GravityCompat.START);
                mDrawer.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                       // goLogin();
                    }
                }, 250);
            }
        });

    }

    /**
     * 打开抽屉
     */
    @Override
    public void onOpenDrawer() {
        if (!mDrawer.isDrawerOpen(GravityCompat.START)) {
            mDrawer.openDrawer(GravityCompat.START);
        }
    }


    @Override
    public boolean onNavigationItemSelected(final MenuItem item) {
        mDrawer.closeDrawer(GravityCompat.START);

        mDrawer.postDelayed(new Runnable() {
            @Override
            public void run() {
                int id = item.getItemId();

                final ISupportFragment topFragment = getTopFragment();
                MySupportFragment myHome = (MySupportFragment) topFragment;

                if (id == R.id.nav_home) {

                    HomeFragment fragment = findFragment(HomeFragment.class);
                    Bundle newBundle = new Bundle();
                    newBundle.putString("from", "From:" + topFragment.getClass().getSimpleName());
                    fragment.putNewBundle(newBundle);

                    myHome.start(fragment, ISupportFragment.SINGLETASK);
                } else if (id == R.id.nav_discover) {
                    DiscoverFragment fragment = findFragment(DiscoverFragment.class);
                    if (fragment == null) {
                        myHome.startWithPopTo(DiscoverFragment.newInstance(), HomeFragment.class, false);
                    } else {
                        // 如果已经在栈内,则以SingleTask模式start
                        myHome.start(fragment, ISupportFragment.SINGLETASK);
                    }
                } else if (id == R.id.nav_shop) {

                    ShopFragment fragment = findFragment(ShopFragment.class);
                    if (fragment == null) {
                        myHome.startWithPopTo(ShopFragment.newInstance(), HomeFragment.class, false);
                    } else {
                        // 如果已经在栈内,则以SingleTask模式start,也可以用popTo
//                        start(fragment, SupportFragment.SINGLETASK);
                        myHome.popTo(ShopFragment.class, false);

                    }
                } else if (id == R.id.nav_login) {
                    goLogin();
                } else if (id == R.id.nav_swipe_back) {
                    //startActivity(new Intent(MainActivity.this, SwipeBackSampleActivity.class));
                }
            }
        }, 300);

        return true;
    }

    private void goLogin() {
        start(LoginFragment.newInstance());
    }


    @Override
    public void onLoginSuccess(String account) {
        mTvName.setText(account);
        mImgNav.setImageResource(R.drawable.ic_account_circle_white_48dp);
        Toast.makeText(this, R.string.sign_in_success, Toast.LENGTH_SHORT).show();
    }

}