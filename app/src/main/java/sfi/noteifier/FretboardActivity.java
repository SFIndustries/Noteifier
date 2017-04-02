package sfi.noteifier;

import android.app.Activity;
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

        final TextView tv = (TextView) findViewById(R.id.textView);
        ImageView imageFBZero = (ImageView) findViewById(R.id.imageView);
        final ImageView imageFB = (ImageView) findViewById(R.id.imageView4);

        final int[] viewCoords = new int[2];
        imageFB.getLocationOnScreen(viewCoords);

        imageFB.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {

                if (event.getAction() == MotionEvent.ACTION_DOWN)
                {
                    // mislim da ova klasa nema smisla
                    // jer se jedino koristi na ovom mjestu
                    // i s tim objektom se jedino radi jedno oduzimanje
                    // tak da prakticki se samo radi set() pa get()
                    // dovoljno je da x i y budu lokalne varijable

                    // int x = event.getX() - viewCoords[0];
                    // int y = event.getY() - viewCoords[1];
                    // ...

                    Point2D touchPoint = new Point2D(event.getX(), event.getY());
                    touchPoint.sub((double) viewCoords[0], (double) viewCoords[1]);

                    //tv.setText(String.valueOf(touchPoint.getX()) + ", " + String.valueOf(touchPoint.getY()));

                    // int fret = (int) Math.round(23 * touchPoint.getX()/((double) viewCoords[0] + imageFB.getWidth()))+ 1;
                    int fret = (int) Math.floor(24 * touchPoint.getX()/((double) viewCoords[0] + imageFB.getWidth()))+ 1;
                    int string = (int) Math.floor(6 * (heightAdjust * touchPoint.getY()/((double) viewCoords[1] + imageFB.getHeight())))+ 1;

                    // tv.setText(String.valueOf(string) + " " + String.valueOf(heightAdjust * touchPoint.getY()/((double) viewCoords[1] + imageFB.getHeight())));
                    tv.setText(String.valueOf(string) + " " + String.valueOf(fret) + " " + String.valueOf(heightAdjust));

                    NoteFB newNote = new NoteFB(fret, string);

                }
                return true;
            }
        });

    }
}
