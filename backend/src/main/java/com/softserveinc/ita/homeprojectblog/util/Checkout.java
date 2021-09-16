package com.softserveinc.ita.homeprojectblog.util;

import com.softserveinc.ita.homeprojectblog.entity.TagEntity;
import com.softserveinc.ita.homeprojectblog.repository.TagRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Checkout {

    String sort;
    Integer pageNum;
    Integer pageSize;

    public Checkout checkoutAndSetDefaults(String sort, Integer pageNum, Integer pageSize) {

        String sortNotNull = Optional.ofNullable(sort).orElse("-id");
        Integer pageNumNotNull = Optional.ofNullable(pageNum).orElse(1) - 1;
        Integer pageSizeNotNull = Optional.ofNullable(pageSize).orElse(10);

        return new Checkout(sortNotNull, pageNumNotNull, pageSizeNotNull);
    }

    public void removeIdAndRepeatsInList(List<TagEntity> tags) {
        for (TagEntity tag : tags) {
            tag.setId(null);
        }

        Set<TagEntity> set = new HashSet<>(tags);
        tags.clear();
        tags.addAll(set);
    }

    public void setExistsTagsOrSetDateForNew(TagRepository tagRepository, List<TagEntity> tags) {
        for (var i = 0; i < tags.size(); i++) {
            Optional<TagEntity> tagOptional = tagRepository.findByName(tags.get(i).getName());
            if (tagOptional.isPresent()) {
                tags.set(i, tagOptional.get());
            } else {
                tags.get(i).setCreateOn(OffsetDateTime.now());
            }
        }
    }

}
