package com.codewith.codewith.controller;
import com.codewith.codewith.model.StageFinish;
import com.codewith.codewith.model.UserInfo;
import com.codewith.codewith.repository.StageFinishRepository;
import com.codewith.codewith.dto.StageFinishRequestDto;
import com.codewith.codewith.service.StageFinishService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;

@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RestController
public class StageFinishController {

    private final StageFinishRepository stageFinishRepository;
    private final StageFinishService stageFinishService;
    @Resource
    private UserInfo userInfo;

    @GetMapping("/api/stageFinish")
    public List<StageFinish> getStageFinish(HttpServletRequest request) {
        HttpSession session = request.getSession();
        //String userId = (String)(session.getAttribute("userId"));
        String userId = "id";
        return stageFinishRepository.findAllByUserId(userId).orElseThrow(
                () -> new IllegalArgumentException("userId가 존재하지 않습니다."));
    }

    //POST (INSERT)
    @PostMapping("/api/stageFinish")
    public StageFinish createStageFinish(@RequestBody StageFinishRequestDto requestDto) {
        StageFinish stageFinish = new StageFinish(requestDto);

        boolean present = stageFinishRepository.findByUserIdAndCourseAndStage(requestDto.getUserId(), requestDto.getCourse(), requestDto.getStage()).isPresent();
        if(present) return null;

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
