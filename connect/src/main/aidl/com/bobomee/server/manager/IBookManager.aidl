// IBookManager.aidl
package com.bobomee.server.manager;

// Declare any non-default types here with import statements
import com.bobomee.server.bean.Book;


interface IBookManager {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    List<Book> getBooks();

    void addBook(in Book book);

}
