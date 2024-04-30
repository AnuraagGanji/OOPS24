package com.example.demo.controllers;

import com.example.demo.dto.CommentResponseDTO;
import com.example.demo.dto.PostDTO;
import com.example.demo.models.Comment;
import com.example.demo.models.Post;
import com.example.demo.models.Users;
import com.example.demo.repositories.CommentRepository;
import com.example.demo.repositories.PostRepository;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class UserFeedController {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public List<PostDTO> getUserFeed() {
        List<Post> postList = postRepository.findAll();
        Collections.reverse(postList);

        List<PostDTO> postDTOList = new ArrayList<>();

        postList.forEach(post -> {
            PostDTO postDTO = new PostDTO(post.getPostId(), post.getPostBody(), post.getLocalDate());

            List<Comment> commentList = commentRepository.findByPostIdOrderByCommentIdDesc(post.getPostId());
            List<CommentResponseDTO> commentResponseDTOList = new ArrayList<>();

            commentList.forEach(comment -> {
                CommentResponseDTO commentResponseDTO = new CommentResponseDTO();
                commentResponseDTO.setCommentBody(comment.getCommentBody());
                commentResponseDTO.setCommentId(comment.getCommentId());

                Optional<Users> user = userRepository.findById(comment.getUserId());
                commentResponseDTO.setCommentCreator(comment.getUserId(), user.get().getName());
                commentResponseDTOList.add(commentResponseDTO);
            });

            postDTO.setComments(commentResponseDTOList);
            postDTOList.add(postDTO);
        });

        return postDTOList;
    }
}
