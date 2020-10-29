package k.example.typingtutor;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class Result_Fragment extends Fragment {

    TextView textView;

    Button playAgain;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_result, container, false);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        textView=view.findViewById(R.id.txt_result_one);


        Bundle mArgs = getArguments();
        String one = mArgs.getString("one");
        String two = mArgs.getString("two");
        String three = mArgs.getString("three");
        String four = mArgs.getString("four");

        int lengthOfOne=one.isEmpty() ? 0 : one.split("\\s+").length;
        int lengthOfTwo=two.isEmpty() ? 0 : two .split("\\s+").length;
        int lengthOfThree=three.isEmpty() ? 0 : three.split("\\s+").length;
        int lengthOfFour=four.isEmpty() ? 0 : four.split("\\s+").length;

        int totalLength=lengthOfOne+lengthOfTwo+lengthOfThree+lengthOfFour;

        try {
            textView.setText("First input Word : "+lengthOfOne+"\n"+
                    "Second input Word : "+lengthOfTwo+"\n"+
                    "Third input Word : "+lengthOfThree+"\n"+
                    "Fourth input Word : "+lengthOfFour+"\n"+
                    "Total word per minute: "+totalLength
                    );


        }catch (Exception e){
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        playAgain=view.findViewById(R.id.btn_again);
        playAgain.setOnClickListener(v -> {
            Navigation.findNavController(view).navigate(R.id.action_result_Fragment_to_start_Fragment);
        });

    }
}