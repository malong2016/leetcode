package com.kwl.data01.designMode.StructMode;

/**
 * 23种设计模式- 第12种    --外观模式
 * @author kuang.weilin
 * @date 2021/7/15 17:05
 */

/**
 * 外观模式:
 * 定义: 为了解决类与类之家的依赖关系的，像spring一样，可以将类和类之间的关系配置到配置文件中，
 * 而外观模式就是将他们的关系放在一个Facade类中，降低了类类之间的耦合度，该模式中没有涉及到接口，
 */
public class Facade {
}

class Computer{         //降低类和类之间的耦合度
    Cpu cpu;
    Disk disk;

    public Computer(Cpu cpu, Disk disk) {
        this.cpu = cpu;
        this.disk = disk;
    }

    void computerStart(){
        System.out.println("computerStart...........");
        cpu.cpuStart();
        disk.diskStart();
    }

    void computerEnd(){
        System.out.println("computerEnd............");
        cpu.cupEnd();
        disk.diskEnd();
    }
}

class Cpu{
    void cpuStart(){
        System.out.println("Cpu开始了");
    }
    void cupEnd(){
        System.out.println("Cpu结束了");
    }
}

class Disk{
    void diskStart(){
        System.out.println("Disk开始了");
    }
    void diskEnd(){
        System.out.println("Disk结束了");
    }
}