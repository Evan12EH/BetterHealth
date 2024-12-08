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

/**
 * The CreateFoodActivity class is used to create
 * new foods to be added to the application
 *
 * @author Alberto Gonzales
 * @author Daniel Salas
 * @author Evan Hudson
 * @author Michael Montesdeoca
 * @author Jayden Hendrix
 */
public class CreateFoodActivity extends AppCompatActivity implements View.OnClickListener {

    private TextInputEditText foodText;
    private TextInputEditText caloriesText;
    private String allFoods;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_createfood);

        setupButton(R.id.button5);
        setupButton(R.id.button16);
        foodText = findViewById(R.id.editText);
        caloriesText = findViewById(R.id.editText2);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.button5) {
            String food = foodText.getText().toString().trim();
            String calories = caloriesText.getText().toString().trim();
            try {
                int caloriesInt = Integer.parseInt(calories);
                caloriesInt = Math.abs(caloriesInt);
                createFood(food, caloriesInt);
                allFoods = Food.readData(this);
                Toast.makeText(getApplicationContext(), "Food added!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, AddFoodActivity.class);
                intent.putExtra("allFoods", allFoods);
                startActivity(intent);
            } catch (NumberFormatException e) {
                Toast.makeText(getApplicationContext(), "Invalid calorie value", Toast.LENGTH_SHORT).show();
            }
        }
        else if(view.getId() == R.id.button16) {
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
     * Function to allow use of createFood
     * @param food The name of a food as a string
     * @param calories The calories of the respective food as an int
     */
    private void createFood(String food, int calories){
        Food.createFood(this, food, calories);
    }

    /**
     * Function to allow use of readData
     */
    private void readData(){
        Food.readData(this);
    }

}
