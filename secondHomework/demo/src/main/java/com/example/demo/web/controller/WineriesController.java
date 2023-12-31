package com.example.demo.web.controller;

import com.example.demo.model.Wineries;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/wineries")
public class WineriesController {

    private List<Wineries> wineries;

    @GetMapping
    public String getWineries(Model model) {
        ObjectMapper objectMapper = new ObjectMapper();

        try (InputStream inputStream = getClass().getResourceAsStream("/wineries.json")) {
            wineries = objectMapper.readValue(inputStream, new TypeReference<List<Wineries>>() {});
            model.addAttribute("wineries", wineries);

            List<String> locationOptions = wineries.stream()
                    .map(Wineries::getLocation)
                    .distinct()
                    .collect(Collectors.toList());

            model.addAttribute("locationOptions", locationOptions);
            // Set selectedLocation to an empty string for the initial state
            model.addAttribute("selectedLocation", "");
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception appropriately
        }

        return "wineries";
    }


    @GetMapping("/filter")
    public String getFilteredWineries(@RequestParam(required = false) String location, Model model) {
        // Perform filtering based on latitude and longitude
        List<Wineries> filteredWineries = filterWineriesByLocation(location);

        // Print filtered wineries for verification
        System.out.println("Filtered Wineries:");

        List<String> locationOptions = wineries.stream()
                .map(Wineries::getLocation)
                .distinct()
                .collect(Collectors.toList());

        model.addAttribute("locationOptions", locationOptions);
        // Set the selectedLocation attribute based on the request parameter
        model.addAttribute("selectedLocation", location);
        model.addAttribute("wineries", wineries);

        // Add the filtered wineries to the model
        model.addAttribute("filteredWineries", filteredWineries);

        // Return the template
        return "wineries";
    }

    private List<Wineries> filterWineriesByLocation(String location) {
        // Implement your filtering logic here based on the provided location
        if (location == null || location.trim().isEmpty()) {
            // If location is empty or null, return all wineries
            return wineries;
        }

        // Otherwise, filter wineries by location
        return wineries.stream()
                .filter(winery -> location.equalsIgnoreCase(winery.getLocation()))
                .collect(Collectors.toList());
    }

}
