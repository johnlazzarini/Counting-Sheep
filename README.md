# Counting Sheep
### https://play.google.com/store/apps/details?id=com.johnlazzarini.countingsheep&hl=en
## Summary
Google Play Store Application, where users try to answer as many math questions as they can before time runs out.

## Current Challenges
In my most recent build, I was able to move the overwhelming majority of the non-DRY code from each of the respective math activities (AddIntegers, AddNaturals) into a single MathActivity from which all others are derived.  Unfortunately, there is still some code that belongs in the super class but that resulted in a failed build when I most recently tried to move it over.  I'm working on a fix as you read this!

## Repository contents
Most of the interesting files -- those that contain the XML and Java requisite to the application's structure -- can be found by accessing the link below:

https://github.com/johnlazzarini/counting-sheep/tree/master/app/src/main
