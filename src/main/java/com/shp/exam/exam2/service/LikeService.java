package com.shp.exam.exam2.service;

import com.shp.exam.exam2.container.Container;
import com.shp.exam.exam2.container.ContainerComponent;
import com.shp.exam.exam2.dto.Article;
import com.shp.exam.exam2.dto.Like;
import com.shp.exam.exam2.dto.Member;
import com.shp.exam.exam2.dto.ResultData;
import com.shp.exam.exam2.repository.LikeRepository;

public class LikeService implements ContainerComponent {

	private LikeRepository likeRepository;

	@Override
	public void init() {
		likeRepository = Container.likeRepository;
	}

	public boolean actorCanLike(Article article, Member actor) {
		return likeRepository.getPoint("article", article.getId(), actor.getId()) == 0;
	}

	public boolean actorCanCancelLike(Article article, Member actor) {
		return likeRepository.getPoint("article", article.getId(), actor.getId()) > 0;
	}

	public boolean actorCanDislike(Article article, Member actor) {
		return likeRepository.getPoint("article", article.getId(), actor.getId()) == 0;
	}

	public boolean actorCanCancelDislike(Article article, Member actor) {
		return likeRepository.getPoint("article", article.getId(), actor.getId()) < 0;
	}

	public ResultData likeUpDown(String relTypeCode, int relId, int actorId) {		
		Like like = likeRepository.getLikeByRelTypeCodeAndMemberId(relTypeCode, relId, actorId);
	
		if(like != null) {
			likeDown(relTypeCode, relId, actorId);
			return ResultData.from("F-1", "좋아요 취소");
		}
		
		Like disLike = likeRepository.getDisLikeByRelTypeCodeAndMemberId(relTypeCode, relId, actorId);
		
		if(disLike != null) {
			disLikeUpdate(relTypeCode, relId, actorId);
			return ResultData.from("S-1", "좋아요");
		}
		
		likeRepository.likeInsert(relTypeCode, relId, actorId);	
		
		return ResultData.from("S-2", "좋아요");
	}

	private void likeDown(String relTypeCode, int relId, int actorId) {
		likeRepository.likeDelete(relTypeCode, relId, actorId);
	}

	public ResultData disLikeUpDown(String relTypeCode, int relId, int actorId) {	
		Like disLike = likeRepository.getDisLikeByRelTypeCodeAndMemberId(relTypeCode, relId, actorId);
	
		if(disLike != null) {
			disLikeDown(relTypeCode, relId, actorId);
			return ResultData.from("S-1", "싫어요 취소");
		}
		
		Like like = likeRepository.getLikeByRelTypeCodeAndMemberId(relTypeCode, relId, actorId);
		
		if(like != null) {
			likeUpdate(relTypeCode, relId, actorId);
			return ResultData.from("S-2", "싫어요");
		}
		
		likeRepository.disLikeInsert(relTypeCode, relId, actorId);	
		
		return ResultData.from("S-3", "싫어요");
	}

	private void likeUpdate(String relTypeCode, int relId, int actorId) {
		likeRepository.likeUpdate(relTypeCode, relId, actorId);
	}
	
	private void disLikeUpdate(String relTypeCode, int relId, int actorId) {
		likeRepository.disLikeUpdate(relTypeCode, relId, actorId);
	}

	private void disLikeDown(String relTypeCode, int relId, int actorId) {
		likeRepository.disLikeDelete(relTypeCode, relId, actorId);
	}
}
