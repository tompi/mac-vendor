package device;

import java.util.Map;

/**
 * Class holding all data it is possible to deduce from mac + hostname
 */
public class Device {
    private final String hostName;
    private final String displayName;
    private final String macAddress;
    private final String nicManufacturer;
    private final DeviceType deviceType;
    private final Map<String, String> metaData;

    public Device(String hostName, String displayName, String macAddress, String nicManufacturer, DeviceType deviceType, Map<String, String> metaData) {
        this.hostName = hostName;
        this.displayName = displayName;
        this.macAddress = macAddress;
        this.nicManufacturer = nicManufacturer;
        this.deviceType = deviceType;
        this.metaData = metaData;
    }
}
