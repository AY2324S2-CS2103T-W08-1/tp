package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;

/**
 * Clears the address book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Address book has been cleared!";
    public static final String MESSAGE_CONFIRM = "Clearing the database is irreversible. Proceed? (y/n)";
    public static final String MESSAGE_ADDRESS_BOOK_IS_EMPTY = "Nothing to clear.";


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (!model.getStatus()) {
            throw new CommandException(MESSAGE_REQUEST_YN);
        }
        if (model.getAddressBook().getPersonList().isEmpty()) {
            throw new CommandException(MESSAGE_ADDRESS_BOOK_IS_EMPTY);
        }
        model.setStatus(false);
        return new CommandResult(MESSAGE_CONFIRM);
    }
}
