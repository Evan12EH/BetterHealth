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

import java.util.ArrayList;
import java.util.List;

import edu.utsa.cs3443.betterhealth.model.Food;

/**
 * The MainActivity class handles the main screen view, accessing
 * the AddFoodActivity, NextActivity, and GoalActivity classes,
 * and displaying the summary of the user's data
 *
 * @author Alberto Gonzales
 * @author Daniel Salas
 * @author Evan Hudson
 * @author Michael Montesdeoca
 * @author Jayden Hendrix
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView textView9;
    private String allFoods;
    private String allProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        checkFiles();
        setupButton(R.id.button);
        setupButton(R.id.button2);
        setupButton(R.id.button6);
        checkIsEmpty();
        allProgress = getInfo();
        textView9 = findViewById(R.id.textView9);
        allFoods = Food.readData(this);

        textView9.setText(allProgress);

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
            int difference = calculateGoal();
            int current = getCurrent();
            String summary = makeSummary(difference, current);
            intent.putExtra("summary", summary);
            intent.putExtra("difference", difference);
            addProgress();
            nextDay();
            startActivity(intent);
        }
        else if(view.getId() == R.id.button6) {
            Intent intent = new Intent(this, GoalActivity.class);
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
     * Function to allow use of nextDay
     */
    private void nextDay(){
        Food.nextDay(this);
    }

    /**
     * Function to allow use of addProgress
     */
    private void addProgress(){
        Food.addProgress(this);
    }

    /**
     * Function to allow use of checkIsEmpty
     */
    private void checkIsEmpty(){
        Food.checkIsEmpty(this);
    }

    /**
     * Function to allow use of calculateGoal
     */
    private int calculateGoal(){
        return Food.calculateGoal(this);
    }

    /**
     * Function to allow use of makeSummary
     * @param difference The difference in calories eaten versus
     * @param current The current calorie goal of the user as an int
     * @return A summary of your eating progress of the day given as a string
     */
    private String makeSummary(int difference, int current){
        return Food.makeSummary(this, difference, current);
    }

    /**
     * Function to allow use of getCurrent
     */
    private int getCurrent(){
        return Food.getCurrent(this);
    }

    /**
     * Function to allow use of getInfo
     */
    private String getInfo(){
        return Food.getInfo(this);
    }

    /**
     * Function to allow use of checkFiles
     */
    private void checkFiles(){
        Food.checkFiles(this);
    }

}