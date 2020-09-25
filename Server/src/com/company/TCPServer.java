package com.company;
        import java.io.*;
        import java.net.ServerSocket;
        import java.net.Socket;
public class TCPServer {
    public static final int SERVER_PORT = 60001;
    public static void main (String[] args)
    { ObjArray Obj;
        //Прослушивание входящих сообщений на порте 60001
        try (ServerSocket server = new ServerSocket(SERVER_PORT))
        {
            String message =null;
            while(!"STOP".equals(message)){
//принятие входящего клиента
                System.out.println("Ожидание соединения");
                try{//настройка сокета соединения с клиентом
                    Socket connectionSocket = server.accept();
//Настройка считывателя входного потока (от клиента)
                    ObjectInputStream incoming= new
                            ObjectInputStream(connectionSocket.getInputStream());
//Настройка записи иходящего потока (к клиенту)
                    ObjectOutputStream outgoing =new
                            ObjectOutputStream(connectionSocket.getOutputStream());
//блок try
//получение отправленного клиентом сообщения
                    System.out.println("Ожидание сообщения клиента");
                    Obj= (ObjArray) incoming.readObject();
                    message = Obj.getM();
                    System.out.println("Получено от клиента сообщение "+ message);
//Отправить сообщение клиенту
                    String s = new String("STOP");
                    String str=new String("");
                    if (!message.equals(s)) {
                        Variant varray = new Variant(Obj.getArray());
                        Integer max = varray.getResult();
                        str="Сообщение сервером получено" ;
                        //outgoing.writeObject(str);
                        str = str+"max= "+ max;
                        //outgoing.writeUTF(str);
                    } else {
                        str="Соединение с сервером закрыто";
                       // outgoing.writeObject(str);
                    }

                    outgoing.writeUTF(str);
//Закрытьвыходной поток, так как больше нету данных для
// оправки клиенту
                    outgoing.close();
                }catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}