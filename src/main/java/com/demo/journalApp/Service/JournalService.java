package com.demo.journalApp.Service;

import com.demo.journalApp.Entity.Journal;
import com.demo.journalApp.Entity.User;

import java.util.List;

public interface JournalService {

    List<Journal> findAllJournals();

    Journal findJournalById(Long id);

    Journal createNewJournal(Long userid, Journal journal);

    void updateJournalById(Long id, Journal journal);

    void deleteJournalById(Long jid);

}
