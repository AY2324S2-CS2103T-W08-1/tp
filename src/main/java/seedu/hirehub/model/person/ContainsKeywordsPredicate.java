package seedu.hirehub.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.hirehub.logic.parser.CliSyntax.PREFIX_COMMENT;
import static seedu.hirehub.logic.parser.CliSyntax.PREFIX_COUNTRY;
import static seedu.hirehub.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.hirehub.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.hirehub.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.hirehub.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

import javafx.application.Application;
import seedu.hirehub.commons.util.ToStringBuilder;
import seedu.hirehub.logic.parser.Prefix;
import seedu.hirehub.model.tag.Tag;

/**
 * Tests that a {@code Person}'s {@code Attribute} matches the corresponding keyword given.
 */
public class ContainsKeywordsPredicate<S, T> implements Predicate<S> {

    private final Prefix prefix;
    private final Optional<T> keywords;

    /**
     * @param prefix of the corresponding field.
     * @param keywords that the test is run against.
     */
    public ContainsKeywordsPredicate(Prefix prefix, Optional<T> keywords) {
        this.prefix = prefix;
        this.keywords = keywords;
    }

    @Override
    public boolean test(S item) {
        requireNonNull(item);
        if (item instanceof Person) {
            Person person = (Person) item;
            return personTest(person);
        }
        if (item instanceof Application) {
            Application application = (Application) item;
            return applicationTest(application);
        }
        throw new IllegalStateException("Unexpected type");
    }

    private boolean personTest(Person person) {
        if (prefix.equals(PREFIX_NAME)) {
            Optional<Name> name = (Optional<Name>) keywords;
            return person.getName().fullName.contains(name.orElse(person.getName()).fullName);
        } else if (prefix.equals(PREFIX_PHONE)) {
            Optional<Phone> phone = (Optional<Phone>) keywords;
            return person.getPhone().value.equals(phone.orElse(person.getPhone()).value);
        } else if (prefix.equals(PREFIX_EMAIL)) {
            Optional<Email> email = (Optional<Email>) keywords;
            return person.getEmail().value.equals(email.orElse(person.getEmail()).value);
        } else if (prefix.equals(PREFIX_COUNTRY)) {
            Optional<Country> country = (Optional<Country>) keywords;
            return person.getCountry().value.equals(country.orElse(person.getCountry()).value);
        } else if (prefix.equals(PREFIX_COMMENT)) {
            Optional<Comment> comment = (Optional<Comment>) keywords;
            return person.getComment().value.contains(comment.orElse(person.getComment()).value);
        } else if (prefix.equals(PREFIX_TAG)) {
            Optional<Set<Tag>> tags = (Optional<Set<Tag>>) keywords;
            if (tags.isEmpty()) {
                return true;
            }
            return isPersonTagsContainsTag(person, tags);
        }
        throw new IllegalStateException("Unexpected prefix: " + prefix);
    }

    private boolean applicationTest(Application application) {
        if (prefix.equals(PREFIX_EMAIL)) {
            Optional<Email> email = (Optional<Email>) keywords;
            return application.getEmail().value.equals(email.orElse(application.getEmail()).value);
        } else if (prefix.equals(PREFIX_TITLE)) {
            Optional<Title> title = (Optional<Title>) keywords;
            return application.getTitle().value.contains(title.orElse(application.getTitle()).value);
        } else if (prefix.equals(PREFIX_STATUS)) {
            Optional<Status> status = (Optional<Status>) keywords;
            return application.getStatus().value.equals(status.orElse(application.getStatus()).value);
        }
        throw new IllegalStateException("Unexpected prefix: " + prefix);
    }

    private boolean isPersonTagsContainsTag(Person person, Optional<Set<Tag>> tags) {
        assert tags.isPresent();
        boolean isContainTag = false;
        for (Tag tag : tags.get()) {
            for (Tag personTag : person.getTags()) {
                if (personTag.tagName.contains(tag.tagName)) {
                    isContainTag = true;
                    break;
                }
            }
        }
        return isContainTag;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ContainsKeywordsPredicate)) {
            return false;
        }

        ContainsKeywordsPredicate<S, T> otherContainsKeywordsPredicate = (ContainsKeywordsPredicate<S, T>) other;
        return keywords.equals(otherContainsKeywordsPredicate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }
}
