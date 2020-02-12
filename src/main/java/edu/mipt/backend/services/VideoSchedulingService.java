package edu.mipt.backend.services;

import edu.mipt.backend.model.Entry;
import edu.mipt.backend.model.Room;
import edu.mipt.backend.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Timer;
import java.util.TimerTask;

@Service
public class VideoSchedulingService {
    @Autowired
    public RoomRepository roomRepository;

    public Void Schedule(Long room_id) {
        if (room_id == null) { System.out.println("room_id is null!"); }
        if (roomRepository == null) { System.out.println("Repo is null!");  }
        if (roomRepository.findOneById(room_id) == null) {System.out.println("bad!");}

        final Room room = roomRepository.findOneById(room_id);
        if(room == null) {return null;}

        Long current = room.currentlyPlaying;
        Entry currentEntry = null;
        for (Entry entry : room.entries) {
            if (entry.id >= current) {
                currentEntry = entry;
                break;
            }
        }

        if (currentEntry == null) {
            return null;
        } else {
            final Entry finalCurrentEntry = currentEntry;
            TimerTask task = new TimerTask() {
                @Transactional
                public void run() {
                    room.currentlyPlaying = finalCurrentEntry.id + 1;
                    roomRepository.save(room);
                    Schedule(room.id);
                }
            };
            Timer timer = new Timer("Timer");

            timer.schedule(task, currentEntry.duration);
        }

        return null;
    };
}
