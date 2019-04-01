package practicaltest01var02.eim.systems.cs.pub.ro.practicaltest01var02;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PracticalTest01Var02SecondaryActivity extends AppCompatActivity {

    EditText result;
    Button correctButton,incorrectButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var02_secondary);

        result = findViewById(R.id.rezultat);
        correctButton = findViewById(R.id.correct);
        incorrectButton = findViewById(R.id.incorrect);

        correctButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_OK, null);
                finish();
            }
        });

        incorrectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED, null);
                finish();
            }
        });

        Intent newIntent = this.getIntent();
        String value = newIntent.getStringExtra("result");
        result.setText(value);
    }
}
