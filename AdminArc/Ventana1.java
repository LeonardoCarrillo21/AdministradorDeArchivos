package AdminArc;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

public class Ventana1 extends JInternalFrame {
  private JPanel p1;
  private JButton btnCarp, btnArc, btnElUb, btnAc;
  private JLabel jlNom, jlex, jlUb, jlPunto, jlCar, jlArc;
  private JTextField jtfNombNuevo, jtfExt;
  private JTextArea jtaUbic;
  private JScrollPane jsp1;
  private int ind;
  private String nomAr, ubicacion, tipog;
  private TextPrompt txtTempArc, txtPromptExt, txtPromptNom;


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

  public Ventana1(){
    setTitle("Crear");
    setSize(500, 400);
    setLayout(new BorderLayout());
    initComponents();
    setVisible(true);
    //setDefaultCloseOperation(JInternalFrame.EXIT_ON_CLOSE);

  }

  public void initComponents(){
    p1 = new JPanel();
    p1.setBackground(new Color(10, 255, 2));
    p1.setLayout(null);

    //Botones
    btnCarp = new JButton("Carpeta");
    btnArc = new JButton("Archivo");
    btnArc.setEnabled(false);
    btnElUb = new JButton("Elegir");
    btnAc = new JButton("Aceptar");
    btnCarp.setBackground(new Color(10, 255, 2));
    btnCarp.setIcon(new ImageIcon("iconoCarpeta.PNG"));
    btnArc.setBackground(new Color(10,255,2));
    btnArc.setIcon(new ImageIcon("iconoArchivo.png"));


    //etiquetas
    jlNom = new JLabel("Nombre del archivo");
    jlex = new JLabel("Ext");
    jlUb = new JLabel("Ubicacion");
    jlPunto = new JLabel(".");
    jlCar = new JLabel("Carpeta");
    jlArc = new JLabel("Archivo");

    //JTextField
    jtfNombNuevo = new JTextField();
    jtfExt = new JTextField();

    //jta con JScrollPane
    jtaUbic = new JTextArea();
    jsp1 = new JScrollPane();


    //poscionar elementos
    btnCarp.setBounds(85, 50, 80, 80);
    btnArc.setBounds(335, 50, 80,80);

    btnElUb.setBounds(380, 240, 80,25);
    btnAc.setBounds( 200 , 300, 100, 25);
    jlNom.setBounds(20, 180, 150, 20);
    jlPunto.setBounds(340, 180, 15, 20);
    jlex.setBounds(350, 160, 55, 25);
    jlUb.setBounds(20, 240, 100, 20);
    jtfNombNuevo.setBounds(135,180,200, 25);
    jtfExt.setBounds(350, 180, 40, 25);
    jtaUbic.setBounds(85, 240, 280, 25);
    jsp1.setBounds(85, 240, 280, 40);
    jsp1.setViewportView(jtaUbic);
    jlCar.setBounds(95, 130, 80, 20);
    jlArc.setBounds(345, 130, 80, 20);
    btnCarp.setBorder(null);
    btnArc.setBorder(null);

    txtTempArc = new TextPrompt("Ejemplo: C:/Users/Pedro", jtaUbic);
    txtTempArc.changeStyle(Font.ITALIC);
    txtPromptExt = new TextPrompt("Ej: txt", jtfExt);
    txtPromptExt.changeStyle(Font.ITALIC);
    txtPromptNom = new TextPrompt("Ej: Mi archivo/carpeta", jtfNombNuevo);
    txtPromptNom.changeStyle(Font.ITALIC);


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
    p1.add(jlNom);
    p1.add(jlex);
    p1.add(jlUb);
    p1.add(jtfNombNuevo);
    p1.add(jtfExt);
    p1.add(jsp1);
    p1.add(jlPunto);
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
        case "Aceptar":
          String nom="";
          if(getInd()==1 ){
            nom = jtfNombNuevo.getText()+"."+ jtfExt.getText();
          }else if(getInd()==2){
            nom = jtfNombNuevo.getText();
          }

          System.out.println("aceptar");
          if(getInd()==1 && (jtfNombNuevo.getText().equals("") || jtfExt.getText().equals(""))){
            JOptionPane.showMessageDialog(null,"No le ha dado un nombre o extension","Verifique",JOptionPane.ERROR_MESSAGE);
          }else if(jtaUbic.getText().equals("")){
            JOptionPane.showMessageDialog(null,"No le ha dado una direccion","Verifique",JOptionPane.ERROR_MESSAGE);
          }else{
            Archivos a1 = new Archivos();
            a1.setNombre(nom);
            a1.setTipo(getInd());
            a1.setUbicacion(jtaUbic.getText());
            String aviso = a1.crear();
            JOptionPane.showMessageDialog(null,aviso,"Estado",JOptionPane.INFORMATION_MESSAGE);
            dispose();
          }

          break;
        case "Carpeta":
          jtfExt.setEnabled(false);
          btnArc.setEnabled(true);
          btnCarp.setEnabled(false);
          jlNom.setText("Nombre de carpeta");
          setTipo("la carpeta");
          setInd(2);
          break;
        case "Archivo":
          jtfExt.setEnabled(true);
          btnArc.setEnabled(false);
          btnCarp.setEnabled(true);
          jlNom.setText("Nombre del archivo");
          setTipo("el archivo");
          setInd(1);
          break;
        case "Elegir":
          System.out.println("elegir");
          JFileChooser jf= new JFileChooser();
          jf.setDialogTitle("Seleccione carpeta para guardar "+getTipo());
          jf.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
          jf.setAcceptAllFileFilterUsed(true);
          int seleccion = jf.showOpenDialog(p1);
          System.out.println(seleccion);
          File carpetaSeleccionada = jf.getSelectedFile();
          jtaUbic.setText(carpetaSeleccionada.getPath());
          break;
      }
    }
  }
}
