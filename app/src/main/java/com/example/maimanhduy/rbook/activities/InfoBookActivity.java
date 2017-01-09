package com.example.maimanhduy.rbook.activities;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ScrollView;

import com.example.maimanhduy.rbook.R;
import com.example.maimanhduy.rbook.adapter.ListInfoMoreBookAdapter;

public class InfoBookActivity extends AppCompatActivity {
    private Toolbar mInfoBookToolBar;
    private DrawerLayout mDrawer;
    private NavigationView mNvViewLeftMenu;
    private RecyclerView mRecylerViewMore;
    private Toolbar mThisToolBar;
    private ImageView mImgShowLeftMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_book);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.colorMain));
        }
        init();
        setSupportActionBar(mInfoBookToolBar);
        setupDrawerContent(mNvViewLeftMenu);
        LinearLayoutManager lln = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false);
        mRecylerViewMore.setHasFixedSize(true);
        mRecylerViewMore.setLayoutManager(lln);
        ListInfoMoreBookAdapter adapter = new ListInfoMoreBookAdapter();
        mRecylerViewMore.setAdapter(adapter);
        mImgShowLeftMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDrawer.openDrawer(Gravity.LEFT);
            }
        });
    }
    public void init(){
        mInfoBookToolBar = (Toolbar) findViewById(R.id.toolbar);
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_info_book);
        mNvViewLeftMenu = (NavigationView) findViewById(R.id.nvInfoView);
        mRecylerViewMore = (RecyclerView)findViewById(R.id.recyclerViewListInfoMore);
        mThisToolBar = (Toolbar)findViewById(R.id.infoBookToolBar);
        mImgShowLeftMenu = (ImageView)mThisToolBar.findViewById(R.id.imgToolBarShowLeftMenu);
        ScrollView scrollView = (ScrollView)findViewById(R.id.scrollViewInforBook);
        scrollView.fullScroll(View.FOCUS_DOWN);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawer.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }
    public void selectDrawerItem(MenuItem menuItem) {
        // Create a new fragment and specify the fragment to show based on nav item clicked
        Fragment fragment = null;
        Class fragmentClass;
        switch(menuItem.getItemId()) {
            case R.id.nav_first_fragment:
                // fragmentClass = FirstFragment.class;
                break;
            case R.id.nav_second_fragment:
                // fragmentClass = SecondFragment.class;
                break;
            case R.id.nav_third_fragment:
                // fragmentClass = ThirdFragment.class;
                break;
            default:
                // fragmentClass = FirstFragment.class;
        }

        try {
            //fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Insert the fragment by replacing any existing fragment
        // FragmentManager fragmentManager = getSupportFragmentManager();
        // fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();

        // Highlight the selected item has been done by NavigationView
        menuItem.setChecked(true);
        // Set action bar title
        setTitle(menuItem.getTitle());
        // Close the navigation drawer
        mDrawer.closeDrawers();
    }


}
