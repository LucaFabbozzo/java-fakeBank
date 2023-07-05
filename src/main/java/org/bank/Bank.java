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
        for(var account: accounts){
            if (account.getHolder().equals(accountId)){
                return account;
            }
        }
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
            System.out.println("3 - Operazione su conto");
            System.out.println("4 - Stampa storico operazioni su conto");
            System.out.println("0 - Uscita");
            scelta = sc.nextInt();
            switch (scelta) {
                case 1 -> aggiungiConto();
                case 2 -> stampaContiCorrenti();
                case 3 -> operazioneSuConto();
                case 4 -> stampaStoricoOperazioniConto();
                case 0 -> System.out.println("Uscita in corso...");
                default -> System.out.println("Input non valido");
            }
        } while (scelta != 0);
    }

    private void stampaStoricoOperazioniConto() {
        System.out.println("stampa storico operazioni su conto");
        var sc = new Scanner(System.in);
        System.out.print("inserisci codice conto > ");
        var holder = sc.nextLine();
        var account = getAccount(holder);
        if (account == null) {
            System.err.println("ERRORE: conto inesistente");
            return;
        }
        for(var transaction: account.getTransactions()){
            String opType = "";
            switch (transaction.getType()) {
                case DEPOSIT -> opType = "deposito";
                case WITHDRAW -> opType = "prelievo";
            }
            System.out.printf("%s : %f", opType, transaction.getAmount());
            System.out.println();
        }
    }

    private void operazioneSuConto()  {
        System.out.println("Operazione su conto");
        var sc = new Scanner(System.in);
        System.out.print("inserisci codice conto > ");
        var holder = sc.nextLine();
        var account = getAccount(holder);
        if (account == null) {
            System.err.println("ERRORE: conto inesistente");
            return;
        }
        int op = 0;
        do {
            System.out.print("Operazione da eseguire(1-deposito, 2-prelievo, 0-uscita) > ");
            op = sc.nextInt();
            switch (op) {
                case 1 -> {
                    System.out.print("Importo> ");
                    var amount = sc.nextFloat();
                    account.deposit(amount);
                }
                case 2 -> {
                    try {
                        System.out.print("Importo> ");
                        var amount = sc.nextFloat();
                        account.withdraw(amount);
                    } catch (InsufficientFundsException e) {
                        System.err.println("balance non sufficiente");
                    }
                }
                case 0 -> System.out.println("ritorno al menu principale");
                default -> System.out.println("Errore di scelta operazione");
            }

        } while (op != 0);
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