package cscie55.hw7.problem2;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class CheckoutWriter {

    public void write(Path filePathAndName, List<Checkout> checkouts) {
        // create or open output file, write each Checkout on a new line (uses Checkout's toString method)
        try(BufferedWriter out = Files.newBufferedWriter(filePathAndName,
                StandardOpenOption.WRITE, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)
        ) {
            checkouts.forEach(checkout -> {
                try {
                    out.write(checkout.toString() + "\n");
                }
                catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            });
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
