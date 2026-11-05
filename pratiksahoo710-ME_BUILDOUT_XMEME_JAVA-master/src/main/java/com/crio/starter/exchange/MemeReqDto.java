package com.crio.starter.exchange;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemeReqDto {
    @NotBlank
    private String name;
    @NotBlank
    private String url;
    @NotBlank
    private String caption;
}
