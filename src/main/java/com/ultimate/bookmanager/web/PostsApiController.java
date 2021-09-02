package com.ultimate.bookmanager.web;

import com.ultimate.bookmanager.service.PostsService;
import com.ultimate.bookmanager.web.dto.PostsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final PostsService postsService;

    @PutMapping("/api/v1/posts")
    public Long save(
            @RequestBody PostsSaveRequestDto requestDto
    ) {
        return postsService.save(requestDto);
    }

}
