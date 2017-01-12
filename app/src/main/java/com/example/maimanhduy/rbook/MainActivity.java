package com.example.maimanhduy.rbook;

import android.content.Intent;
import android.os.AsyncTask;
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
import com.example.maimanhduy.rbook.activities.SettingActivity;
import com.example.maimanhduy.rbook.adapter.ListNewAdapter;
import com.example.maimanhduy.rbook.adapter.MainRecyclerAdapter;
import com.example.maimanhduy.rbook.model.BookInFireBase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ListNewAdapter.callBackFormListNew {
    private RecyclerView mRecyclerViewMain;
    private MainRecyclerAdapter mMainRecylerAdapter;
    private DrawerLayout mDrawer;
    private Toolbar mMainToolbar;
    private NavigationView mNvViewLeftMenu;
    protected Toolbar mLocalMainTool;
    private ImageView mImgButtonShowMenuLeft;
    private ArrayList<BookInFireBase> arrListLightNovel = new ArrayList<>();
    private ArrayList<BookInFireBase> arrListComic = new ArrayList<>();
    private ArrayList<BookInFireBase> arrListOther = new ArrayList<>();
    private ArrayList<BookInFireBase> arrListHot = new ArrayList<>();
    private ArrayList<BookInFireBase> arrListNew = new ArrayList<>();
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        setupToolBar();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        mRecyclerViewMain.setHasFixedSize(true);
        mRecyclerViewMain.setLayoutManager(linearLayoutManager);
        mMainRecylerAdapter = new MainRecyclerAdapter(MainActivity.this, arrListLightNovel, arrListComic, arrListOther, arrListNew, arrListHot);
        mRecyclerViewMain.setAdapter(mMainRecylerAdapter);
        LoadDataAsyncTask async = new LoadDataAsyncTask();
        async.execute();
    }

    private void init() {
        mMainToolbar = (Toolbar) findViewById(R.id.toolbar);
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        mNvViewLeftMenu = (NavigationView) findViewById(R.id.nvView);
        mLocalMainTool = (Toolbar) findViewById(R.id.mainToolBar);
        mImgButtonShowMenuLeft = (ImageView) mLocalMainTool.findViewById(R.id.imgToolBarShowLeftMenu);
        mRecyclerViewMain = (RecyclerView) findViewById(R.id.recycerViewMain);
        databaseReference = FirebaseDatabase.getInstance().getReference();
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
        switch (menuItem.getItemId()) {
            case R.id.nav_home:
                // fragmentClass = FirstFragment.class;
                startActivity(new Intent(MainActivity.this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
                break;
            case R.id.nav_favorite:
                // fragmentClass = SecondFragment.class;
                startActivity(new Intent(MainActivity.this, FavoriteBookActivity.class));
                break;
            case R.id.nav_book_in_sd_card:
                // fragmentClass = ThirdFragment.class;
                startActivity(new Intent(MainActivity.this, BookInSdCardActivity.class));
                break;
            case R.id.nav_setting:
                startActivity(new Intent(MainActivity.this, SettingActivity.class));
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

    private void setupToolBar() {

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
    }

    public void loadListHot() {
        databaseReference.child("LightNovel").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                arrListHot.clear();
                for (DataSnapshot snap : dataSnapshot.getChildren()) {
                    String title = (String) snap.child("Title").getValue();
                    String author = (String) snap.child("Author").getValue();
                    String linkImage = (String) snap.child("LinkImage").getValue();
                    String linkBook = (String) snap.child("LinkBook").getValue();
                    String id = snap.getKey();
                    String views = snap.child("Views").getValue().toString();
                    if (arrListHot.size() != 0) {
                        for (int i = 0; i < arrListHot.size(); i++) {
                            if (arrListHot.get(i).getId().equals(id)) {
                                arrListHot.remove(i);
                            }
                        }
                    }
                    arrListHot.add(new BookInFireBase(title, author, linkImage, linkBook, id, views,dataSnapshot.getKey()));
                }
                loadComic();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void loadOther() {
        databaseReference.child("Other").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snap : dataSnapshot.getChildren()) {
                    String title = (String) snap.child("Title").getValue();
                    String author = (String) snap.child("Author").getValue();
                    String linkImage = (String) snap.child("LinkImage").getValue();
                    String linkBook = (String) snap.child("LinkBook").getValue();
                    String id = snap.getKey();
                    String views = snap.child("Views").getValue().toString();
                    if (arrListHot.size() != 0) {
                        for (int i = 0; i < arrListHot.size(); i++) {
                            if (arrListHot.get(i).getId().equals(id)) {
                                arrListHot.remove(i);
                            }
                        }
                    }
                    arrListHot.add(new BookInFireBase(title, author, linkImage, linkBook, id, views,dataSnapshot.getKey()));
                }
                mMainRecylerAdapter.notifyItemChanged(2);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void loadComic() {
        databaseReference.child("Comic").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snap : dataSnapshot.getChildren()) {
                    String title = (String) snap.child("Title").getValue();
                    String author = (String) snap.child("Author").getValue();
                    String linkImage = (String) snap.child("LinkImage").getValue();
                    String linkBook = (String) snap.child("LinkBook").getValue();
                    String id = snap.getKey();
                    String views = snap.child("Views").getValue().toString();
                    if (arrListHot.size() != 0) {
                        for (int i = 0; i < arrListHot.size(); i++) {
                            if (arrListHot.get(i).getId().equals(id)) {
                                arrListHot.remove(i);
                            }
                        }
                    }
                    arrListHot.add(new BookInFireBase(title, author, linkImage, linkBook, id, views,dataSnapshot.getKey()));
                }
                loadOther();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void openInforBookListNew(String category,String position) {
        Intent intent = new Intent(MainActivity.this, InfoBookActivity.class);
        intent.putExtra("id",position);
        intent.putExtra("category",category);
        startActivity(intent);
    }

    public class LoadDataAsyncTask extends AsyncTask<Void, Integer, Void> {


        @Override
        protected Void doInBackground(Void... voids) {
            databaseReference.child("LightNovel").limitToFirst(3).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    arrListNew.clear();
                    for (DataSnapshot snap : dataSnapshot.getChildren()) {
                        String title = (String) snap.child("Title").getValue();
                        String author = (String) snap.child("Author").getValue();
                        String linkImage = (String) snap.child("LinkImage").getValue();
                        String linkBook = (String) snap.child("LinkBook").getValue();
                        String views = snap.child("Views").getValue().toString();
                        String id = snap.getKey();
                        if (arrListNew.size() != 0) {
                            for (int i = 0; i < arrListNew.size(); i++) {
                                if (arrListNew.get(i).getId().equals(id)) {
                                    arrListNew.remove(i);
                                }
                            }
                        }
                        arrListNew.add(new BookInFireBase(title, author, linkImage, linkBook, id, views,dataSnapshot.getKey()));
                    }
                    databaseReference.child("Other").limitToFirst(3).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for (DataSnapshot snap : dataSnapshot.getChildren()) {
                                String title = (String) snap.child("Title").getValue();
                                String author = (String) snap.child("Author").getValue();
                                String linkImage = (String) snap.child("LinkImage").getValue();
                                String linkBook = (String) snap.child("LinkBook").getValue();
                                String views = snap.child("Views").getValue().toString();
                                String id = snap.getKey();
                                if (arrListNew.size() != 0) {
                                    for (int i = 0; i < arrListNew.size(); i++) {
                                        if (arrListNew.get(i).getId().equals(id)) {
                                            arrListNew.remove(i);
                                        }
                                    }
                                }
                                arrListNew.add(new BookInFireBase(title, author, linkImage, linkBook, id, views,dataSnapshot.getKey()));
                                databaseReference.child("Comic").limitToFirst(3).addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        for (DataSnapshot snap : dataSnapshot.getChildren()) {
                                            String title = (String) snap.child("Title").getValue();
                                            String author = (String) snap.child("Author").getValue();
                                            String linkImage = (String) snap.child("LinkImage").getValue();
                                            String linkBook = (String) snap.child("LinkBook").getValue();
                                            String views = snap.child("Views").getValue().toString();
                                            String id = snap.getKey();
                                            if (arrListNew.size() != 0) {
                                                for (int i = 0; i < arrListNew.size(); i++) {
                                                    if (arrListNew.get(i).getId().equals(id)) {
                                                        arrListNew.remove(i);
                                                    }
                                                }
                                            }
                                            arrListNew.add(new BookInFireBase(title, author, linkImage, linkBook, id, views,dataSnapshot.getKey()));
                                        }
                                        mMainRecylerAdapter.notifyItemChanged(1);
                                    }

                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {

                                    }
                                });
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            databaseReference.child("LightNovel").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    arrListLightNovel.clear();
                    for (DataSnapshot snap : dataSnapshot.getChildren()) {
                        String title = (String) snap.child("Title").getValue();
                        String author = (String) snap.child("Author").getValue();
                        String linkImage = (String) snap.child("LinkImage").getValue();
                        String linkBook = (String) snap.child("LinkBook").getValue();
                        String id = snap.getKey();
                        String views = snap.child("Views").getValue().toString();
                        //Log.d("ThongTin",title+"-"+author+"-"+linkImage+"-"+linkBook+"-"+id);
                        arrListLightNovel.add(new BookInFireBase(title, author, linkImage, linkBook, id, views,dataSnapshot.getKey()));
                    }
                    mMainRecylerAdapter.notifyItemChanged(3);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            databaseReference.child("Other").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    arrListOther.clear();
                    for (DataSnapshot snap : dataSnapshot.getChildren()) {
                        String title = (String) snap.child("Title").getValue();
                        String author = (String) snap.child("Author").getValue();
                        String linkImage = (String) snap.child("LinkImage").getValue();
                        String linkBook = (String) snap.child("LinkBook").getValue();
                        String id = snap.getKey();
                        String views = snap.child("Views").getValue().toString();
                        arrListOther.add(new BookInFireBase(title, author, linkImage, linkBook, id, views,dataSnapshot.getKey()));
                    }
                    mMainRecylerAdapter.notifyItemChanged(5);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            databaseReference.child("Comic").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    arrListComic.clear();
                    for (DataSnapshot snap : dataSnapshot.getChildren()) {
                        String title = (String) snap.child("Title").getValue();
                        String author = (String) snap.child("Author").getValue();
                        String linkImage = (String) snap.child("LinkImage").getValue();
                        String linkBook = (String) snap.child("LinkBook").getValue();
                        String id = snap.getKey();
                        String views = snap.child("Views").getValue().toString();
                        arrListComic.add(new BookInFireBase(title, author, linkImage, linkBook, id, views,dataSnapshot.getKey()));
                    }
                    mMainRecylerAdapter.notifyItemChanged(4);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            loadListHot();
            return null;
        }
    }

}
