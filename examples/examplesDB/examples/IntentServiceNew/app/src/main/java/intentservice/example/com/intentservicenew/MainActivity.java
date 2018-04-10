package intentservice.example.com.intentservicenew;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

    private TextView textView;
    private BroadcastReceiver receiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle bundle = intent.getExtras();
            handleResult(bundle);
        }


    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.status);
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(receiver, new IntentFilter(
                MyIntentService.NOTIFICATION));
    }


    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiver);
    }

    public void onClick(View view) {

        Intent intent = new Intent(this, MyIntentService.class);
        // add infos for the service which file to download and where to store
        intent.putExtra(MyIntentService.NAME, "teste");
        startService(intent);
        textView.setText("Service started");
    }

    private void handleResult(Bundle bundle) {
        if (bundle != null) {
            int resultCode = bundle.getInt(MyIntentService.RESULT);
            if (resultCode == RESULT_OK) {
                textView.setText("Service done");
            } else {
                textView.setText("Service failed");
            }
        }
    }

}
