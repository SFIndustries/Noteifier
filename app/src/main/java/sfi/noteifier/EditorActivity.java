package sfi.noteifier;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class EditorActivity extends Activity {

    Button btnAddNew;

    LinearLayout linearLayoutSheet;
    LinearLayout linearLayoutLines;
    RelativeLayout relativeLayoutSheet;

    ImageView imageViewUp;
    ImageView imageViewDown;
    ImageView imageViewLines;

    int[] linearLayoutSheetCoordinates;

    int nLevels;
    int levelHeight;

    int linesHeight;

    int noteHeightInLevels = 4;

    float noteImageRatio;
    int noteWidth;
    int noteHeight;

    static int spaceWidthPercent = 20;

    int noteCounter = 1;

    static float percent = 0.02f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        btnAddNew = (Button) findViewById(R.id.btnAddNew);

        btnAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EditorActivity.this, FretboardActivity.class));
            }
        });

        linearLayoutSheet = (LinearLayout) findViewById(R.id.linearLayoutSheet);
        linearLayoutLines = (LinearLayout) findViewById(R.id.linearLayoutLines);

        relativeLayoutSheet = (RelativeLayout) findViewById(R.id.relativeLayoutSheet);

        imageViewUp = (ImageView) findViewById(R.id.imageViewUp);
        imageViewDown = (ImageView) findViewById(R.id.imageViewDown);
        imageViewLines = (ImageView) findViewById(R.id.imageViewLines);
    }

    @Override
    public void onWindowFocusChanged (boolean hasFocus) {

        int sheetHeight = linearLayoutSheet.getHeight();
        linesHeight = linearLayoutLines.getHeight();

        int imageViewLinesHeight = imageViewLines.getHeight();
        int imageViewLinesWeight = imageViewLines.getWidth();

        imageViewUp.setLayoutParams(new LinearLayout.LayoutParams(
                imageViewLinesWeight,
                imageViewLinesHeight));
        imageViewDown.setLayoutParams(new LinearLayout.LayoutParams(
                imageViewLinesWeight,
                imageViewLinesHeight));

        nLevels = (int) linearLayoutSheet.getWeightSum() * 2;

        levelHeight = 2 * sheetHeight / nLevels;
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

//        for(int i = 0; i <= nLevels; i++) {
//            addNote(i);
//        }

        addNote(10, R.drawable._4th_note);
        addNote(12, R.drawable._4th_note);
        addNote(10, R.drawable._4th_note);
        addNote(15, R.drawable._4th_note);
        addNote(8, R.drawable._4th_note);
        addNote(7, R.drawable._4th_note);
        addNote(13, R.drawable._4th_note);
        addNote(14, R.drawable._4th_note);

    }

    void addNote(int level, int note) {
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(note);

        imageView.setLayoutParams(new LinearLayout.LayoutParams(
                noteWidth,
                noteHeight));

        imageView.setScaleType(ImageView.ScaleType.FIT_XY);

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(noteWidth, noteHeight);
        relativeLayoutSheet.addView(imageView, params);

        imageView.setX(noteCounter * noteWidth);
        imageView.setY((nLevels - level) * levelHeight/2 - noteHeight + percent*linesHeight);

        noteCounter += 2;
    }

}
