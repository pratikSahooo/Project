package com.crio.starter.repository;

import java.util.List;
import com.crio.starter.data.Meme;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemeRepo extends  MongoRepository<Meme, String>  {
    List<Meme> findTop100ByOrderByCreatedAtDesc();
    boolean existsByNameAndUrlAndCaption(String name, String url, String caption);
    
}
