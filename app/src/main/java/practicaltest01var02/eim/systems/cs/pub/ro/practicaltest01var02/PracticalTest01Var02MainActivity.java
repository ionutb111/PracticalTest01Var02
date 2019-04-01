package practicaltest01var02.eim.systems.cs.pub.ro.practicaltest01var02;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PracticalTest01Var02MainActivity extends AppCompatActivity {

    Button changeActivity,plusButton,miusButton;
    EditText firstNumber,secondNumber,result;

    private IntentFilter intentFilter = new IntentFilter();
    boolean serviceStarted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var02_main);

        changeActivity=findViewById(R.id.changeActivity);
        plusButton=findViewById(R.id.plus);
        miusButton=findViewById(R.id.minus);

        firstNumber=findViewById(R.id.firstNumber);
        secondNumber=findViewById(R.id.secondNumber);
        result=findViewById(R.id.result);

        intentFilter.addAction("plus");
        intentFilter.addAction("minus");



        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text1 = firstNumber.getText().toString().trim();
                String text2 = secondNumber.getText().toString().trim();

                Integer nr1;
                Integer nr2;

                try {
                    nr1=Integer.parseInt(firstNumber.getText().toString().trim());
                    nr2=Integer.parseInt(secondNumber.getText().toString().trim());
                    Integer resultNr=nr1+nr2;
                    String resultText =  text1 + " + " + text2 + " = " + String.valueOf(resultNr);
                    result.setText(resultText);

                    Intent intent = new Intent(PracticalTest01Var02MainActivity.this, PracticalTest01Var02Service.class);
                    intent.putExtra("firstNumber", firstNumber.getText().toString().trim());
                    intent.putExtra("secondNumber", secondNumber.getText().toString().trim());

                    PracticalTest01Var02MainActivity.this.startService(intent);
                    serviceStarted = true;
                    Toast.makeText(PracticalTest01Var02MainActivity.this, "SERVICE STARTED", Toast.LENGTH_SHORT).show();

                } catch (NumberFormatException e) {
                    Toast.makeText(PracticalTest01Var02MainActivity.this, "INTRODU NUMERE!!!", Toast.LENGTH_SHORT).show();
                }

            }
        });

        miusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text1 = firstNumber.getText().toString().trim();
                String text2 = secondNumber.getText().toString().trim();

                Integer nr1;
                Integer nr2;

                try {
                    nr1=Integer.parseInt(firstNumber.getText().toString().trim());
                    nr2=Integer.parseInt(secondNumber.getText().toString().trim());
                    Integer resultNr=nr1-nr2;
                    String resultText =  text1 + " - " + text2 + " = " + String.valueOf(resultNr);
                    result.setText(resultText);

                    Intent intent = new Intent(PracticalTest01Var02MainActivity.this, PracticalTest01Var02Service.class);
                    intent.putExtra("firstNumber", firstNumber.getText().toString().trim());
                    intent.putExtra("secondNumber", secondNumber.getText().toString().trim());

                    PracticalTest01Var02MainActivity.this.startService(intent);
                    serviceStarted = true;
                    Toast.makeText(PracticalTest01Var02MainActivity.this, "SERVICE STARTED", Toast.LENGTH_SHORT).show();

                } catch (NumberFormatException e) {
                    Toast.makeText(PracticalTest01Var02MainActivity.this, "INTRODU NUMERE!!!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        changeActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PracticalTest01Var02MainActivity.this,PracticalTest01Var02SecondaryActivity.class);
                intent.putExtra("result", result.getText().toString().trim());

                //PracticalTestMainActivity.this.startActivity(intent);
                startActivityForResult(intent, 100);
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.d("WOW", "savedInstanceState");
        savedInstanceState.putString("result", String.valueOf(result.getText().toString().trim()));
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {

        Log.d("WOW", "onRestoreInstanceState");

        if (savedInstanceState.containsKey("result")) {
            String res = savedInstanceState.getString("result").toString();
            Toast.makeText(this, res, Toast.LENGTH_LONG).show();
        }

    }

    // Task C2
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == 100) {
            Toast.makeText(this, "The activity returned with result " + resultCode, Toast.LENGTH_LONG).show();
        }
    }

    // Task D2

    private MessageBroadcastReceiver messageBroadcastReceiver = new MessageBroadcastReceiver();
    private class MessageBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            // If-ul e optional, doar daca vrei sa verifici actiunea
            if(intent.getAction() != null && intent.getAction().equals("plus")) {
                Log.d("[Message ]", " Intent: plus => " + intent.getStringExtra("message"));
            }

            if(intent.getAction() != null && intent.getAction().equals("minus")) {
                Log.d("[Message ]", " Intent: minus => " + intent.getStringExtra("message"));
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(messageBroadcastReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        unregisterReceiver(messageBroadcastReceiver);
        super.onPause();
    }
}
