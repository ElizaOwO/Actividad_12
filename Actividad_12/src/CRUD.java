import java.util.Map;
import java.util.Scanner;

public class CRUD {

    //Declaración de variables
    static AddressBook adress = new AddressBook();
    static Scanner sc = new Scanner(System.in);
    static String nombre, telefono, respuesta="0";
    static boolean b = false;
    static final String TELEFONO= "Ingresa el teléfono \uD83D\uDCDE",
                        NOMBRE = "Ingresa el nombre ✍️",
                        TEL_EXIST = "⚠️⚠️Número ya existente    ⚠️⚠️",
                        TEL_NO_EXIST = "⚠️⚠️Número no existente    ⚠️⚠️",
                        OP_NO_VAL = "⚠️⚠️Opción no válida    ⚠️⚠️";

    //Método principal
    public static void main(String[] args) {

        //Cargar agenda al HashMap
        adress.load();

        //Menú
        while (!respuesta.equals("5")) {
            output("#################################");
            output("(◕▿◕✿)☞◦✾◦AGENDA TELEFÓNICA◦✾◦");
            output("#################################");
            output("1.- Mostrar agenda \uD83D\uDCD2");
            output("2.- Crear contacto ✔");
            output("3.- Borrar contacto ❌");
            output("4.- Editar contacto \uD83D\uDD01");
            output("5.- Salir \uD83D\uDEAA");

            respuesta = sc.nextLine();

            //Comparar respuesta
            //llamar métodos correspondientes
            switch (respuesta) {
                case "1": list(); break;
                case "2": create(); break;
                case "3": delete(); break;
                case "4": update(); break;
                case "5":adress.save();
                    System.exit(0); break;
                default:
                    output(OP_NO_VAL);
            }
        }
    }

    //Método que itera el HashMap
    //e imprime en pantalla con el formato establecido
    public static void list () {
        for (Map.Entry<String, String> entry : adress.agenda.entrySet()) {
            System.out.println("{" + entry.getKey() + "} : {" + entry.getValue() + "}");
        }
    }

    //Método que crea un nuevo contacto
    //Pedir número, nombre
    //válidar y guardar en HashMap
    public static void create () {
        do {
            b = false;
            output(TELEFONO);
            telefono = sc.nextLine();
            verificarContacto();
            if (!b) {
                output(NOMBRE);
                nombre = sc.nextLine();
                adress.agenda.put(telefono, nombre);
            } else { //T, F
                output(TEL_EXIST);
            }
        }while (b);
    }

    //Método que elimina un contacto
    //Pedir número
    //válidar y elimina del HashMap
    public static void delete () {
        //ask(2);
        do {
            b = false;
            output(TELEFONO);
            telefono = sc.nextLine();
            verificarContacto();
            if (b) {
                adress.agenda.remove(telefono);
            } else {
                output(TEL_NO_EXIST);
            }
        }while (!b);
    }

    //Método que pregunta qué modificar
    //Llama los métodos correspondientes
    public static void update () {
        do {
            output("1.- Modificar teléfono ");
            output("2.- Modificar nombre ");
            respuesta = sc.nextLine();
            if(!respuesta.equals("1") && !respuesta.equals("2")){
                output(OP_NO_VAL);
            }
        }while (!respuesta.equals("1") && !respuesta.equals("2"));

        if (respuesta.equals("1")) {
            updateNumber();
        }else{
            updateName();
        }
    }

    //Método que modificar el número
    //Pide número, número nuevo,
    //válida y actualiza del HashMap
    public static void updateNumber () {
        String telefonoEliminar;
        Boolean bl= true;

        do {
            b = false;
            output(TELEFONO);
            telefono = sc.nextLine();
            telefonoEliminar = telefono;

            //El numero existe
            verificarContacto();

            if (b) {
                do {
                    b = false;
                    output(TELEFONO + " nuevo");
                    telefono = sc.nextLine();

                    //Checar si numero nuevo ya existe
                    verificarContacto();
                    if (!b) {
                        String getName = adress.agenda.get(telefonoEliminar);
                        adress.agenda.remove(telefonoEliminar);
                        adress.agenda.put(telefono, getName);
                        bl = false;
                    } else { // T , F
                        output(TEL_EXIST);
                    }
                }while (b);
            } else {
                output(TEL_NO_EXIST);
            }
        }while (!b && bl);
    }

    //Método que modificar el nombre
    //Pide número, nombre nuevo,
    //válida y actualiza del HashMap
    public static void updateName () {
        do {
            b = false;
            output(TELEFONO);
            telefono = sc.nextLine();

            //checar si el numero es verdadero
            verificarContacto();
            if (b) {
                output(NOMBRE + "nuevo ");
                nombre = sc.nextLine();
                adress.agenda.replace(telefono, nombre);
            } else {
                output(TEL_NO_EXIST);
            }
        }while (!b);
    }

    //Método que verifica el contacto
    //si la key ya existe;
    //Asigna la respuesta a la variable booleana
    public static void verificarContacto(){
        for (Map.Entry<String, String> entry : adress.agenda.entrySet()) {
            if(b =(telefono.equals(entry.getKey()))){
                break;
            }
        }
    }

    //Método que retorna un String
    //que está como parámetro
    public static void output (String st){
        System.out.println(st);
    }
}

