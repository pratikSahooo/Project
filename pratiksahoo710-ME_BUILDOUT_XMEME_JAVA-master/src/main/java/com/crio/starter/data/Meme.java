package com.crio.starter.data;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import javax.annotation.Generated;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document(collection = "memes")
public class Meme {
    @Id
    private String id;
    private String name;
    private String url;
    private String caption;
    private LocalDateTime createdAt;
}
