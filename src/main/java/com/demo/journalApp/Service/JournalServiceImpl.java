package com.demo.journalApp.Service;

import com.demo.journalApp.Entity.Journal;
import com.demo.journalApp.Entity.User;
import com.demo.journalApp.Exceptions.JournalNotFoundException;
import com.demo.journalApp.Exceptions.UserNotFoundException;
import com.demo.journalApp.Repository.JournalRepository;
import com.demo.journalApp.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class JournalServiceImpl implements JournalService {

    private final JournalRepository journalRepository;
    private final UserRepository userRepository;

    public JournalServiceImpl(JournalRepository journalRepository, UserRepository userRepository) {
        this.journalRepository = journalRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Journal> findAllJournals() {
        return journalRepository.findAll();
    }

    public Journal findJournalById(Long id) {
        return journalRepository.findById(id)
                .orElseThrow(() -> new JournalNotFoundException("Journal with id " + id + " not found"));
    }

    public Journal createNewJournal(Long userid, Journal journal) {
        User existingUser = userRepository.findById(userid)
                .orElseThrow(() -> new UserNotFoundException("user with id " + userid + " not found"));
        journal.setDate(LocalDateTime.now());
        journal.setUser(existingUser);
        existingUser.getJournalEntries().add(journal);
        return journalRepository.save(journal);
    }

    @Override
    public void updateJournalById(Long id, Journal journal) {
        Journal existingJournal = journalRepository.findById(id)
                .orElseThrow(() -> new JournalNotFoundException("Journal with id " + id + " not found"));
        existingJournal.setTitle(journal.getTitle());
        existingJournal.setContent(journal.getContent());
        existingJournal.setDate(LocalDateTime.now());
        journalRepository.save(existingJournal);
    }

    @Override
    public void deleteJournalById(Long jid) {
        Journal existingJournal = journalRepository.findById(jid)
                .orElseThrow(() -> new JournalNotFoundException("Journal with id " + jid + " not found"));
        journalRepository.deleteById(existingJournal.getId());
    }

}
