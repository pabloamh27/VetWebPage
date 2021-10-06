package com.hospital.hospitalVeterinario;

import Modelos.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@ComponentScan({"Controlador"})
public class HospitalVeterinarioApplication {

	public static InventarioImpl inventario = new InventarioImpl();
	public static Veterinarios veterinarios = new Veterinarios();
	public static Mascotas mascotas = new Mascotas();
	public static Duenios duenios = new Duenios();

	private static String productostxt = "C:\\Users\\pablo\\Desktop\\Repos Github\\proyecto_lenguajes_java\\Veterinario\\hospitalVeterinario\\src\\main\\java\\com\\hospital\\hospitalVeterinario\\datos\\productos.txt";
	private static String veterinariostxt = "C:\\Users\\pablo\\Desktop\\Repos Github\\proyecto_lenguajes_java\\Veterinario\\hospitalVeterinario\\src\\main\\java\\com\\hospital\\hospitalVeterinario\\datos\\Veterinario.txt";
	private static String mascotastxt = "C:\\Users\\pablo\\Desktop\\Repos Github\\proyecto_lenguajes_java\\Veterinario\\hospitalVeterinario\\src\\main\\java\\com\\hospital\\hospitalVeterinario\\datos\\mascotas.txt";
	private static String dueniostxt = "C:\\Users\\pablo\\Desktop\\Repos Github\\proyecto_lenguajes_java\\Veterinario\\hospitalVeterinario\\src\\main\\java\\com\\hospital\\hospitalVeterinario\\datos\\duenios.txt";



	public static void cargarDatos() throws IOException {
		System.out.println("Cargando datos");

		File archivo = new File(productostxt);
		FileReader fr = new FileReader(archivo);
		BufferedReader br = new BufferedReader(fr);
		cargarProductos();
		cargarVeterinario();
		cargarMascotas();
		cargarDuenios();
	}

	public static void cargarProductos() throws IOException {
		System.out.println("cargando los productos");
		File archivo = new File(productostxt);
		FileReader fr = new FileReader(archivo);
		BufferedReader br = new BufferedReader(fr);

		String linea;
		while((linea=br.readLine()) != null) {

			String id = linea;
			linea = br.readLine();
			String nombre = linea;
			linea = br.readLine();
			String descripcion = linea;
			linea = br.readLine();
			String cantidad = linea;
			br.readLine();

			ProductoImpl producto = new ProductoImpl(Integer.parseInt(id), nombre, descripcion, Integer.parseInt(cantidad));
			inventario.ingresarNuevoProducto(producto);
		}
	}

	public static void cargarVeterinario() throws IOException {
		System.out.println("cargando los veterinarios");
		File archivo = new File(veterinariostxt);
		FileReader fr = new FileReader(archivo);
		BufferedReader br = new BufferedReader(fr);

		String linea;
		while((linea=br.readLine()) != null) {

			String cedula = linea;
			linea = br.readLine();
			String nombre = linea;
			linea = br.readLine();
			String ap1 = linea;
			linea = br.readLine();
			String ap2 = linea;
			linea = br.readLine();
			String direccion = linea;
			linea = br.readLine();
			String telefono = linea;
			linea = br.readLine();
			String especialidad = linea;
			linea = br.readLine();
			String horario = linea;

			VeterinarioImpl veterinario = new VeterinarioImpl(Integer.parseInt(cedula), nombre, ap1, ap2, direccion, telefono, especialidad, horario);
			veterinarios.agregarVeterinario(veterinario);
		}
	}

	public static void cargarMascotas() throws IOException {
		System.out.println("cargando las mascotas");
		File archivo = new File(mascotastxt);
		FileReader fr = new FileReader(archivo);
		BufferedReader br = new BufferedReader(fr);

		String linea;
		while((linea=br.readLine()) != null) {

			String id = linea;
			linea = br.readLine();
			String tipo = linea;
			linea = br.readLine();
			String nombre = linea;
			linea = br.readLine();
			String fecha = linea;


			MascotaImpl mascota = new MascotaImpl(Integer.parseInt(id),tipo,nombre,fecha);
			mascotas.agregarMascota(mascota);
		}
	}

	public static void cargarDuenios() throws IOException {
		System.out.println("cargando los duenios");
		File archivo = new File(dueniostxt);
		FileReader fr = new FileReader(archivo);
		BufferedReader br = new BufferedReader(fr);

		String linea;
		while((linea=br.readLine()) != null) {

			String cedula = linea;
			linea = br.readLine();
			String nombre = linea;
			linea = br.readLine();
			String ap1 = linea;
			linea = br.readLine();
			String ap2 = linea;
			linea = br.readLine();
			String direccion = linea;
			linea = br.readLine();
			String telefono = linea;
			linea = br.readLine();
			String mascotas_del_duenio = linea;

			String[] splitMascotas = mascotas_del_duenio.split(",");

			ArrayList<MascotaImpl> listMascotasImpl = new ArrayList<MascotaImpl>();
			for(int i = 0; i  < splitMascotas.length; i++){
				listMascotasImpl.add(mascotas.consultarMascota(Integer.parseInt(splitMascotas[i])));
			}
			DuenioImpl duenio = new DuenioImpl(Integer.parseInt(cedula),nombre,ap1,ap2,direccion,telefono,listMascotasImpl);
			duenios.agregarDuenio(duenio);
		}
	}


	public static void main(String[] args) throws IOException {
		SpringApplication.run(HospitalVeterinarioApplication.class, args);
		cargarDatos();
	}



}
