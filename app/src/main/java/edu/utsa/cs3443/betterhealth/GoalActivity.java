package edu.utsa.cs3443.betterhealth;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

import edu.utsa.cs3443.betterhealth.model.Food;

public class GoalActivity extends AppCompatActivity implements View.OnClickListener {

    private TextInputEditText goalText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_goal);

        setupButton(R.id.button7);
        setupButton(R.id.button11);

        goalText = findViewById(R.id.editText4);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.button7) {
            String goal = goalText.getText().toString().trim();
            int goalValue = Integer.parseInt(goal);
            setGoal(goalValue);
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            Toast.makeText(getApplicationContext(), "Goal Set!", Toast.LENGTH_SHORT).show();
        }
        if(view.getId() == R.id.button11) {
            Intent intent = new Intent(this, ResetActivity.class);
            startActivity(intent);
        }
    }

    private void setupButton(int buttonID) {
        Button button = findViewById(buttonID);
        button.setOnClickListener(this);
    }

    private void setGoal(int goal){
        Food.setGoal(this, goal);
    }

}
