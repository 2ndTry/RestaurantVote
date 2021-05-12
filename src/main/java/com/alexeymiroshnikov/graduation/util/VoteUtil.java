package com.alexeymiroshnikov.graduation.util;

import com.alexeymiroshnikov.graduation.model.Vote;
import com.alexeymiroshnikov.graduation.to.VoteTo;

import java.util.List;
import java.util.stream.Collectors;

public class VoteUtil {

    public static VoteTo asTo(Vote vote) {
        return new VoteTo(vote.getId(), vote.getDate(), vote.getRestaurant().getId());
    }

    public static List<VoteTo> asTo(List<Vote> votes) {
        return votes.stream().map(VoteUtil::asTo).collect(Collectors.toList());
    }
}
