package org.otus.platform.courseservice.model.schedule;

import jakarta.persistence.*;
import lombok.*;
import org.otus.platform.courseservice.model.course.Course;

import java.time.ZonedDateTime;
import java.util.UUID;
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "courses_schedule")
public class CourseSchedule {

    @Id
    @Column(name = "course_id", nullable = false)
    private UUID id;

    @OneToOne
    @JoinColumn(name = "course_id",  nullable = false)
    @MapsId
    private Course course;

    @Column(name = "course_title", nullable = false)
    private String courseTitle;

    @Column(name = "lesson_date", nullable = false)
    private ZonedDateTime lessonDate;

    @Column(name = "status", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private LessonStatus lessonStatus;

    @Column(name = "reschedule_to_date")
    private ZonedDateTime rescheduleToDate;

    @Column(name = "deleted_at")
    private ZonedDateTime deletedAt;

    @Column(name = "deleted_hash")
    private UUID deletedHash;
}
