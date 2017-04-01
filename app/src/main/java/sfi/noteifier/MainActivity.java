package sfi.noteifier;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity
{
    public static final String PREFS_FILE_NAME = "SharedPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
}
