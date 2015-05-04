package lesson.android.yangchang.ls4_hw;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

import lesson.android.yangchang.demo.R;

public class Tic_tac_toa extends ActionBarActivity {

    private boolean status = true;

    private void initOnClickListeners() {
        TableLayout tableLayout = (TableLayout) findViewById(R.id.tttTableLayout);
        for (int y = 0; y < tableLayout.getChildCount(); y ++) {
            TableRow tableRow = (TableRow) tableLayout.getChildAt(y);
            for (int x = 0; x < tableRow.getChildCount(); x++) {
                Button eachButton = (Button) tableRow.getChildAt(x);
                eachButton.setOnClickListener(new Tic_tac_toaOnClick());
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tic_tac_toa);
        initOnClickListeners();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tic_tac_toa, menu);
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

    private class Tic_tac_toaOnClick implements Button.OnClickListener {

        public Tic_tac_toaOnClick() {}

        @Override
        public void onClick(View view) {
            Button B = (Button) view;
            B.setText(status ? "O" : "X");
            B.setEnabled(false);
            status = !status;
        }
    }
}

