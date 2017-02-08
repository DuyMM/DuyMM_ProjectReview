package com.rootrbook.maimanhduy.rbook.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;

import com.rootrbook.maimanhduy.rbook.MainActivity;
import com.rootrbook.maimanhduy.rbook.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ListenerNewBook extends Service {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference;
    private boolean check = true;
    private SharedPreferences pre;
    @Override
    public void onCreate() {
        super.onCreate();
       database.goOnline();
        pre = getSharedPreferences(getString(R.string.status_service),MODE_PRIVATE);
        SharedPreferences.Editor edit = pre.edit();
        edit.putBoolean("status",true);
        edit.apply();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        databaseReference = database.getReference();
        databaseReference.child("vi").child("Notification").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (check){
                    check=false;
                }else {
                    NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getApplicationContext());
                    mBuilder.setAutoCancel(true);
                    mBuilder.setSmallIcon(R.drawable.ic_info);
                    mBuilder.setTicker("This is a ticker");
                    Intent intent = null;
                        intent = new Intent(getApplicationContext(), MainActivity.class);
                    // PendingIntent.getActivity(..) sẽ start mới một Activity và trả về
                    // đối tượng PendingIntent.
                    // Nó cũng tương đương với gọi Context.startActivity(Intent).
                    PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 100,
                            intent, PendingIntent.FLAG_UPDATE_CURRENT);
                    // Sét đặt thời điểm sự kiện xẩy ra.
                    // Các thông báo trên Panel được sắp xếp bởi thời gian này.
                    //mBuilder.setWhen(System.currentTimeMillis()+ 10* 1000);
                    mBuilder.setContentIntent(pendingIntent);
                    mBuilder.setContentTitle(getString(R.string.notification_title));
                    mBuilder.setContentText(dataSnapshot.getValue()+"");
                    NotificationManager notificationService  =
                            (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
                    Notification notification =  mBuilder.build();
                    if (dataSnapshot.getValue()!=null){
                        notificationService.notify(1, notification);
                    }

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        SharedPreferences.Editor edit = pre.edit();
        edit.putBoolean("status",false);
        edit.apply();
        database.goOffline();
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return null;
    }
}
