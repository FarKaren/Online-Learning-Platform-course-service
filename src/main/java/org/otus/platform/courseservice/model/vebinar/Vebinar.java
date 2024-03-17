package org.otus.platform.courseservice.model.vebinar;

import jakarta.persistence.*;
import lombok.*;
import org.otus.platform.courseservice.model.course.Course;
import org.otus.platform.courseservice.model.user.User;

import java.time.ZonedDateTime;
import java.util.UUID;
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "vebinar")
public class Vebinar {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @OneToOne
    @JoinColumn(name = "course_id",  nullable = false)
    private Course course;

    @ManyToOne
    @JoinColumn(name = "teacher_id", nullable = false)
    private User teacher;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "lesson_date", nullable = false)
    private ZonedDateTime lessonDate;

    @Column(name = "summary", nullable = false)
    private String summary;


    @Column(name = "task")
    private String  task;

    @Column(name = "deleted_at")
    private ZonedDateTime deletedAt;

    @Column(name = "deleted_hash")
    private UUID deletedHash;
}
