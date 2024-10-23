package com.lautadev.tradear.controller;

import com.lautadev.tradear.dto.BookmarkDTO;
import com.lautadev.tradear.model.Bookmark;
import com.lautadev.tradear.service.IBookmarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bookmark")
@PreAuthorize("permitAll()")
public class BookmarkController {

    @Autowired
    private IBookmarkService bookmarkService;

    @PostMapping("/save")
    public ResponseEntity<BookmarkDTO> saveBookmark(@RequestBody Bookmark bookmark){
        return ResponseEntity.ok(bookmarkService.saveBookmark(bookmark));
    }

    @GetMapping("/get")
    public ResponseEntity<List<BookmarkDTO>> getBookmarks(){
        return ResponseEntity.ok(bookmarkService.getBookmarks());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<BookmarkDTO> findBookmark(@PathVariable Long id){
        Optional<BookmarkDTO> bookmarkDTO = bookmarkService.findBookmark(id);
        return bookmarkDTO.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/get/findBookmarkByUser/{id}")
    public ResponseEntity<BookmarkDTO> findBookmarkByUser(@PathVariable Long id){
        Optional<BookmarkDTO> bookmarkDTO = bookmarkService.findByUserSec_Id(id);
        return bookmarkDTO.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteBookmark(@PathVariable Long id){
        bookmarkService.deleteBookmark(id);
        return ResponseEntity.ok("Bookmark deleted");
    }

    @PatchMapping("/edit/{id}")
    public ResponseEntity<BookmarkDTO> editBookmark(@PathVariable Long id, @RequestBody Bookmark bookmark){
        return ResponseEntity.ok(bookmarkService.editBookmark(id,bookmark));
    }

}
