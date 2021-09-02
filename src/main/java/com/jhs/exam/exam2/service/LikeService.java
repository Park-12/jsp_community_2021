package com.jhs.exam.exam2.service;

import com.jhs.exam.exam2.container.Container;
import com.jhs.exam.exam2.container.ContainerComponent;
import com.jhs.exam.exam2.dto.Article;
import com.jhs.exam.exam2.dto.Member;
import com.jhs.exam.exam2.repository.LikeRepository;

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
	
}
