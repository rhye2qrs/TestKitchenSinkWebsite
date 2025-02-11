package edu.ucsb.cs156.kitchensink.controllers;


import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import edu.ucsb.cs156.kitchensink.services.UCSBDiningService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(description="Dining Commons Info from UCSB")
@Slf4j
@RestController
@RequestMapping("/api/dining")
public class UCSBDiningController {
    
    @Autowired
    UCSBDiningService ucsbDiningService;

    @ApiOperation(value = "Get list of dining commons serving meals on given date.", 
    notes = "JSON return format documented here: https://developer.ucsb.edu/apis/dining/dining-menu#/")
    @GetMapping("/commons")
    public ResponseEntity<String> getDiningCommons(
        @ApiParam("date in ISO format, e.g. 2021-10-04") @RequestParam String date
    ) throws JsonProcessingException {
        String result = ucsbDiningService.getDiningCommonsJSON(date);
        return ResponseEntity.ok().body(result);
    }

    @ApiOperation(value = "Get list of meals for a dining commons on a given date.", 
    notes = "JSON return format documented here: https://developer.ucsb.edu/apis/dining/dining-menu#/")
    @GetMapping("/meals")
    public ResponseEntity<String> getDiningCommonsMealsJSON(
        @ApiParam("date in ISO format, e.g. 2021-10-04") @RequestParam String date,
        @ApiParam("dining commons code, e.g. de-la-guerra") @RequestParam String diningCommonsCode
    ) throws JsonProcessingException {
        String result = ucsbDiningService.getDiningCommonsMealsJSON(date, diningCommonsCode);
        return ResponseEntity.ok().body(result);
    }

    @ApiOperation(value = "Get list of items on the menu for a dining commons on a given date.", 
    notes = "JSON return format documented here: https://developer.ucsb.edu/apis/dining/dining-menu#/")
    @GetMapping("/menu")
    public ResponseEntity<String> getDiningCommonsMealsJSON(
        @ApiParam("date in ISO format, e.g. 2021-10-04") @RequestParam String date,
        @ApiParam("dining commons code, e.g. de-la-guerra") @RequestParam String diningCommonsCode,
        @ApiParam("meal code, e.g. dinner") @RequestParam String mealCode
    ) throws JsonProcessingException {
        String result = ucsbDiningService.getDiningCommonsMealItemsJSON(date, diningCommonsCode, mealCode);
        return ResponseEntity.ok().body(result);
    }


}