package lesson.android.yangchang.ls7;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.net.URI;

import lesson.android.yangchang.demo.R;
import lesson.android.yangchang.demo.YangApp;

public class ChangeActivity2 extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change2);
        final Intent it = getIntent();
        Bundle bundle = it.getExtras();
        int bu_id = bundle.getInt("BU_ID", 0);
        int id = it.getIntExtra("ID", 0);
        String name = it.getStringExtra("NAME");
        LinearLayout ll = (LinearLayout) findViewById(R.id.MainChangeLayout2);
        final EditText editName = new EditText(this);
        YangApp yangApp = (YangApp) getApplicationContext();
        yangApp.count++;
        editName.setText(id + name + bu_id + yangApp.count);
        ll.addView(editName);

        Button button = new Button(this);
        ll.addView(button);
        button.setText("Back");
        button.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent();
                it.setClass(ChangeActivity2.this, ChangeActivity.class);
                startActivity(it);
                finish();
            }
        });

        Button buttonLink = new Button(this);
        buttonLink.setText("Go Google");
        ll.addView(buttonLink);
        buttonLink.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("http://www.google.com");
                Intent it = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(it);
            }
        });
    }
}
