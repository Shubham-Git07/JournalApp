package com.demo.journalApp.Controller;

import com.demo.journalApp.Entity.Journal;
import com.demo.journalApp.Entity.User;
import com.demo.journalApp.Exceptions.UserNotFoundException;
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

    @GetMapping("/findAllJournals")
    public ResponseEntity<List<Journal>> findAllJournals() {
        List<Journal> allJournals = journalService.findAllJournals();
        if (allJournals.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(allJournals);
    }

    @GetMapping("/findAllJournalOfUser/{userid}")
    public ResponseEntity<List<Journal>> findAllJournalOfUser(@PathVariable Long userid) {
        User user = userService.findUserById(userid);
        List<Journal> journals = user.getJournalEntries();
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

    @PostMapping("/createUserJournal/{userid}")
    public ResponseEntity<Journal> createUserJournal(@PathVariable Long userid, @RequestBody Journal journal) {
        try {
            Journal savedJournal = journalService.createNewJournal(userid, journal);
            return new ResponseEntity<>(savedJournal, HttpStatus.CREATED);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/updateJournalById/{jid}")
    public ResponseEntity<Journal> updateJournalById(@PathVariable Long jid, @RequestBody Journal journal) {
        journalService.updateJournalById(jid, journal);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/deleteJournalById/{jid}")
    public ResponseEntity<?> deleteJournalById(@PathVariable Long jid) {
        journalService.deleteJournalById(jid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}