package utilities;

import io.qameta.allure.internal.shadowed.jackson.databind.JsonNode;
import io.qameta.allure.internal.shadowed.jackson.databind.ObjectMapper;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class DriverSettings {
    private static final File DRIVER_SETTINGS_FILE;
    private static final ObjectMapper mapper = new ObjectMapper();
    private static JsonNode jsonSettingsNode = null;
    private static final String APPLICATION_PATH_KEY = "applicationPath";
    private static final String APP_CAPABILITY_KEY = "app";
    private static final String DEVICE_KEY_KEY = "deviceKey";
    private static final String PLATFORM_NAME = "android";

    static {
        DRIVER_SETTINGS_FILE = new File(".\\src\\test\\resources\\settings.json");
        try {
            jsonSettingsNode = mapper.readTree(DRIVER_SETTINGS_FILE);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public DriverSettings() {
    }

    public Capabilities getCapabilities() {
        Map<String, Object> capabilitiesFromSettings = getCapabilitiesFromSettings();
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilitiesFromSettings.forEach((key, value) -> {
            if (key.toLowerCase().endsWith("options")) {
                value = mapper.convertValue(jsonSettingsNode.at(getDriverSettingsPath("capabilities", key)), Map.class);
            }
            capabilities.setCapability(key, value);
        });
        if (hasApplicationPath()) {
            capabilities.setCapability(APP_CAPABILITY_KEY, getAbsolutePath(getApplicationPath()));
        }
        return capabilities.merge(getDeviceCapabilities());
    }

    private Map<String, Object> getCapabilitiesFromSettings() {
        return mapper.convertValue(jsonSettingsNode.at(getDriverCapabilitiesJsonPath()), Map.class);
    }

    private String getDriverCapabilitiesJsonPath() {
        return getDriverSettingsPath("capabilities");
    }

    private String getAbsolutePath(String relativePath) {
        try {
            return new File(relativePath).getCanonicalPath();
        } catch (IOException e) {
            throw new IllegalArgumentException("Exception while reading file");
        }
    }

    private boolean hasApplicationPath() {
        return mapper.convertValue(jsonSettingsNode.at(getDriverSettingsPath()), Map.class).containsKey(APPLICATION_PATH_KEY);
    }

    private Capabilities getDeviceCapabilities() {
        DeviceSettings deviceSettings = new DeviceSettings(getDeviceKeyName());
        return deviceSettings.getCapabilities();
    }

    private String getDeviceKeyName() {
        return jsonSettingsNode.at(getDriverSettingsPath(DEVICE_KEY_KEY)).asText();
    }

    public URL getConnectionURL() {
        try {
            return new URL(jsonSettingsNode.get("remoteConnectionUrl").asText());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public String getApplicationPath() {
        return jsonSettingsNode.at(getDriverSettingsPath(APPLICATION_PATH_KEY)).asText();
    }

    private String getDriverSettingsPath(final String... paths) {
        String pathToDriverSettings = String.format("/driverSettings/%s", PLATFORM_NAME.toString().toLowerCase());
        return pathToDriverSettings.concat(Arrays.stream(paths).map("/"::concat).collect(Collectors.joining()));
    }
}
