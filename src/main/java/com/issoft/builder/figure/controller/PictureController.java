package com.issoft.builder.figure.controller;

import com.issoft.builder.figure.model.Picture;
import com.issoft.builder.figure.service.PictureService;
import io.swagger.annotations.ApiOperation;
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

    @ApiOperation("Get all pictures ordered by last modification date.")
    @GetMapping
    public List<Picture> getPictures() {
        return pictureService.getPictures();
    }

    @ApiOperation("Get picture by id.")
    @GetMapping("/picture")
    public Picture getPicture(long id) {
        return pictureService.getPicture(id);
    }

    @ApiOperation("Get all picture names ordered by last modification date.")
    @GetMapping("/names")
    public List<String> getPictureNames() {
        return pictureService.getPictureNames();
    }

    @ApiOperation("Update picture properties. Modification children properties is not allowed here.")
    @PostMapping
    public Picture updatePicture(@RequestBody Picture picture) {
        return pictureService.updatePicture(picture);
    }

    @ApiOperation("Save picture and its child elements.")
    @PutMapping
    public Picture savePicture(@RequestBody Picture picture) {
        return pictureService.savePicture(picture);
    }

    @ApiOperation("Delete picture and its child elements.")
    @DeleteMapping
    public void deletePicture(Long id) {
        pictureService.deletePicture(id);
    }
}
