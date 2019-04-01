package practicaltest01var02.eim.systems.cs.pub.ro.practicaltest01var02;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class PracticalTest01Var02Service  extends Service {
    private ProcessingThread processingThread = null;


    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("SERVICE", "onCreate() method was invoked");
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d("SERVICE", "onBind() method was invoked");
        return null;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d("SERVICE", "onUnbind() method was invoked");
        return true;
    }

    @Override
    public void onRebind(Intent intent) {
        Log.d("SERVICE", "onRebind() method was invoked");
    }

    @Override
    public void onDestroy() {
        Log.d("SERVICE", "onDestroy() method was invoked");
        processingThread.stopThread();
    }



    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String firstNumber = intent.getStringExtra("firstNumber");
        String secondNumber = intent.getStringExtra("secondNumber");
        processingThread = new ProcessingThread(this, firstNumber, secondNumber);
        processingThread.start();
        return Service.START_REDELIVER_INTENT;
    }


}