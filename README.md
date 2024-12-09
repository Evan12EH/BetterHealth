BetterHealth
=============================
BetterHealth is a simple Android application that helps you easily track
your caloric intake throughout the day.


Installing and Running BetterHealth
=============================
First, copy the repository:
`https://github.com/Evan12EH/BetterHealth.git`

Next, open your copy of Android Studio.

click on `File > New > Project from Version Control...`

Select GitHub from the list of options.

Paste the repository URL into the search bar at the top, and set the
directory for where you want the project to download at the bottom.

Click `Clone`

Open the project. If there is no configuration set when you first
open the app, continue with the instructions, otherwise there are
no more steps or downloads to make the application work.

Click the `Add Configuration...` button and then the
`Edit Configurations...` button.

Select `Android App` and name it "App" (or whatever you prefer).

Set the Module to `BetterHealth.app.main`

Press `Apply` and then `OK`

Finally, run the project. There are no other
steps or downloads to make the application work.

Features and Functionality
=============================
BetterHealth allows users to log foods and their calories into
the app, and then add those foods to your current day's progress
so the app can keep track of how many calories you've had.

The extent of BetterHealth's functoinalities are as follows: it
allows you to set and change your calorie goal. It allows you to
create new foods to be added to a list, whose info consists of
the name of the food and its calorie content. It allows you to
see all of the foods you created in a list, as well as delete
those foods. With those steps complete, you can add foods so
that the app can start keeping track of how many calories
you've eaten that day. Once you're ready to move on to the
next day, clickling the "next day" button will move the app
along to the next day, reseting your current calories and
summarizing your previous data. If you so choose, you are
able to delete your progress at any time.

When adding foods, you are allowed to add as many as you want, but
do note that the only way to look at all the foods you have added
is to scroll through text on a single page.

BetterHealth will not accept any non-integer characters when inputting
calorie amounts or your goal, and if a negative number is input
the app will return the absolute value of whatever was given.

When resetting progress, the foods you have added to the app will
remain there, so if you wanted to completely empty your list, you
would need to individually delete every food you have made.
