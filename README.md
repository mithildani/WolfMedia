# WolfMedia
DBMS Project Spring 2023

The system has a highLevel main menu that gives the user the following options: InfromationProcessing, MaintainingMetadata, MaintainingPayments, Reports and Exit.  When prompted, the user enters a number corresponding with what kind of action they would like to take. For each menu option there is a submenu(service) displayed that has specific operations listed. Our system has a main class (WolfMediaService.java) that facilitates switching between the main menu options and separate classes.

The system is divided into models as, Album, Artist, PodcastEpisode, PodcastHost, Song, User. 
There is a separate folder service in each substructure which allows for all operations like InfromationProcessing, MaintainingMetadata, MaintainingPayments, Miscellaneous, and Reports to take place.  

We use a DAO which is a data access object which acts as an interface the models and the operations that we have in the app.
We use a DBConnection object to establish only one single connection to the database. Each time the program execution ends, the connection too the database is closed. 
						
