package com.softserveinc.ita.home.blog;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.softserveinc.ita.home.blog.api.TagsApi;
import com.softserveinc.ita.home.blog.model.Tag;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
public class HomeProjectBlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(HomeProjectBlogApplication.class, args);
    }

    @RequestMapping("api/0")
    public static class BaseController {

    }

    @Controller
    public static class TagsController extends BaseController implements TagsApi {
        @Override
        public ResponseEntity<List<Tag>> getAllTags(@Valid BigDecimal id,
                                                    @Valid String name,
                                                    @Valid String sort,
                                                    @Valid Integer pageNum,
                                                    @Valid Integer pageSize) {
            return ResponseEntity.of(
                    Optional.of(
                            Arrays.asList(
                                    new Tag()
                                            .eid(BigDecimal.ONE)
                                            .name("Java8"),
                                    new Tag()
                                            .eid(BigDecimal.valueOf(2l))
                                            .name("SpringBoot2.5.0")
                            )
                    )
            );
        }
    }
}
