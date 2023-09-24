package constants;

public class ErrorMessage {

    private ErrorMessage() {}

    public static final String WRONG_DATA = "Введены неверные данные";
    public static final String EMPTY_LOGIN = "Логин не может пустым";
    public static final String EMPTY_PASSWORD = "Пароль не может пустым";
    public static final String MAX_LENGTH_LOGIN = "Максимальное допустимое количество символов: 50 - поле логин";
    public static final String MAX_LENGTH_PASSWORD = "Максимальное допустимое количество символов: 50 - поле пароль";
    public static final String EXCEPT_VALUE = "Логин должен иметь: латинские буквы A..Z, a..z, символы [ . , /э ' _ -], пробел";
}
