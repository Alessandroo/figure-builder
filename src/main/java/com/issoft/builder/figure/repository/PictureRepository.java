package com.issoft.builder.figure.repository;

import com.issoft.builder.figure.model.Picture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PictureRepository extends JpaRepository<Picture, Long> {
    @Query("select name from Picture order by modificationDate desc")
    List<String> getPictureNamesOrderedByModificationDate();
}
