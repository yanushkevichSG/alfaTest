package constants;

import java.time.Duration;

public class Timeouts {

    private Timeouts() {}

    public static Duration getDefaultTimeout() {
        return Duration.ofSeconds(15);
    }
    public static Duration getImplicitTimeout() { return Duration.ofSeconds(30); }
}
