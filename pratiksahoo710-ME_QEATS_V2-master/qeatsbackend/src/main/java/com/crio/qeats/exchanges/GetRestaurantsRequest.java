/*
 *
 *  * Copyright (c) Crio.Do 2019. All rights reserved
 *
 */

package com.crio.qeats.exchanges;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Configurable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

// TODO: CRIO_TASK_MODULE_RESTAURANTSAPI
//  Implement GetRestaurantsRequest.
//  Complete the class such that it is able to deserialize the incoming query params from
//  REST API clients.
//  For instance, if a REST client calls API
//  /qeats/v1/restaurants?latitude=28.4900591&longitude=77.536386&searchFor=tamil,
//  this class should be able to deserialize lat/long and optional searchFor from that.
@Data
@NoArgsConstructor
@AllArgsConstructor
@Configurable
public class GetRestaurantsRequest {
    @Min(-90)
    @Max(90)
    @NotNull
    private Double latitude;

    @Min(-180)
    @Max(180)
    // @DecimalMax("-180.00")
    @NotNull
    private Double longitude;
    public String searchFor; 

    public GetRestaurantsRequest(Double latiDouble , Double longDouble){
        this.latitude = latiDouble;
        this.longitude = longDouble;
    }
}

