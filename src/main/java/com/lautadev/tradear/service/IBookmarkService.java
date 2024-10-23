package com.lautadev.tradear.service;

import com.lautadev.tradear.dto.BookmarkDTO;
import com.lautadev.tradear.model.Bookmark;

import java.util.List;
import java.util.Optional;

public interface IBookmarkService {
    public BookmarkDTO saveBookmark(Bookmark bookmark);
    public List<BookmarkDTO> getBookmarks();
    public Optional<BookmarkDTO> findBookmark(Long id);
    public void deleteBookmark(Long id);
    public BookmarkDTO editBookmark(Long id, Bookmark bookmark);
    Optional<BookmarkDTO> findByUserSec_Id(Long userId);
}
