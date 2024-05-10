package project1.sideproject.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import project1.sideproject.dto.PostDto;
import project1.sideproject.dto.responseDto.PostResponse;
import project1.sideproject.service.PostService;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostService postService;

    @PostMapping("/posts")
    public void post(@Valid @RequestBody PostDto postDto) {
        postService.save(postDto);
    }

    //글 조회
    @GetMapping("/posts/{postId}")
    public PostResponse findPost(@PathVariable Long postId) {
        PostResponse postResponse = postService.find(postId);
        return postResponse;
    }

    @GetMapping("/posts")
    public List<PostResponse> findPostList(Pageable pageable){
        return postService.findPostList(pageable);
    }

}
