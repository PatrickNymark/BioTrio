package com.final_project.controllers;

import com.final_project.entities.Theater;
import com.final_project.repositories.TheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TheaterController {

    @Autowired
    TheaterRepository theaterRepository;

    @GetMapping ("/manage/all-theaters")
    public String getAllTheaters(Model model){
        List<Theater> theaterList = theaterRepository.findAllTheaters();

        model.addAttribute("theaters", theaterList);

        return "theater/all-theaters";
    }

    @GetMapping ("/manage/add-theater")
    public String getAddTheater(@ModelAttribute Theater theater, Model model) {

        model.addAttribute("theater", theater);
        return "theater/add-theater";
    }

    @PostMapping("/manage/add-theater")
    public String addTheater(@ModelAttribute Theater theater) {
        theaterRepository.addTheater(theater);
        return "redirect:/manage/all-theaters";
    }

    @GetMapping ("/manage/theater/{id}")
    public String getTheaterById (@PathVariable (name = "id") int id, Model model){
        Theater theater = theaterRepository.findTheaterById(id);

        model.addAttribute("theater", theater);
        return "theater/theater-page";
    }

    @PostMapping("/manage/delete-theater/{theater_id}")
    public String deleteTheater(@PathVariable(name= "theater_id") int id){
        theaterRepository.deleteTheater(id);
        return "redirect:/manage/all-theaters";
    }

    @GetMapping("/manage/edit-theater/{id}")
    public String getEditTheater(@PathVariable(name="id") int id, Model model){
        Theater theaterToEdit = theaterRepository.findTheaterById(id);

        model.addAttribute("theater", theaterToEdit);
        return "theater/edit-theater";
    }

   @PostMapping("/manage/edit-theater/{id}")
   public String editTheater(@PathVariable(name="id") int id, @ModelAttribute Theater theater, Model model){
        theaterRepository.editTheater(theater);
       return "redirect:/manage/all-theaters";
   }
}
