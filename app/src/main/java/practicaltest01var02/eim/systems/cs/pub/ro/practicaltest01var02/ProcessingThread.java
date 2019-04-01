package practicaltest01var02.eim.systems.cs.pub.ro.practicaltest01var02;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import java.util.Date;
import java.util.Random;

class ProcessingThread  extends Thread {

        private Context context = null;
        private boolean isRunning = true;
        private String firstNumber;
        private String secondNumber;
        private Random random = new Random();

        public ProcessingThread(Context context, String firstNumber, String secondNumber) {
            this.context = context;
            this.firstNumber = firstNumber;
            this.secondNumber  =  secondNumber; }

        @Override
        public void run() {
            Log.d("[ProcessingThread]", "Thread has started!");
            sendMessage(firstNumber,secondNumber,"plus");
            sleep();
            sendMessage(firstNumber,secondNumber,"minus");
            sleep();
            Log.d("[ProcessingThread]", "Thread has stopped!");
        }

        private void sleep() {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
        }

        private void sendMessage(String firstNumber, String secondNumber, String action) {
            if(action.equals("plus")){
                Intent intent = new Intent();
                intent.setAction(action);
                int message   =  Integer.valueOf(firstNumber) +  Integer.valueOf(secondNumber);
                intent.putExtra("message", new Date(System.currentTimeMillis()) + " PLUS:  " + String.valueOf(message));
                context.sendBroadcast(intent);
            } else if(action.equals("minus"))  {
                Intent intent = new Intent();
                intent.setAction(action);
                int message   =  Integer.valueOf(firstNumber) -  Integer.valueOf(secondNumber);
                intent.putExtra("message", new Date(System.currentTimeMillis()) + " MINUS: " + String.valueOf(message));
                context.sendBroadcast(intent);
            }
        }

        public void stopThread() {
            isRunning = false;
        }
    }

