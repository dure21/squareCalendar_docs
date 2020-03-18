package io.yagus.squarecalendar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.squareup.timessquare.CalendarPickerView;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static com.squareup.timessquare.CalendarPickerView.SelectionMode.RANGE;

public class MainActivity extends AppCompatActivity {

    CalendarPickerView calendarView;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Calendar nextYear = Calendar.getInstance();
        nextYear.add(Calendar.YEAR, 1);

        calendarView = (CalendarPickerView) findViewById(R.id.calendar_view);
        Date today = new Date();
        calendarView.init(today, nextYear.getTime())
                .inMode(RANGE);

        calendarView.setOnDateSelectedListener(new CalendarPickerView.OnDateSelectedListener() {
            @Override
            public void onDateSelected(Date date) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);
                String str = String.format(Locale.getDefault(),"Selected : %d %02d %02d",
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH)+1,
                        calendar.get(Calendar.DATE));

                Toast.makeText(getApplicationContext(),str,Toast.LENGTH_SHORT).show();

                button.setEnabled(calendarView.getSelectedDates().size() > 1);
//                dateFinish();
            }

            @Override
            public void onDateUnselected(Date date) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);
                String str = String.format(Locale.getDefault(),"Deselected : %d %02d %02d",
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH)+1,
                        calendar.get(Calendar.DATE));
                Toast.makeText(getApplicationContext(),str,Toast.LENGTH_SHORT).show();
                button.setEnabled(calendarView.getSelectedDates().size() > 1);
            }
        });

        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(calendarView.getSelectedDates().size() == 2){
                    Toast.makeText(getApplicationContext(),"FINISH",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(),"날짜 2개를 입력해주세요.",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
