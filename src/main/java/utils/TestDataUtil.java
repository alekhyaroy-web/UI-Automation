package utils;

import net.datafaker.Faker;

import java.security.SecureRandom;
import java.util.Locale;

public class TestDataUtil {
    private static Faker faker = new Faker(new Locale("en-IN"));
    private static final SecureRandom secureRandom = new SecureRandom();

    // Static storage
    private static String storedEmail;
    private static String storedPassword;
    private static String firstName;
    private static Boolean isMale;
    private static String lastName;

    public static String getRandomPassword(int length){
        if(length < 8){
            throw new IllegalArgumentException("Password Length must be more than 8");
        }
        String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lower = "abcdefghijklmnopqrstuvwxyz";
        String digits = "0123456789";
        String specials = "@#$%&*!";
        String ALL = upper + lower + digits + specials;

        StringBuilder password = new StringBuilder();
        password.append(upper.charAt(secureRandom.nextInt(upper.length())));
        password.append(lower.charAt(secureRandom.nextInt(lower.length())));
        password.append(digits.charAt(secureRandom.nextInt(digits.length())));
        password.append(specials.charAt(secureRandom.nextInt(specials.length())));
        for(int i = 4 ; i < length; i++){
            password.append(ALL.charAt(secureRandom.nextInt(ALL.length())));
        }
        return password.toString();
    }

    public static String getRandomMail(){
        return "test" + System.currentTimeMillis() + "@gmail.com";
    }

    // Generate and store credentials
    public static void generateCredentials(){
        storedEmail = getRandomMail();
        storedPassword = getRandomPassword(10);
        isMale = secureRandom.nextBoolean();
        firstName = isMale ? faker.name().malefirstName() : faker.name().femaleFirstName();
        lastName = faker.name().lastName();

    }

    // Accessors
    public static String getStoredEmail(){ return storedEmail; }
    public static String getStoredPassword(){ return storedPassword; }
    public static String getFirstName(){ return firstName; }
    public static Boolean getGender(){ return isMale; }
    public static String getGenderLabel(){ return isMale ? "Mr" : "Mrs"; }
    public static String getLastName(){return lastName ;}
}
