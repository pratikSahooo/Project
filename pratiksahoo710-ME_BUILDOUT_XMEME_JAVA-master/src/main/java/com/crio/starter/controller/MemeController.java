package com.crio.starter.controller;

import java.util.List;
import com.crio.starter.exchange.*;
import com.crio.starter.service.MemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

@RestController
@RequestMapping("/memes")
public class MemeController {
    @Autowired
    private MemeService memeService;
    @PostMapping("/")
    // version1 
    public ResponseEntity<MemeRespID> createMemes(@Valid @RequestBody MemeReqDto meme){
        String memeId = memeService.createMeme(meme);
        return ResponseEntity.status(HttpStatus.CREATED).body(new MemeRespID(memeId));
    }
    @GetMapping("/")
    public ResponseEntity<List<MemeRespDto>> getAllMeme() {
        return ResponseEntity.ok(memeService.getAllMeme());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemeRespDto> getById(@PathVariable String id) {
        return ResponseEntity.ok(memeService.getById(id));
    }
}
