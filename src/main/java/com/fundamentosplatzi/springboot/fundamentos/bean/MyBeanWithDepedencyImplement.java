package com.fundamentosplatzi.springboot.fundamentos.bean;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;

public class MyBeanWithDepedencyImplement implements MyBeanWithDependency{

    private final Log LOGGER = LogFactory.getLog(MyBeanWithDepedencyImplement.class);

    private MyOperation myOperation;

    public MyBeanWithDepedencyImplement(MyOperation myOperation) {
        this.myOperation = myOperation;
    }

    @Override
    public void printWithDepedency() {
        LOGGER.info("Hemos ingresado al metodo printWithDepedency");
        int numero = 1;
        LOGGER.debug("El numero enviado como parametro a la dependencia operation es: "+numero);
        System.out.println(myOperation.sum(numero));
        System.out.println("Hola desde la implementación de un bean con dependencia");
    }
}
