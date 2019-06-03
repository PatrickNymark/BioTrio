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

    /**
     * Method retrieves all theaters
     *
     * @param model
     * @return String
     */
    @GetMapping ("/manage/all-theaters")
    public String getAllTheaters(Model model){
        List<Theater> theaterList = theaterRepository.findAllTheaters();

        model.addAttribute("theaters", theaterList);

        return "theater/all-theaters";
    }

    /**
     * Method retrieves add theater form
     *
     * @param theater
     * @param model
     * @return
     */
    @GetMapping ("/manage/add-theater")
    public String getAddTheater(@ModelAttribute Theater theater, Model model) {

        model.addAttribute("theater", theater);
        return "theater/add-theater";
    }

    /**
     * Method takes a theater model and saves it to database
     *
     * @param theater
     * @return
     */
    @PostMapping("/manage/add-theater")
    public String addTheater(@ModelAttribute Theater theater) {
        theaterRepository.addTheater(theater);
        return "redirect:/manage/all-theaters";
    }

    /**
     * Method retrieves single theater page
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping ("/manage/theater/{id}")
    public String getTheaterById (@PathVariable (name = "id") int id, Model model){
        Theater theater = theaterRepository.findTheaterById(id);

        model.addAttribute("theater", theater);
        return "theater/theater-page";
    }

    /**
     * Method takes a path variable and then deletes record from database
     *
     * @param id
     * @return
     */
    @PostMapping("/manage/delete-theater/{theater_id}")
    public String deleteTheater(@PathVariable(name= "theater_id") int id){
        theaterRepository.deleteTheater(id);
        return "redirect:/manage/all-theaters";
    }

    /**
     * Method retrieves edit theater form
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/manage/edit-theater/{id}")
    public String getEditTheater(@PathVariable(name="id") int id, Model model){
        Theater theaterToEdit = theaterRepository.findTheaterById(id);

        model.addAttribute("theater", theaterToEdit);
        return "theater/edit-theater";
    }

    /**
     * Method takes a theater and then saves it to the database
     *
     * @param id
     * @param theater
     * @param model
     * @return
     */
   @PostMapping("/manage/edit-theater/{id}")
   public String editTheater(@PathVariable(name="id") int id, @ModelAttribute Theater theater, Model model){
        theaterRepository.editTheater(theater);
       return "redirect:/manage/all-theaters";
   }
}
