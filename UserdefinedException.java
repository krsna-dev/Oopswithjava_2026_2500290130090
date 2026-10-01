+public class UserdefinedException {

    public static void main(String[] args){
        double balance = 5000;
        double amount = 7500;
    }
    try{
        withdraw(balance, amount);

    }
    catch(InsufficientBalanceException e){
        System.out.println("Exception caught: " + e.getMessage());
    }
    finally{
        System.out.println("Finally block executed");
    }
    static void withdraw(double balance, double amount){
        if(amount>balance){
            throw new InsufficientBalanceException("Insufficient balance");
        }
        else{
            balance -= amount;
            System.out.println("Withdrawal successful");
        }
    }
}

class InsufficientBalanceException extends RuntimeException{
    public InsufficientBalanceException(String message){
        super(message);
    }
}