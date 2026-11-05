package com.crio.starter.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import com.crio.starter.data.Meme;
import com.crio.starter.exchange.DuplicateMemeException;
import com.crio.starter.exchange.MemeNotFoundException;
import com.crio.starter.exchange.MemeReqDto;
import com.crio.starter.exchange.MemeRespDto;
import com.crio.starter.repository.MemeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemeServiceImpl implements MemeService {
    @Autowired
    private MemeRepo memeRepo;

    public String createMeme(MemeReqDto meme) {
        if (memeRepo.existsByNameAndUrlAndCaption(meme.getName(), meme.getUrl(),
                meme.getCaption())) {
            throw new DuplicateMemeException("Meme already exists");
        }
        Meme newMeme = new Meme();
        newMeme.setName(meme.getName());
        newMeme.setUrl(meme.getUrl());
        newMeme.setCaption(meme.getCaption());
        newMeme.setCreatedAt(LocalDateTime.now());
        Meme savedMeme = memeRepo.save(newMeme);

        return savedMeme.getId();
    }

    public List<MemeRespDto> getAllMeme() {
        List<Meme> memeList = memeRepo.findTop100ByOrderByCreatedAtDesc();
        return memeList.stream().map((val) -> new MemeRespDto(val.getId(), val.getName(),
                val.getUrl(), val.getCaption())).collect(Collectors.toList());
    }

    public MemeRespDto getById(String id){
        Meme foundMeme = memeRepo.findById(id)
                .orElseThrow(() -> new MemeNotFoundException("Didn't find such meme"));
        return new MemeRespDto(foundMeme.getId(), foundMeme.getName(), foundMeme.getUrl(),
                foundMeme.getCaption());
    }
}
