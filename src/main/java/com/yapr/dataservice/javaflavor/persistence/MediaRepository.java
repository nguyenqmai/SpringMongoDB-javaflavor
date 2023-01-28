package com.yapr.dataservice.javaflavor.persistence;

import com.yapr.dataservice.javaflavor.model.MediaItem;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MediaRepository extends MongoRepository<MediaItem, Long>, MediaRepositoryExtra {
}
