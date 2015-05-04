package lesson.android.yangchang.lesson4;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import lesson.android.yangchang.demo.R;

public class Main2Activity extends ActionBarActivity {

    private Button button;
    private EditText fahrenheitEditText, celsiusEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }

    @Override
    protected void onResume(){
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View v) {
                celsiusEditText = (EditText) findViewById(R.id.celsiusEditText);
                fahrenheitEditText = (EditText) findViewById(R.id.fahrenheitEditText);
                double celsius = Double.parseDouble(celsiusEditText.getText().toString());
                double fahrenheit = celsius*9/5.+32.;
                fahrenheitEditText.setText(String.valueOf(fahrenheit));
            }
        });

        LinearLayout ll = (LinearLayout) findViewById(R.id.MainLinearLayout);
        final EditText editName = new EditText(this);
        Button btnHello = new Button(this);
        ll.addView(editName);
        ll.addView(btnHello);
        btnHello.setText("Hello");
        btnHello.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View v) {
                Toast.makeText(Main2Activity.this, "Hello "+ editName.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        super.onResume();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
