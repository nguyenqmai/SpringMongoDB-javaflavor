package com.yapr.dataservice.javaflavor.persistence;

import com.yapr.dataservice.javaflavor.model.MediaItem;

import java.util.Collection;

public interface MediaRepositoryExtra {

    Collection<String> getDataCollectionNames();
    MediaItem findByOwnerId(Long ownerId);
}
