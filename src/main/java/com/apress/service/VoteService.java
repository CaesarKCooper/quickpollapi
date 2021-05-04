package com.apress.service;

import com.apress.domain.Poll;
import com.apress.domain.Vote;
import com.apress.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VoteService {

    @Autowired
    VoteRepository voteRepository;

    public void createVote(Vote vote){

        voteRepository.save(vote);
    }

    public Iterable<Vote> getAllVotes(Long pollId){

        return voteRepository.findByPoll(pollId);
    }

    public void deleteVote(Long voteId){

        voteRepository.deleteById(voteId);
    }

    }

