package com.restapi.service;

import java.util.List;
import java.util.Optional;

import com.restapi.entity.FavoriteTag;
import com.restapi.entity.FavoriteTagId;
import com.restapi.repository.FavoriteTagRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FavoriteTagService {

    @Autowired
    private FavoriteTagRepository favoriteTagRepository;

    public FavoriteTag createProgress(FavoriteTag progress) {
        try {
            return favoriteTagRepository.save(progress);
        } catch (Exception e) {
            return null;
        }
    }

    public List<FavoriteTag> getFavoriteTags(long userNo) {
        return favoriteTagRepository.findByFtIdUserNo(userNo);
    }

    public boolean deleteFavoriteTag(FavoriteTagId ftId){
        final Optional<FavoriteTag> favoriteTag = favoriteTagRepository.findById(ftId);
        if(favoriteTag.isPresent()){
            favoriteTagRepository.deleteById(ftId);
            return true;
        }
        else return false;
    }
}