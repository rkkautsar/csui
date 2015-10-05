class BankAccount{
	private double balance;

	public BankAccount(){
		balance = 0;
	}

	public BankAccount(double startingBalance){
		balance = startingBalance;
	}

	void deposit(double amount){
		balance += amount;
	}

	void withdraw(double amount){
		balance -= amount;
	}

	public double getBalance(){
		return balance;
	}
}
