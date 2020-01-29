package io.pivotal.pal.tracker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {
    HashMap<Long, TimeEntry> timeEntries;
    private long index = 1L;

    public InMemoryTimeEntryRepository () {
        timeEntries = new HashMap<>();
    }

    @Override
    public TimeEntry create(TimeEntry any) {
        TimeEntry timeEntry = new TimeEntry(index, any.getProjectId(), any.getUserId(), any.getDate(), any.getHours());
        timeEntries.put(index++, timeEntry);

        return timeEntry;
    }

    @Override
    public TimeEntry find(long id) {
        return timeEntries.get(id);
    }

    @Override
    public List<TimeEntry> list() {
        return  timeEntries.values().stream()
                .collect(Collectors.toList());
    }

    @Override
    public TimeEntry update(long id, TimeEntry any) {
        TimeEntry timeEntry = new TimeEntry(id, any.getProjectId(), any.getUserId(), any.getDate(), any.getHours());
        timeEntries.replace(id, timeEntry);

        return timeEntries.get(id);
    }

    @Override
    public void delete(long id) {
        timeEntries.remove(id);
    }
}
