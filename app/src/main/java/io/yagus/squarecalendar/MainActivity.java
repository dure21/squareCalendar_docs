package io.yagus.squarecalendar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.squareup.timessquare.CalendarPickerView;

import java.util.Calendar;
import java.util.Date;

import static com.squareup.timessquare.CalendarPickerView.SelectionMode.RANGE;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Calendar nextYear = Calendar.getInstance();
        nextYear.add(Calendar.YEAR, 1);

        CalendarPickerView calendar = (CalendarPickerView) findViewById(R.id.calendar_view);
        Date today = new Date();
        calendar.init(today, nextYear.getTime())
                .inMode(RANGE);

        calendar.setOnDateSelectedListener(new CalendarPickerView.OnDateSelectedListener() {
            @Override
            public void onDateSelected(Date date) {
                Toast.makeText(getApplicationContext(),"Selected:"+date,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDateUnselected(Date date) {
                Toast.makeText(getApplicationContext(),"Unselected:"+date,Toast.LENGTH_SHORT).show();
            }
        });

    }
}
