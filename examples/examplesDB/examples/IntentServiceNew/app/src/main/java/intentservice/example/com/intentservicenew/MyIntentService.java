package intentservice.example.com.intentservicenew;

import android.app.Activity;
import android.app.IntentService;
import android.content.Intent;

/**
 * Created by thiag_000 on 29/03/2017.
 */

public class MyIntentService extends IntentService {

    private int result = Activity.RESULT_CANCELED;
    public static final String NAME = "name";
    public static final String RESULT = "result";
    public static final String NOTIFICATION = "com.example.intentservice";

    public MyIntentService() {
        super("MyIntentService");
    }


    // Will be called asynchronously be Android
    @Override
    protected void onHandleIntent(Intent intent) {
        String name = intent.getStringExtra(NAME);
        // Successful finished
        result = Activity.RESULT_OK;

        publishResults(result);
    }

    private void publishResults(int result) {
        Intent intent = new Intent(NOTIFICATION);
        intent.putExtra(RESULT, result);
        sendBroadcast(intent);
    }
}
