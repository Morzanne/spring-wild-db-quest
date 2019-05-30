package com.wildcodeschool.myProjectWithDB.controllers;


import com.wildcodeschool.myProjectWithDB.entities.School;
import com.wildcodeschool.myProjectWithDB.repositories.SchoolRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;



import java.util.*;
import java.sql.*;




@Controller
@ResponseBody
public class SchoolController {

    @PostMapping("/api/school")
    @ResponseStatus(HttpStatus.CREATED)
    public School store(

            @RequestParam String name,
            @RequestParam int capacity,
            @RequestParam String country

    ){

        return SchoolRepository.SelectById(
                SchoolRepository.insertInto(
                        name,
                        capacity,
                        country
                ));





    }
}



