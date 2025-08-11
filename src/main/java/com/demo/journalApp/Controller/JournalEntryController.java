package com.demo.journalApp.Controller;

import com.demo.journalApp.Entity.Journal;
import com.demo.journalApp.Service.JournalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {


    private final JournalService journalService;

    public JournalEntryController(JournalService journalService) {
        this.journalService = journalService;
    }

    @GetMapping("/getAllJournal")
    public List<Journal> findAllJournal(){
        return journalService.findAllJournal();
    }

    @PostMapping("/createJournal")
    public Journal createNewJournal(@RequestBody Journal journal){
        return journalService.createNewJournal(journal);
    }

    @PutMapping("/updateJournal/{id}")
    public Journal updateJournal(@PathVariable Long id, @RequestBody Journal journal){
        return journalService.updateJournal(id, journal);
    }

    @DeleteMapping("/deleteJournal/{id}")
    public void deleteJournal(@PathVariable Long id){
         journalService.deleteJournal(id);
    }
}
