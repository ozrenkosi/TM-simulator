//
// Created by ozrenkosi on 02.05.2017..
//

import java.util.*;

public class SimTS {

    private static List<String> turingMachineTape = new ArrayList<>();
    private static List<String> acceptedStates = new ArrayList<>();
    private static String initialState;
    private static int turingMachineTapeInitialPosition;
    private static Map<List<String>, List<String>> transitionFunction = new HashMap<>();
    private static String currentState;
    private static int currentHeadPosition;

    public static void main(String[] args) {

        dataInput();

        runTuringMachineSimulation();

        printOutput();

    }

    private static void dataInput() {
        String[] temporaryInput;

        Scanner reader = new Scanner(System.in);

        reader.nextLine();
        reader.nextLine();
        reader.nextLine();
        reader.nextLine();

        temporaryInput = reader.nextLine().split("");
        turingMachineTape.addAll(Arrays.asList(temporaryInput));

        temporaryInput = reader.nextLine().split(",");
        acceptedStates.addAll(Arrays.asList(temporaryInput));

        initialState = reader.nextLine();

        turingMachineTapeInitialPosition = Integer.parseInt(reader.nextLine());

        while (reader.hasNextLine()) {
            temporaryInput = reader.nextLine().split("->");

            if (temporaryInput[0].equals("")) {
                break;
            }

            List<String> key = new ArrayList<>();
            List<String> value = new ArrayList<>();

            key.addAll(Arrays.asList(temporaryInput[0].split(",")));
            value.addAll(Arrays.asList(temporaryInput[1].split(",")));

            transitionFunction.put(key, value);
        }

        reader.close();
    }

    private static void runTuringMachineSimulation() {
        List<String> key = new ArrayList<>();

        currentState = initialState;
        currentHeadPosition = turingMachineTapeInitialPosition;

        while (checkForTransition(currentState, currentHeadPosition)) {
            key.addAll(Arrays.asList(currentState, turingMachineTape.get(currentHeadPosition)));

            currentState = transitionFunction.get(key).get(0);
            turingMachineTape.set(currentHeadPosition, transitionFunction.get(key).get(1));
            changeHeadPosition(key);

            key.clear();
        }
    }

    private static boolean checkForTransition(String currentState, int currentHeadPosition) {
        List<String> key = new ArrayList<>();

        key.addAll(Arrays.asList(currentState, turingMachineTape.get(currentHeadPosition)));

        return transitionFunction.containsKey(key) && !(transitionFunction.get(key).get(2).equals("L") && currentHeadPosition == 0) && !(transitionFunction.get(key).get(2).equals("R") && currentHeadPosition == 69);
    }

    private static void changeHeadPosition(List<String> key) {
        if (transitionFunction.get(key).get(2).equals("R")) {
            currentHeadPosition = currentHeadPosition + 1;
        }
        else {
            currentHeadPosition = currentHeadPosition - 1;
        }
    }

    private static void printOutput() {
        String tapeFormatted = turingMachineTape.toString()
                .replace(" ", "")
                .replace("[", "")
                .replace("]", "")
                .replace(",", "")
                .trim();

        System.out.println(currentState + "|" + currentHeadPosition + "|" + tapeFormatted + "|" + checkDefinition());
    }

    private static int checkDefinition() {
        if (acceptedStates.contains(currentState)) {
            return 1;
        }
        else {
            return 0;
        }
    }

}