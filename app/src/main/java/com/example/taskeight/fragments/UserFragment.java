package com.example.taskeight.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.taskeight.R;
import com.example.taskeight.utils.SharedDataManager;

public class UserFragment extends Fragment {

    private TextView sharedDataText, profileName, memberSince, readingStreak, chaptersRead, bookmarksCount, readingPlanTitle, readingPlanProgressText;
    private EditText nameInput;
    private Button updateButton, editProfileButton, viewPlanButton, viewAllFavoritesButton;
    private ProgressBar readingPlanProgress;
    private ImageView profileImage;

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, container, false);

        nameInput = view.findViewById(R.id.user_input);
        updateButton = view.findViewById(R.id.user_share_button);
        profileName = view.findViewById(R.id.profile_name);
        memberSince = view.findViewById(R.id.member_since);
        readingStreak = view.findViewById(R.id.reading_streak);
        chaptersRead = view.findViewById(R.id.chapters_read);
        bookmarksCount = view.findViewById(R.id.bookmarks_count);
        readingPlanTitle = view.findViewById(R.id.reading_plan_title);
        readingPlanProgress = view.findViewById(R.id.reading_plan_progress);
        readingPlanProgressText = view.findViewById(R.id.reading_plan_progress_text);
        editProfileButton = view.findViewById(R.id.edit_profile_button);
        viewPlanButton = view.findViewById(R.id.view_plan_button);
        viewAllFavoritesButton = view.findViewById(R.id.view_all_favorites_button);
        profileImage = view.findViewById(R.id.background_image);

        setupButtonListeners();
        loadUserProfile();

        updateButton.setOnClickListener(v -> {
            String name = nameInput.getText().toString().trim();
            if (!name.isEmpty()) {
                SharedDataManager.getInstance().setUserName(name);
                profileName.setText(name);
                Toast.makeText(getContext(), "Profile updated successfully", Toast.LENGTH_SHORT).show();
                updateSharedDataText();
            } else {
                Toast.makeText(getContext(), "Please enter your name", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    private void setupButtonListeners() {
        editProfileButton.setOnClickListener(v ->
                Toast.makeText(getContext(), "Edit your profile settings below", Toast.LENGTH_SHORT).show()
        );

        viewPlanButton.setOnClickListener(v ->
                Toast.makeText(getContext(), "View Reading Plan Clicked", Toast.LENGTH_SHORT).show()
        );

        viewAllFavoritesButton.setOnClickListener(v ->
                Toast.makeText(getContext(), "View All Favorites Clicked", Toast.LENGTH_SHORT).show()
        );
    }

    private void loadUserProfile() {
        String currentName = SharedDataManager.getInstance().getUserName();
        if (!currentName.isEmpty()) {
            nameInput.setText(currentName);
            profileName.setText(currentName);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        updateSharedDataText();
        updateProfileData();
    }

    private void updateProfileData() {
        memberSince.setText("Member since: January 2023");
        readingStreak.setText("12 days");
        chaptersRead.setText("87");
        bookmarksCount.setText("14");
        readingPlanTitle.setText("Through the Bible in a Year");
        readingPlanProgress.setProgress(35);
        readingPlanProgressText.setText("55% Complete (128/365 days)");
    }

    private void updateSharedDataText() {
        if (sharedDataText != null) {
            String dataSummary = SharedDataManager.getInstance().getDataSummary();
            sharedDataText.setText(dataSummary.isEmpty() ? "No shared data available" : dataSummary);
        }
    }
}
