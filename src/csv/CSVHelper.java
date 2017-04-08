package csv;

import java.io.Reader;
import java.util.List;
import java.util.Vector;

/**
 * Copied from: https://agiletribe.wordpress.com/2012/11/23/the-only-class-you-need-for-csv-files/
 */
public class CSVHelper
{

    /**
     * Returns a null when the input stream is empty
     */
    public static List<String> parseLine(Reader r) throws Exception {
        int ch = r.read();
        while (ch == 'r') {
            ch = r.read();
        }
        if (ch<0) {
            return null;
        }
        Vector<String> store = new Vector<>();
        StringBuffer curVal = new StringBuffer();
        boolean inquotes = false;
        boolean started = false;
        label:
        while (ch >= 0) {
            if (inquotes) {
                started = true;
                if (ch == '"') {
                    inquotes = false;
                } else {
                    curVal.append((char) ch);
                }
            } else {
                if (ch == '"') {
                    inquotes = true;
                    if (started) {
                        // if this is the second quote in a value, add a quote
                        // this is for the double quote in the middle of a value
                        curVal.append('"');
                    }
                } else if (ch == ',') {
                    store.add(curVal.toString());
                    curVal = new StringBuffer();
                    started = false;
                } else switch (ch) {
                    case '\r':
                        //ignore LF characters
                        break;
                    case '\n':
                        //end of a line, break out
                        break label;
                    default:
                        curVal.append((char) ch);
                        break;
                }
            }
            ch = r.read();
        }
        store.add(curVal.toString());
        return store;
    }
}