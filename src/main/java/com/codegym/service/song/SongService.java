package com.codegym.service.song;

import com.codegym.model.Song;
import com.codegym.repository.song.ISongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongService implements ISongService{
    @Autowired
    private ISongRepository songRepository;

    @Override
    public List<Song> findAll() {
        return songRepository.findAll();
    }

    @Override
    public List<Song> findByName(String name) {
        name = "%" + name + "%";
        return songRepository.findByName(name);
    }

    @Override
    public Song findById(Long id) {
        return songRepository.findById(id);
    }

    @Override
    public void remove(Long id) {
        songRepository.remove(id);
    }

    @Override
    public void save(Song song) {
        songRepository.save(song);
    }
}
