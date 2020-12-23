package com.issoft.builder.figure.controller;

import com.issoft.builder.figure.model.Picture;
import com.issoft.builder.figure.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pictures")
public class PictureController {
    private final PictureService pictureService;

    @Autowired
    public PictureController(PictureService pictureService) {
        this.pictureService = pictureService;
    }

    @GetMapping
    public List<Picture> getPictures() {
        return pictureService.getPictures();
    }

    @GetMapping("/names")
    public List<String> getPictureNames() {
        return pictureService.getPictureNames();
    }

    @PostMapping
    public Picture updatePicture(@RequestBody Picture picture) {
        return pictureService.updatePicture(picture);
    }

    @PutMapping
    public Picture savePicture(@RequestBody Picture picture) {
        return pictureService.savePicture(picture);
    }

    @DeleteMapping
    public void deletePicture(Long id) {
        pictureService.deletePicture(id);
    }
}
