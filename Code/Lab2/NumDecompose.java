package com.huawei.classroom.student.h52;

import java.util.HashSet;
import java.util.Set;

public class NumDecompose {
	/**
	 * 将num进行质因数分解，将分解到的质因数放到Set里面返回
	 */
	public Set<Integer> decompose(int num) {
		Set<Integer> set= new HashSet<Integer>();
		boolean prime[] = new boolean [num + 1];
		for(int i = 2; i <= num; i++) {
			if(isPrime(i)) prime[i] = true;
			else prime[i] = false;
		}
		if(prime[num]) {
			set.add(num);
			return set;
		}
		else {
			for(int i = 2; i < num; i++) {
				if(isPrime(i)) {
					if(num % i == 0) {
						set.add(i);
					}
				}
				else {
					continue;
				}
			}
		}
		return set;
		
	}
	
    public boolean isPrime(int n) {
        if (n <= 1) {
            return false;
        }
        for (long i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
