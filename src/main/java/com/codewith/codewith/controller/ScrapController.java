package com.codewith.codewith.controller;

import com.codewith.codewith.model.Scrap;
import com.codewith.codewith.repository.ScrapRepository;
import com.codewith.codewith.dto.ScrapRequestDto;
import com.codewith.codewith.service.ScrapService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ScrapController {

    private final ScrapRepository scrapRepository;

    @GetMapping("/api/scrap")
    public List<Scrap> getScrap() {
        return scrapRepository.findAll();
    }
    private final ScrapService scrapService;

    //POST (INSERT)
    @PostMapping("/api/scrap")
    public Scrap createScrap(@RequestBody ScrapRequestDto requestDto) {
        Scrap scrap = new Scrap(requestDto);
        return scrapRepository.save(scrap);
    }

    //PUT (UPDATE)
    @PutMapping("/api/scrap/{id}")
    public Long updateScrap(@PathVariable Long id, @RequestBody ScrapRequestDto requestDto) {
        return scrapService.update(id, requestDto);
    }

    //DELETE
    @DeleteMapping("/api/scrap/{id}")
    public Long deleteScrap(@PathVariable Long id){
        scrapRepository.deleteById(id);
        return id;
    }
}
