package com.apress.controller;

import com.apress.domain.Poll;
import com.apress.domain.Vote;
import com.apress.exception.ResourceNotFoundException;
import com.apress.repository.VoteRepository;
import com.apress.service.PollService;
import com.apress.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Optional;

@RestController
public class VoteController {

    @Autowired
    private VoteRepository voteRepository;
    @Autowired
    private VoteService voteService;
    @Autowired
    private PollService pollService;

    @RequestMapping(value = "/polls/{pollId}/votes", method = RequestMethod.POST)
    public ResponseEntity<?> createVote(@PathVariable Long pollId, @RequestBody Vote vote) {

        verifyPoll(pollId);
        voteService.createVote(vote);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(ServletUriComponentsBuilder.
                fromCurrentRequest().path("/{id}").buildAndExpand(vote.getId()).toUri());
        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

    @RequestMapping(value="/polls/{pollId}/votes", method=RequestMethod.GET)
    public Iterable<Vote> getAllVotes(@PathVariable Long pollId) {
        verifyPoll(pollId);
        return voteService.getAllVotes(pollId);
    }

    protected void verifyPoll(Long pollId) throws ResourceNotFoundException {

        Optional<Poll> poll = pollService.getPoll(pollId);
        if (poll.isEmpty()) {
            throw new ResourceNotFoundException("Poll with id " + pollId + " not found");
        }
    }

    @RequestMapping(value = "/polls/votes/{voteId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteVote(@PathVariable Long voteId){

        voteService.deleteVote(voteId);
        return new ResponseEntity<>(HttpStatus.OK);

    }
}
