package com.demo.journalApp.Service;

import com.demo.journalApp.Entity.Journal;

import java.util.List;

public interface JournalService {

    public List<Journal> findAllJournal();

    public Journal createNewJournal(Journal journal);

    Journal updateJournal(Long id, Journal journal);

    void deleteJournal(Long id);
}
