package com.github.seratch.jslack.api.model.block.element;

import com.github.seratch.jslack.api.model.block.composition.ConfirmationDialogObject;
import com.github.seratch.jslack.api.model.block.composition.OptionObject;
import com.github.seratch.jslack.api.model.block.composition.PlainTextObject;
import lombok.*;

import java.util.List;

/**
 * https://api.slack.com/reference/block-kit/block-elements#radio
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RadioButtonsElement extends BlockElement {
    public static final String TYPE = "radio_buttons";
    private final String type = TYPE;

    /**
     * An identifier for the action triggered when the radio button group is changed.
     * You can use this when you receive an interaction payload to identify the source of the action.
     * Should be unique among all other action_ids used elsewhere by your app.
     * Maximum length for this field is 255 characters.
     */
    private String actionId;

    /**
     * An array of option objects.
     * <p>
     * NOTE: The reason I didn't initialize the List<> fields is because Slack (sometimes) gives errors
     * when it encounters an empty list in the generated JSON.
     * The proper solution if/when you don't want un-initialized fields is to have a Gson type adapter that skips empty lists
     *
     * @see "https://github.com/slackapi/java-slack-sdk/pull/103"
     * @see "https://stackoverflow.com/questions/11942118/how-do-you-get-gson-to-omit-null-or-empty-objects-and-empty-arrays-and-lists"
     */
    private List<OptionObject> options;

    /**
     * An option object that exactly matches one of the options within options.
     * This option will be selected when the radio button group initially loads.
     */
    private OptionObject initialOption;

    /**
     * A confirm object that defines an optional confirmation dialog
     * that appears after clicking one of the radio buttons in this element.
     */
    private ConfirmationDialogObject confirm;
}