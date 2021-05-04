package com.apress.controller;

import com.apress.domain.Poll;
import com.apress.domain.Vote;
import com.apress.dto.OptionCount;
import com.apress.dto.VoteResult;
import com.apress.exception.ResourceNotFoundException;
import com.apress.repository.VoteRepository;
import com.apress.service.ComputeResultService;
import com.apress.service.PollService;
import com.apress.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
public class ComputeResultController {

    @Autowired
    private ComputeResultService computeResultService;
    @Autowired
    private PollService pollService;

    @RequestMapping(value = "/computeresult", method = RequestMethod.GET)
    public ResponseEntity<?> computeResult(@RequestParam Long pollId) {
        verifyPoll(pollId);
        return new ResponseEntity<VoteResult>(computeResultService.computeResult(pollId), HttpStatus.OK);

    }

    protected void verifyPoll(Long pollId) throws ResourceNotFoundException {

        Optional<Poll> poll = pollService.getPoll(pollId);
        if (poll.isEmpty()) {
            throw new ResourceNotFoundException("Poll with id " + pollId + " not found");
        }
    }
}


