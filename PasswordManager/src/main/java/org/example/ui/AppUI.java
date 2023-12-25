package org.example.ui;

import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class AppUI {
    private enum Command {
        VEW_OPTIONS_COMMAND("options", 0, new CommandExecuter() {
            @Override
            public void excute(String[] attributes, UserEventsObserver eventsObserver) {
                System.out.println("option 1: new-password <account: String> <password: String> <website: String>");
                System.out.println("option 2: update-password <id: int> <account: String> <password: String> <website: String>");
                System.out.println("option 3: delete-password <id: int>");
                System.out.println("option 4: get-password <id: ing>");
                System.out.println("option 5: get-all-passwords");
                System.out.println("option 6: delete-all-passwords");
                System.out.println("option 7: exit");
            }
        }),
        ADD_PASSWORD("new-password", 3, new CommandExecuter() {
            @Override
            public void excute(String[] attributes, UserEventsObserver eventsObserver) {
                if (attributes.length == 3) {
                    System.out.println("adding new password ....");
                    eventsObserver.addNewPasswordEvent(attributes[0], attributes[1], attributes[2]);
                } else  {
                    System.out.println("command miss some arguments");
                }
            }
        }),
        UPDATE_PASSWORD("update-password", 4, new CommandExecuter() {
            @Override
            public void excute(String[] attributes, UserEventsObserver eventsObserver) {
                if (attributes.length == 4) {
                    System.out.println("updating password ....");
                    eventsObserver.updatePasswordEvent(Integer.parseInt(attributes[0]), attributes[1], attributes[2], attributes[3]);
                } else  {
                    System.out.println("command miss some arguments");
                }
            }
        }),
        DELETE_PASSWORD("delete-password", 1, new CommandExecuter() {
            @Override
            public void excute(String[] attributes, UserEventsObserver eventsObserver) {
                if (attributes.length == 1) {
                    System.out.println("deleting password ....");
                    eventsObserver.deletePasswordEvent(Integer.parseInt(attributes[0]));
                } else  {
                    System.out.println("command miss some arguments");
                }
            }
        }),
        GET_PASSWORD("get-password", 1, new CommandExecuter() {
            @Override
            public void excute(String[] attributes, UserEventsObserver eventsObserver) {
                if (attributes.length == 1) {
                    System.out.println("providing password ....");
                    eventsObserver.getPasswordEvent(Integer.parseInt(attributes[0]));
                } else  {
                    System.out.println("command miss some arguments");
                }
            }
        }),
        VIEW_ALL_PASSWORDS("get-all-passwords", 0, new CommandExecuter() {
            @Override
            public void excute(String[] attributes, UserEventsObserver eventsObserver) {
                System.out.println("providing all passwords ....");
                eventsObserver.getAllPasswordsEvent();
            }
        }),
        DELETE_ALL_PASSWORDS("delete-all-passwords", 0, new CommandExecuter() {
            @Override
            public void excute(String[] attributes, UserEventsObserver eventsObserver) {
                System.out.println("deleting all passwords ....");
                eventsObserver.deleteAllPasswords();
            }
        }),
        EXIT("exit", 0, new CommandExecuter() {
            @Override
            public void excute(String[] attributes, UserEventsObserver eventsObserver) {
                System.out.println("closing ....");
                System.exit(0);
            }
        });

        public final String command;
        public final int attributesCount;
        public CommandExecuter executer;

        Command(String str, int attributesCount, CommandExecuter executer) {
            command = str;
            this.attributesCount = attributesCount;
            this.executer = executer;
        }

    }

    interface CommandExecuter{
        void excute(String[] attributes, UserEventsObserver eventsObserver);
    }
    public AppUI(UserEventsObserver eventObserver) {
        this.eventsObserver = eventObserver;
    }

    UserEventsObserver eventsObserver;

    public void startApplication() {
        printIntro();
        excuteUserInput();

    }

    private void excuteUserInput() {
        boolean exit = false;
        Scanner scanner = new Scanner(System.in);
        while (!exit) {
            String input = scanner.nextLine();
            processInput(input);

        }
    }

    private void processInput(String input) {
        String[] commands = input.split(" ");
        for (Command c : Command.values()) {

            if (Objects.equals(c.command, commands[0])) {
                String [] attributes;
                /*if (commands.length != 1) {
                    attributes = Arrays.copyOfRange(commands, 1, commands.length - 1);
                }
                else {
                    attributes = commands;
                }*/
                if (commands.length ==0)
                    attributes = new String[0];
                else {
                    attributes = new String[commands.length - 1];

                    for (int i = 1; i < commands.length; i++) {
                        attributes[i - 1] = commands[i];
                    }
                }
                c.executer.excute(
                        attributes,
                        eventsObserver);
            }
        }
    }

    private void printIntro() {
        System.out.println("Welcome to password key store");
        System.out.println("please enter options to view all commands");
    }
}
