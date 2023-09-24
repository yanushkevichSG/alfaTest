package utilities;

import io.qameta.allure.internal.shadowed.jackson.databind.JsonNode;
import io.qameta.allure.internal.shadowed.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class Environment {
    private static final File ENVIRONMENT_SETTINGS_FILE;
    private static final ObjectMapper mapper = new ObjectMapper();
    private static JsonNode jsonNode = null;

    static {
        String environment = System.getProperty("environment", "qa");
        ENVIRONMENT_SETTINGS_FILE = new File(String.format(".\\src\\test\\resources\\environment\\%s.json", environment.toLowerCase()));
        try {
            jsonNode = mapper.readTree(ENVIRONMENT_SETTINGS_FILE);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String login() {
        return jsonNode.get("login").asText();
    }

    public static String password() {
        return jsonNode.get("password").asText();
    }

    public static String invalidLogin() {
        return jsonNode.get("invalidLogin").asText();
    }

    public static String invalidPassword() {
        return jsonNode.get("invalidPassword").asText();
    }
}
