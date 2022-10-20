package com.codegym.repository.song;


import com.codegym.model.Song;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class SongRepository implements ISongRepository{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Song> findAll() {
        String queryStr = "SELECT s FROM Song AS s";
        TypedQuery<Song> query = entityManager.createQuery(queryStr, Song.class);
        return query.getResultList();
    }

    @Override
    public List<Song> findByName(String name) {
        String queryStr = "SELECT s FROM Song AS s WHERE s.songName like :name";
        TypedQuery<Song> query = entityManager.createQuery(queryStr, Song.class);
        query.setParameter("name", name);
        return query.getResultList();
    }

    @Override
    public Song findById(Long id) {
        String queryStr = "SELECT s FROM Song AS s WHERE s.id = :id";
        TypedQuery<Song> query = entityManager.createQuery(queryStr, Song.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public void remove(Long id) {
        entityManager.remove(findById(id));
    }

    @Override
    public void save(Song song) {
        if ((song.getId()) == null) {
            entityManager.persist(song);
        } else {
            entityManager.merge(song);
        }
    }
}
