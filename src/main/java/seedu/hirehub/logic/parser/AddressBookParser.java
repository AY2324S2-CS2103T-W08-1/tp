package seedu.hirehub.logic.parser;

import static seedu.hirehub.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.hirehub.logic.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.hirehub.commons.core.LogsCenter;
import seedu.hirehub.logic.commands.AddApplicationCommand;
import seedu.hirehub.logic.commands.AddCommand;
import seedu.hirehub.logic.commands.AddJobCommand;
import seedu.hirehub.logic.commands.ClearCommand;
import seedu.hirehub.logic.commands.Command;
import seedu.hirehub.logic.commands.CommentCommand;
import seedu.hirehub.logic.commands.DeleteApplicationCommand;
import seedu.hirehub.logic.commands.DeleteJobCommand;
import seedu.hirehub.logic.commands.DeletePersonCommand;
import seedu.hirehub.logic.commands.DeleteTagCommand;
import seedu.hirehub.logic.commands.EditCommand;
import seedu.hirehub.logic.commands.EditJobCommand;
import seedu.hirehub.logic.commands.ExitCommand;
import seedu.hirehub.logic.commands.GetCommand;
import seedu.hirehub.logic.commands.HelpCommand;
import seedu.hirehub.logic.commands.ListApplicationCommand;
import seedu.hirehub.logic.commands.ListCommand;
import seedu.hirehub.logic.commands.ListJobCommand;
import seedu.hirehub.logic.commands.SearchApplicationCommand;
import seedu.hirehub.logic.commands.SearchCommand;
import seedu.hirehub.logic.commands.SearchJobCommand;
import seedu.hirehub.logic.commands.SlotsLeftCommand;
import seedu.hirehub.logic.commands.StatusCommand;
import seedu.hirehub.logic.commands.TagCommand;
import seedu.hirehub.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class AddressBookParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");
    private static final Logger logger = LogsCenter.getLogger(AddressBookParser.class);

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");

        // Note to developers: Change the log level in config.json to enable lower level (i.e., FINE, FINER and lower)
        // log messages such as the one below.
        // Lower level log messages are used sparingly to minimize noise in the code.
        logger.fine("Command word: " + commandWord + "; Arguments: " + arguments);

        switch (commandWord) {

        case AddCommand.COMMAND_WORD:
            return new AddCommandParser().parse(arguments);

        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parse(arguments);

        case DeletePersonCommand.COMMAND_WORD:
            return new DeletePersonCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case SearchCommand.COMMAND_WORD:
            return new SearchCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case GetCommand.COMMAND_WORD:
            return new GetCommandParser().parse(arguments);

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case StatusCommand.COMMAND_WORD:
            return new StatusCommandParser().parse(arguments);

        case CommentCommand.COMMAND_WORD:
            return new CommentCommandParser().parse(arguments);

        case TagCommand.COMMAND_WORD:
            return new TagCommandParser().parse(arguments);

        case DeleteTagCommand.COMMAND_WORD:
            return new DeleteTagCommandParser().parse(arguments);

        case AddJobCommand.COMMAND_WORD:
            return new AddJobCommandParser().parse(arguments);

        case EditJobCommand.COMMAND_WORD:
            return new EditJobCommandParser().parse(arguments);

        case DeleteJobCommand.COMMAND_WORD:
            return new DeleteJobCommandParser().parse(arguments);

        case SearchJobCommand.COMMAND_WORD:
            return new SearchJobCommandParser().parse(arguments);

        case AddApplicationCommand.COMMAND_WORD:
            return new AddApplicationCommandParser().parse(arguments);

        case DeleteApplicationCommand.COMMAND_WORD:
            return new DeleteApplicationCommandParser().parse(arguments);

        case SearchApplicationCommand.COMMAND_WORD:
            return new SearchApplicationCommandParser().parse(arguments);

        case ListApplicationCommand.COMMAND_WORD:
            return new ListApplicationCommand();

        case SlotsLeftCommand.COMMAND_WORD:
            return new SlotsLeftCommandParser().parse(arguments);

        case ListJobCommand.COMMAND_WORD:
            return new ListJobCommand();

        default:
            logger.finer("This user input caused a ParseException: " + userInput);
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }
}
