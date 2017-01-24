package com.example.maimanhduy.rbook;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
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

import com.example.maimanhduy.rbook.activities.AllBookActivity;
import com.example.maimanhduy.rbook.activities.BookInSdCardActivity;
import com.example.maimanhduy.rbook.activities.FavoriteBookActivity;
import com.example.maimanhduy.rbook.activities.InfoBookActivity;
import com.example.maimanhduy.rbook.activities.SettingActivity;
import com.example.maimanhduy.rbook.adapter.ListHotAdapter;
import com.example.maimanhduy.rbook.adapter.ListLightNovelAdapter;
import com.example.maimanhduy.rbook.adapter.ListNewAdapter;
import com.example.maimanhduy.rbook.adapter.MainRecyclerAdapter;
import com.example.maimanhduy.rbook.adapter.SlideSwipeAdapter;
import com.example.maimanhduy.rbook.model.BookInFireBase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements ListNewAdapter.callBackFormListNew, ListLightNovelAdapter.CallBackMainFormListLightNovel, ListHotAdapter.callBackFormListHot, SlideSwipeAdapter.onCallBackFormSlide, MainRecyclerAdapter.onCallBackFormMainAdapter {
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
    private ArrayList<BookInFireBase> arrSpecial = new ArrayList<>();
    private DatabaseReference databaseReference;
    Random mRandomGeneral;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        setupToolBar();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        mRecyclerViewMain.setHasFixedSize(true);
        mRecyclerViewMain.setLayoutManager(linearLayoutManager);
        mMainRecylerAdapter = new MainRecyclerAdapter(MainActivity.this, arrListLightNovel, arrListComic, arrListOther, arrListNew, arrListHot, arrSpecial);
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
            case R.id.nav_about_me:
                new AlertDialog.Builder(this)
                        .setTitle(getString(R.string.about_title))
                        .setMessage(getString(R.string.about_content))
                        .setPositiveButton("Close", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                               dialog.dismiss();
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
                break;
            default:
                // fragmentClass = FirstFragment.class;
        }

     /*   try {
            //fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
*/
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

    public void loadLightNovel() {
        databaseReference.child("vi").child(getString(R.string.lightnovel)).limitToLast(5).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                arrListLightNovel.clear();
                for (DataSnapshot snap : dataSnapshot.getChildren()) {
                    String title = (String) snap.child(getString(R.string.title)).getValue();
                    String author = (String) snap.child(getString(R.string.author)).getValue();
                    String linkImage = (String) snap.child(getString(R.string.linkimage)).getValue();
                    String linkBook = (String) snap.child(getString(R.string.linkbook)).getValue();
                    String id = snap.getKey();
                    String views = snap.child(getString(R.string.views)).getValue().toString();
                    //Log.d("ThongTin",title+"-"+author+"-"+linkImage+"-"+linkBook+"-"+id);
                    arrListLightNovel.add(new BookInFireBase(title, author, linkImage, linkBook, id, views,dataSnapshot.getKey()));
                }
                Collections.reverse(arrListLightNovel);
                mMainRecylerAdapter.notifyItemChanged(4);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void loadOther() {
        databaseReference.child("vi").child(getString(R.string.other)).limitToLast(5).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                arrListOther.clear();
                for (DataSnapshot snap : dataSnapshot.getChildren()) {
                    String title = (String) snap.child(getString(R.string.title)).getValue();
                    String author = (String) snap.child(getString(R.string.author)).getValue();
                    String linkImage = (String) snap.child(getString(R.string.linkimage)).getValue();
                    String linkBook = (String) snap.child(getString(R.string.linkbook)).getValue();
                    String id = snap.getKey();
                    String views = snap.child(getString(R.string.views)).getValue().toString();
                    arrListOther.add(new BookInFireBase(title, author, linkImage, linkBook, id, views,dataSnapshot.getKey()));
                }
                mMainRecylerAdapter.notifyItemChanged(6);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void loadComic() {
        databaseReference.child("vi").child(getString(R.string.comic)).limitToLast(5).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                arrListComic.clear();
                for (DataSnapshot snap : dataSnapshot.getChildren()) {
                    String title = (String) snap.child(getString(R.string.title)).getValue();
                    String author = (String) snap.child(getString(R.string.author)).getValue();
                    String linkImage = (String) snap.child(getString(R.string.linkimage)).getValue();
                    String linkBook = (String) snap.child(getString(R.string.linkbook)).getValue();
                    String id = snap.getKey();
                    String views = snap.child(getString(R.string.views)).getValue().toString();
                    arrListComic.add(new BookInFireBase(title, author, linkImage, linkBook, id, views,dataSnapshot.getKey()));
                }
                mMainRecylerAdapter.notifyItemChanged(5);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void openInforBookListNew(String category,String position) {
        openInfoBook(position,category);
    }

    @Override
    public void openInfoBookFormLightNovel(String pos, String category) {
    openInfoBook(pos,category);
    }

    @Override
    public void openInfoBookFormListHot(String pos, String category) {
        openInfoBook(pos,category);
    }

    @Override
    public void openBookOnSlide(String category, String position) {
        openInfoBook(position,category);
    }

    @Override
    public void openAllBookFormCategory(String category) {
        Intent intent = new Intent(MainActivity.this, AllBookActivity.class);
        intent.putExtra("category",category);
        startActivity(intent);
    }

    public class LoadDataAsyncTask extends AsyncTask<Void, Integer, Void> {


        @Override
        protected Void doInBackground(Void... voids) {
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    arrListNew.clear();
                    loadNewList();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            loadLightNovel();
            loadOther();
            loadComic();
            loadListHot();
            return null;
        }
    }
    private void loadNewList(){
        databaseReference.child("vi").child(getString(R.string.lightnovel)).limitToLast(3).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snap : dataSnapshot.getChildren()) {
                    String title = (String) snap.child(getString(R.string.title)).getValue();
                    String author = (String) snap.child(getString(R.string.author)).getValue();
                    String linkImage = (String) snap.child(getString(R.string.linkimage)).getValue();
                    String linkBook = (String) snap.child(getString(R.string.linkbook)).getValue();
                    String views = snap.child(getString(R.string.views)).getValue().toString();
                    String id = snap.getKey();
                    if (arrListNew.size() != 0) {
                        for (int i = 0; i < arrListNew.size(); i++) {
                            if (arrListNew.get(i).getId().equals(id)) {
                                arrListNew.remove(i);
                            }
                        }
                    }
             /*       if (arrListHot.size() != 0) {
                        for (int i = 0; i < arrListHot.size(); i++) {
                            if (arrListHot.get(i).getId().equals(id)) {
                                arrListHot.remove(i);
                            }
                        }
                    }*/
                    arrListNew.add(new BookInFireBase(title, author, linkImage, linkBook, id, views,dataSnapshot.getKey()));
                }
                databaseReference.child("vi").child(getString(R.string.other)).limitToLast(3).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot snap : dataSnapshot.getChildren()) {
                            String title = (String) snap.child(getString(R.string.title)).getValue();
                            String author = (String) snap.child(getString(R.string.author)).getValue();
                            String linkImage = (String) snap.child(getString(R.string.linkimage)).getValue();
                            String linkBook = (String) snap.child(getString(R.string.linkbook)).getValue();
                            String views = snap.child(getString(R.string.views)).getValue().toString();
                            String id = snap.getKey();
                            if (arrListNew.size() != 0) {
                                for (int i = 0; i < arrListNew.size(); i++) {
                                    if (arrListNew.get(i).getId().equals(id)) {
                                        arrListNew.remove(i);
                                    }
                                }
                            }
                       /*     if (arrListHot.size() != 0) {
                                for (int i = 0; i < arrListHot.size(); i++) {
                                    if (arrListHot.get(i).getId().equals(id)) {
                                        arrListHot.remove(i);
                                    }
                                }
                            }*/
                            arrListNew.add(new BookInFireBase(title, author, linkImage, linkBook, id, views,dataSnapshot.getKey()));
                            databaseReference.child("vi").child(getString(R.string.comic)).limitToLast(3).addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    for (DataSnapshot snap : dataSnapshot.getChildren()) {
                                        String title = (String) snap.child(getString(R.string.title)).getValue();
                                        String author = (String) snap.child(getString(R.string.author)).getValue();
                                        String linkImage = (String) snap.child(getString(R.string.linkimage)).getValue();
                                        String linkBook = (String) snap.child(getString(R.string.linkbook)).getValue();
                                        String views = snap.child(getString(R.string.views)).getValue().toString();
                                        String id = snap.getKey();
                                        if (arrListNew.size() != 0) {
                                            for (int i = 0; i < arrListNew.size(); i++) {
                                                if (arrListNew.get(i).getId().equals(id)) {
                                                    arrListNew.remove(i);
                                                }
                                            }
                                        }
                                    /*    if (arrListHot.size() != 0) {
                                            for (int i = 0; i < arrListHot.size(); i++) {
                                                if (arrListHot.get(i).getId().equals(id)) {
                                                    arrListHot.remove(i);
                                                }
                                            }
                                        }*/
                                      //  arrListHot.add(new BookInFireBase(title, author, linkImage, linkBook, id, views,dataSnapshot.getKey()));
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
    }
    private void avgListHot(){
        loadSpecial();
        Collections.sort(arrListHot, new Comparator<BookInFireBase>() {
            @Override
            public int compare(BookInFireBase book1, BookInFireBase book2) {
                if (Integer.parseInt(book1.getViews()) < Integer.parseInt(book2.getViews())) {
                    return 1;
                } else {
                    if (Integer.parseInt(book1.getViews()) == Integer.parseInt(book2.getViews())) {
                        return 0;
                    } else {
                        return -1;
                    }
                }
            }
        });
        mMainRecylerAdapter.notifyItemChanged(3);
    }
    public void openInfoBook(String position, String category){
        Intent intent = new Intent(MainActivity.this, InfoBookActivity.class);
        intent.putExtra("id",position);
        intent.putExtra("category",category);
        startActivity(intent);
    }
    public void loadSpecial(){
        if (arrSpecial.size()==0){
            mRandomGeneral = new Random();
            int index = mRandomGeneral.nextInt(arrListLightNovel.size());
            arrSpecial.add(arrListLightNovel.get(index));
            int index2 = mRandomGeneral.nextInt(arrListComic.size());
            arrSpecial.add(arrListComic.get(index2));
            int index3 = mRandomGeneral.nextInt(arrListOther.size());
            arrSpecial.add(arrListOther.get(index3));
            mMainRecylerAdapter.notifyItemChanged(0);
        }

    }
    public void loadListHot(){
        databaseReference.child("vi").child(getString(R.string.lightnovel)).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snap : dataSnapshot.getChildren()) {
                    String title = (String) snap.child(getString(R.string.title)).getValue();
                    String author = (String) snap.child(getString(R.string.author)).getValue();
                    String linkImage = (String) snap.child(getString(R.string.linkimage)).getValue();
                    String linkBook = (String) snap.child(getString(R.string.linkbook)).getValue();
                    String views = snap.child(getString(R.string.views)).getValue().toString();
                    String id = snap.getKey();
                    if (arrListHot.size() != 0) {
                        for (int i = 0; i < arrListHot.size(); i++) {
                            if (arrListHot.get(i).getId().equals(id)) {
                                arrListHot.remove(i);
                            }
                        }
                    }
                    arrListHot.add(new BookInFireBase(title, author, linkImage, linkBook, id, views,dataSnapshot.getKey()));
                }
                databaseReference.child("vi").child(getString(R.string.other)).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot snap : dataSnapshot.getChildren()) {
                            String title = (String) snap.child(getString(R.string.title)).getValue();
                            String author = (String) snap.child(getString(R.string.author)).getValue();
                            String linkImage = (String) snap.child(getString(R.string.linkimage)).getValue();
                            String linkBook = (String) snap.child(getString(R.string.linkbook)).getValue();
                            String views = snap.child(getString(R.string.views)).getValue().toString();
                            String id = snap.getKey();
                            if (arrListHot.size() != 0) {
                                for (int i = 0; i < arrListHot.size(); i++) {
                                    if (arrListHot.get(i).getId().equals(id)) {
                                        arrListHot.remove(i);
                                    }
                                }
                            }
                            arrListHot.add(new BookInFireBase(title, author, linkImage, linkBook, id, views,dataSnapshot.getKey()));
                            databaseReference.child("vi").child(getString(R.string.comic)).addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    for (DataSnapshot snap : dataSnapshot.getChildren()) {
                                        String title = (String) snap.child(getString(R.string.title)).getValue();
                                        String author = (String) snap.child(getString(R.string.author)).getValue();
                                        String linkImage = (String) snap.child(getString(R.string.linkimage)).getValue();
                                        String linkBook = (String) snap.child(getString(R.string.linkbook)).getValue();
                                        String views = snap.child(getString(R.string.views)).getValue().toString();
                                        String id = snap.getKey();
                                        if (arrListHot.size() != 0) {
                                            for (int i = 0; i < arrListHot.size(); i++) {
                                                if (arrListHot.get(i).getId().equals(id)) {
                                                    arrListHot.remove(i);
                                                }
                                            }
                                        }
                                        arrListHot.add(new BookInFireBase(title, author, linkImage, linkBook, id, views,dataSnapshot.getKey()));
                                    }
                                    avgListHot();
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
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
