package com.example.satellite_api_service.controller;

//import satellite_api_service.domain.dto.SatRecord;
import com.example.commondto.dto.SatRecord;
import com.example.satellite_api_service.service.ISatService;

//import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiImplicitParam;
//import io.swagger.annotations.ApiOperation;

//@Api(tags = "购物车相关接口")
@RestController
@RequestMapping("/sat")

public class SatController {

    private final ISatService iSatService;

    public SatController(ISatService iSatService) {
        this.iSatService = iSatService;
    }

    @GetMapping("/latest")
    public List<SatRecord> newRecord() {

        return iSatService.latest();

    }
}
