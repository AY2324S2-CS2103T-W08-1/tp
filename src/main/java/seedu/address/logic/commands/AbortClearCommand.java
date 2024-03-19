package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.CommandBoxState;
import seedu.address.model.Model;

/**
 * Aborts the clearing of the database.
 */
public class AbortClearCommand extends Command {
    public static final String MESSAGE_SUCCESS = "Clearing aborted";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        return new CommandResult(MESSAGE_SUCCESS, CommandBoxState.NORMAL);
    }
}
