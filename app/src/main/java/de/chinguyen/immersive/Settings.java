package de.chinguyen.immersive;

import android.os.Bundle;
import android.support.v7.preference.PreferenceFragmentCompat;

public class Settings extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.preferences);
    }
}
