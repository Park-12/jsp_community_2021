package com.jhs.exam.exam2.http.controller;

import java.util.List;

import com.jhs.exam.exam2.container.Container;
import com.jhs.exam.exam2.dto.Article;
import com.jhs.exam.exam2.dto.Board;
import com.jhs.exam.exam2.dto.ResultData;
import com.jhs.exam.exam2.http.Rq;
import com.jhs.exam.exam2.service.ArticleService;
import com.jhs.exam.exam2.service.BoardService;
import com.jhs.exam.exam2.service.LikeService;
import com.jhs.exam.exam2.util.Ut;

public class UsrLikeController extends Controller {
	private LikeService likeService;

	public void init() {
		likeService = Container.likeService;
	}

	@Override
	public void performAction(Rq rq) {
		switch (rq.getActionMethodName()) {
		case "doLike":
			actionDoLike(rq);
			break;
		default:
			rq.println("존재하지 않는 페이지 입니다.");
			break;
		}
	}

	private void actionDoLike(Rq rq) {
		int id = rq.getIntParam("id", 0);
		String redirectUri = rq.getParam("redirectUri", Ut.f("../article/detail?id=%d", id));

		if (id == 0) {
			rq.historyBack("id를 입력해주세요.");
			return;
		}
		
		ResultData doLikeRd = likeService.doLike(rq.getLoginedMember(), id);

		if (doLikeRd.isFail()) {
			rq.historyBack(doLikeRd.getMsg());
			return;
		}

		ResultData likeRd = likeService.like(id);

		rq.replace(likeRd.getMsg(), redirectUri);
	}
}
