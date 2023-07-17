/**
 * 
 */
package com.huawei.classroom.student.h53;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

//import cn.com.enorth.utility.Beans;



public class RabbitCount {

    /**
     * 1对兔子出生以后经过180天可以生出一窝（2对）兔子，以后每隔90天繁殖一次生出一窝（2对）兔子
     * 每对兔子的寿命是700天
     *
     * @param startCount 第0天 开始的时候初生的兔子对数
     * @param days       经过的天份数
     * @return 目前系统中存活的兔子的对数
     */
    public int getLivingRabbit(int startCount, int days) {
        int initialBreedingAge = 180;
        int breedingInterval = 90;
        int lifeSpan = 700;

        List<Integer> rabbitPairs = new ArrayList<>();
        List<Integer> newBornRabbitPairs = new ArrayList<>();

        for (int i = 0; i < startCount; i++) {
            rabbitPairs.add(0);
        }

        for (int day = 1; day <= days; day++) {
            newBornRabbitPairs.clear();

            for (int i = 0; i < rabbitPairs.size(); i++) {
                int age = rabbitPairs.get(i);
                age++; 

                if (age >= lifeSpan) {
                    rabbitPairs.remove(i);
                    i--; 
                } else {
                    rabbitPairs.set(i, age);

                    if (age >= initialBreedingAge && (age - initialBreedingAge) % breedingInterval == 0) {
                        newBornRabbitPairs.add(0);
                        newBornRabbitPairs.add(0);
                    }
                }
            }

            rabbitPairs.addAll(newBornRabbitPairs);
        }

        return rabbitPairs.size();
    }


}