package com.codegym.controller;

import com.codegym.model.Song;
import com.codegym.model.SongForm;
import com.codegym.service.song.ISongService;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller

public class SongController {
    @Value("${file-upload}")
    private String fileUpload;

    @Autowired
    private ISongService songService;
    @GetMapping("/home")
    public ModelAndView home() {
        List<Song> songList = songService.findAll();
        ModelAndView modelAndView = new ModelAndView("/song/list");
        modelAndView.addObject("songlist", songList);
        return modelAndView;
    }
//    @RequestParam(name = "search", required = false) String search
//    @GetMapping("/home")
//    public ModelAndView home() {
//        ModelAndView modelAndView = new ModelAndView("/song/list");
//        List<Song> songList = songService.findAll();
////        if (search == null || search.equals("")) {
////            songList =
////        } else {
////            songList = songService.findByName(search);
////        }
//        modelAndView.addObject("songList", songList);
//        return modelAndView;
//    }

    @GetMapping("/create")
    public ModelAndView createForm() {
        return new ModelAndView("/song/create", "songForm", new SongForm());
    }

    @PostMapping("/save")
    public String createSong(@ModelAttribute SongForm songForm, Model model) throws IOException {
        String fileName = convertMultipartFileToString(songForm);
        Song song = new Song(songForm.getId(), songForm.getSongName(), songForm.getSinger(), songForm.getCategory(), fileName);
        songService.save(song);
        if (songForm.getId() == null) {
            model.addAttribute("message", "Add new song successfully!!!");
        } else {
            model.addAttribute("message", "Update info song successfully!!!");
        }
        return "/song/create";
    }

    private String convertMultipartFileToString(SongForm songForm) throws IOException {
        MultipartFile multipartFile = songForm.getLink();
        String fileName = multipartFile.getOriginalFilename();
        FileCopyUtils.copy(songForm.getLink().getBytes(), new File(fileUpload + fileName));
        return fileName;
    }

    @GetMapping("/{id}/edit")
    public ModelAndView formUpdate(@PathVariable("id") Long id) throws IOException {
        Song song = songService.findById(id);
        File file = new File(song.getLink());
        DiskFileItem fileItem = new DiskFileItem("file", "text/plain", false, file.getName(), (int) file.length() , file.getParentFile());
        fileItem.getOutputStream();
        MultipartFile multipartFile = new CommonsMultipartFile(fileItem);
        SongForm songForm = new SongForm(
        );
        ModelAndView modelAndView = new ModelAndView("/song/edit");
        modelAndView.addObject("songForm", songForm);
        return modelAndView;
    }

    @GetMapping("/{id}/delete")
    public ModelAndView formDelete(@PathVariable("id") Long id) throws IOException {
        Song song = songService.findById(id);
        ModelAndView modelAndView = new ModelAndView("/song/delete");
        modelAndView.addObject("song", song);
        return modelAndView;
    }

    @PostMapping("/delete")
    public String delete(@ModelAttribute Song song) {
        songService.remove(song.getId());
        return "redirect:/home";
    }
}
