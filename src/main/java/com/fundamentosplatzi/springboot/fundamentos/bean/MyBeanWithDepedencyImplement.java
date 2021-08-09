package com.fundamentosplatzi.springboot.fundamentos.bean;

public class MyBeanWithDepedencyImplement implements MyBeanWithDependency{

    private MyOperation myOperation;

    public MyBeanWithDepedencyImplement(MyOperation myOperation) {
        this.myOperation = myOperation;
    }

    @Override
    public void printWithDepedency() {
        int numero = 1;
        System.out.println(myOperation.sum(numero));
        System.out.println("Hola desde la implementación de un bean con dependencia");
    }
}
