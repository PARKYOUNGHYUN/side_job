package com.restapi.service;

import java.util.List;
import java.util.Optional;

import com.restapi.entity.ProgressTime;
import com.restapi.repository.ProgressTimeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProgressTimeService {

    @Autowired
    private ProgressTimeRepository timeRepository;

    public ProgressTime createProgressTime(ProgressTime time) {
        try {
            return timeRepository.save(time);
        } catch (Exception e) {
            return null;
        }
    }

    public List<ProgressTime> getProgressTimeByUserNo(Long id) {
        return timeRepository.findByUserNoEquals(id);
    }

    public ProgressTime patchProgressTime(Long id, ProgressTime time){
        final Optional<ProgressTime> fetchedProgressTime = timeRepository.findById(id);
        if(fetchedProgressTime.isPresent()){
            if(time.getStartAt() != null) fetchedProgressTime.get().setStartAt(time.getStartAt());
            if(time.getEndAt() != null) fetchedProgressTime.get().setEndAt(time.getEndAt());
            
            return timeRepository.save(fetchedProgressTime.get());
        }
        else return null;
    }    

    public boolean deleteProgressTime(Long id){
        final Optional<ProgressTime> progressTime = timeRepository.findById(id);
        if(progressTime.isPresent()){
            timeRepository.deleteById(id);
            return true;
        }
        else return false;
    }
}