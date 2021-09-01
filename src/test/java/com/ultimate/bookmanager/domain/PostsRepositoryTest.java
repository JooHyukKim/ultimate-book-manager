package com.ultimate.bookmanager.domain;

import com.ultimate.bookmanager.domain.posts.Posts;
import com.ultimate.bookmanager.domain.posts.PostsRepository;
import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@DataJpaTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @After("")
    public void cleanup() {
        postsRepository.deleteAll();
    }

    @Test
    public void savePostsTest() {
        // test entity
        String title = "테스트 게시글";
        String content = "테스트 본문";
        String author = "Vince Kim";

        Posts savedPosts = postsRepository.save(Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build()
        );

        Assertions.assertEquals(savedPosts.getAuthor(), author);
        Assertions.assertEquals(savedPosts.getTitle(), title);
        Assertions.assertEquals(savedPosts.getContent(), content);

        Posts firstPosts = findPostsList().get(0);

        Assertions.assertEquals(firstPosts.getAuthor(), author);
        Assertions.assertEquals(firstPosts.getTitle(), title);
        Assertions.assertEquals(firstPosts.getContent(), content);

        Assertions.assertEquals(firstPosts, savedPosts);
    }

    private List<Posts> findPostsList() {
        return postsRepository.findAll();
    }


}
