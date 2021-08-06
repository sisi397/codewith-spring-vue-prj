package com.codewith.codewith.controller;

import com.codewith.codewith.model.StageIng;
import com.codewith.codewith.model.UserInfo;
import com.codewith.codewith.repository.StageIngRepository;
import com.codewith.codewith.dto.StageIngRequestDto;
import com.codewith.codewith.service.StageIngService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
@RestController
public class StageIngController {

    private final StageIngRepository stageIngRepository;
    private final StageIngService stageIngService;
    @Resource
    private UserInfo userInfo;

    @GetMapping("/api/stageIng/{course}")
    public List<StageIng> getStageIng(@PathVariable int course, HttpServletRequest request) {
        HttpSession session = request.getSession();
        //String userId = (String)(session.getAttribute("userId"));
        String userId = "id";
        return stageIngRepository.findAllByUserIdAndCourse(userId,course).orElseThrow(() -> new IllegalArgumentException("존재하지 않습니다."));
    }

    //전체 데이터 불러오기
    @GetMapping("/api/stageIng")
    public List<StageIng> getStageIngAll(HttpServletRequest request) {
        HttpSession session = request.getSession();
        //String userId = (String)(session.getAttribute("userId"));
        String userId = "id";
        return stageIngRepository.findAllByUserId(userId).orElseThrow(() -> new IllegalArgumentException("존재하지 않습니다."));
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

        boolean present = stageIngRepository.findByUserIdAndCourse(requestDto.getUserId(), requestDto.getCourse()).isPresent();
        if(present) {
            StageIng found = stageIngRepository.findByUserIdAndCourse(requestDto.getUserId(), requestDto.getCourse()).get();
            stageIngService.update(found.getId(), requestDto);
            return stageIng;
        }

        return stageIngRepository.save(stageIng);
    }

    //PUT (UPDATE)
    @PutMapping("/api/stageIng")
    public Long updateStageIng(@RequestBody StageIngRequestDto requestDto) {

        StageIng found = stageIngRepository.findByUserIdAndCourse(requestDto.getUserId(), requestDto.getCourse()).get();

        return stageIngService.update(found.getId(), requestDto);
    }

    //DELETE
    @DeleteMapping("/api/stageIng/{id}")
    public Long deleteStageIng(@PathVariable Long id){
        stageIngRepository.deleteById(id);
        return id;
    }
}
