package io.pivotal.pal.tracker;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TimeEntryController {

    private TimeEntryRepository timeEntryRepository;

    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.timeEntryRepository = timeEntryRepository;
    }

    @PostMapping ("/time-entries")
    public ResponseEntity<TimeEntry> create(@RequestBody TimeEntry timeEntryToCreate) {
        TimeEntry timeEntry = this.timeEntryRepository.create(timeEntryToCreate);

        return new ResponseEntity<TimeEntry>(timeEntry, new HttpHeaders(), HttpStatus.CREATED);
    }

    @GetMapping ("/time-entries/{timeEntryId}")
    public ResponseEntity<TimeEntry> read(@PathVariable long timeEntryId) {
        TimeEntry timeEntry = this.timeEntryRepository.find(timeEntryId);

        return new ResponseEntity<TimeEntry>(timeEntry, new HttpHeaders(), timeEntry == null?HttpStatus.NOT_FOUND:HttpStatus.OK);
    }

    @GetMapping ("/time-entries")
    public ResponseEntity<List<TimeEntry>> list() {
        List<TimeEntry> timeEntryList = timeEntryRepository.list();

        return new ResponseEntity<List<TimeEntry>>(timeEntryList, new HttpHeaders(), HttpStatus.OK);
    }

    @PutMapping ("/time-entries/{TIME_ENTRY_ID}")
    public ResponseEntity<TimeEntry> update(@PathVariable("TIME_ENTRY_ID") long timeEntryId, @RequestBody TimeEntry expected) {
        TimeEntry timeEntry = timeEntryRepository.update(timeEntryId, expected);
        return new ResponseEntity<TimeEntry>(timeEntry, new HttpHeaders(), timeEntry == null?HttpStatus.NOT_FOUND:HttpStatus.OK);
     }

    @DeleteMapping ("/time-entries/{timeEntryId}")
    public ResponseEntity delete(@PathVariable long timeEntryId) {
        timeEntryRepository.delete(timeEntryId);
        return new ResponseEntity<TimeEntry>(new HttpHeaders(), HttpStatus.NO_CONTENT);
    }
}

