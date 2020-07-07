package test;

import beans.HolaMundoEJBRemote;
import javax.naming.*;
public class TestHolaMundoEJB {
    public static void main(String[] args) {
        System.out.println("Iniciando llamada al EJB desde el cliente\n");
        try {
            Context  jndi = new InitialContext();
            HolaMundoEJBRemote holaMundoEJB = (HolaMundoEJBRemote) jndi.lookup("java:global/HolaMundoEJB/HolaMundoEJBImpl!beans.HolaMundoEJBRemote");
            int resultado = holaMundoEJB.sumar(5, 2);
            System.out.println("Resultado EJB suma 5 + 3 = " + resultado);
        } catch (NamingException e) {
            e.printStackTrace(System.out);
        }
    }
}
