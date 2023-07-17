package com.huawei.classroom.student.h61;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Simulate {
	public SimResult sim(Param param, int days) {
		SimResult ans = new SimResult();
		int cityp = param.getCityPopulation();
		List<Integer> wait = new ArrayList<Integer>();
		double spreadfamilyr = param.getSpreadRateFamily();
		double spreadcompanyr = param.getSpreadRateCompany();
		double deathratehome = param.getDeathRateHome();
		double deathratehos = param.getDeathRateHospital();
		double cureratehome = param.getHealingRateHome();
		double cureratehos = param.getHealingRateHospital();
		int familysize = param.getFamilySize();
		int companysize = param.getCompanySize();
		int maxsize = param.getHospitalSize();
		double effect = param.getImmuEffect();
		double immrate = param.getImmuRate();
		int pinhos = 0;
		int latime = param.getLatentPeriod();
		int inhome = latime + 1;
		int inhos = latime + 2;
		int norp = latime + 3;
		int snorp = latime + 4;
		int curedp = latime + 5;
		int[] state = new int[cityp];
		int compnum = (cityp+companysize-1) / companysize;
		List<Integer> compmap = new LinkedList<Integer>();
		for (int i = 0; i < cityp; i++) {
			compmap.add(i % compnum);
		}
		Collections.shuffle(compmap);
		Object[] compmapp = compmap.toArray();
		int[] comp = new int[compnum];
		int[] fami = new int[cityp / familysize];
		for (int i = 0; i < cityp; i++) {
			double r = Math.random();
			if (r < immrate)
				state[i] = snorp;
			else
				state[i] = norp;
		}
		for (int i = 0; i < param.getInitPatients().size(); i++) {
			int num = param.getInitPatients().get(i);
			state[num] = 1;
			comp[(int) compmapp[num]]++;
			fami[num / familysize] ++;
		}
		for (int i = 1; i <= days; i++) {
			for (int j = 0; j < cityp; j++) {
				if (state[j] == norp) {
					if (fami[j / familysize] >= 1) {
						double r = Math.random();
						if (r < spreadfamilyr) {
							state[j] = 1;
							fami[j / familysize]++;
							comp[(int) compmapp[j]]++;
							continue;
						}
					}
					if (comp[(int) compmapp[j]] >= 1) {
						double r = Math.random();
						if (r < spreadcompanyr) {
							state[j] = 1;
							fami[j / familysize]++;
							comp[(int) compmapp[j]]++;
						}
					}
				}
				if (state[j] == snorp) {
					if (fami[j / familysize] >= 1) {
						double r = Math.random();
						if (r < spreadfamilyr * (1 - effect)) {
							state[j] = 1;
							fami[j / familysize]++;
							comp[(int) compmapp[j]]++;
							continue;
						}
					}
					if (comp[(int) compmapp[j]] >= 1) {
						double r = Math.random();
						if (r < spreadcompanyr * (1 - effect)) {
							state[j] = 1;
							fami[j / familysize]++;
							comp[(int) compmapp[j]]++;
						}
					}
				}
				if (state[j] >= 1 && state[j] <= latime) 
					state[j]++;
				if (state[j] == inhome) {
					wait.add(j);
					if (pinhos < maxsize) {
						fami[j / familysize]--;
						state[wait.get(0)] = inhos;
						pinhos++;
						wait.remove(0);
					} else {
						double p = Math.random();
						if (p > 1 - cureratehome) {
							state[j] = curedp;
						} else if (p < deathratehome) {
							state[j] = 0;
						}
					}
				}
				if (state[j] == inhos) {
					double p = Math.random();
					if (p > 1 - cureratehos) {
						state[j] = curedp;
						pinhos--;
					}
					if (p < deathratehos) {
						state[j] = 0;
						pinhos--;
					}
				}
			}
		}
		int death = 0, cured = 0, paitent = 0, la = 0;
		for (int j = 0; j < cityp; j++) {
			if (state[j] == 0)
				death++;
			if (state[j] == curedp)
				cured++;
			if (state[j] == inhome || state[j] == inhos)
				paitent++;
			if (state[j] >= 1 && state[j] <= latime)
				la++;
		}
		ans.setDeaths(death);
		ans.setCured(cured);
		ans.setPatients(paitent);
		ans.setLatents(la);
		return ans;
	}
}
