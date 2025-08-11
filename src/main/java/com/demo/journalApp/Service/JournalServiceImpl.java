package com.demo.journalApp.Service;

import com.demo.journalApp.Entity.Journal;
import com.demo.journalApp.Exceptions.JournalNotFoundException;
import com.demo.journalApp.Repository.JournalRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JournalServiceImpl implements JournalService {

    private final JournalRepository journalRepository;

    public JournalServiceImpl(JournalRepository journalRepository) {
        this.journalRepository = journalRepository;
    }

    @Override
    public List<Journal> findAllJournal() {
        return journalRepository.findAll();
    }

    public Journal createNewJournal(Journal journal) {
        return journalRepository.save(journal);
    }

    @Override
    public Journal updateJournal(Long id, Journal journal) {
        Journal existingJournal = journalRepository.findById(id)
                .orElseThrow(() -> new JournalNotFoundException("Journal with id " + id + " not found"));

        existingJournal.setTitle(journal.getTitle());
        existingJournal.setContent(journal.getContent());
        return journalRepository.save(existingJournal);
    }

    @Override
    public void deleteJournal(Long id) {
        journalRepository.deleteById(id);
    }


}
