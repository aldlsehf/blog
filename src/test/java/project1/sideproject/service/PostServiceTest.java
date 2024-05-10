package project1.sideproject.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import project1.sideproject.domain.Post;
import project1.sideproject.dto.PostDto;
import project1.sideproject.dto.responseDto.PostResponse;
import project1.sideproject.respository.PostRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PostServiceTest {

    @BeforeEach
    public void deleteAll() {
        postRepository.deleteAll();
    }

    @Autowired
    private PostService postService;

    @Autowired
    private PostRepository postRepository;

    @Test
    @DisplayName("글 작성")
    void test1() {
        //given
        PostDto postDto = PostDto.builder()
                .title("제목입니다.")
                .content("내용입니다.")
                .build();

        //when
        postService.save(postDto);

        //then
        Assertions.assertEquals(1L, postRepository.count());
        Post post = postRepository.findAll().get(0);
        Assertions.assertEquals("제목입니다.", post.getTitle());
        Assertions.assertEquals("내용입니다.", post.getContent());

    }

    @Test
    @DisplayName("글 한개 조회")
    public void findTest1() {

        Post post = Post.builder()
                .title("제목1")
                .content("내용1")
                .build();

        postRepository.save(post);

        PostResponse findPost = postService.find(post.getId());

        Assertions.assertNotNull(findPost);
        Assertions.assertEquals(findPost.getTitle(), "제목1");
        Assertions.assertEquals(findPost.getContent(), "내용1");

    }

    @Test
    @DisplayName("글 첫페이지 조회")
    public void findPostsTest() throws Exception {
        //when
        List<Post> requestPosts = IntStream.range(1, 31)
                .mapToObj(i -> {
                    return Post.builder()
                            .title("제목 " + i)
                            .content("내용 " + i)
                            .build();
                }).collect(Collectors.toList());

        postRepository.saveAll(requestPosts);

        //then

        Pageable pageable = PageRequest.of(0, 5, Sort.by(Sort.Direction.DESC, "id"));

        List<PostResponse> postList = postService.findPostList(pageable);

        Assertions.assertEquals(postList.size(), 5);
        Assertions.assertEquals(postList.get(0).getTitle(), "제목 30");
        Assertions.assertEquals(postList.get(4).getTitle(), "제목 26");


    }


}