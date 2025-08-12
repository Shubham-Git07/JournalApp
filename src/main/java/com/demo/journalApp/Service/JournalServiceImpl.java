package com.demo.journalApp.Service;

import com.demo.journalApp.Entity.Journal;
import com.demo.journalApp.Exceptions.JournalNotFoundException;
import com.demo.journalApp.Repository.JournalRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    public Journal findJournalById(Long id) {
        return journalRepository.findById(id)
                .orElseThrow(() -> new JournalNotFoundException("Journal with id " + id + " not found"));
    }

    public void createNewJournal(Journal journal) {
        journal.setDate(LocalDateTime.now());
        journalRepository.save(journal);
    }

    @Override
    public void updateJournal(Long id, Journal journal) {
        Journal existingJournal = journalRepository.findById(id)
                .orElseThrow(() -> new JournalNotFoundException("Journal with id " + id + " not found"));
        existingJournal.setTitle(journal.getTitle());
        existingJournal.setContent(journal.getContent());
        existingJournal.setDate(LocalDateTime.now());
        journalRepository.save(existingJournal);
    }

    @Override
    public void deleteJournal(Long id) {
        Journal existingJournal = journalRepository.findById(id)
                .orElseThrow(() -> new JournalNotFoundException("Journal with id " + id + " not found"));
        journalRepository.deleteById(existingJournal.getId());
    }

}
