package edu.utsa.cs3443.betterhealth;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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

/**
 * The GoalActivity class handles setting the calorie goal
 * of the user and accessing the ResetActivity screen
 *
 * @author Alberto Gonzales
 * @author Daniel Salas
 * @author Evan Hudson
 * @author Michael Montesdeoca
 * @author Jayden Hendrix
 */
public class GoalActivity extends AppCompatActivity implements View.OnClickListener {

    private TextInputEditText goalText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_goal);

        setupButton(R.id.button7);
        setupButton(R.id.button11);
        setupButton(R.id.button14);

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
            if ( TextUtils.isDigitsOnly(goal) ) {
                int goalValue = Integer.parseInt(goal);
                goalValue = Math.abs(goalValue);
                setGoal(goalValue);
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Goal Set!", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(getApplicationContext(), "Invalid goal value", Toast.LENGTH_SHORT).show();
            }
        }
        else if(view.getId() == R.id.button11) {
            Intent intent = new Intent(this, ResetActivity.class);
            startActivity(intent);
        }
        else if(view.getId() == R.id.button14) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }

    /**
     * Function to setup button
     * @param buttonID ID of the button to setup
     */
    private void setupButton(int buttonID) {
        Button button = findViewById(buttonID);
        button.setOnClickListener(this);
    }

    /**
     * Function to allow use of setGoal
     * @param goal The new calorie goal to be set as an int
     */
    private void setGoal(int goal){
        Food.setGoal(this, goal);
    }

}
