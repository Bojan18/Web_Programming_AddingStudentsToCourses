package mk.ukim.finki.wp.lab.model.exceptions;

public class CourseNameExistsException extends RuntimeException{
    public CourseNameExistsException(String course) {
        super(String.format("Course with the name %s already exists", course));
    }
}
