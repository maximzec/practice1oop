package com.company;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
    public static final int SERVER_PORT = 6001;
    public static void main (String[] args)
    {
        //Прослушивание входящих сообщений на порте 60001
        try (ServerSocket server = new ServerSocket(SERVER_PORT))
        {
            String message  = null;
            while(!"STOP".equals(message)){
                //принятие входящего клиента
                System.out.println("Ожидание соединения");
                try{//настройка сокета соединения с клиентом
                    Socket connectionSocket = server.accept();
                    //Настройка считывателя входного потока (от клиента)
                    DataInputStream incoming= new    DataInputStream(connectionSocket.getInputStream());
                    //Настройка записи иходящего потока (к клиенту)
                    DataOutputStream outgoing=new
                            DataOutputStream(connectionSocket.getOutputStream());
                    //блок try
                    //получение отправленного клиентом сообщения
                    System.out.println("Ожидание сообщения клиента");
                    message = incoming.readUTF().trim();//trim() -Удаляет все пробелы в начале и конце строки.
                    System.out.println("Получено от клиента сообщение"+ message);
                    //Отправить сообщение клиенту
                    String str=new String("");
                    if (!message.equals("STOP")) {
                        str="Сообщение сервером получено";
                    } else {
                        str="Соединение с сервером закрыто";
                    }
                    outgoing.writeUTF(str);
                    //Закрытьвыходной поток, так как больше нету данных для
                    // оправки клиенту
                    outgoing.close();
                }catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
