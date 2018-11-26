package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashSet;
import java.util.Set;

@Controller
public class HomeController {
    @Autowired
    AlbumRepository albumRepository;

    @RequestMapping ("/")
    public String Index (Model model) {
        // First let's create a director
        Album album = new Album();
        album.setName("Nothing was the Same");
        album.setGenre("Rap");

        //Now let's create a movie
        Song song = new Song ();
        song.setTitle("Legend");
        song.setYear(2017);
        song.setDescription("Song about Drake calling himself a legend ");

        // Add the movie to an empty list
        Set<Song> songs = new HashSet<Song>();
        songs.add(song);

        song = new Song ();
        song.setTitle("6God");
        song.setYear(2011);
        song.setDescription ("Song by Drake saying he is a 6God");
        songs.add(song);

        // Add the list of movies to the directors' movie list
        album.setSongs(songs);

        // Save the director to the database
        albumRepository.save(album);

        // Grad all the directors from the database and send them to
        // the template
        model.addAttribute ("albums", albumRepository.findAll());
        return "index";
    }
}
