package seedu.hirehub.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.hirehub.commons.core.LogsCenter;
import seedu.hirehub.commons.exceptions.DataLoadingException;
import seedu.hirehub.commons.exceptions.IllegalValueException;
import seedu.hirehub.commons.util.FileUtil;
import seedu.hirehub.commons.util.JsonUtil;
import seedu.hirehub.model.ReadOnlyAddressBook;
import seedu.hirehub.model.application.Application;
import seedu.hirehub.model.application.UniqueApplicationList;
import seedu.hirehub.model.job.Job;
import seedu.hirehub.model.job.UniqueJobList;
import seedu.hirehub.model.status.Status;


/**
 * A class to access application data stored as a json file on the hard disk.
 */
public class JsonApplicationStorage implements ApplicationStorage {
    private static final Logger logger = LogsCenter.getLogger(JsonApplicationStorage.class);
    public static final String EXCEEDS_VACANCY_MESSAGE = "Accepted candidates for %s exceeds stipulated vacancy!";

    private Path filePath;

    public JsonApplicationStorage(Path filePath) {
        this.filePath = filePath;
    }

    @Override
    public Path getApplicationFilePath() {
        return filePath;
    }

    @Override
    public Optional<UniqueApplicationList> readApplicationList(UniqueJobList jobs,
                                                               ReadOnlyAddressBook people) throws DataLoadingException {
        return readApplicationList(jobs, people, filePath);
    }

    /**
     * Similar to {@link #readApplicationList(UniqueJobList, ReadOnlyAddressBook)} ()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataLoadingException if loading the data from storage failed.
     */
    public Optional<UniqueApplicationList> readApplicationList(UniqueJobList jobs,
                                                               ReadOnlyAddressBook people,
                                                               Path filePath) throws DataLoadingException {
        requireNonNull(filePath);

        Optional<JsonSerializableApplicationList> jsonApplicationList = JsonUtil.readJsonFile(
                filePath, JsonSerializableApplicationList.class);
        if (!jsonApplicationList.isPresent()) {
            return Optional.empty();
        }

        try {
            UniqueApplicationList applicationList = jsonApplicationList.get().toModelType(jobs, people);
            HashMap<Job, Integer> offeredCount = new HashMap<>();
            for (Application a: applicationList) {
                if (a.getStatus().equals(new Status("OFFERED"))) {
                    Job offeredJob = a.getJob();
                    offeredCount.merge(offeredJob, 1, Integer::sum);
                    if (offeredCount.get(offeredJob) > offeredJob.getVacancy()) {
                        throw new IllegalValueException(String.format(EXCEEDS_VACANCY_MESSAGE, offeredJob));
                    }
                }
            }
            return Optional.of(jsonApplicationList.get().toModelType(jobs, people));
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataLoadingException(ive);
        }
    }

    @Override
    public void saveApplicationList(UniqueApplicationList applicationList) throws IOException {
        saveApplicationList(applicationList, filePath);
    }

    /**
     * Similar to {@link #saveApplicationList(UniqueApplicationList)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    @Override
    public void saveApplicationList(UniqueApplicationList applicationList, Path filePath) throws IOException {
        requireNonNull(applicationList);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableApplicationList(applicationList), filePath);
    }
}