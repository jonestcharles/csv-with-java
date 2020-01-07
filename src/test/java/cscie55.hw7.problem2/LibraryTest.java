package cscie55.hw7.problem2;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class LibraryTest {
    private Library library;

    @Before
    public void setup() {
        library = Library.getInstance();
        Reader myReader = new Reader();
        library.setCheckoutList(myReader.read("src\\main\\resources\\smaller_seatle.csv"));
    }

    @Test
    public void verifyRead() {
        Reader myReader = new Reader();
        List<Checkout> newList = myReader.read("src\\main\\resources\\smaller_seatle.csv");
        // newList has 2518 rows
        assertEquals(2518, newList.size());
    }

    @Test
    public void testAuthorsFilter() {
        List<Checkout> aNames = library.getAuthorsBeginsWith("A");
        // check output length and String matching
        assertEquals(111, aNames.size());
        assertTrue(aNames.stream().allMatch(c -> c.getAuthor().startsWith("A")));
        // write output
        CheckoutWriter writer = new CheckoutWriter();
        writer.write(Paths.get("target\\author_filter.txt"), aNames);
    }

    @Test
    public void testGetAllPublishedIn() {
        List<Checkout> lastYear = library.getAllPublishedIn(2018);
        // check output length and String matching
        assertEquals(108, lastYear.size());
        assertTrue(lastYear.stream().allMatch(c -> c.getPublicationDate() == 2018));
        // write output
        CheckoutWriter writer = new CheckoutWriter();
        writer.write(Paths.get("target\\published_in.txt"), lastYear);
    }

    @Test
    public void testFilteredBy() {
        List<Checkout> physical = library.getFilteredBy(p -> p.getBookKind().contains("PHYSICAL"));
        // check output length and String matching
        assertEquals(1663, physical.size());
        assertTrue(physical.stream().allMatch(c -> c.getBookKind().contains("PHYSICAL")));
        // write output
        CheckoutWriter writer = new CheckoutWriter();
        writer.write(Paths.get("target\\filtered_by.txt"), physical);
    }

    @Test
    public void testMostPopular() {
        List<Checkout> popular = library.getMostPopular(2);
        // check output length
        assertEquals(6, popular.size());
        // write output
        CheckoutWriter writer = new CheckoutWriter();
        writer.write(Paths.get("target\\most_popular.txt"), popular);
    }
}
