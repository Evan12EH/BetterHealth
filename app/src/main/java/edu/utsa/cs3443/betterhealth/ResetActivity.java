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

import edu.utsa.cs3443.betterhealth.model.Food;

/**
 * The ResetActivity class handles the logic for
 * resetting the data in the app
 *
 * @author Alberto Gonzales
 * @author Daniel Salas
 * @author Evan Hudson
 * @author Michael Montesdeoca
 * @author Jayden Hendrix
 */
public class ResetActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_reset);

        setupButton(R.id.button9);
        setupButton(R.id.button10);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.button9) {
            Intent intent = new Intent(this, MainActivity.class);
            resetProgress();
            Toast.makeText(getApplicationContext(), "Progress reset!", Toast.LENGTH_SHORT).show();
            startActivity(intent);
        }
        else if(view.getId() == R.id.button10) {
            Intent intent = new Intent(this, MainActivity.class);
            Toast.makeText(getApplicationContext(), "Progress kept", Toast.LENGTH_SHORT).show();
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
     * Function to allow use of resetProgress
     */
    private void resetProgress() {
        Food.resetProgress(this);
    }

}
