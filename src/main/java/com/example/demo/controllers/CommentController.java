package com.example.demo.controllers;

import com.example.demo.dto.CommentDTO;
import com.example.demo.dto.CommentResponseDTO;
import com.example.demo.dto.EditCommentDTO;
import com.example.demo.models.Comment;
import com.example.demo.models.Post;
import com.example.demo.models.Users;
import com.example.demo.repositories.CommentRepository;
import com.example.demo.repositories.PostRepository;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @PostMapping
    public String postComment(@RequestBody CommentDTO commentDTO) {
        Optional<Users> user = userRepository.findById(commentDTO.getUserId());
        if(user.isEmpty()) {
            return "User does not exist";
        }
        Optional<Post> post = postRepository.findById(commentDTO.getPostID());
        if(post.isEmpty()) {
            return "Post does not exist";
        }
        Comment comment = new Comment(commentDTO.getCommentBody(), commentDTO.getUserId(), commentDTO.getPostID());
        commentRepository.save(comment);
        return "Comment created successfully";
    }

    @GetMapping
    public Object getComment(@RequestParam("commentID") Integer commentId) {
        Optional<Comment> commentOptional = commentRepository.findById(commentId);
        if(commentOptional.isEmpty()) {
            return "Comment does not exist";
        }

        Comment comment = commentOptional.get();
        CommentResponseDTO commentResponseDTO = new CommentResponseDTO();
        commentResponseDTO.setCommentBody(comment.getCommentBody());
        commentResponseDTO.setCommentId(comment.getCommentId());

        Optional<Users> user = userRepository.findById(comment.getUserId());
        commentResponseDTO.setCommentCreator(comment.getUserId(), user.get().getName());

        return commentResponseDTO;
    }

    @PatchMapping
    public String editComment(@RequestBody EditCommentDTO editCommentDTO) {
        Optional<Comment> commentOptional = commentRepository.findById(editCommentDTO.getCommentId());
        if(commentOptional.isEmpty()) {
            return "Comment does not exist";
        }
        Comment comment = commentOptional.get();
        comment.setCommentBody(editCommentDTO.getCommentBody());
        commentRepository.save(comment);
        return "Comment edited successfully";
    }

    @DeleteMapping
    public String deleteComment(@RequestParam("commentID") Integer commentId) {
        Optional<Comment> comment = commentRepository.findById(commentId);
        if(comment.isEmpty()) {
            return "Comment does not exist";
        }
        commentRepository.delete(comment.get());
        return "Comment deleted";
    }
}