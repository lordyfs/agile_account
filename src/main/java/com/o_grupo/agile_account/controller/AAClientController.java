package com.o_grupo.agile_account.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.o_grupo.agile_account.database.model.AAClientModel;
import com.o_grupo.agile_account.service.AAClientService;


@Controller
public class AAClientController {
    @Autowired
    private AAClientService service;

    @GetMapping("/")
    public String boasVindas(){
        return "./login.html";
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AAClientModel client){
        return ResponseEntity.ok(this.service.login(client));
    }
}
