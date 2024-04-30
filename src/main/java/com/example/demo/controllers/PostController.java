package com.example.demo.controllers;

import com.example.demo.dto.CommentResponseDTO;
import com.example.demo.dto.CreatePostDTO;
import com.example.demo.dto.EditPostDTO;
import com.example.demo.dto.PostDTO;
import com.example.demo.models.Comment;
import com.example.demo.models.Post;
import com.example.demo.models.Users;
import com.example.demo.repositories.CommentRepository;
import com.example.demo.repositories.PostRepository;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CommentRepository commentRepository;

    @PostMapping
    public String createPost(@RequestBody CreatePostDTO createPostDTO) {
        Optional<Users> user = userRepository.findById(createPostDTO.getUserId());
        if(user.isEmpty()) {
            return "User does not exist";
        }
        Post post = new Post(createPostDTO.getPostBody());
        postRepository.save(post);
        return "Post created successfully";
    }

    @GetMapping
    public Object getPost(@RequestParam("postID") Integer postId) {
        Optional<Post> postOptional = postRepository.findById(postId);
        if(postOptional.isEmpty()) {
            return "Post does not exist";
        }
        Post post = postOptional.get();
        PostDTO postDTO = new PostDTO(post.getPostId(), post.getPostBody(), post.getLocalDate());
        List<Comment> commentList = commentRepository.findByPostIdOrderByCommentIdDesc(postId);
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

        return postDTO;
    }

    @PatchMapping
    public String editPost(@RequestBody EditPostDTO editPostDTO) {
        Optional<Post> postOptional = postRepository.findById(editPostDTO.getPostId());
        if(postOptional.isEmpty()) {
            return "Post does not exist";
        }
        Post post = postOptional.get();
        post.setLocalDate(LocalDate.now());
        post.setPostBody(editPostDTO.getPostBody());
        postRepository.save(post);
        return "Post edited successfully";
    }

    @DeleteMapping
    public String deletePost(@RequestParam("postID") Integer postId) {
        Optional<Post> post = postRepository.findById(postId);
        if(post.isEmpty()) {
            return "Post does not exist";
        }
        postRepository.delete(post.get());
        return "Post deleted";
    }
}
