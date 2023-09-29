package ru.testtask.properties;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * The type Config. Read the file application.properties and get the value of this property.
 */
public class Config {
    private static final String CONFIG_PATH = "/application.properties";

    private static final Properties properties = new Properties();

    static {
        try {
            InputStream input = Config.class.getResourceAsStream(CONFIG_PATH);
            properties.load(input);
        } catch (IOException ex) {
            System.err.println("Can`t open configuration file");
        }
    }

    /**
     * Get max health entity int.
     *
     * @return the int - max health {@link ru.testtask.models.Entity}
     */
    public static int getMaxHealthEntity() {
        return getNaturalParameter("entity.max_health");
    }


    /**
     * Get min damage entity int.
     *
     * @return the int - min damage {@link ru.testtask.models.Entity}
     */
    public static int getMinDamageEntity() {
        return getNaturalParameter("entity.min_damage");
    }

    /**
     * Get max damage entity int.
     *
     * @return the int - max damage {@link ru.testtask.models.Entity}
     */
    public static int getMaxDamageEntity() {
        return getNaturalParameter("entity.max_damage");
    }


    /**
     * Try convert parameter from file to Integer, if can`t this doing, print error and exit from application.
     * @param property the name parameter in application.properties.
     * @return int - number in application.properties.
     */
    private static int getNaturalParameter(String property) {
        String parameter = checkOnNull(property);
        try {
            int number = Integer.parseInt(parameter);
            return checkOnNaturalNumber(property, number);
        } catch (NumberFormatException ex) {
            System.err.println("Parameter in " + property + " can`t transform to Integer, replace this value. " +
                    "Further execution of the program is not possible.");
            System.exit(130);
        }
        return 0;
    }

    private static String checkOnNull(String property){
        String parameter = properties.getProperty(property);
        if(parameter == null){
            System.err.println("Parameter " + property + " does not exist in application.properties. " +
                    "Further execution of the program is not possible.");
            System.exit(130);
        }
        return parameter;
    }

    private static int checkOnNaturalNumber(String property, int number){
        if(number < 0){
            System.out.println("Property " + property + " can not less or equals zero. Write a natural number for " +
                    property + " in application.properties.");
            System.out.println("Further execution of the program is not possible.");
            System.exit(130);
        }
        return number;
    }
}
