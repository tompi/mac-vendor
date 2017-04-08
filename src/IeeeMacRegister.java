import csv.CSVHelper;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * In memory table of IEEE list of reserved mac adresses:
 */
public class IeeeMacRegister {
  private static Map<String, String> smallMap;
  private static Map<String, String> mediumMap;
  private static Map<String, String> largeMap;
  private static boolean initialized = false;

  static {
    // Since parsing the CSV takes a long time, do it in separate thread and
    // just hold up callers of the lookup until we are done.
    // Doing all in the same thread in a static block holds up the whole application startup
    new Thread(() -> {
      smallMap = getMapFromFile("csv/oui36.csv");
      mediumMap = getMapFromFile("csv/mam.csv");
      largeMap = getMapFromFile("csv/oui.csv");
      initialized = true;
    }).start();
  }

  private static Map<String, String> getMapFromFile(String filename) {
    Map<String, String> map = new HashMap<>();

    File fileTemplate = new File(IeeeMacRegister.class.getResource(filename).getFile());
    try (InputStream is = new FileInputStream(fileTemplate)) {
      Reader fr = new InputStreamReader(is, "UTF-8");
      List<String> line = CSVHelper.parseLine(fr);
      while (line != null) {
        map.put(line.get(1), line.get(2));
        line = CSVHelper.parseLine(fr);
      }
      return map;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }


  public static String getManufacturer(String macAddress) {
    // Make sure we are initialized
    while (!initialized) {
      try {
        // Takes around half a second to parse csv files (on my machine...)
        TimeUnit.MILLISECONDS.sleep(500);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }

    if (macAddress == null) {
      return null;
    }

    String sanitizedMac = macAddress.trim().toUpperCase().replace(":", "");

    if (sanitizedMac.length() < 9) {
      return null;
    }

    // First try the small register
    String manufacturer = smallMap.get(sanitizedMac.substring(0, 9));
    if (manufacturer != null) {
      return manufacturer;
    }

    // Next try the medium
    manufacturer = mediumMap.get(sanitizedMac.substring(0, 7));
    if (manufacturer != null) {
      return manufacturer;
    }

    // Finally try the large register
    return largeMap.get(sanitizedMac.substring(0, 6));
  }
}
