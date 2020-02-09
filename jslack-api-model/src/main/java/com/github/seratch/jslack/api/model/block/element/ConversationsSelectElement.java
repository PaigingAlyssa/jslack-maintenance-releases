package com.github.seratch.jslack.api.model.block.element;

import com.github.seratch.jslack.api.model.block.composition.ConfirmationDialogObject;
import com.github.seratch.jslack.api.model.block.composition.PlainTextObject;
import lombok.*;


/**
 * https://api.slack.com/reference/block-kit/block-elements#conversation_select
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConversationsSelectElement extends BlockElement {
    public static final String TYPE = "conversations_select";
    private final String type = TYPE;

    /**
     * A plain_text only text object that defines the placeholder text shown on the menu.
     * Maximum length for the text in this field is 150 characters.
     */
    private PlainTextObject placeholder;

    /**
     * An identifier for the action triggered when a menu option is selected.
     * You can use this when you receive an interaction payload to identify the source of the action.
     * Should be unique among all other action_ids used elsewhere by your app.
     * Maximum length for this field is 255 characters.
     */
    private String actionId;

    /**
     * The ID of any valid conversation to be pre-selected when the menu loads.
     */
    private String initialConversation;

    /**
     * A confirm object that defines an optional confirmation dialog that appears after a menu item is selected.
     */
    private ConfirmationDialogObject confirm;

}