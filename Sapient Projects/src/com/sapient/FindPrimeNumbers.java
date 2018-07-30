package com.sapient;

public class FindPrimeNumbers {
    public int count = 0;

    private void findPrimeNumber(int n) {
        Boolean prime[] = new Boolean[n + 1];
        for (int i = 0; i <= n; i++) {
            prime[i] = true;
        }

        for (int p = 2; p <= n; p++) {
            if (prime[p]) {
                for (int i = p * 2; i <= n; i += p) {
                    prime[i] = false;
                }
            }
        }

        for (int i = 2; i <= n; i++) {
            if (prime[i])
                System.out.println(i + " ");

        }
        for (int p = 2; p <= n; p++) {
            if (prime[p])
                count++;
        }
        System.out.println("Total Prime Numbers = " + count);


    }

    public static void main(String[] args) {
        FindPrimeNumbers findPrimeNumbers = new FindPrimeNumbers();
        findPrimeNumbers.findPrimeNumber(10000);

    }


}
