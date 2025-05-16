package fr.rating.concert.controller;

import fr.rating.concert.dto.ConcertFormDto;
import fr.rating.concert.dto.RatingFormDto;
import fr.rating.concert.dto.UserResponseDto;
import fr.rating.concert.service.*;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/concerts")
@AllArgsConstructor
public class ConcertController {
    private ConcertService concertService;
    private UserService userService;
    private RatingService ratingService;
    private BandService bandService;
    private HallService hallService;

    // Affiche la liste des concerts
    @GetMapping
    public String listConcerts(HttpSession session, Model model) {
        model.addAttribute("concerts", concertService.getAllConcerts());
        model.addAttribute("user", session.getAttribute("user"));
        return "concerts";
    }

    // Affiche le formulaire d’ajout de concert
    @GetMapping("/add")
    public String addConcertForm(HttpSession session, Model model) {
        if (session.getAttribute("user") == null) {
            return "redirect:/user/login";
        }
        model.addAttribute("concertFormDto", new ConcertFormDto());
        model.addAttribute("user", session.getAttribute("user"));
        return "add-concert";
    }

    // Traite l’ajout d’un concert
    @PostMapping("/add")
    public String addConcertAction(@Valid ConcertFormDto concertFormDto, BindingResult result, HttpSession session, Model model) {
        if (session.getAttribute("user") == null) {
            return "redirect:/user/login";
        }
        if (result.hasErrors()) {
            model.addAttribute("user", session.getAttribute("user"));
            return "add-concert";
        }
        concertService.addConcert(concertFormDto);
        return "redirect:/concerts";
    }

    // Affiche les détails d’un concert
    @GetMapping("/{id}")
    public String concertDetails(@PathVariable Long id, HttpSession session, Model model) {
        model.addAttribute("concert", concertService.getConcertById(id));
        model.addAttribute("ratings", ratingService.getRatingsByConcertId(id));
        model.addAttribute("ratingDto", new RatingFormDto());
        model.addAttribute("user", session.getAttribute("user"));
        return "concert-details";
    }

    // Traite l’ajout d’une note
    @PostMapping("/{id}/ratings")
    public String addRating(@PathVariable Long id, @Valid RatingFormDto ratingFormDto, BindingResult result, HttpSession session, Model model) {
        UserResponseDto user = (UserResponseDto) session.getAttribute("user");
        if (user == null) {
            return "redirect:/user/login";
        }
        if (result.hasErrors()) {
            model.addAttribute("concert", concertService.getConcertById(id));
            model.addAttribute("ratings", ratingService.getRatingsByConcertId(id));
            model.addAttribute("user", user);
            return "concert-details";
        }
        ratingService.addRating(ratingFormDto, id, userService.getUserIdByUsername(user.getUsername()));
        return "redirect:/concerts/" + id;
    }
}