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

    public void addUserInput(UserInputType userInputType) {
        UserInput userInput = new UserInput(userInputType);
        userInputs.remove(USER_INPUT_NONE);

        if (userInput.getUserInputType().equals(UserInputType.FAST_FORWARD)) {
            fastForward = true;
        } else {
            if (!userInputs.contains(userInput)) {
                System.out.println("ADD USER INPUT: " + userInputType);
                System.out.println(userInputs.size());
                this.userInputs.add(userInput);
            }
        }
    }

    public void removeUserInput(UserInputType userInputType) {

        if (userInputType.equals(UserInputType.FAST_FORWARD)) {
            fastForward = false;
        } else {
            userInputs.stream()
                    .filter(input -> input.getUserInputType().equals(userInputType))
                    .findFirst()
                    .ifPresent(this.userInputs::remove);
        }
        System.out.println("Fast Forward: " + fastForward);

    }

    public boolean contains(UserInputType userInputType) {
        return userInputs.stream()
                .anyMatch(input -> input.getUserInputType().equals(userInputType));
    }

    public boolean isEmpty() {
        return userInputs.isEmpty();
    }


    public UserInputType getLatestInput() {
        Optional<UserInput> userInput = userInputs.stream()
                .filter(input -> input.getUserInputType() != UserInputType.FAST_FORWARD)
                .min((o1, o2) -> Long.compare(o2.getTime(), o1.getTime()));
        if (userInput.isPresent()) {
            return userInput.get().getUserInputType();
        }
        return UserInputType.NONE;
    }

    public int size() {
        return userInputs.size();
    }
}
