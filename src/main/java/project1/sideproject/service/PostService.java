package project1.sideproject.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import project1.sideproject.domain.Post;
import project1.sideproject.dto.PostDto;
import project1.sideproject.dto.responseDto.PostResponse;
import project1.sideproject.respository.PostRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public void save(PostDto postDto) {
        Post post = Post.builder()
                .title(postDto.getTitle())
                .content(postDto.getContent())
                .build();

        postRepository.save(post);
    }

    public PostResponse find(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 글입니다."));

        PostResponse postResponse = PostResponse.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .build();

        return postResponse;
    }

    public List<PostResponse> findPostList(Pageable pageable) {

        return postRepository.findAll(pageable).stream()
                .map(post -> new PostResponse(post)).collect(Collectors.toList());
    }
}
