package com.ultimate.bookmanager.web;

import com.ultimate.bookmanager.domain.posts.Posts;
import com.ultimate.bookmanager.domain.posts.PostsRepository;
import com.ultimate.bookmanager.web.dto.PostsSaveRequestDto;
import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostsApiControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PostsRepository postsRepository;

    @AfterEach
    public void tearDown() throws Exception {
        postsRepository.deleteAll();
    }

    @Test
    public void savePosts() throws Exception {
        String title = "테스트 게시글";
        String content = "테스트 본문";
        String author = "Vince Kim";

        PostsSaveRequestDto requestDto = PostsSaveRequestDto.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();

        String url = "http://localhost:" + port + "/api/v1/posts";

        //when
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);

        // then
        Assertions.assertTrue(responseEntity.getStatusCode().equals(HttpStatus.OK));
        Assertions.assertTrue(responseEntity.getBody() > 0L);

        List<Posts> all = postsRepository.findAll();
        Assertions.assertTrue(all.get(0).getTitle().equals(title));
        Assertions.assertTrue(all.get(0).getContent().equals(content));

    }

}
