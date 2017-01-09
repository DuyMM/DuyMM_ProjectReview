package com.example.maimanhduy.rbook;

import android.content.Intent;
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

import com.example.maimanhduy.rbook.activities.BookInSdCardActivity;
import com.example.maimanhduy.rbook.activities.FavoriteBookActivity;
import com.example.maimanhduy.rbook.activities.InfoBookActivity;
import com.example.maimanhduy.rbook.activities.ReadBookActivity;
import com.example.maimanhduy.rbook.adapter.MainRecyclerAdapter;

public class MainActivity extends AppCompatActivity  {
    private RecyclerView mRecyclerViewMain;
    private MainRecyclerAdapter mMainRecylerAdapter;
    private DrawerLayout mDrawer;
    private Toolbar mMainToolbar;
    private NavigationView mNvViewLeftMenu;
    private  Toolbar mLocalMainTool;
    private ImageView mImgButtonShowMenuLeft;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.colorMain));
        }
        setSupportActionBar(mMainToolbar);
        setupDrawerContent(mNvViewLeftMenu);
        mImgButtonShowMenuLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDrawer.openDrawer(Gravity.LEFT);
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        mRecyclerViewMain.setHasFixedSize(true);
        mRecyclerViewMain.setLayoutManager(linearLayoutManager);
        mMainRecylerAdapter = new MainRecyclerAdapter(MainActivity.this);
        mRecyclerViewMain.setAdapter(mMainRecylerAdapter);
    }
    private void init(){
        mMainToolbar = (Toolbar) findViewById(R.id.toolbar);
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        mNvViewLeftMenu = (NavigationView) findViewById(R.id.nvView);
        mLocalMainTool = (Toolbar)findViewById(R.id.mainToolBar);
        mImgButtonShowMenuLeft = (ImageView)mLocalMainTool.findViewById(R.id.imgToolBarShowLeftMenu);
        mRecyclerViewMain = (RecyclerView)findViewById(R.id.recycerViewMain);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close the drawer.
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
                startActivity(new Intent(MainActivity.this, InfoBookActivity.class));
                break;
            case R.id.nav_second_fragment:
               // fragmentClass = SecondFragment.class;
                startActivity(new Intent(MainActivity.this, ReadBookActivity.class));
                break;
            case R.id.nav_third_fragment:
               // fragmentClass = ThirdFragment.class;
                startActivity(new Intent(MainActivity.this, BookInSdCardActivity.class));
                break;
            case R.id.action_favorite:
                startActivity(new Intent(MainActivity.this, FavoriteBookActivity.class));
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
