package com.ultimate.bookmanager.service;

import com.ultimate.bookmanager.domain.posts.PostsRepository;
import com.ultimate.bookmanager.web.dto.PostsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        Long id = postsRepository.save(requestDto.toEntity()).getId();
        return id;
    }

}
