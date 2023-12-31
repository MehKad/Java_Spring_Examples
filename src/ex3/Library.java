package ex3;

import java.util.List;

public class Library {
    private List<Book> books;
    private List<Reader> readers;

    public Library() {
    }

    public List<Book> getBooks() {
        return books;
    }

    public List<Reader> getReaders() {
        return readers;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public void setReaders(List<Reader> readers) {
        this.readers = readers;
    }
}
