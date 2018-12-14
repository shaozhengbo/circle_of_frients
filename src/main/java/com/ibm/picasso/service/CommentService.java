package com.ibm.picasso.service;

import java.util.List;

import com.ibm.picasso.domain.Comment;

public interface CommentService {
	
	int addComment(Comment comment);
	
	int deleteComment(Comment comment);
	
	List<Comment> findCommentByMid(Long mid);
	
}
