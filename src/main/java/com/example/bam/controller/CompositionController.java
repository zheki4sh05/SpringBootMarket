package com.example.bam.controller;

import com.example.bam.dto.CompositionDto;
import com.example.bam.entity.Composition;
import com.example.bam.entity.User;
import com.example.bam.service.CompositionService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/composition")
public class CompositionController {

    @Autowired
    private final CompositionService compositionService;

    @SneakyThrows
    @PostMapping
    public ResponseEntity<Composition> create (@RequestPart String title,
                                               @RequestPart String description,
                                               @RequestPart String genre,
                                               @RequestPart("notes") MultipartFile notes,
                                               @RequestPart("song") MultipartFile song) {
        Composition composition = new Composition();
        composition.setTitle(title);
        composition.setNotes(notes.getBytes());
        composition.setSong(song.getBytes());
        composition.setDescription(description);
        composition.setGenre(genre);
        Composition newComposition = compositionService.save(composition);
        return ResponseEntity.ok(newComposition);
    }

    @GetMapping("/findByTitle/{title}")
    public ResponseEntity<Composition> findUserByTitle (@PathVariable String title) {
        Composition composition =  compositionService.findByTitle(title);
        return ResponseEntity.ok(composition);
    }


    @GetMapping("/findAll")
    public ResponseEntity<List<Composition>> findAll() {
        List<Composition> compositions = compositionService.findAll();
        return ResponseEntity.ok(compositions);
    }

    @GetMapping(value = "/{id}/notes", produces = "notes/pdf")
    public ResponseEntity<byte[]> downloadNotes(@PathVariable Long id) {
        Composition composition = compositionService.findById(id).orElseThrow();
        byte[] notes =composition.getNotes();
        return ResponseEntity.ok(notes);
    }

    @GetMapping(value = "/{id}/song", produces = "song/mp3")
    public ResponseEntity<byte[]> downloadSong(@PathVariable Long id) {
        Composition composition = compositionService.findById(id).orElseThrow();
        byte[] song = composition.getSong();
        return ResponseEntity.ok(song);
    }

    @GetMapping("/findByGenre/{genre}")
    public ResponseEntity<Composition> findByGenre (@PathVariable String genre) {
        Composition composition = compositionService.findByGenre(genre);
        return ResponseEntity.ok(composition);
    }

    @GetMapping("/findByAuthor/{author}")
    public ResponseEntity<Composition> findByAuthor (@PathVariable User author) {
        Composition composition = compositionService.findByAuthor(author);
        return ResponseEntity.ok(composition);
    }

    @DeleteMapping("/deleteCompositionById/{id}")
    public HttpStatus deleteComposition (@PathVariable Long id) {
        compositionService.delete(id);
        return HttpStatus.OK;
    }
}






