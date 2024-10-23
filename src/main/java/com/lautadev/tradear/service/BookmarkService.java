package com.lautadev.tradear.service;

import com.lautadev.tradear.dto.BookmarkDTO;
import com.lautadev.tradear.model.Bookmark;
import com.lautadev.tradear.repository.IBookmarkRepository;
import com.lautadev.tradear.throwable.EntityNotFoundException;
import com.lautadev.tradear.util.NullAwareBeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookmarkService implements IBookmarkService{

    @Autowired
    private IBookmarkRepository bookmarkRepository;

    @Override
    public BookmarkDTO saveBookmark(Bookmark bookmark) {
        if(bookmark !=null){
            bookmarkRepository.save(bookmark);
        }

        return BookmarkDTO.fromBookmark(bookmark);
    }

    @Override
    public List<BookmarkDTO> getBookmarks() {
        List<Bookmark> bookmarks = bookmarkRepository.findAll();
        List<BookmarkDTO> bookmarkDTOS = new ArrayList<>();

        for(Bookmark bookmark:bookmarks){
            bookmarkDTOS.add(BookmarkDTO.fromBookmark(bookmark));
        }

        return bookmarkDTOS;
    }

    @Override
    public Optional<BookmarkDTO> findBookmark(Long id) {
        Bookmark bookmark = bookmarkRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Entity Not Found"));
        return Optional.ofNullable(BookmarkDTO.fromBookmark(bookmark));
    }

    @Override
    public void deleteBookmark(Long id) {
        bookmarkRepository.deleteById(id);
    }

    @Override
    public BookmarkDTO editBookmark(Long id, Bookmark bookmark) {
        Bookmark bookmarkEdit = bookmarkRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Entity Not Found"));

        NullAwareBeanUtils.copyNonNullProperties(bookmark,bookmarkEdit);

        return this.saveBookmark(bookmarkEdit);
    }

    @Override
    public Optional<BookmarkDTO> findByUserSec_Id(Long userId) {
        Bookmark bookmark = bookmarkRepository.findByUserSec_Id(userId);
        return Optional.ofNullable(BookmarkDTO.fromBookmark(bookmark));
    }
}
