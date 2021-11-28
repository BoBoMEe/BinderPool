package com.bobomee.server.service.manager

import com.bobomee.server.bean.Book
import com.bobomee.server.manager.IBookManager

class BookManagerService : IBookManager.Stub() {

    private val mBookDataList = mutableListOf<Book>()

    override fun getBooks(): MutableList<Book> {
        return mBookDataList
    }

    override fun addBook(book: Book) {
        mBookDataList.add(book)
    }
}