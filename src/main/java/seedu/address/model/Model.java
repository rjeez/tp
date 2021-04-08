package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.CssSettings;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.person.Person;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /** Returns the AddressBook */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    boolean hasPerson(Person person);

    /**
     * Deletes the given person.
     * The person must exist in the address book.
     */
    void deletePerson(Person target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the address book.
     */
    void addPerson(Person person);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    void setPerson(Person target, Person editedPerson);

    /**
     * Deletes all contacts within the specified index range (inclusive).
     */
    void massDelete(int startIndex, int endIndex);

    /**
     * Blacklists all unblacklisted contacts within the specified index range (inclusive).
     * If the contact is already blacklisted, then no change will occur.
     */
    void massBlacklist(int startIndex, int endIndex);

    /**
     * Unblacklists all blacklisted contacts within the specified index range (inclusive).
     * If the contact is already unblacklisted, then no change will occur.
     */
    void massUnblacklist(int startIndex, int endIndex);

    /**
     * Sorts the contact in the address book by name in alphabetical order.
     *
     * @param isAscending The list will be sorted by ascending order if true and descending
     * otherwise.
     */
    void sortByName(boolean isAscending);

    /**
     * Toggles the blacklist status of the given person {@code target}.
     * {@code target} must exist in the address book.
     */
    void toggleBlacklist(Person target);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);
  
    void unblacklistPerson(Person target);

    /**
     * Sets the user prefs' CSS settings.
     */
    CssSettings getCssSettings();

    /**
     * Sets the user prefs' CSS settings.
     */
    void setCssSettings(CssSettings cssSettings);    

}
