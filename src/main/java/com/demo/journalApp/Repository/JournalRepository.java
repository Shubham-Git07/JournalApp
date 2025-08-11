package com.demo.journalApp.Repository;

import com.demo.journalApp.Entity.Journal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JournalRepository extends JpaRepository<Journal, Long> {

}
