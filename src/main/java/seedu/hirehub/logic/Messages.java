package seedu.hirehub.logic;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.hirehub.logic.parser.Prefix;
import seedu.hirehub.model.application.Application;
import seedu.hirehub.model.job.Job;
import seedu.hirehub.model.person.Person;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command.";
    public static final String MESSAGE_UNKNOWN_COMMAND_CLEAR_CONFIRMATION =
            "Unknown command, enter Y to clear or N to abort.";
    public static final String MESSAGE_UNKNOWN_COMMAND_DELETE_CONFIRMATION =
            "Unknown command, enter Y to delete or N to abort.";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format!\n%1$s";
    public static final String MESSAGE_INVALID_PERSON_DISPLAYED_INDEX = "The person index provided is invalid.";
    public static final String MESSAGE_INVALID_JOB_DISPLAYED_INDEX = "The job index provided is invalid.";
    public static final String MESSAGE_INVALID_APPLICATION_DISPLAYED_INDEX = "The application index provided is "
            + "invalid.";
    public static final String MESSAGE_PERSONS_LISTED_OVERVIEW = "%1$d persons listed!";
    public static final String MESSAGE_JOBS_LISTED_OVERVIEW = "%1$d jobs listed!";
    public static final String MESSAGE_APPLICATIONS_LISTED_OVERVIEW = "%1$d applications listed!";
    public static final String MESSAGE_DUPLICATE_FIELDS =
                "Multiple values specified for the following single-valued field(s): ";

    /**
     * Returns an error message indicating the duplicate prefixes.
     */
    public static String getErrorMessageForDuplicatePrefixes(Prefix... duplicatePrefixes) {
        assert duplicatePrefixes.length > 0;

        Set<String> duplicateFields =
                Stream.of(duplicatePrefixes).map(Prefix::toString).collect(Collectors.toSet());

        return MESSAGE_DUPLICATE_FIELDS + String.join(" ", duplicateFields);
    }

    /**
     * Formats the {@code person} for display to the user.
     */
    public static String format(Person person) {
        final StringBuilder builder = new StringBuilder();
        builder.append("Person(Name: ")
                .append(person.getName())
                .append("; Phone: ")
                .append(person.getPhone())
                .append("; Email: ")
                .append(person.getEmail())
                .append("; Country: ")
                .append(person.getCountry())
                .append("; Comment: ")
                .append(person.getComment())
                .append("; Tags: ");
        person.getTags().forEach(builder::append);
        return builder.append(")").toString();
    }

    /**
     * Formats the {@code job} for display to the user.
     */
    public static String format(Job job) {
        return "Job(Title: "
                + job.getTitle()
                + "; Description: "
                + job.getDescription()
                + "; Vacancy: "
                + job.getVacancy()
                + ")";
    }

    /**
     * Formats the {@code application} for display to the user.
     */
    public static String format(Application application) {
        return "Application(Person: "
                + application.getPerson().getName()
                + "; Job: "
                + application.getJob().getTitle()
                + "; Status: "
                + application.getStatus()
                + ")";
    }
}
