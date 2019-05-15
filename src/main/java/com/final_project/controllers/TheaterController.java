package com.final_project.controllers;

import com.final_project.entities.Theater;
import com.final_project.repositories.TheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TheaterController {

    @Autowired
    TheaterRepository theaterRepository;

    @GetMapping ("/all-theaters")
    public String getAllTheaters(Model model){
        List<Theater> theaterList = theaterRepository.findAllTheaters();

        model.addAttribute("theaters", theaterList);

        return "all-theaters";
    }

    @GetMapping ("/add-theater")
    public String getAddTheater() {
        return "add-theater";
    }

    @PostMapping("/add-theater")
    public String addTheater(@RequestParam("theater") String theater,
                             @RequestParam("numberOfRows") int numberOfRows,
                             @RequestParam("seatsPerRow")int seatsPerRow) {

        Theater newTheater = new Theater();

        newTheater.setTheaterName(theater);
        newTheater.setNumberOfRows(numberOfRows);
        newTheater.setSeatsPerRow(seatsPerRow);

        theaterRepository.addTheater(newTheater);
        return "redirect:/all-theater";
    }
}
