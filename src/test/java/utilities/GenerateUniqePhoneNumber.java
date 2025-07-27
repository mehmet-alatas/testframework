package utilities;

import com.github.javafaker.Faker;

public class GenerateUniqePhoneNumber {


    public static String GenerateUniqePhoneNumber() {
        Integer areaCode = Faker.instance().number().numberBetween(100, 999);
        Integer firstPart = Faker.instance().number().numberBetween(100, 999);
        Integer secondPart = Faker.instance().number().numberBetween(1000, 9999);
        return  "(" + areaCode + ") " + firstPart + "-" + secondPart + "";
    }

}
