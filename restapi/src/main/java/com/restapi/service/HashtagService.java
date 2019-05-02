package com.restapi.service;

import java.util.Optional;

import com.restapi.entity.Hashtag;
import com.restapi.repository.HashtagRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HashtagService {

    @Autowired
    private HashtagRepository hashtagRepository;

    public Optional<Hashtag> getHashtag(Long id){
        return hashtagRepository.findById(id);
    }

    public Hashtag createHashtag(Hashtag hashtag) {
        try {
            return hashtagRepository.save(hashtag);
        } catch (Exception e) {
            return null;
        }
    }
}