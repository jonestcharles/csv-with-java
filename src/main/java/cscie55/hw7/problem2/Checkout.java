package cscie55.hw7.problem2;

import java.util.Objects;

public class Checkout {
    private String title;
    private String author;
    private int publicationDate;
    private enum Kind {PHYSICAL, DIGITAL, OTHER};
    private Kind bookKind;

    // default constructor
    public Checkout() {
        this.title = null;
        this.author = null;
        this.publicationDate = -1;
        this.bookKind = Kind.OTHER;
    }

    // constructor
    public Checkout(String title, String author, int publicationDate, String bookKind) {
        this.title = title;
        this.author = author;
        this.publicationDate = publicationDate;
        if (bookKind.equals("Physical")) {
            this.bookKind = Kind.PHYSICAL;
        }
        else if (bookKind.equals("Digital")) {
            this.bookKind = Kind.DIGITAL;
        }
        else {
            this.bookKind = Kind.OTHER;
        }
    }

    @Override
    public String toString() {
        return title + ", " + author + ", " + publicationDate + ", " + bookKind;
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, author, publicationDate, bookKind);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (other == null) {
            return false;
        }

        if (getClass() != other.getClass()) {
            return false;
        }

        Checkout checkout = (Checkout) other;
        return (this.author == checkout.getAuthor() && this.title == checkout.getTitle()
            && this.publicationDate == checkout.getPublicationDate()
            && this.bookKind.toString() == checkout.getBookKind()
            && hashCode() == checkout.hashCode());
    }

    public String getAuthor() {
        return this.author;
    }

    public String getTitle() {
        return this.title;
    }

    public int getPublicationDate() {
        return this.publicationDate;
    }

    public String getBookKind() {
        return this.bookKind.toString();
    }
}
