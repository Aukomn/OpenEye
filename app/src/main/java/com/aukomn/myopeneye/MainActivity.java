package com.aukomn.myopeneye;

import android.content.Intent;
import android.os.Bundle;

import com.aukomn.myopeneye.ui.ScrollingFragment;
import com.aukomn.myopeneye.ui.dashboard.DashboardFragment;
import com.aukomn.myopeneye.ui.notifications.NotificationsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;
import io.flutter.embedding.android.FlutterFragment;

public class MainActivity extends AppCompatActivity  implements ViewPager.OnPageChangeListener{
    private static final String TAG_FLUTTER_FRAGMENT = "flutter_fragment";
  //  private List<Fragment> mFragmentList = new ArrayList<>(4);
    // Declare a local variable to reference the FlutterFragment so that you
    // can forward calls to it later.
    private BottomNavigationView navView;
    private FlutterFragment flutterFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();
         navView = findViewById(R.id.nav_view);
       // FrameLayout contentViewPager=findViewById(R.id.nav_host_fragment);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        // Get a reference to the Activity's FragmentManager to add a new
        // FlutterFragment, or find an existing one.
    //    mFragmentList.add(flutterFragment);
      //  contentViewPager.setAdapter(new HomeFragmentPagerAdapter(getSupportFragmentManager(), mFragmentList));
    //    contentViewPager.setOnPageChangeListener(this);
        FragmentManager fragmentManager = getSupportFragmentManager();
        // Attempt to find an existing FlutterFragment,
        // in case this is not the first time that onCreate() was run.
        flutterFragment = (FlutterFragment) fragmentManager
                .findFragmentByTag(TAG_FLUTTER_FRAGMENT);
        // Create and attach a FlutterFragment if one does not exist.
        if (flutterFragment == null) {
            flutterFragment = FlutterFragment.withNewEngine().initialRoute("/").build();
         //   flutterFragment = FlutterFragment.withNewEngine().build();
            fragmentManager
                    .beginTransaction()
                    .add(
                            R.id.containerm,
                            flutterFragment,
                            TAG_FLUTTER_FRAGMENT
                    )
                    .commit();
        }
        navView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.navigation_home: {
                    //if (flutterFragment == null) {
                        flutterFragment= FlutterFragment.withNewEngine().initialRoute("/").build();
                      //  flutterFragment = FlutterFragment.createDefault();
                    fragmentManager
                            .beginTransaction().
                            replace(
                                    R.id.containerm,
                                    flutterFragment,
                                    TAG_FLUTTER_FRAGMENT
                            )
                            .commit();
                }
                break;
                case R.id.navigation_dashboard:
                    flutterFragment= FlutterFragment.withNewEngine().initialRoute("/route2").build();
                   fragmentManager
                    .beginTransaction().
                    replace(
                            R.id.containerm,
                            flutterFragment,
                            TAG_FLUTTER_FRAGMENT
                    )
                    .commit();

                    break;
                case R.id.navigation_notifications:
                    fragmentManager
                            .beginTransaction().
                            replace(
                                    R.id.containerm,
                                    new NotificationsFragment()
                            )
                            .commit();
                    break;
                case R.id.scrollingFragment:
                    fragmentManager
                            .beginTransaction().
                            replace(
                                    R.id.containerm,
                                    new ScrollingFragment()
                            )
                            .commit();
                    break;
                default:
                    break;
            }
            return true;
        });
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
            switch (position){
                case 0:
                    navView.setSelectedItemId(R.id.navigation_home);
                    break;
                case 1:
                    navView.setSelectedItemId(R.id.navigation_dashboard);
                    break;
                case 2:
                    navView.setSelectedItemId(R.id.navigation_notifications);
                    break;
                case 3:
                    navView.setSelectedItemId(R.id.scrollingFragment);
                    break;
                default:
                    break;
            }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onPostResume() {
        super.onPostResume();
        flutterFragment.onPostResume();
    }

    @Override
    protected void onNewIntent(@NonNull Intent intent) {
        super.onNewIntent(intent);
        flutterFragment.onNewIntent(intent);
    }

    @Override
    public void onBackPressed() {
        flutterFragment.onBackPressed();
    }

    @Override
    public void onRequestPermissionsResult(
            int requestCode,
            @NonNull String[] permissions,
            @NonNull int[] grantResults
    ) {
        flutterFragment.onRequestPermissionsResult(
                requestCode,
                permissions,
                grantResults
        );
    }

    @Override
    public void onUserLeaveHint() {
        flutterFragment.onUserLeaveHint();
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        flutterFragment.onTrimMemory(level);
    }
//      AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
//                .build();
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
//        NavigationUI.setupWithNavController(navView, navController);
}