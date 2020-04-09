package edu.mipt.backend.Responses;

import edu.mipt.backend.model.Entry;

import java.util.ArrayList;
import java.util.List;

public class EntryResponse {
    public EntryResponse(Entry entry) {
        this.id = entry.id;
        this.user_id = entry.user.id;
        this.username = entry.user.username;
        this.url = entry.url;
        this.title = entry.title;
        this.duration = entry.duration;
        this.uploadTimestamp = entry.uploadTimestamp;
    }

    public static Iterable<EntryResponse> convertList(Iterable<Entry> entryList) {
        List<EntryResponse> responseList = new ArrayList<EntryResponse>();
        for (Entry entry : entryList) {
            responseList.add(new EntryResponse(entry));
        }
        return responseList;
    }

    public Long id;
    public Long user_id;
    public String username;
    public String url;
    public String title;
    public Long duration;
    public Long uploadTimestamp;
}
