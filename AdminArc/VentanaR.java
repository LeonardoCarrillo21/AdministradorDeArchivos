package AdminArc;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.JFileChooser;
import java.awt.event.*;
import java.util.*;
import java.io.*;

public class VentanaR extends JInternalFrame {
  private JPanel p1;
  private JButton btnCarp, btnArc, btnElUb, btnAc;
  private JLabel jlViejo,  jlNuevo, jlCar, jlArc;
  private JTextField jtfNombNuevo;
  private JTextArea jtaUbic;
  private JScrollPane jsp1;
  private int ind;
  private String nomAr, ubicacion, tipog;
  private TextPrompt txtTempArc, txtTempNuevo;

  public void setTipo(String tipo){
    this.tipog = tipo;
  }
  public String getTipo(){
    return tipog;
  }

  public void setInd(int a){
    this.ind = a;
  }
  public int getInd(){
    return ind;
  }
  public void setUbAux(String ub){
    this.ubicacion = ub;
  }
  public String getUbicacion(){
    return ubicacion;
  }

  public VentanaR(){
    setTitle("Renombrar");
    setSize(500, 400);
    setLayout(new BorderLayout());
    initComponents();
    setVisible(true);
    //setDefaultCloseOperation(JInternalFrame.EXIT_ON_CLOSE);
  }

