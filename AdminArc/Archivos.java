package AdminArc;
import java.util.*;
import java.io.*;

public class Archivos{
  public String nombre, ubicacion, nuevoNom;
  public int tipo;

  public void setNombre(String nom){
    this.nombre = nom;
  }
  public void setTipo(int tipo){
    this.tipo = tipo;
  }
  public void setUbicacion(String ubicacion){
    this.ubicacion = ubicacion;
  }
  public void setNuevoNombre(String nom){
    this.nuevoNom= nom;
  }
  public String getNombre(){
    return nombre;
  }
  public int getTipo(){
    return tipo;
  }
  public String getUbicacion(){
    return ubicacion;
  }
  public String getNuevoNombre(){
    return nuevoNom;
  }
  //funcion para crear
  public String crear(){
    String nom = getNombre();
    String dir = getUbicacion();
    int ta = getTipo();
    String aviso="";
    //si es para crear un archivo
    if(ta == 1){
      File nuevo = null;

      try{
        nuevo = new File(dir+ '/'+nom);
        //si el archivo existe
        if (nuevo.exists()) {
          System.out.println("OJO: Archivo con el mismo nombre existente");
          aviso = "No creado: Archivo del mismo nombre existente";
        }else{
          System.out.println(dir+ '/' +nom);
          nuevo.createNewFile();
          aviso += "Archivo "+nom+ " creado correctamente";
        }
      }catch(IOException ex){
        System.out.println("\nError: Creacion de Archivo");
        aviso += "Error: Creacion de Archivo";
      }
      //Si es para crear una carpeta
    }else if(ta == 2){
      File d1 = new File(dir+'/'+nom);
      d1.mkdir();
      aviso += "Se creo la carpeta";
    }
    //se retorna el aviso de si se creo o no o si hubo una Exception para imprimirlo en un JOptionPane
    return aviso;
  }

  public String renombrar(){
    String aviso = "";
    //obtiene el nombre original
    File oldfile = new File(getNombre());
    //obtiene el nuevo nombre
    //ambos nombres son obtenidos de la clase ventanaR en el evento botones se especifica como estos parametros son pasados
    File newfile = new File(getNuevoNombre());
    //Si existe el archivo o carpeta
    if(oldfile.exists()){
      try{
        oldfile.renameTo(newfile);
        aviso = "Renombrado con exito";
      }catch(Exception ex){
        System.out.println("Error");
        aviso+="No se pudo renombrar";
      }
      //se imprime en la consola el nombre viejo vs el nuevo
      System.out.println(getNombre());
      System.out.println(getNuevoNombre());
    }//Si no existe
    else{
      aviso+="No existe el directorio especificado";
    }

    return aviso;
  }
 

}
