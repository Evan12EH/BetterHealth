package edu.utsa.cs3443.betterhealth;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

import java.security.cert.CRL;

import edu.utsa.cs3443.betterhealth.model.Food;

/**
 * The AddFoodActivity class handles adding the food
 * food the user's eaten and accessing the DeleteFoodActivity
 * and CreateFoodActivity screens
 *
 * @author Alberto Gonzales
 * @author Daniel Salas
 * @author Evan Hudson
 * @author Michael Montesdeoca
 * @author Jayden Hendrix
 */
public class AddFoodActivity extends AppCompatActivity implements View.OnClickListener {

    private String allFoods;
    private int caloriesValue;
    private TextInputEditText foodText;
    private TextView textView5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_addfood);

        textView5 = findViewById(R.id.textView5);

        setupButton(R.id.button3);
        setupButton(R.id.button4);
        setupButton(R.id.button12);
        setupButton(R.id.button17);
        foodText = findViewById(R.id.editText3);

        Intent intent = getIntent();
        allFoods = intent.getStringExtra("allFoods");

        textView5.setText(allFoods);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.button3) {
            Intent intent = new Intent(this, CreateFoodActivity.class);
            startActivity(intent);
        }
        else if(view.getId() == R.id.button4) {
            String food = foodText.getText().toString().trim();
            caloriesValue = getCalories(food);
            if(caloriesValue == 0) {
                Toast.makeText(getApplicationContext(), "Calories cannot be 0", Toast.LENGTH_SHORT).show();
            }
            else {
                addFood(caloriesValue);
                Toast.makeText(getApplicationContext(), "Food added!", Toast.LENGTH_SHORT).show();
                foodText.setText("");
            }
        }
        else if(view.getId() == R.id.button12) {
            Intent intent = new Intent(this, DeleteFoodActivity.class);
            startActivity(intent);
        }
        else if(view.getId() == R.id.button17) {
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
     * Function to allow use of getCalories
     * @param food The name of a food as a string
     * @return The number of calories returned as an
     * int from the food given as a parameter
     */
    private int getCalories(String food){
        return Food.getCalories(this, food);
    }

    /**
     * Function to allow use of addFood
     * @param calories The calories to be added to the current total as an int
     */
    private void addFood(int calories){
        Food.addFood(this, calories);
    }

}