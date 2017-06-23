package com.taoxu.androiddeviceid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.taoxu.library.deviceid.DeviceIdUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView showText = (TextView) findViewById(R.id.my_text_view);
        showText.setText("唯一设备号:" + DeviceIdUtils.getUniqueDeviceId(this));
    }
}
