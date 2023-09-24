package utilities;

import io.qameta.allure.internal.shadowed.jackson.databind.JsonNode;
import io.qameta.allure.internal.shadowed.jackson.databind.ObjectMapper;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DeviceSettings {

    private static final File DEVICE_SETTINGS_FILE;
    private static final ObjectMapper mapper = new ObjectMapper();
    private static JsonNode jsonDeviceNode = null;
    private final String deviceKey;

    static {
        DEVICE_SETTINGS_FILE = new File(".\\src\\test\\resources\\devices.json");
        try {
            jsonDeviceNode = mapper.readTree(DEVICE_SETTINGS_FILE);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public DeviceSettings(String deviceKey) {
        this.deviceKey = deviceKey;
    }

    public Capabilities getCapabilities() {
        Map<String, Object> deviceCapabilities = getCapabilitiesFromSettings();
        DesiredCapabilities capabilities = new DesiredCapabilities();
        deviceCapabilities.forEach(capabilities::setCapability);
        return capabilities;
    }

    private Map<String, Object> getCapabilitiesFromSettings() {
        Map<String, Object> deviceCapabilities = new HashMap<>();
        if (deviceKey != null) {
            String path = "/".concat(deviceKey).concat("/capabilities");
            deviceCapabilities = mapper.convertValue(jsonDeviceNode.at(path), Map.class);
        }
        return deviceCapabilities;
    }
}
