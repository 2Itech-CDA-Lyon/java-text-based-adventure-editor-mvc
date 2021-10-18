package com.tba.editor.controller;

import com.tba.editor.repository.RoomRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/rooms")
public class RoomController {

    @Autowired
    private RoomRepository roomRepository;
    
    @GetMapping
    public String list(Model model)
    {
        model.addAttribute("rooms", roomRepository.findAll());
        return "room/list.html";
    }
}
