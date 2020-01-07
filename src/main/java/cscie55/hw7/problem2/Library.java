package cscie55.hw7.problem2;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Library {
    private List<Checkout> checkoutList;
    private static Library library = null;
    private Reader myReader = new Reader();

    private Library() {
        checkoutList = new ArrayList<>();
    }

    // Singleton - if no instance, call constructor. Return the instance
    public static Library getInstance() {
        if (library == null) {
            library = new Library();
        }

        return library;
    }

    // setter for setting thsi from the Reader
    public void setCheckoutList(List<Checkout> checkouts) {
        this.checkoutList = checkouts;
    }

    // gets Checkouts with occurrences greater than or equal to limit
    public List<Checkout> getMostPopular(int limit) {
        // group by title
        Map<String, Long> byTitle = checkoutList.stream()
                .collect(Collectors.groupingBy(Checkout::getTitle, Collectors.counting()));

        // filter for those over the limit
        Map<String, Long> popular = new TreeMap<>();
        byTitle.entrySet().stream()
                .filter(e -> e.getValue() >= limit)
                .forEachOrdered(e -> popular.put(e.getKey(), e.getValue()));

        // get a subList of Checkouts that occur more than the limit
        List<Checkout> result = checkoutList.stream()
                .filter(checkout -> popular.containsKey(checkout.getTitle()))
                .filter(distinctByKey(p -> p.getTitle()))
                .collect(Collectors.toList());

        return result;
    }

    // filters for Checkouts whose author begins with s
    public List<Checkout> getAuthorsBeginsWith(String s) {
        return checkoutList.stream().filter(checkout -> checkout.getAuthor().startsWith(s))
                .collect(Collectors.toList());
    }

    // gets a list of Checkouts published in year year
    public List<Checkout> getAllPublishedIn(int year) {
        return checkoutList.stream().filter(checkout -> checkout.getPublicationDate() == year)
                .collect(Collectors.toList());
    }

    // filters checkoutList with a custom Predicate
    public List<Checkout> getFilteredBy(Predicate<Checkout> p) {
        return checkoutList.stream().filter(p).collect(Collectors.toList());
    }

    // private helper method for checking if a Checkout has occured before, to avoid duplciates in
    // getMostPopular
    private static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
        Map<Object, Boolean> seen= new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }
}
