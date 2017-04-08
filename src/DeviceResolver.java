import device.Device;
import device.DeviceType;

import java.util.HashMap;

/**
 * Guessing device type
 */
public class DeviceResolver {
    /**
     * Guess device type and properties from hostname and mac address
     *
     * @param macAddress
     *      EUI-48 mac address
     * @param hostName
     *      The hostname as reported by e.g. dhcp server or nmap
     * @return
     *      Best guess information about device based on mac and hostname
     */
    public static Device guessDevice(String macAddress, String hostName) {

        // Find manufacturer from IEEE register
        String manufacturer = IeeeMacRegister.getManufacturer(macAddress);

        // If hostname has the format "android-<16 hex digits>", this should be an android device


        return new Device(hostName, hostName, macAddress, manufacturer, DeviceType.unknown, new HashMap<>());
    }
}
