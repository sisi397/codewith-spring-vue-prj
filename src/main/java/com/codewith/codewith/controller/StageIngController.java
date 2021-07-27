package com.codewith.codewith.controller;

import com.codewith.codewith.model.StageIng;
import com.codewith.codewith.repository.StageIngRepository;
import com.codewith.codewith.dto.StageIngRequestDto;
import com.codewith.codewith.service.StageIngService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class StageIngController {

    private final StageIngRepository stageIngRepository;
    private final StageIngService stageIngService;

    @GetMapping("/api/stageIng")
    public List<StageIng> getStageIng() {
        return stageIngRepository.findAll();
    }
//    @GetMapping("/api/stageIng")
//    public List<StageIng> getStageIng(Principal principal) {
//        String userId = principal.getName();
//        return stageIngRepository.findAllByUserId(userId).orElseThrow(
//                () -> new IllegalArgumentException("userId가 존재하지 않습니다."));
//    }


    //POST (INSERT)
    @PostMapping("/api/stageIng")
    public StageIng createStageIng(@RequestBody StageIngRequestDto requestDto) {
        StageIng stageIng = new StageIng(requestDto);
        return stageIngRepository.save(stageIng);
    }

    //PUT (UPDATE)
    @PutMapping("/api/stageIng/{id}")
    public Long updateStageIng(@PathVariable Long id, @RequestBody StageIngRequestDto requestDto) {
        return stageIngService.update(id, requestDto);
    }

    //DELETE
    @DeleteMapping("/api/stageIng/{id}")
    public Long deleteStageIng(@PathVariable Long id){
        stageIngRepository.deleteById(id);
        return id;
    }
}
