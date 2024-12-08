package edu.utsa.cs3443.betterhealth;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

/**
 * The NextActivity class handles summarizing and reseting
 * data for the user to prepare for the next day
 *
 * @author Alberto Gonzales
 * @author Daniel Salas
 * @author Evan Hudson
 * @author Michael Montesdeoca
 * @author Jayden Hendrix
 */
public class NextActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView textView8;
    private ImageView imageView;
    private String summary;
    private int difference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_next);

        setupButton(R.id.button8);

        Intent intent = getIntent();
        summary = intent.getStringExtra("summary");
        difference = intent.getIntExtra("difference", 0);
        textView8 = findViewById(R.id.textView8);
        imageView = findViewById(R.id.imageView);

        if (difference > 0) {
            imageView.setImageResource(R.drawable.greenyes);
        }
        else if (difference < 0) {
            imageView.setImageResource(R.drawable.redno);
        }
        else if (difference == 0) {
            imageView.setImageResource(R.drawable.gray);
        }

        textView8.setText(summary);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.button8) {
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
}