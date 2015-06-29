package lesson.android.yangchang.ls6_hw;

import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import lesson.android.yangchang.demo.R;

public class MainHW6Activity extends ActionBarActivity {

    private LinearLayout nine_con;
    NinePwdView view;
    TextView show;
    boolean isSetFirst = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        view = new NinePwdView(this);
        nine_con = (LinearLayout) findViewById(R.id.nine_con);
        nine_con.addView(view);
        show = (TextView) findViewById(R.id.show_set_info);
        getSetPwd();
    }

    public void getSetPwd(){
        SharedPreferences sharedPreferences = getSharedPreferences("GUE_PWD", 0);
        isSetFirst = sharedPreferences.getBoolean("IS_SET_FIRST", false);
        if(!isSetFirst){
            show.setText("Setting your password");
            sharedPreferences.edit().clear().commit();
        }else{
            show.setText("confirm again");
        }
        boolean is_sec_err = sharedPreferences.getBoolean("SECOND_ERROR", false);
        if(is_sec_err)
            show.setText("Two times are different");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_nine_pwd, menu);
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
