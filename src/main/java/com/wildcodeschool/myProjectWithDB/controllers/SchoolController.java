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
    @PutMapping("/api/school/{id}")

    public School update(
            @PathVariable int id,
            @RequestParam (required = false) String name,
            @RequestParam (required = false) Integer capacity,
            @RequestParam (required = false) String country

    ){
        School school = SchoolRepository.SelectById(id);
        SchoolRepository.update(
                id,
                name !=null ? name : school.getName(),
                capacity !=null ? capacity : school.getCapacity(),
                country !=null ? country : school.getCountry()
        );
        return SchoolRepository.SelectById(id);
    }

    @DeleteMapping("/api/school/{id}")
    public void delete(@PathVariable int id) {
        SchoolRepository.delete(id);
    }
}



