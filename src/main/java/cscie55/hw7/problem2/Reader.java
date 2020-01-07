package cscie55.hw7.problem2;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Reader {
    // regex pattern used in helper function isNumeric()
    private Pattern numPattern = Pattern.compile("-?\\d+(\\.\\d+)?");

    public List<Checkout> read(String filename) {
        List<Checkout> checkoutList = new ArrayList<>();
        File csvData = new File(filename);

        try (CSVParser parser = CSVParser.parse(csvData, Charset.defaultCharset(), CSVFormat.EXCEL)) {

            // parse csv by line, use helper method to get the Integer value or set a default
            for (CSVRecord record : parser) {
                String author = record.get(7);
                String title = record.get(6);
                String pubDate = record.get(10);
                int pubYear;
                if (isNumeric(pubDate)) {
                    pubYear = Integer.parseInt(pubDate);
                }
                else {
                    pubYear = 0;
                }
                String bookKind = record.get(0);

                // create a Checkout object, add to return List
                Checkout bookCheckout = new Checkout(title, author, pubYear, bookKind);
                checkoutList.add(bookCheckout);
            }
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return checkoutList;
    }

    // Helper method to check if a String can be parsed to an Integer
    private boolean isNumeric(String s) {
        if (s == null) {
            return false;
        }
        return numPattern.matcher(s).matches();
    }

}
