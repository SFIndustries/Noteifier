package sfi.noteifier;

/**
 * Created by Jasmin on 27.3.2017..
 */

public class NoteFB extends Note
{
    int fret;
    int string;

    NoteFB(int _fret, int _string)
    {
        fret = _fret;
        string = _string;

        FSToNoteName();
    }

    void FSToNoteName()
    {
        String[][] fretboard =
            {
                {"E4","F4","F#4","G4","G#4","A4","A#4","B4","C5","C#5","D5","D#5","E5","F5","F#5","G5","G#5","A5","A#5","B5","C6","C#6","D6","D#6","E6"},
                {"B3","C4","C#4","D4","D#4","E4","F4","F#4","G4","G#4","A4","A#4","B4","C5","C#5","D5","D#5","E5","F5","F#5","G5","G#5","A5","A#5","B5"},
                {"G3","G#3","A3","A#3","B3","C4","C#4","D4","D#4","E4","F4","F#4","G4","G#4","A4","A#4","B4","C5","C#5","D5","D#5","E5","F5","F#5","G5"},
                {"D3","D#3","E3","F3","F#3","G3","G#3","A3","A#3","B3","C4","C#4","D4","D#4","E4","F4","F#4","G4","G#4","A4","A#4","B4","C5","C#5","D5"},
                {"A2","A#2","B2","C3","C#3","D3","D#3","E3","F3","F#3","G3","G#3","A3","A#3","B3","C4","C#4","D4","D#4","E4","F4","F#4","G4","G#4","A4"},
                {"E2","F2","F#2","G2","G#2","A2","A#2","B2","C3","C#3","D3","D#3","E3","F3","F#3","G3","G#3","A3","A#3","B3","C4","C#4","D4","D#4","E4"}
            };

        String tempNote = fretboard[string][fret];

        if (tempNote.length() > 2)
            name = "" + tempNote.charAt(0) + tempNote.charAt(1);
        else
            name = Character.toString(tempNote.charAt(0));

        octave = Integer.parseInt(Character.toString(tempNote.charAt(tempNote.length() - 1)));
    }
}
