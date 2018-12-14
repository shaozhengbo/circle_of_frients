package com.ibm.picasso.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.picasso.dao.CommentDao;
import com.ibm.picasso.domain.Comment;
import com.ibm.picasso.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	private CommentDao commentDao;

	@Override
	public int addComment(Comment comment) {
		return commentDao.insert(comment);
	}

	@Override
	public int deleteComment(Comment comment) {
		return commentDao.deleteByPrimaryKey(comment);
	}

	@Override
	public List<Comment> findCommentByMid(Long mid) {
		return commentDao.selectByMid(mid);
	}



}
