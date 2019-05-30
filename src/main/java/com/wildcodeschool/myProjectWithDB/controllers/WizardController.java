package com.wildcodeschool.myProjectWithDB.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.wildcodeschool.myProjectWithDB.entities.Wizard;
import com.wildcodeschool.myProjectWithDB.repositories.WizardRepository;

import java.util.*;
import java.sql.Date;

@Controller
@ResponseBody
public class WizardController {

    @GetMapping("/api/wizards")
    public List<Wizard> getWizards(@RequestParam(defaultValue = "%") String family) {
        return WizardRepository.selectByLastname(family);
    }

    @PostMapping("/api/wizards")
    @ResponseStatus(HttpStatus.CREATED)

    public Wizard store(
            @RequestParam String firstname,
            @RequestParam String lastname,
            @RequestParam Date birthday,
            @RequestParam(required = false, name="birth_place") String birthPlace,
            @RequestParam(required = false) String biography,
            @RequestParam(name = "is_muggle") Boolean iMuggle
    ){
        int idGeneratedByInsertion = WizardRepository.insert(
                firstname,
                lastname,
                birthday,
                birthPlace,
                biography,
                iMuggle
        );
        return WizardRepository.selectById(
                idGeneratedByInsertion
        );
    }
}
