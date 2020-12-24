package com.issoft.builder.figure.service;

import com.issoft.builder.figure.model.Group;
import com.issoft.builder.figure.model.Picture;
import com.issoft.builder.figure.repository.PictureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PictureService {
    private final GroupService groupService;

    private final PictureRepository pictureRepository;

    @Autowired
    public PictureService(GroupService groupService, PictureRepository pictureRepository) {
        this.groupService = groupService;
        this.pictureRepository = pictureRepository;
    }

    public List<Picture> getPictures() {
        return pictureRepository.findAll(Sort.by(Sort.Direction.DESC, "modificationDate"));
    }

    public List<String> getPictureNames() {
        return pictureRepository.getPictureNamesOrderedByModificationDate();
    }

    public Picture getPicture(long id) {
        if (id < 1) {
            throw new IllegalArgumentException("Picture has to have correct id > 0.");
        }
        return pictureRepository.getOne(id);
    }

    public Picture updatePicture(Picture picture) {
        if (picture.getId() < 1) {
            throw new IllegalArgumentException("Picture has to have id.");
        }

        return pictureRepository.save(picture);
    }

    public Picture savePicture(Picture picture) {
        Group rootGroup = picture.getGroup();
        groupService.saveGroup(rootGroup);
        return pictureRepository.save(picture);
    }

    public void deletePicture(Long id) {
        pictureRepository.deleteById(id);
    }
}
