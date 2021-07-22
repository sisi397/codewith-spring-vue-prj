package com.codewith.codewith.controller;
import com.codewith.codewith.model.StageFinish;
import com.codewith.codewith.repository.StageFinishRepository;
import com.codewith.codewith.dto.StageFinishRequestDto;
import com.codewith.codewith.service.StageFinishService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class StageFinishController {

    private final StageFinishRepository stageFinishRepository;
    private final StageFinishService stageFinishService;

    @GetMapping("/api/stageFinish")
    public List<StageFinish> getStageFinish(Principal principal) {
        String userId = principal.getName();
        return stageFinishRepository.findAllByUserId(userId).orElseThrow(
                () -> new IllegalArgumentException("userId가 존재하지 않습니다."));
    }

    //POST (INSERT)
    @PostMapping("/api/stageFinish")
    public StageFinish createStageFinish(@RequestBody StageFinishRequestDto requestDto) {
        StageFinish stageFinish = new StageFinish(requestDto);
        return stageFinishRepository.save(stageFinish);
    }

    //PUT (UPDATE)
    @PutMapping("/api/stageFinish/{id}")
    public Long updateStageFinish(@PathVariable Long id, @RequestBody StageFinishRequestDto requestDto) {
        return stageFinishService.update(id, requestDto);
    }

    //DELETE
    @DeleteMapping("/api/stageFinish/{id}")
    public Long deleteStageFinish(@PathVariable Long id){
        stageFinishRepository.deleteById(id);
        return id;
    }
}
