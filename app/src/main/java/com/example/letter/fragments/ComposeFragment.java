package com.example.letter.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.letter.MainActivity;
import com.example.letter.R;
import com.parse.ParseUser;

public class ComposeFragment extends Fragment {

    public static final String TAG = "ComposeFragment";
    private EditText etHeading;
    private Spinner spCategory;
    private EditText etContent;
    private Button btnSubmit;

    public ComposeFragment() {
        // Required empty public constructor
    }

    // The onCreateView method is called when Fragment should create its View object hierarchy,
    // either dynamically or via XML layout inflation.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_compose, container, false);
    }

    // This event is triggered soon after onCreateView().
    // Any view setup should occur here.  E.g., view lookups and attaching view listeners.
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Setup any handles to view objects here
        // EditText etFoo = (EditText) view.findViewById(R.id.etFoo);
        super.onViewCreated(view, savedInstanceState);
        etHeading = view.findViewById(R.id.etHeading);
        spCategory= view.findViewById(R.id.spCategory);
        etContent= view.findViewById(R.id.etContent);
        btnSubmit= view.findViewById(R.id.btnSubmit);

        spCategory = view.findViewById(R.id.spCategory);
        //create a list of items for the spinner.
        String[] items = new String[]{"Love", "Friendship", "Work","Study","Other..."};
        //adapter created to describe how the items are displayed. 'this' is used instead of getContext() in some samples
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, items);
        //set the spinners adapter to the previously created one.
        spCategory.setAdapter(adapter);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String heading = etHeading.getText().toString();
                String content = etContent.getText().toString();
                //TODO: include a condition to check if Category is selected. Must find the what type the spinner content is.
                if(heading.isEmpty()){
                    Toast.makeText(getContext(),"Heading cannot be empty",Toast.LENGTH_SHORT).show();
                    return;
                }

                if(content.isEmpty()){
                    Toast.makeText(getContext(),"Content cannot be empty",Toast.LENGTH_SHORT).show();
                    return;
                }
                ParseUser currentUser = ParseUser.getCurrentUser();
                //save post and some other components must be added. check 5:35 of ep 8
            }
        });
    }

}