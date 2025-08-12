package com.demo.journalApp.Service;

import com.demo.journalApp.Entity.Journal;

import java.util.List;

public interface JournalService {

    List<Journal> findAllJournal();

    Journal findJournalById(Long id);

    void createNewJournal(Journal journal);

    void updateJournal(Long id, Journal journal);

    void deleteJournal(Long id);
}
