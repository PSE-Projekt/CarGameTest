package de.cargame.controller.input;


import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

@Getter
@Setter
public class UserInputBundle {

    private final List<UserInput> userInputs = new CopyOnWriteArrayList<>();
    private final UserInput USER_INPUT_NONE = new UserInput(UserInputType.NONE);
    private boolean fastForward;


    public UserInputBundle() {
        reset();
    }

    public void reset() {
        this.userInputs.removeAll(this.userInputs);
        this.fastForward = false;
    }

    /**
     * Adds a user input to the input bundle. If the input type is {@code UserInputType.FAST_FORWARD},
     * the fast-forward mode is enabled. If the input type is not currently in the list of user inputs,
     * it is added. The placeholder input type {@code USER_INPUT_NONE} is removed before processing.
     *
     * @param userInputType the type of user input to be added
     */
    public void addUserInput(UserInputType userInputType) {
        UserInput userInput = new UserInput(userInputType);
        userInputs.remove(USER_INPUT_NONE);

        if (userInput.getUserInputType().equals(UserInputType.FAST_FORWARD)) {
            fastForward = true;
        } else {
            if (!userInputs.contains(userInput)) {
                this.userInputs.add(userInput);
            }
        }
    }


    /**
     * Removes a user input from the input bundle. If the provided user input type is
     * {@code UserInputType.FAST_FORWARD}, it disables the fast-forward mode. For other input types,
     * it searches the list of user inputs and removes the first occurrence of the specified type.
     *
     * @param userInputType the type of user input to be removed
     */
    public void removeUserInput(UserInputType userInputType) {

        if (userInputType.equals(UserInputType.FAST_FORWARD)) {
            fastForward = false;
        } else {
            userInputs.stream()
                    .filter(input -> input.getUserInputType().equals(userInputType))
                    .findAny()
                    .ifPresent(this.userInputs::remove);
        }
    }


    public boolean isEmpty() {
        return userInputs.isEmpty();
    }


    /**
     * Retrieves the most recent user input from the list of user inputs, excluding any inputs of type
     * {@code UserInputType.FAST_FORWARD}. The method determines the latest input based on the
     * time associated with each {@code UserInput}, returning the one with the most recent timestamp.
     *
     * @return an {@code Optional} containing the latest {@code UserInput} if available, or an empty
     * {@code Optional} if no applicable user inputs are present.
     */
    public Optional<UserInput> getLatestInput() {
        return userInputs.stream()
                .filter(input -> input.getUserInputType() != UserInputType.FAST_FORWARD)
                .min((o1, o2) -> Long.compare(o2.getTime(), o1.getTime()));
    }

    public int size() {
        return userInputs.size();
    }
}