  public void initComponents(){
    p1 = new JPanel();
    p1.setBackground(new Color(52, 228, 159));
    p1.setLayout(null);

    //Botones
    btnCarp = new JButton("Carpeta");
    btnArc = new JButton("Archivo");
    btnArc.setEnabled(false);
    btnElUb = new JButton("Elegir");
    btnAc = new JButton("Aceptar");
    btnCarp.setBackground(new Color(52, 228, 159));
    btnCarp.setIcon(new ImageIcon("iconoCarpeta.PNG"));
    btnArc.setBackground(new Color(52, 228, 159));
    btnArc.setIcon(new ImageIcon("iconoArchivo.png"));

    //etiquetas
    jlViejo = new JLabel("<html><center>Nombre y ubicacion<P>del archivo");
    jlNuevo = new JLabel("Nuevo nombre");
    jlCar = new JLabel("Carpeta");
    jlArc = new JLabel("Archivo");

    //JTextField
    jtfNombNuevo = new JTextField();

    //jta con JScrollPane
    jtaUbic = new JTextArea();
    jsp1 = new JScrollPane();
    txtTempArc = new TextPrompt("Ej: C:/Users/Pedro/Carpeta/archivo.txt", jtaUbic);
    txtTempArc.changeStyle(Font.ITALIC);

    txtTempNuevo = new TextPrompt("Ej:archivoRenombrado", jtfNombNuevo);
    txtTempNuevo.changeStyle(Font.ITALIC);


    //poscionar elementos
    btnCarp.setBounds(85, 50, 80, 80);
    btnArc.setBounds(335, 50, 80,80);
    btnElUb.setBounds(400, 180, 80,25);
    btnAc.setBounds(200 , 300, 100, 25);
    jlViejo.setBounds(20, 180, 150, 40);
    jlNuevo.setBounds(20, 240, 100, 20);
    jtfNombNuevo.setBounds(110, 240, 180, 25);
    jtaUbic.setBounds(135,180,245, 35);
    jsp1.setBounds(135,180,245, 35);
    jsp1.setViewportView(jtaUbic);
    jlCar.setBounds(95, 130, 80, 20);
    jlArc.setBounds(345, 130, 80, 20);
    btnCarp.setBorder(null);
    btnArc.setBorder(null);

    EventoBotones in = new EventoBotones();
    btnCarp.addActionListener(in);
    btnArc.addActionListener(in);
    btnElUb.addActionListener(in);
    btnAc.addActionListener(in);
    setTipo("el archivo");
    setInd(1);

    p1.add(btnCarp);
    p1.add(btnArc);
    p1.add(btnAc);
    p1.add(btnElUb);
    p1.add(jlViejo);
    p1.add(jlNuevo);
    p1.add(jtfNombNuevo);
    p1.add(jsp1);
    p1.add(jlCar);
    p1.add(jlArc);
    this.setClosable(true);
    setIconifiable(true);
    setMaximizable(true);
    setResizable(true);
    add(p1);

  }
  public class EventoBotones implements ActionListener{
    public void actionPerformed(ActionEvent ev){
      String boton = ev.getActionCommand();
      switch(boton){
        //cuando se da aceptar hay de dos, que se renormbre una carpeta o un archivo
        case "Aceptar":
        String nom = jtaUbic.getText();
        String nuevo = jtfNombNuevo.getText();
        String aviso ="";
          if((jtaUbic.getText().equals("") || jtfNombNuevo.getText().equals("")) ||(jtaUbic.getText().equals("") && jtfNombNuevo.getText().equals(""))){ //si no se han llenado los campos
            JOptionPane.showMessageDialog(null,"Por favor llene todos los datos","Verifique",JOptionPane.INFORMATION_MESSAGE);

          }else{
            //si estan llenados verifica
            int a=0;
            if(getInd()==1){//se renombra un archivo
              String tipo="";

              //se obtiene la extension del archivo
              for(int i=0; i<nom.length(); i++){
                if(nom.charAt(i)=='.'){ //cuando encuentre el punto es la extension
                  a+=i;
                  }
                }
              System.out.println(a); //a partir de donde encontro el punto copia
              for(int i=a; i<nom.length(); i++){
                tipo+=nom.charAt(i);
              }
              Archivos ren = new Archivos();
              ren.setNombre(nom);
              ren.setTipo(getInd());
              ren.setUbicacion(getUbicacion());
              //se pasa el nombre con la ubicacion y extension
              ren.setNuevoNombre(ubicacion+"/"+nuevo+tipo);
              aviso += ren.renombrar();
            }else if(getInd()==2){ //si es carpeta
              Archivos ren = new Archivos();
              ren.setNombre(nom);
              ren.setNombre(nom);
              ren.setTipo(getInd());
              ren.setUbicacion(getUbicacion());
              ren.setNuevoNombre(ubicacion+"/"+nuevo);
              aviso += ren.renombrar(); //manda a llamar a la funcion
            }
            JOptionPane.showMessageDialog(null,aviso +"("+getTipo()+")","Estado",JOptionPane.INFORMATION_MESSAGE);
            dispose();
          }
        break;
        case "Carpeta":
          btnArc.setEnabled(true);
          btnCarp.setEnabled(false);
          jlViejo.setText("<html><center>Nombre y ubicacion<P>de la carpeta");
          setTipo("la carpeta");
          setInd(2);
          break;
        case "Archivo":
          btnArc.setEnabled(false);
          btnCarp.setEnabled(true);
          jlViejo.setText("<html><center>Nombre y ubicacion<P>del archivo");
          setTipo("el archivo");
          setInd(1);
          break;
        case "Elegir":
          if(getInd()==1){
            //muestra todo los archivos
            JFileChooser jf = new JFileChooser();
            jf.setDialogTitle("Seleccione "+getTipo()+" a renombrar");
            jf.showOpenDialog(p1);
            File archivo = jf.getSelectedFile();
            File aux = jf.getCurrentDirectory();
            if(archivo!=null){
              jtaUbic.setText(archivo.getPath());
              System.out.println(aux.getAbsolutePath());
              setUbAux(aux.getAbsolutePath());
            }
          }else if(getInd()==2){
            JFileChooser jf= new JFileChooser();
            jf.setDialogTitle("Seleccione "+getTipo()+" a renombrar");
            //filtra solo las carpetas
            jf.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            jf.setAcceptAllFileFilterUsed(true);
            int seleccion = jf.showOpenDialog(p1);
            System.out.println(seleccion);
            File carpetaSeleccionada = jf.getSelectedFile();
            File aux = jf.getCurrentDirectory();
            jtaUbic.setText(carpetaSeleccionada.getPath());
            setUbAux(aux.getAbsolutePath());
          }
          break;
      }//switch
    }//ev
  }//EventoBotones
}//class
