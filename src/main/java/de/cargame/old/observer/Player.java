package de.cargame.old.observer;

public class Player implements UserInputObserver {
private UserInput userInput;

    @Override
    public void update(UserInput userInput) {
        setUserInput(userInput);
        System.out.println("Userinput: "+userInput.name());
    }


    public void setUserInput(UserInput userInput) {
        this.userInput = userInput;
    }

    public UserInput getUserInput() {
        return userInput;
    }
}