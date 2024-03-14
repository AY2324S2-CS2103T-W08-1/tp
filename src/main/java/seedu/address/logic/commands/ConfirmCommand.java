package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_UNKNOWN_COMMAND;

public class ConfirmCommand extends Command{
    public static final String MESSAGE_SUCCESS = "Address book has been cleared!";
    public static final String MESSAGE_CLEAR_ABORT = "Clearing has been aborted";
    private final boolean isConfirmed;

    public ConfirmCommand(boolean isConfirmed) {
        this.isConfirmed = isConfirmed;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (model.getStatus()) {
            throw new CommandException(MESSAGE_UNKNOWN_COMMAND);
        }
        model.setStatus(true);
        if (!isConfirmed) {
            return new CommandResult(MESSAGE_CLEAR_ABORT);
        }
        model.setAddressBook(new AddressBook());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
