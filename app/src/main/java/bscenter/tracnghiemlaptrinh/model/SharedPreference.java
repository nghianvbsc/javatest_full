package bscenter.tracnghiemlaptrinh.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.List;

import bscenter.tracnghiemlaptrinh.model.objects.SaveTest;

/**
 * Created by NIT Admin on 04/06/2016
 */

public class SharedPreference {
    private static final String NAME_PREFS = "TRAC_NGHIEM_JAVA";
    private SharedPreferences prefs;
    private static SharedPreference pref;

    //config setting
    private static final String SOUND = "sound";
    private static final String VIBRATE = "vibrate";
    private static final String IS_PLAYED = "is_played";

    private SharedPreference(Context context) {
        prefs = context.getSharedPreferences(NAME_PREFS, Context.MODE_PRIVATE);
    }

    public static SharedPreference getInstance(Context context) {
        if (pref == null) {
            pref = new SharedPreference(context);
        }
        return pref;
    }

    public void onSetting(boolean sound, boolean vibrate) {
        SharedPreferences.Editor e = prefs.edit();
        e.putBoolean(SOUND, sound);
        e.putBoolean(VIBRATE, vibrate);
        e.apply();
    }

    public boolean isTurnSound() {
        return prefs.getBoolean(SOUND, true);
    }

    public boolean isTurnVibrate() {
        return prefs.getBoolean(VIBRATE, false);
    }

    public boolean isPlayed() {
        return prefs.getBoolean(IS_PLAYED, false);
    }

    private void setPlayed() {
        SharedPreferences.Editor e = prefs.edit();
        e.putBoolean(IS_PLAYED, true);
        e.apply();
    }

    public void saveLastTest(Level level, int score, int countTrueAnswer, int countQuestion, int timePlay) {

        if (isPlayed()) {
            SaveTest saveTest = SaveTest.findWithQuery(SaveTest.class, "SELECT * FROM savetest WHERE type = 1").get(0);
            saveTest.delete();
        }
        setPlayed();
        String timeDevice = TimeHelper.getCurrentTimeUTC();
        SaveTest saveTest = SaveTest.builder()
                .level(level.getValue())
                .trueAnswer(countTrueAnswer)
                .countQuestions(countQuestion)
                .score(score)
                .timeTest(timePlay)
                .timePlay(timeDevice)
                .type(1).build();
        saveTest.save();


        SaveTest saveBestTest = SaveTest.builder()
                .level(level.getValue())
                .trueAnswer(countTrueAnswer)
                .countQuestions(countQuestion)
                .score(score)
                .timeTest(timePlay == -1 ? Integer.MAX_VALUE : timePlay)
                .timePlay(timeDevice)
                .type(2).build();
        saveBestTest.save();

        List<SaveTest> tests = SaveTest.findWithQuery(SaveTest.class, "SELECT * FROM savetest WHERE type = 2");
        Log.d("TEST", "tests.size" + tests.size());
        if (tests.size() > 5) {
            SaveTest testDeleted = SaveTest.findWithQuery(SaveTest.class,
                    "SELECT * FROM savetest WHERE type = 2 ORDER BY score ASC, time_test DESC").get(0);
            testDeleted.delete();
        }
    }
}
