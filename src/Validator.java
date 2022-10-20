import java.util.regex.Pattern;

public class Validator {
    public boolean isValidRegistrationNumber(String reg_no) {
        return (Pattern.matches("\\d{4}", reg_no) && !(reg_no.equals("0000")));
    }

    public boolean isValidBrand(String brand) {
        return (Pattern.matches("[A-Za-z ]+", brand));
    }

    public boolean isValidNumber(String number) {
        return (Pattern.matches("^[0-9]*$", number));
    }
}
