package com.example.cleanblogservice.before.service;

import com.example.cleanblogservice.before.dto.PostDto;
import com.example.cleanblogservice.before.entity.PostEntity;
import com.example.cleanblogservice.before.repository.PostRepository;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(final PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public void registerPost(PostDto.RegisterRequest registerRequest) {
        // 고민1. dto -> entity 변환 책임은 누구?
        // 핵심은 단방향 의존성
        // 따라서 dto가 entity로 반환 책임을 가짐

        PostEntity initedPost = registerRequest.toEntity();

        // 고민2. Entity의 Validation 책임 누구??
         // 1. 서비스에서 책임을 가져가기 (private method)
        //  2. Validator을 만들어 책임 할당하기
        //  3. Entity에서 책임을 가져가기
        //   -> 3번을 픽할 것  왜냐 Validtion이 Service의 관심사냐? ㄴㄴ Validator를 생성?? 각각 맞는 Validator가 있어야 하는데 오버헤드가 큼
        //      Post의 정합성을 보장해야 할 책임은 Post 도메인 자체에 있다고 보는 것이 좋다 생각함
        //      왜냐 Post의 정합성은 비즈니스에 기반함 따라서 Post 객체는 Post 비즈니스를 표현하고 책임질 필요가 있음
        //      그럼 생성 시점에 validation을 체크할 래 ? 아니면 따로 호출 할래?
        //      -> 나는 따로 호출 할 것임
        //         -> 왜냐? 너무 비즈니스 로직을 숨기는 것도 별로임 -> 서비스 layer를 통해 비즈니스들이 어떻게 응용되는지 표현할 수 있어야 한다 생각함

        // 고민3. Post 자체의 validation이 아니라 중복 검사라던가 유효성 검증을 어떻게 할래?
        // 아직 완전한 답을 내리지는 못함 -> Facade가 한다? Post가 한다?
        // Post가 한다면?

        // optional로 리턴되니까 문제가 생기는데 이를 개선하려면 그냥 getBy로 하고 여기서 null 일 때 저장하는 로직을 짠다??
//        PostEntity post = postRepository.findByTitle(registerRequest.getTitle());
//        post.checkTitleDuplicated(registerRequest.getTitle());

    }
}
