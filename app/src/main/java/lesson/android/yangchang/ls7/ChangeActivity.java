package lesson.android.yangchang.ls7;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import lesson.android.yangchang.demo.R;
import lesson.android.yangchang.demo.YangApp;

public class ChangeActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change);
        LinearLayout ll = (LinearLayout) findViewById(R.id.MainChangeLayout);
        Button button = new Button(this);
        ll.addView(button);
        TextView idTextView = new TextView(this);
        idTextView.setText("ID");
        final EditText idText = new EditText(this);
        ll.addView(idTextView);
        ll.addView(idText);
        TextView NameTextView = new TextView(this);
        NameTextView.setText("NAME");
        final EditText nameText = new EditText(this);
        ll.addView(NameTextView);
        ll.addView(nameText);

        YangApp yangApp = (YangApp) getApplicationContext();
        yangApp.count++;

        button.setText("GO");
        button.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent();
                Bundle bundle = new Bundle();
                bundle.putInt("BU_ID", 7);
                it.putExtra("ID", Integer.parseInt(idText.getText().toString()));
                it.putExtra("NAME", nameText.getText().toString());
                it.putExtras(bundle);
                it.setClass(ChangeActivity.this, ChangeActivity2.class);
                startActivity(it);
                finish();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_change, menu);
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
