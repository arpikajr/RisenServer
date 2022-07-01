package com.risen.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.risen.dto.SubRisenDto;
import com.risen.model.Post;
import com.risen.model.SubRisen;

@Mapper(componentModel = "spring")
public interface SubRisenMapper {

    @Mapping(target = "numberOfPosts", expression = "java(mapPosts(subRisen.getPosts()))")
    SubRisenDto mapSubRisenToDto(SubRisen subRisen);

    default Integer mapPosts(List<Post> numberOfPosts) {
        return numberOfPosts.size();
    }

    @InheritInverseConfiguration
    @Mapping(target = "posts", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "user", ignore = true)
    SubRisen mapDtoToSubRisen(SubRisenDto subDto);
}
