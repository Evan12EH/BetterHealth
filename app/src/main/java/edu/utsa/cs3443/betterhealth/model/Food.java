package edu.utsa.cs3443.betterhealth.model;

import android.content.Context;
import android.content.res.AssetManager;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import edu.utsa.cs3443.betterhealth.MainActivity;

public class Food {

    private String name;
    private String calories;

    public static String readData(Context context) {
        FileInputStream fis = null;
        Scanner scan = null;
        String filename = "foodInfo.txt";
        StringBuilder allFoods = new StringBuilder();
        try {
            fis = context.openFileInput("foodInfo.txt");
            scan = new Scanner(fis);

            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                if (allFoods.length() > 0) {
                    allFoods.append("\n");
                }
                allFoods.append(line);
            }

        } catch (IOException e) {}
        return allFoods.toString();
    }

    public static void createFood(Context context, String food, int calories){
        try {
            OutputStream out = context.openFileOutput("foodInfo.txt", Context.MODE_APPEND );
            String updatedData = food + ": " + calories + "cal\n";
            out.write(updatedData.getBytes(StandardCharsets.UTF_8));
            out.close();
        } catch (IOException e) {
            System.out.println("Failed to write data");
        }
    }

    public static int getCalories(Context context, String food){
        FileInputStream fis = null;
        Scanner scan = null;
        String filename = "foodInfo.txt";
        String calorieText = null;
        int calories = 0;
        StringBuilder allFoods = new StringBuilder();
        try {
            fis = context.openFileInput("foodInfo.txt");
            scan = new Scanner(fis);

            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                if (line.contains(food)) {
                    String[] parts = line.split(":");
                    String newPart = parts[1];
                    calorieText = parts[1].substring(0, parts[1].length() - 3);
                    calories = Integer.parseInt(calorieText);
                    return calories;
                }
            }

        } catch (IOException e) {}
        return calories;
    }

    public static void addFood(Context context, int calories){
        FileInputStream fis = null;
        Scanner scan = null;
        String filename = "current.txt";
        try {
            fis = context.openFileInput("current.txt");
            scan = new Scanner(fis);
            int currentDay = 0;
            int currentCalories = 0;

            if (scan.hasNextLine()) {
                currentDay = Integer.parseInt(scan.nextLine());
            }
            if (scan.hasNextLine()) {
                currentCalories = Integer.parseInt(scan.nextLine()); // Read the second line as an integer
            }

            try {
                OutputStream out = context.openFileOutput("progress.txt", Context.MODE_APPEND );
                String updatedData = currentDay + "," + currentCalories + "\n";
                out.write(updatedData.getBytes(StandardCharsets.UTF_8));
                out.close();
            } catch (IOException e) {
                System.out.println("Failed to write data");
            }

        } catch (IOException e) {}
    }

    public static void nextDay(Context context){
        FileInputStream fis = null;
        Scanner scan = null;
        String filename = "current.txt";
        try {
            fis = context.openFileInput("current.txt");
            scan = new Scanner(fis);
            int currentDay = 0;
            int currentCalories = 0;

            if (scan.hasNextLine()) {
                currentDay = Integer.parseInt(scan.nextLine());
            }

            currentDay += 1;

            try {
                OutputStream out = context.openFileOutput("current.txt", Context.MODE_PRIVATE );
                String updatedData = currentDay + "\n" + currentCalories + "\n";
                out.write(updatedData.getBytes(StandardCharsets.UTF_8));
                out.close();
            } catch (IOException e) {
                System.out.println("Failed to write data");
            }

        } catch (IOException e) {}
    }

    public static void resetProgress(Context context){
        FileInputStream fis = null;
        Scanner scan = null;
        String filename = "current.txt";
        try {
            fis = context.openFileInput("current.txt");
            scan = new Scanner(fis);
            int currentDay = 0;
            int currentCalories = 0;

            if (scan.hasNextLine()) {
                currentDay = Integer.parseInt(scan.nextLine());
            }
            if (scan.hasNextLine()) {
                currentCalories = Integer.parseInt(scan.nextLine()); // Read the second line as an integer
            }

            try {
                OutputStream out = context.openFileOutput("progress.txt", Context.MODE_APPEND );
                String updatedData = currentDay + "," + currentCalories + "\n";
                out.write(updatedData.getBytes(StandardCharsets.UTF_8));
                out.close();
            } catch (IOException e) {
                System.out.println("Failed to write data");
            }

        } catch (IOException e) {}
    }

    public static void checkIsEmpty(Context context){
        FileInputStream fis = null;
        Scanner scan = null;
        String filename = "current.txt";
        List<String> allFoods = new ArrayList<>();
        try {
            fis = context.openFileInput("current.txt");
            scan = new Scanner(fis);

            if (!scan.hasNextLine()) {
                try {
                    OutputStream out = context.openFileOutput("current.txt", Context.MODE_PRIVATE );
                    out.write("1\n0\n".getBytes(StandardCharsets.UTF_8));
                    out.close();
                } catch (IOException e) {
                    System.out.println("Failed to write data");
                }

            }

        } catch (IOException e) {}
    }

}
