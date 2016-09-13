/*
 * Copyright (C) 2016 The Pure Nexus Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.pure.settings.fragments;

import android.app.Activity;
import android.app.ActivityManagerNative;
import android.app.IActivityManager;
import android.content.ContentResolver;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceScreen;
import android.provider.Settings;

import com.android.internal.logging.MetricsProto.MetricsEvent;

import com.android.settings.R;
import com.android.settings.SettingsPreferenceFragment;

public class PowerSettings extends SettingsPreferenceFragment {

    private static final String KEY_PROXIMITY_WAKE = "proximity_on_wake";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Activity activity = getActivity();
        final ContentResolver resolver = activity.getContentResolver();

        addPreferencesFromResource(R.xml.power_settings);

        boolean proximityCheckOnWake = getResources().getBoolean(
                com.android.internal.R.bool.config_proximityCheckOnWake);
        if (!proximityCheckOnWake) {
            getPreferenceScreen().removePreference(findPreference(KEY_PROXIMITY_WAKE));
            Settings.System.putInt(resolver, Settings.System.PROXIMITY_ON_WAKE, 0);
        }
    }

    @Override
    protected int getMetricsCategory() {
        return MetricsEvent.PURE_SETTINGS;
    }
}
