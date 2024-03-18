package org.example.main.URL;

import org.example.main.URL.pojo.*;

import java.security.SecureRandom;
import java.util.ArrayList;

public class ALL {
    SecureRandom secureRandom = new SecureRandom();
    Zero_0 zero = new Zero_0();
    One_1 one1 = new One_1();
    Two_2 two2 = new Two_2();
    Three_3 three3 = new Three_3();
    Four_4 four4 = new Four_4();
    Five_5 five5 = new Five_5();
    Six_6 six6 = new Six_6();
    Seven_7 seven7 = new Seven_7();
    Eight_8 eight8 = new Eight_8();
    Nine_9 nine9 = new Nine_9();
    Ten_10 ten10 = new Ten_10();
    Eleven_11 eleven11 = new Eleven_11();
    Twelve_12 twelve12 = new Twelve_12();
    Thirteen_13 thirteen13 = new Thirteen_13();
    Fourteen_14 fourteen14 = new Fourteen_14();
    Fifteen_15 fifteen15 = new Fifteen_15();

    ArrayList<connface> arrayList = new ArrayList<>();

    public ALL() {
        arrayList.add(zero);
        arrayList.add(one1);
        arrayList.add(two2);
        arrayList.add(three3);
        arrayList.add(four4);
        arrayList.add(five5);
        arrayList.add(six6);
        arrayList.add(seven7);
        arrayList.add(eight8);
        arrayList.add(nine9);
        arrayList.add(ten10);
        arrayList.add(eleven11);
        arrayList.add(twelve12);
        arrayList.add(thirteen13);
        arrayList.add(fourteen14);
        arrayList.add(fifteen15);


        for (connface i : arrayList) {
            test(i);
        }
        System.out.println();
        System.out.println("测试完毕！！！---------------------------------------------------------");
        System.out.println();
        System.out.println();
    }

    public connface suiji() {
        int num = secureRandom.nextInt(arrayList.size());
        connface randomElement = arrayList.get(num);
        System.gc();
        return randomElement;
    }


    public void test(connface yiyan) {
        String[] conn = yiyan.conn();
        if (conn[0].length() > 0 && !conn[0].contains("出错了")) {
            System.out.println("测试成功：" + yiyan.getId()+"\n");
        } else {
            System.out.println("测试失败：" + yiyan.getId()+"\n");
        }

    }

}
