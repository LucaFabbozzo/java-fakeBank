package org.bank;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Bank {

    private final List<Account> accounts;

    public void addAccount(Account account) {
        accounts.add(account);
    }

    //todo: implement!
    public Account getAccount(String accountId){
        return null;
    }

    public Bank() {
        accounts = new ArrayList<>();
    }

    public static void main(String[] args) {
        var bank = new Bank();
        bank.run();
    }

    private void run() {
        int scelta;

        var sc = new Scanner(System.in);
        do {
            System.out.println("Benvenuti nella banca ABC");
            System.out.println();
            System.out.println("Scegliere l'opzione desiderata:");
            System.out.println("1 - Aggiunta conto corrente");
            System.out.println("2 - Stampa conti correnti");
            scelta = sc.nextInt();
            switch (scelta) {
                case 1 -> aggiungiConto();
                case 2 -> stampaContiCorrenti();
                case 0 -> System.out.println("Uscita in corso...");
                default -> System.out.println("Input non valido");
            }
        } while (scelta != 0);
    }

    private void stampaContiCorrenti() {
        System.out.println("Stampa conti correnti");
        for (var account: accounts){
            System.out.printf("%s: %f%n", account.getHolder(), account.getBalance());
        }
    }

    private void aggiungiConto() {
        System.out.println("Aggiunta conto corrente");
        var sc = new Scanner(System.in);
        System.out.println("Inserisci codice titolare> ");
        var holder = sc.nextLine();
        System.out.println("Inserisci bilancio iniziale> ");
        var balance = sc.nextInt();
        var account = new Account(holder);
        account.setBalance(balance);
        accounts.add(account);
    }
}