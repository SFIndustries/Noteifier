package sfi.noteifier;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class EditorActivity extends Activity
{
    LinearLayout linearLayoutSheet;
    LinearLayout linearLayoutLines;
    RelativeLayout relativeLayoutSheet;

    int[] linearLayoutSheetCoordinates;

    int nLevels;
    int levelHeight;

    int noteHeightInLevels = 4;

    float noteImageRatio;
    int noteWidth;
    int noteHeight;

    int noteCounter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        linearLayoutSheet = (LinearLayout) findViewById(R.id.linearLayoutSheet);
        linearLayoutLines = (LinearLayout) findViewById(R.id.linearLayoutLines);

        relativeLayoutSheet = (RelativeLayout) findViewById(R.id.relativeLayoutSheet);
    }

    @Override
    public void onWindowFocusChanged (boolean hasFocus) {

        int sheetHeight = linearLayoutSheet.getHeight();
        int linesHeight = linearLayoutLines.getHeight();

        nLevels = (int) linearLayoutSheet.getWeightSum();

        levelHeight = sheetHeight / nLevels;
        levelHeight = linesHeight / 4;

        BitmapFactory.Options dimensions = new BitmapFactory.Options();
        dimensions.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), R.drawable._4th_note, dimensions);
        int imageHeight = dimensions.outHeight;
        int imageWidth =  dimensions.outWidth;

        noteImageRatio = (float) imageWidth / imageHeight;
        noteHeight = levelHeight * noteHeightInLevels;
        noteWidth = (int) (noteHeight * noteImageRatio);

        linearLayoutSheetCoordinates = new int[2];
        linearLayoutSheet.getLocationOnScreen(linearLayoutSheetCoordinates);

        for(int i = 0; i <= nLevels; i++) {
            addNote(i);
        }

    }

    void addNote(int level) {
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(R.drawable._4th_note);

        imageView.setLayoutParams(new LinearLayout.LayoutParams(
                noteWidth,
                noteHeight));

        imageView.setScaleType(ImageView.ScaleType.FIT_XY);

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(noteWidth, noteHeight);
        relativeLayoutSheet.addView(imageView, params);

        imageView.setX(noteCounter++ * noteWidth);
        imageView.setY((nLevels - level) * levelHeight - noteHeight);

    }
}
