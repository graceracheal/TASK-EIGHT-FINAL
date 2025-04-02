package com.example.taskeight.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.taskeight.R;
import com.example.taskeight.utils.SharedDataManager;

public class DashboardFragment extends Fragment {

    private TextView verseOfDay;
    private TextView readingStreak;
    private TextView lastReadPassage;
    private LinearLayout readBibleButton;
    private LinearLayout devotionalButton;
    private LinearLayout prayerButton;
    private TextView searchBible;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        findViews(view);

        updateDashboardData();
        setupClickListeners();

        return view;
    }
    private void findViews(View view) {
        verseOfDay = view.findViewById(R.id.verse_of_day);
        readingStreak = view.findViewById(R.id.reading_streak);
        lastReadPassage = view.findViewById(R.id.last_read_passage);
        readBibleButton = view.findViewById(R.id.read_bible_button);
        devotionalButton = view.findViewById(R.id.devotional_button);
        prayerButton = view.findViewById(R.id.prayer_button);
        searchBible = view.findViewById(R.id.search_bible);
    }

    private void setupClickListeners() {
        readBibleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedDataManager.getInstance().setLastReadPassage("Psalms 23");
                SharedDataManager.getInstance().incrementReadingStreak();
                updateDashboardData();
                Toast.makeText(getContext(), "Reading Bible", Toast.LENGTH_SHORT).show();
            }
        });

        devotionalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Opening Devotional", Toast.LENGTH_SHORT).show();
            }
        });

        prayerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Opening Prayer Section", Toast.LENGTH_SHORT).show();
            }
        });
        searchBible.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Searching Bible", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        updateDashboardData();
    }
    private void updateDashboardData() {
        SharedDataManager dataManager = SharedDataManager.getInstance();
        String dailyVerse = dataManager.getDailyVerse();
        if (verseOfDay != null) {
            verseOfDay.setText(dailyVerse);
        }
        if (readingStreak != null) {
            int streak = dataManager.getReadingStreak();
            readingStreak.setText(streak + " days");
        }
        if (lastReadPassage != null) {
            String passage = dataManager.getLastReadPassage();
            if (passage != null && !passage.isEmpty()) {
                lastReadPassage.setText("Last read: " + passage);
            } else {
                lastReadPassage.setText("Start your reading journey today");
            }
        }
    }
}