package com.metuncc.PhotoHocam.repository;

import com.metuncc.PhotoHocam.domain.Image;
import com.metuncc.PhotoHocam.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image,Long> {

    @Query("select i from Image i where i.id=:id")
    Image getById(@Param("id") Long id);

    @Query("select i from Image i where i.receiver.id=:user")
    List<Image> getImages(@Param("user") Long user);


}
