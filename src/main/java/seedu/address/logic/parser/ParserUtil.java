package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javafx.util.Pair;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.ModeOfContact;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Remark;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";
    public static final String MESSAGE_INVALID_RANGE = "Invalid range format.";
    public static final String MESSAGE_INVALID_START = "Start index must be positive and start index < end index";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static Phone parsePhone(String phone) throws ParseException {
        requireNonNull(phone);
        String trimmedPhone = phone.trim();
        if (!Phone.isValidPhone(trimmedPhone)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(trimmedPhone);
    }

    /**
     * Parses a {@code String address} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static Address parseAddress(String address) throws ParseException {
        requireNonNull(address);
        String trimmedAddress = address.trim();
        if (!Address.isValidAddress(trimmedAddress)) {
            throw new ParseException(Address.MESSAGE_CONSTRAINTS);
        }
        return new Address(trimmedAddress);
    }

    /**
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Email parseEmail(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (!Email.isValidEmail(trimmedEmail)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(trimmedEmail);
    }

    /**
     * Parses a {@code String remark} into a {@code Remark}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code remark} is invalid.
     */
    public static Remark parseRemark(String remark) throws ParseException {
        requireNonNull(remark);
        String trimmedRemark = remark.trim();
        if (!Remark.isValidRemark(trimmedRemark)) {
            throw new ParseException(Remark.MESSAGE_CONSTRAINTS);
        }
        return new Remark(trimmedRemark);
    }

    /**
     * Parses  a {@code String isAscending} into a {@code boolean}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code isAscending} is invalid.
     */
    public static boolean parseIsAscending(String isAscending) throws ParseException {
        requireNonNull(isAscending);
        String trimmedIsAscending = isAscending.trim();
        if (!trimmedIsAscending.equals("ascending") && !trimmedIsAscending.equals("descending")) {
            throw new ParseException("Input should either be ascending or descending");
        }
        return trimmedIsAscending.equals("ascending");
    }

    /**
     * Parses  a {@code String isBlacklist} into a {@code boolean}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code isBlacklist} is invalid.
     */
    public static boolean parseIsBlacklist(String isBlacklist) throws ParseException {
        requireNonNull(isBlacklist);
        String trimmedIsAscending = isBlacklist.trim();
        if (!trimmedIsAscending.equals("blacklist") && !trimmedIsAscending.equals("unblacklist")) {
            throw new ParseException("Input should either be blacklist or unblacklist");
        }
        return trimmedIsAscending.equals("blacklist");
    }

    /**
     * Parses a {@code String modeOfContact} into a {@code ModeOfContact}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code modeOfContact} is invalid.
     */
    public static ModeOfContact parseModeOfContact(String modeOfContact) throws ParseException {
        requireNonNull(modeOfContact);
        String trimmedModeOfContact = modeOfContact.trim();
        if (!ModeOfContact.isValidModeOfContact(trimmedModeOfContact)) {
            throw new ParseException(ModeOfContact.MESSAGE_CONSTRAINTS);
        }
        return new ModeOfContact(trimmedModeOfContact);
    }

    /**
     * Parses  a {@code String range} into a {@code Pair}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code isAscending} is invalid.
     */
    public static Pair<Index, Index> parseRange(String range) throws ParseException {
        requireNonNull(range);
        String trimmedRange = range.trim();
        try {
            String[] splitRange = trimmedRange.split("-");
            if (splitRange.length != 2) {
                throw new ParseException(MESSAGE_INVALID_RANGE);
            }
            Index startIndex = parseIndex(splitRange[0]);
            Index endIndex = parseIndex(splitRange[1]);
            if (startIndex.getZeroBased() > endIndex.getZeroBased()) {
                throw new ParseException(MESSAGE_INVALID_START);
            }
            return new Pair<>(startIndex, endIndex);
        } catch (NumberFormatException exception) {
            throw new ParseException(MESSAGE_INVALID_RANGE);
        }
    }

    /**
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static Tag parseTag(String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();
        if (!Tag.isValidTagName(trimmedTag)) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new Tag(trimmedTag);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     */
    public static Set<Tag> parseTags(Collection<String> tags) throws ParseException {
        requireNonNull(tags);
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(parseTag(tagName));
        }
        return tagSet;
    }
}
