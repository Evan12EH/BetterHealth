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

import java.util.ArrayList;
import java.util.List;

import edu.utsa.cs3443.betterhealth.model.Food;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private String allFoods;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        setupButton(R.id.button);
        setupButton(R.id.button2);
        checkIsEmpty();
        allFoods = Food.readData(this);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.button) {
            Intent intent = new Intent(this, AddFoodActivity.class);
            intent.putExtra("allFoods", allFoods);
            startActivity(intent);
        }
        else if(view.getId() == R.id.button2) {
            Intent intent = new Intent(this, NextActivity.class);
            resetProgress();
            nextDay();
            startActivity(intent);
        }
    }

    private void setupButton(int buttonID) {
        Button button = findViewById(buttonID);
        button.setOnClickListener(this);
    }

    private void nextDay(){
        Food.nextDay(this);
    }

    private void resetProgress(){
        Food.resetProgress(this);
    }

    private void checkIsEmpty(){
        Food.checkIsEmpty(this);
    }

}