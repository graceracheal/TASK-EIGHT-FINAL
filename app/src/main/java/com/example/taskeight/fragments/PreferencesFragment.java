package com.example.taskeight.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.taskeight.R;
import com.example.taskeight.utils.SharedDataManager;

public class PreferencesFragment extends Fragment {

    private TextView sharedDataText;
    private CheckBox darkModeCheckbox;
    private CheckBox notificationsCheckbox;
    private Button saveButton;
    private RadioGroup bibleVersionGroup;
    private RadioGroup fontSizeGroup;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_preferences, container, false);
        sharedDataText = view.findViewById(R.id.preferences_shared_data_text);
        darkModeCheckbox = view.findViewById(R.id.dark_mode_checkbox);
        notificationsCheckbox = view.findViewById(R.id.notifications_checkbox);
        saveButton = view.findViewById(R.id.preferences_save_button);
        bibleVersionGroup = view.findViewById(R.id.bible_version_group);
        fontSizeGroup = view.findViewById(R.id.font_size_group);

        loadPreferences();

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean darkMode = darkModeCheckbox.isChecked();
                SharedDataManager.getInstance().setDarkMode(darkMode);
                boolean notifications = notificationsCheckbox.isChecked();
                SharedDataManager.getInstance().setNotificationsEnabled(notifications);

                int selectedVersionId = bibleVersionGroup.getCheckedRadioButtonId();
                RadioButton selectedVersion = view.findViewById(selectedVersionId);
                if (selectedVersion != null) {
                    String version = selectedVersion.getText().toString().substring(0, 3); // Get abbreviated version name (KJV, NIV, etc.)
                    SharedDataManager.getInstance().setCurrentBibleVersion(version);
                }

                int selectedFontSizeId = fontSizeGroup.getCheckedRadioButtonId();
                int fontSize = 16;
                if (selectedFontSizeId == R.id.font_size_small) {
                    fontSize = 14;
                } else if (selectedFontSizeId == R.id.font_size_large) {
                    fontSize = 18;
                }
                SharedDataManager.getInstance().setFontSize(fontSize);

                updateSharedDataText();

                Toast.makeText(getContext(), "Preferences saved successfully", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        loadPreferences();
        updateSharedDataText();
    }

    private void loadPreferences() {
        darkModeCheckbox.setChecked(SharedDataManager.getInstance().isDarkMode());

        notificationsCheckbox.setChecked(SharedDataManager.getInstance().isNotificationsEnabled());
        String currentVersion = SharedDataManager.getInstance().getCurrentBibleVersion();
        if (bibleVersionGroup != null) {
            for (int i = 0; i < bibleVersionGroup.getChildCount(); i++) {
                View child = bibleVersionGroup.getChildAt(i);
                if (child instanceof RadioButton) {
                    RadioButton button = (RadioButton) child;
                    if (button.getText().toString().startsWith(currentVersion)) {
                        button.setChecked(true);
                        break;
                    }
                }
            }
        }

        int fontSize = SharedDataManager.getInstance().getFontSize();
        if (fontSizeGroup != null) {
            if (fontSize <= 14) {
                RadioButton small = saveButton.findViewById(R.id.font_size_small);
                if (small != null) small.setChecked(true);
            } else if (fontSize >= 18) {
                RadioButton large = saveButton.findViewById(R.id.font_size_large);
                if (large != null) large.setChecked(true);
            } else {
                RadioButton medium = saveButton.findViewById(R.id.font_size_medium);
                if (medium != null) medium.setChecked(true);
            }
        }
    }

    private void updateSharedDataText() {
        if (sharedDataText != null) {
            String dataSummary = SharedDataManager.getInstance().getDataSummary();
            if (!dataSummary.isEmpty()) {
                sharedDataText.setText(dataSummary);
            } else {
                sharedDataText.setText("No shared data available");
            }
        }
    }
}