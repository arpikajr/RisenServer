package com.risen.service;



import static java.util.stream.Collectors.toList;

import java.util.List;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.risen.dto.PostRequest;
import com.risen.dto.PostResponse;
import com.risen.exception.PostNotFoundException;
import com.risen.exception.SubRisenNotFoundException;
import com.risen.mapper.PostMapper;
import com.risen.model.Post;
import com.risen.model.SubRisen;
import com.risen.model.User;
import com.risen.repository.PostRepository;
import com.risen.repository.SubRisenRepository;
import com.risen.repository.UserRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class PostService {

    private final PostRepository postRepository;
    private final SubRisenRepository subRisenRepository;
    private final UserRepository userRepository;
    private final AuthService authService;
    private final PostMapper postMapper;

    public void save(PostRequest postRequest) {
        SubRisen subRisen = subRisenRepository.findByName(postRequest.getSubRisenName())
                .orElseThrow(() -> new SubRisenNotFoundException(postRequest.getSubRisenName()));
        postRepository.save(postMapper.map(postRequest, subRisen, authService.getCurrentUser()));
    }

    @Transactional(readOnly = true)
    public PostResponse getPost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException(id.toString()));
        return postMapper.mapToDto(post);
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getAllPosts() {
        return postRepository.findAll()
                .stream()
                .map(postMapper::mapToDto)
                .collect(toList());
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getPostsBySubRisen(Long subRisenId) {
        SubRisen subRisen = subRisenRepository.findById(subRisenId)
                .orElseThrow(() -> new SubRisenNotFoundException(subRisenId.toString()));
        List<Post> posts = postRepository.findAllBySubRisen(subRisen);
        return posts.stream().map(postMapper::mapToDto).collect(toList());
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getPostsByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
        return postRepository.findByUser(user)
                .stream()
                .map(postMapper::mapToDto)
                .collect(toList());
    }
}
