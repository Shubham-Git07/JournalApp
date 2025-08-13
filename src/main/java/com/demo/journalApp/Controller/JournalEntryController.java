package com.demo.journalApp.Controller;

import com.demo.journalApp.Entity.Journal;
import com.demo.journalApp.Entity.User;
import com.demo.journalApp.Service.JournalService;
import com.demo.journalApp.Service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    private final JournalService journalService;

    private final UserService userService;

    public JournalEntryController(JournalService journalService, UserService userService) {
        this.journalService = journalService;
        this.userService = userService;
    }

    @GetMapping("/getAllJournal/{userid}")
    public ResponseEntity<List<Journal>> findAllJournalOfUser(@PathVariable Long userid) {
        User user = userService.findUserById(userid);
        List<Journal> journals = user.getJournals();
        if (journals.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(journals);
    }

    @GetMapping("/getJournalById/{id}")
    public ResponseEntity<Journal> getJournalById(@PathVariable Long id) {
        Journal journal = journalService.findJournalById(id);
        return ResponseEntity.ok(journal);
    }

    @PostMapping("/createJournal/{userid}")
    public ResponseEntity<Journal> createNewJournal(@PathVariable Long userid, @RequestBody Journal journal) {
        try {
            User user = userService.findUserById(userid);
            journalService.createNewJournal(userid, journal);
            List<Journal> journals = user.getJournals();
            user.setJournals(journals);
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

    @DeleteMapping("/deleteJournal/{userid}")
    public ResponseEntity<?> deleteJournal(@PathVariable Long userid) {
        User user = userService.findUserById(userid);
        List<Journal> allJournals = user.getJournals();
        journalService.deleteAllJournals(allJournals);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/deleteAllJournal/{userid}")
    public ResponseEntity<?> deleteAllJournal(@PathVariable Long userid) {
        User user = userService.findUserById(userid);
        List<Journal> allJournals = user.getJournals();
        journalService.deleteAllJournals(allJournals);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}