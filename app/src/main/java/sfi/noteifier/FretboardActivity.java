package sfi.noteifier;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class FretboardActivity extends Activity
{

    static double heightAdjust = 714.0/634.0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fretboard);

        SharedPreferences sharedPrefs = getSharedPreferences(MainActivity.PREFS_FILE_NAME, Context.MODE_PRIVATE);

        final TextView tv1 = (TextView) findViewById(R.id.textView);
        final TextView tv2 = (TextView) findViewById(R.id.textView2);
        ImageView imageFBZero = (ImageView) findViewById(R.id.imageView);
        final ImageView imageFB = (ImageView) findViewById(R.id.imageView4);

        final int[] viewCoords = new int[2];
        imageFB.getLocationOnScreen(viewCoords);

        int tempo = sharedPrefs.getInt("tempo", 120);
        tv1.setText(getString(R.string.tempo) + " " + Integer.toString(tempo));

        int ts1 = sharedPrefs.getInt("time_signature_lower", 4);
        int ts2 = sharedPrefs.getInt("time_signature_upper", ts1);
        tv1.setText(getString(R.string.time_signature) + " " + Integer.toString(ts1) + "/" + Integer.toString(ts2));

        imageFB.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {

                if (event.getAction() == MotionEvent.ACTION_DOWN)
                {
                    Point2D touchPoint = new Point2D(event.getX(), event.getY());
                    touchPoint.sub((double) viewCoords[0], (double) viewCoords[1]);

                    //tv.setText(String.valueOf(touchPoint.getX()) + ", " + String.valueOf(touchPoint.getY()));

                    // int fret = (int) Math.round(23 * touchPoint.getX()/((double) viewCoords[0] + imageFB.getWidth()))+ 1;
                    int fret = (int) Math.floor(24 * touchPoint.getX()/((double) viewCoords[0] + imageFB.getWidth()))+ 1;
                    int string = (int) Math.floor(6 * (heightAdjust * touchPoint.getY()/((double) viewCoords[1] + imageFB.getHeight())))+ 1;

                    // tv.setText(String.valueOf(string) + " " + String.valueOf(heightAdjust * touchPoint.getY()/((double) viewCoords[1] + imageFB.getHeight())));
                    // tv.setText(String.valueOf(string) + " " + String.valueOf(fret) + " " + String.valueOf(heightAdjust));

                    NoteFB newNote = new NoteFB(fret, string);

                }
                return true;
            }
        });

    }
}
