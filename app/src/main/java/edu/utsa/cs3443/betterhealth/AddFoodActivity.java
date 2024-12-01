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
    }

    private void setupButton(int buttonID) {
        Button button = findViewById(buttonID);
        button.setOnClickListener(this);
    }

    private int getCalories(String food){
        return Food.getCalories(this, food);
    }

    private void addFood(int calories){
        Food.addFood(this, calories);
    }

}