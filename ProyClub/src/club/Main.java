package club;

import java.util.Scanner;
import club.Socio.Tipo;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
        int op;
        Club c = new Club();
        String nombre, cedula, concepto;
        int tipoNum;
        double valor;
        Tipo tipo;

        do{
            System.out.println("1. Afiliar un socio al club.");
            System.out.println("2. Registrar una persona autorizada por un socio.");
            System.out.println("3. Pagar una factura.");
            System.out.println("4. Registrar un consumo en la cuenta de un socio");
            System.out.println("5. Aumentar fondos de la cuenta de un socio");
            System.out.println("6. Salir");
            System.out.print("Ingrese una opcion: ");
            op = Integer.parseInt(sc.next());
            switch (op){
                case 1:{
                    do{
                    System.out.println("Ingrese el nombre");
                    nombre = sc.next();
                    }while (nombre ==null || nombre =="");
                    do{
                    System.out.println("Ingrese la cedula");
                    cedula = sc.next();
                    }while (cedula ==null || cedula =="");
                    do{
                    System.out.println("Ingrese el tipo, 1 para VIP y 2 para regular");
                    tipoNum = Integer.parseInt(sc.next());
                    }while (tipoNum <1 || tipoNum > 2);
                    if(tipoNum == 1)
                        tipo = Tipo.VIP;
                    else
                        tipo = Tipo.REGULAR;
                    try{
                        c.afiliarSocio( cedula, nombre, tipo );
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }break;
                case 2:{
                    do{
                        System.out.println("Ingrese la cedula");
                        cedula = sc.next();
                    }while (cedula ==null || cedula =="");
                    do{
                        System.out.println("Ingrese el nombre");
                        nombre = sc.next();
                    }while (nombre ==null || nombre =="");
                    try{
                        c.agregarAutorizadoSocio(cedula, nombre);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }break;
                case 3:{
                    do{
                        System.out.println("Ingrese la cedula");
                        cedula = sc.next();
                    }while (cedula ==null || cedula =="");
                    do{
                        System.out.println("Ingrese el indice de la factura");
                        tipoNum = Integer.parseInt(sc.next());
                    }while (tipoNum < 0 );
                    try{
                        c.pagarFacturaSocio( cedula, tipoNum);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }break;
                case 4:{
                    do{

                        System.out.println("Ingrese la cedula");
                        cedula = sc.next();
                    }while (cedula ==null || cedula =="");
                    do{
                        System.out.println("Ingrese el nombre");
                        nombre = sc.next();
                    }while (nombre ==null || nombre =="");
                    do{
                        System.out.println("Ingrese el concepto");
                        concepto = sc.next();
                    }while (concepto ==null || concepto =="");
                    do{
                        System.out.println("Ingrese el valor");
                        valor = Double.parseDouble(sc.next());
                    }while (valor < 0 );
                    try{
                        c.registrarConsumo(cedula, nombre, concepto, valor);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }break;
                case 5:{
                    do{
                        System.out.println("Ingrese la cedula");
                        cedula = sc.next();
                    }while (cedula ==null || cedula =="");
                    do{
                        System.out.println("Ingrese el valor");
                        valor = Double.parseDouble(sc.next());
                    }while (valor < 0 );
                    try{
                        c.aumentarFondosSocio(cedula, valor);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }break;
                case 6:{
                    System.out.println("Gracias!");
                }break;
                default:
                    System.out.println("opcion invalida");
            }
        }while(op!=6);


    }
}