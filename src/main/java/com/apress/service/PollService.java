package com.apress.service;

import com.apress.domain.Poll;
import com.apress.repository.PollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class PollService {

    @Autowired
    PollRepository pollRepository;

    public Iterable<Poll> getAllPolls(){

        return pollRepository.findAll();
    }

    public void createPoll(Poll poll){

        pollRepository.save(poll);
    }

    public Optional<Poll> getPoll(Long pollId){

        return pollRepository.findById(pollId);
    }

    public void deletePoll(Long pollId){

        pollRepository.deleteById(pollId);
    }

    public void updatePoll(Poll poll){

        pollRepository.save(poll);
    }

}
