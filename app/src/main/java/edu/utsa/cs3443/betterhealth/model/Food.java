package edu.utsa.cs3443.betterhealth.model;

import android.content.Context;
import android.content.res.AssetManager;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import edu.utsa.cs3443.betterhealth.MainActivity;

/**
 * The Food class represents a food, and holds
 * all of the functions necessary for the application
 *
 * @author Alberto Gonzales
 * @author Daniel Salas
 * @author Evan Hudson
 * @author Michael Montesdeoca
 * @author Jayden Hendrix
 */
public class Food {

    private String name;
    private String calories;

    /**
     * Returns a string containing the text data from "foodInfo.txt"
     * @param context The context of the application
     * @return A string containing the text data from "foodInfo.txt"
     */
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

    /**
     * Adds a new food and its calories to "foodInfo.txt"
     * @param context The context of the application
     * @param food The name of a food as a string
     * @param calories The calories of the respective food as an int
     */
    public static void createFood(Context context, String food, int calories){
        try {
            OutputStream out = context.openFileOutput("foodInfo.txt", Context.MODE_APPEND );
            String updatedData = food + ": " + calories + "cal" + "\n";
            out.write(updatedData.getBytes(StandardCharsets.UTF_8));
            out.close();
        } catch (IOException e) {
            System.out.println("Failed to write data");
        }
    }

