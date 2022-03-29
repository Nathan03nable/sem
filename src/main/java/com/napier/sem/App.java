package com.napier.sem;

import com.napier.sem.languages.Language;
import com.napier.sem.population.Population;
import com.napier.sem.countries.Country;
import java.util.logging.Level;
import java.util.logging.Logger;

public class App
{
    static final String limit = "5";
    private static final String COUNTRY = "'Ukraine'";
    private static final String DISTRICT = "'Kabol'";
    private static final String CITY = "'Edinburgh'";
    private static final Logger LOGGER = Logger.getLogger(App.class.getName());
    private static final String WORLD_POPULATION = "6078547450";
    public static void main(String[] args) {
        String location;
        if(args.length < 1){
            location = "localhost:33060";
        }else{
            location = args[0];
        }
        IDatabaseConnection databaseConnection = DatabaseConnectionImpl.getInstance(location);

        SqlManager sqlManager = new SqlManager(databaseConnection);

        Population population = new Population(sqlManager);

        Country country = new Country(sqlManager);

        Language language = new Language(sqlManager);
        if(LOGGER.isLoggable(Level.INFO)){
            LOGGER.info(language.languagesReport(WORLD_POPULATION));
        }
        databaseConnection.disconnect();
    }
}