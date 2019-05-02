package com.restapi.service;

import java.util.Optional;

import com.restapi.entity.EntryProgress;
import com.restapi.entity.EntryProgressId;
import com.restapi.repository.EntryProgressRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EntryProgressService {

    @Autowired
    private EntryProgressRepository entryProgressRepository;

    public EntryProgress createProgress(EntryProgress progress) {
        try {
            return entryProgressRepository.save(progress);
        } catch (Exception e) {
            return null;
        }
    }

    public Optional<EntryProgress> getEntryProgress(EntryProgressId epId) {
        return entryProgressRepository.findById(epId);
    }

    public EntryProgress patchEntryProgress(EntryProgress progress){
        final Optional<EntryProgress> fetchProgress = entryProgressRepository.findById(progress.getEpId());
        if(fetchProgress.isPresent()){
            if(progress.getEntryStatus() > 0 
                && progress.getEntryStatus() != fetchProgress.get().getEntryStatus())
                fetchProgress.get().setEntryStatus(progress.getEntryStatus());
            return entryProgressRepository.save(fetchProgress.get());
        }
        else return null;
    }
}