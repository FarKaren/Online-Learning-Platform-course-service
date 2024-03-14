package org.otus.platform.courseservice.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
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
@Table(name = "homeworks")
public class Homework {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "homework_id", nullable = false)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private User student;

    @ManyToOne
    @JoinColumn(name = "teacher_id", nullable = false)
    private User teacher;

    @Column(name = "on_review", nullable = false)
    private boolean onReview;

    @CreationTimestamp
    @Column(name = "added", nullable = false)
    private ZonedDateTime added;

    @Column(name = "deleted_at")
    private ZonedDateTime deletedAt;

    @Column(name = "deleted_hash")
    private UUID deletedHash;
}
