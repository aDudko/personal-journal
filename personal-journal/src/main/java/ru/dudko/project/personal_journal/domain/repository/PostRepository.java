package ru.dudko.project.personal_journal.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.dudko.project.personal_journal.domain.model.Post;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @Query(value = "SELECT * FROM post WHERE post.status != 'DELETED'", nativeQuery = true)
    List<Post> findAllActive();

}
