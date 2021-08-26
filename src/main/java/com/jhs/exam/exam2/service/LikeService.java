package com.jhs.exam.exam2.service;

import com.jhs.exam.exam2.dto.Member;
import com.jhs.exam.exam2.dto.ResultData;

public class LikeService {

	public ResultData doLike(Member actor, int id) {
		int id = actor.getId();

		if (memberId != writerMemberId) {
			return ResultData.from("F-1", "권한이 없습니다.");
		}

		return ResultData.from("S-1", "수정이 가능합니다.");
	}

}