    /**
     * Returns the number of calories as an
     * int from the food given as a parameter
     * @param context The context of the application
     * @param food The name of a food as a string
     * @return The number of calories returned as an
     * int from the food given as a parameter
     */
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
                    calorieText = parts[1].substring(0, parts[1].length() - 3).trim();
                    calories = Integer.parseInt(calorieText);
                    return calories;
                }
            }

        } catch (IOException e) {}
        return calories;
    }

    /**
     * Deletes a food and its calories from "foodInfo.txt"
     * @param context The context of the application
     * @param food The name of a food as a string
     */
    public static void deleteFood(Context context, String food){
        FileInputStream fis = null;
        Scanner scan = null;
        String filename = "foodInfo.txt";
        StringBuilder newInfo = new StringBuilder();
        StringBuilder allFoods = new StringBuilder();
        try {
            fis = context.openFileInput("foodInfo.txt");
            scan = new Scanner(fis);

            while (scan.hasNextLine()) {
                String line = scan.nextLine();

                if (!line.contains(food)) {
                    if (newInfo.length() > 0) {
                        newInfo.append("\n");
                    }
                    newInfo.append(line);
                }
            }

            if (newInfo.length() > 0 && !newInfo.toString().endsWith("\n")) {
                newInfo.append("\n");
            }

            try {
                OutputStream out = context.openFileOutput("foodInfo.txt", Context.MODE_PRIVATE );
                out.write(newInfo.toString().getBytes(StandardCharsets.UTF_8));
                out.close();
            } catch (IOException e) {
                System.out.println("Failed to write data");
            }
        } catch (IOException e) {}
    }

    /**
     * Adds the calories of a food you ate to the current calories
     * @param context The context of the application
     * @param calories The calories to be added to the current total as an int
     */
    public static void addFood(Context context, int calories){
        FileInputStream fis = null;
        Scanner scan = null;
        String filename = "current.txt";
        try {
            fis = context.openFileInput("current.txt");
            scan = new Scanner(fis);
            int currentDay = 0;
            int currentCalories = 0;
            int newCalories = 0;
            int currentGoal = 0;

            if (scan.hasNextLine()) {
                currentDay = Integer.parseInt(scan.nextLine());
            }
            if (scan.hasNextLine()) {
                currentCalories = Integer.parseInt(scan.nextLine());
            }
            if (scan.hasNextLine()) {
                currentGoal = Integer.parseInt(scan.nextLine());
            }

            try {
                newCalories = currentCalories + calories;
                OutputStream out = context.openFileOutput("current.txt", Context.MODE_PRIVATE );
                String updatedData = currentDay + "\n" + newCalories + "\n" + currentGoal + "\n";
                out.write(updatedData.getBytes(StandardCharsets.UTF_8));
                out.close();
            } catch (IOException e) {
                System.out.println("Failed to write data");
            }

        } catch (IOException e) {}
    }

    /**
     * Resets the text data in "current.txt" when
     * user goes to the next day
     * @param context The context of the application
     */
    public static void nextDay(Context context){
        FileInputStream fis = null;
        Scanner scan = null;
        String filename = "current.txt";
        try {
            fis = context.openFileInput("current.txt");
            scan = new Scanner(fis);
            int currentDay = 0;
            int currentCalories = 0;
            int currentGoal = 0;
            int resetCalories = 0;

            if (scan.hasNextLine()) {
                currentDay = Integer.parseInt(scan.nextLine());
            }
            if (scan.hasNextLine()) {
                currentCalories = Integer.parseInt(scan.nextLine());
            }
            if (scan.hasNextLine()) {
                currentGoal = Integer.parseInt(scan.nextLine());
            }

            currentDay += 1;

            try {
                OutputStream out = context.openFileOutput("current.txt", Context.MODE_PRIVATE );
                String updatedData = currentDay + "\n" + resetCalories + "\n" + currentGoal + "\n";
                out.write(updatedData.getBytes(StandardCharsets.UTF_8));
                out.close();
            } catch (IOException e) {
                System.out.println("Failed to write data");
            }

        } catch (IOException e) {}
    }

    /**
     * Adds the info from "current.txt" and adds it to
     * "progress.txt" to log the users data
     * @param context The context of the application
     */
    public static void addProgress(Context context){
        FileInputStream fis = null;
        Scanner scan = null;
        String filename = "current.txt";
        try {
            fis = context.openFileInput("current.txt");
            scan = new Scanner(fis);
            int currentDay = 0;
            int currentCalories = 0;
            int currentGoal = 0;

            if (scan.hasNextLine()) {
                currentDay = Integer.parseInt(scan.nextLine());
            }
            if (scan.hasNextLine()) {
                currentCalories = Integer.parseInt(scan.nextLine());
            }
            if (scan.hasNextLine()) {
                currentGoal = Integer.parseInt(scan.nextLine());
            }

            try {
                OutputStream out = context.openFileOutput("progress.txt", Context.MODE_APPEND );
                String updatedData = currentDay + "," + currentCalories + "," + currentGoal + "\n";
                out.write(updatedData.getBytes(StandardCharsets.UTF_8));
                out.close();
            } catch (IOException e) {
                System.out.println("Failed to write data");
            }

        } catch (IOException e) {}
    }

    /**
     * Returns a text summary of all previous data
     * recorded by the app as a string
     * @param context The context of the application
     * @return A text summary of all previous data
     * recorded by the app as a string
     */
    public static String getInfo(Context context) {
        FileInputStream fis = null;
        Scanner scan = null;
        String filename = "progress.txt";
        StringBuilder allProgress = new StringBuilder();
        int less = 0;
        int more = 0;
        int equal = 0;
        try {
            fis = context.openFileInput("progress.txt");
            scan = new Scanner(fis);

            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                if (allProgress.length() > 0) {
                    allProgress.append("\n");
                }
                String[] parts = line.split(",");
                int difference = Integer.parseInt(parts[2]) - Integer.parseInt(parts[1]);
                int day = Integer.parseInt(parts[0]);
                if ( difference > 0 ) {
                    allProgress.append("Day #" + day + ": " + "You ate " + difference + " less calories than your Goal!");
                    less += 1;
                }
                else if ( difference < 0 ) {
                    allProgress.append("Day #" + day + ": " + "You ate " + Math.abs(difference) + " more calories than your Goal.");
                    more += 1;
                }
                else if ( difference == 0 ) {
                    allProgress.append("Day #" + day + ": " + "You ate the same amount as your goal.");
                    equal += 1;
                }
            }
            allProgress.append("\n\nYou've had " + less + " days you ate less than your goal! \n");
            allProgress.append("You've had " + more + " days you ate more than your goal. \n");
            allProgress.append("You've had " + equal + " days you ate the same as your goal! \n");
        } catch (IOException e) {}
        return allProgress.toString();
    }

    /**
     * Returns the difference between the amount of calories eaten
     * in a day versus the set calorie goal as an int
     * @param context The context of the application
     * @return The difference between the amount of calories eaten
     * in a day versus the set calorie goal as an int
     */
    public static int calculateGoal(Context context){
        FileInputStream fis = null;
        Scanner scan = null;
        String filename = "current.txt";
        int solved = 0;
        try {
            fis = context.openFileInput("current.txt");
            scan = new Scanner(fis);
            int currentDay = 0;
            int currentCalories = 0;
            int currentGoal = 0;

            if (scan.hasNextLine()) {
                currentDay = Integer.parseInt(scan.nextLine());
            }
            if (scan.hasNextLine()) {
                currentCalories = Integer.parseInt(scan.nextLine());
            }
            if (scan.hasNextLine()) {
                currentGoal = Integer.parseInt(scan.nextLine());
            }
            solved = currentGoal - currentCalories;
        } catch (IOException e) {}
        return solved;
    }

    /**
     * Returns the current calorie goal of the user as an int
     * @param context The context of the application
     * @return The current calorie goal of the user as an int
     */
    public static int getCurrent(Context context){
        FileInputStream fis = null;
        Scanner scan = null;
        String filename = "current.txt";
        int currentDay = 0;
        int currentCalories = 0;
        int currentGoal = 0;
        try {
            fis = context.openFileInput("current.txt");
            scan = new Scanner(fis);

            if (scan.hasNextLine()) {
                currentDay = Integer.parseInt(scan.nextLine());
            }
            if (scan.hasNextLine()) {
                currentCalories = Integer.parseInt(scan.nextLine());
            }
            if (scan.hasNextLine()) {
                currentGoal = Integer.parseInt(scan.nextLine());
            }
        } catch (IOException e) {}
        return currentCalories;
    }

    /**
     * Sets the calorie goal of the user
     * @param context The context of the application
     * @param goal The new calorie goal to be set as an int
     */
    public static void setGoal(Context context, int goal){
        FileInputStream fis = null;
        Scanner scan = null;
        String filename = "current.txt";
        List<String> allFoods = new ArrayList<>();
        try {
            fis = context.openFileInput("current.txt");
            scan = new Scanner(fis);
            int currentDay = 0;
            int currentCalories = 0;
            int currentGoal = 0;

            if (scan.hasNextLine()) {
                currentDay = Integer.parseInt(scan.nextLine());
            }
            if (scan.hasNextLine()) {
                currentCalories = Integer.parseInt(scan.nextLine());
            }
            if (scan.hasNextLine()) {
                currentGoal = Integer.parseInt(scan.nextLine());
            }

            try {
                OutputStream out = context.openFileOutput("current.txt", Context.MODE_PRIVATE );
                String updatedData = currentDay + "\n" + currentCalories + "\n" + goal + "\n";
                out.write(updatedData.getBytes(StandardCharsets.UTF_8));
                out.close();
            } catch (IOException e) {
                System.out.println("Failed to write data");
            }
        } catch (IOException e) {}
    }

    /**
     * Returns a summary of your eating progress of the day given as a string
     * @param context The context of the application
     * @param difference The difference in calories eaten versus
     * the set calorie goal as an int
     * @param current The current calorie goal of the user as an int
     * @return A summary of your eating progress of the day given as a string
     */
    public static String makeSummary(Context context, int difference, int current) {
        FileInputStream fis = null;
        Scanner scan = null;
        String filename = "progress.txt";
        String lastLine = null;
        StringBuilder summary = new StringBuilder();
        try {
            fis = context.openFileInput("progress.txt");
            scan = new Scanner(fis);

            if ( difference > 0 ) {
                summary.append("You ate " + difference + " less calories than your Goal! \n");
            }
            else if ( difference < 0 ) {
                summary.append("You ate " + Math.abs(difference) + " more calories than your Goal. \n");
            }
            else if ( difference == 0 ) {
                summary.append("You ate the same amount as your goal. \n");
            }

            if (scan.hasNextLine()) {
                while (scan.hasNextLine()) {
                    lastLine = scan.nextLine();
                }
                String[] parts = lastLine.split(",");
                String yesterday = parts[1].trim();
                int yesterdayValue = Integer.parseInt(yesterday);
                int lastValue = current - yesterdayValue;

                if ( lastValue > 0 ) {
                    summary.append("You ate " + lastValue + " more calories than yesterday. \n");
                }
                else if ( lastValue < 0 ) {
                    summary.append("You ate " + Math.abs(lastValue) + " less calories than yesterday! \n");
                }
                else if ( lastValue == 0 ) {
                    summary.append("You ate the same amount as yesterday. \n");
                }
            }
        } catch (IOException e) {}
        return summary.toString();
    }

    /**
     * Checks if the text file "current.txt is empty, if
     * it is, add the necessary data for a first time user
     * @param context The context of the application
     */
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
                    out.write("1\n0\n0\n".getBytes(StandardCharsets.UTF_8));
                    out.close();
                } catch (IOException e) {
                    System.out.println("Failed to write data");
                }

            }

        } catch (IOException e) {}
    }

    /**
     * Resets the text data in "current.txt" and "progress.txt"
     * to that of a first time user
     * @param context The context of the application
     */
    public static void resetProgress(Context context){
        try {
            OutputStream out = context.openFileOutput("current.txt", Context.MODE_PRIVATE );
            out.write("1\n0\n0\n".getBytes(StandardCharsets.UTF_8));
            out.close();
        } catch (IOException e) {
            System.out.println("Failed to write data");
        }
        try {
            OutputStream out = context.openFileOutput("progress.txt", Context.MODE_PRIVATE );
            out.write("".getBytes(StandardCharsets.UTF_8));
            out.close();
        } catch (IOException e) {
            System.out.println("Failed to write data");
        }
    }

    /**
     * Checks if the files necessary for the application are
     * present, if not, creates them
     * @param context The context of the application
     */
    public static void checkFiles(Context context) {
        String[] fileNames = {"current.txt", "progress.txt", "foodInfo.txt"};

        for (int i = 0; i < fileNames.length; i++) {
            String fileName = fileNames[i];

            File file = new File(context.getFilesDir(), fileName);

            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
