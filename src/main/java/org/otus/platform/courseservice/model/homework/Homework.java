package org.otus.platform.courseservice.model.homework;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.otus.platform.courseservice.model.course.Course;
import org.otus.platform.courseservice.model.user.User;
import org.otus.platform.courseservice.model.vebinar.Vebinar;

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
    @JoinColumn(name = "vebinar_id", nullable = false)
    private Vebinar vebinar;

    @ManyToOne
    @JoinColumn(name = "teacher_id", nullable = false)
    private User teacher;

    @Column(name = "complete_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private CompleteStatus completeStatus;

    @CreationTimestamp
    @Column(name = "added", nullable = false)
    private ZonedDateTime added;

    @Column(name = "deleted_at")
    private ZonedDateTime deletedAt;

    @Column(name = "deleted_hash")
    private UUID deletedHash;

    @Column(name = "content", nullable = false)
    private String content;
}
