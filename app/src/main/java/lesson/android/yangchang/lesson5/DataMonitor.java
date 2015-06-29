package lesson.android.yangchang.lesson5;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;

import java.util.Calendar;

import lesson.android.yangchang.demo.R;

public class DataMonitor extends ActionBarActivity {

    private LinearLayout linearLayoutBase, linearLayoutTitle, linearLayoutAction;
    private DatePicker datePicker;
    private TimePicker timePicker;
    private EditText editText;
    private RadioGroup radioGroup;
    private Button button;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_monitor);

        linearLayoutBase = (LinearLayout) findViewById(R.id.DataMonitorMainLinearLayout);
        linearLayoutTitle = new LinearLayout(this);
        linearLayoutTitle.setOrientation(LinearLayout.HORIZONTAL);
        linearLayoutTitle.setGravity(Gravity.CENTER_VERTICAL);
        datePicker = new DatePicker(this);
        datePicker.setCalendarViewShown(false);

        LinearLayout dateTimeLayout = (LinearLayout) datePicker.getChildAt(0);
        LinearLayout dateLayout = (LinearLayout) dateTimeLayout.getChildAt(0);
        for(int i = 0 ; i < dateLayout.getChildCount();i++){
            NumberPicker numberPicker = (NumberPicker) dateLayout.getChildAt(i);
            LinearLayout.LayoutParams linearLayoutParams =
                    new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT);
            linearLayoutParams.setMargins(10, 0, 10, 0);
            numberPicker.setLayoutParams(linearLayoutParams);
        }

        timePicker = new TimePicker(this);
        timePicker.setIs24HourView(true);
        linearLayoutTitle.addView(datePicker);
        linearLayoutTitle.addView(timePicker);
        linearLayoutBase.addView(linearLayoutTitle);

        Calendar calendar = Calendar.getInstance();
        datePicker.init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH), null);

        timePicker.setCurrentHour(calendar.get(Calendar.HOUR_OF_DAY));
        timePicker.setCurrentMinute(calendar.get(Calendar.MINUTE));

        linearLayoutAction = new LinearLayout(this);
        linearLayoutAction.setOrientation(LinearLayout.HORIZONTAL);

        editText = new EditText(this);
        editText.setWidth(500);
        editText.setInputType(InputType.TYPE_CLASS_NUMBER);
        linearLayoutAction.addView(editText);

        radioGroup = new RadioGroup(this);

        RadioGroup.LayoutParams radioGroupLayoutParams = new RadioGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);


        RadioButton radioButton = new RadioButton(this);
        radioButton.setId(0);
        radioButton.setText("便前");
        radioButton.setChecked(true);
        radioGroup.addView(radioButton, radioGroupLayoutParams);
        radioButton = new RadioButton(this);
        radioButton.setId(1);
        radioButton.setText("便後");
        radioGroup.addView(radioButton, radioGroupLayoutParams);
        radioGroup.setOrientation(RadioGroup.HORIZONTAL);
        linearLayoutAction.addView(radioGroup);

        button = new Button(this);
        button.setText("按我");
        linearLayoutAction.addView(button);

        linearLayoutBase.addView(linearLayoutAction);

        String[] showText = new String[]{"DEMO1, DEMO2, DEMO3"};

        listView = new ListView(this);
        ListAdapter listAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, showText);
        listView.setAdapter(listAdapter);

        linearLayoutBase.addView(listView);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_data_monitor, menu);
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
