package com.crio.starter.service;

import com.crio.starter.exchange.MemeReqDto;
import com.crio.starter.exchange.MemeRespDto;
import java.util.*;

public interface MemeService {
    public String createMeme(MemeReqDto meme);
    public List<MemeRespDto> getAllMeme();
    public MemeRespDto getById(String id) throws RuntimeException;
}
