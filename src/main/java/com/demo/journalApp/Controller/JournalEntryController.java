package com.demo.journalApp.Controller;

import com.demo.journalApp.Entity.Journal;
import com.demo.journalApp.Service.JournalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    private final JournalService journalService;

    public JournalEntryController(JournalService journalService) {
        this.journalService = journalService;
    }

    @GetMapping("/getAllJournal")
    public ResponseEntity<List<Journal>> findAllJournal() {
        List<Journal> journals = journalService.findAllJournal();
        if (journals.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(journals);
    }

    @GetMapping("/getJournalById/{id}")
    public ResponseEntity<Journal> getJournalById(@PathVariable Long id) {
        Journal journal = journalService.findJournalById(id);
        return ResponseEntity.ok(journal);
    }

    @PostMapping("/createJournal")
    public ResponseEntity<Journal> createNewJournal(@RequestBody Journal journal) {
        try {
            journal.setDate(LocalDateTime.now());
            journalService.createNewJournal(journal);
            return new ResponseEntity<>(journal, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/updateJournal/{id}")
    public ResponseEntity<Journal> updateJournal(@PathVariable Long id, @RequestBody Journal journal) {
        journalService.updateJournal(id, journal);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/deleteJournal/{id}")
    public ResponseEntity<?> deleteJournal(@PathVariable Long id) {
        journalService.deleteJournal(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}