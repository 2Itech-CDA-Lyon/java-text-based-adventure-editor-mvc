package com.tba.editor.controller;

import com.tba.editor.entity.Room;
import com.tba.editor.repository.RoomRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.view.RedirectView;

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

    @GetMapping("/create")
    public String createForm(Model model)
    {
        model.addAttribute("room", new Room());
        model.addAttribute("header", "Create new room");
        return "room/edit.html";
    }

    @PostMapping("/create")
    public RedirectView create(@RequestParam String name)
    {
        Room room = new Room();
        room.setName(name);
        roomRepository.save(room);
        return new RedirectView("/rooms");
    }

    @GetMapping("/{id}/update")
    public String updateForm(Model model, @PathVariable int id) throws ResponseStatusException
    {
        Room room = roomRepository.findById(id).orElseThrow(
            () -> { throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Room #" + id + " does not exist."); }
        );
        model.addAttribute("room", room);
        model.addAttribute("header", "Edit room #" + room.getId());
        return "room/edit.html";
    }

    @PostMapping("/{id}/update")
    public RedirectView update(@PathVariable int id, @RequestParam String name)
    {
        Room room = roomRepository.findById(id).orElseThrow(
            () -> { throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Room #" + id + " does not exist."); }
        );
        room.setName(name);
        roomRepository.save(room);
        return new RedirectView("/rooms");
    }

    @PostMapping("/{id}/delete")
    public RedirectView delete(@PathVariable int id)
    {
        Room room = roomRepository.findById(id).orElseThrow(
            () -> { throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Room #" + id + " does not exist."); }
        );
        roomRepository.delete(room);
        return new RedirectView("/rooms");
    }
}
