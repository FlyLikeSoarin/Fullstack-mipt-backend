package edu.mipt.backend.repositories;

import edu.mipt.backend.model.Entry;
import org.springframework.data.repository.CrudRepository;

public interface EntryRepository extends CrudRepository<Entry, Long> {
    Entry findOneById(Long id);
}
